package com.example.demo.common.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LeftJoinPlugin  extends PluginAdapter {
    Logger logger = LoggerFactory.getLogger(LeftJoinPlugin.class);

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    /**
     * 在Example类中添加leftJoinDTO属性,并且修改内部类GeneratedCriteria中的三个addCriterion方法,使其生成条件额度时候带上表名
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        generatorLeftJoinField(topLevelClass);

        // 使得条件语句前面能够插入表名
        for (InnerClass innerClass : topLevelClass.getInnerClasses()) {
            System.out.println("-----------modelExampleClassGenerated:");
            if(innerClass.getType().getShortName().equals("GeneratedCriteria")){
                for (Method method : innerClass.getMethods()) {
                    if(method.getName().startsWith("addCriterion")){
                        // 形如andUserIdIsNull
                        System.out.println(method.getName()+":\t"+method.getBodyLines());
                        method.addBodyLine(0,"condition = \""+getTableName(introspectedTable)+".\" + condition;");
                    }
                }
            }
        }

        return true;
    }

    /**
     * 在selectByExample里添加必要的sql以结合leftJoinDTO
     * @param element
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        for (int i = 0; i < element.getElements().size(); i++) {
            Element subElement = element.getElements().get(i);


            if(subElement.getClass().equals(XmlElement.class)){
                continue;
            }
            if(subElement.getClass().equals(TextElement.class)){
                String content = ((TextElement)subElement).getContent();
                if(content.startsWith("from")) {
                    XmlElement afterWhere = new XmlElement("if");
                    afterWhere.addAttribute(new Attribute("test", "leftJoin != null"));
                    afterWhere.addElement(new TextElement("left join ${leftJoin.leftTableName} on ${leftJoin.leftTableName}.${leftJoin.leftColumn} = ${leftJoin.rightTableName}.${leftJoin.rightColumn}"));


                    element.addElement(i + 1, new XmlElement(afterWhere));
                }
                continue;
            }
            System.out.println(subElement.getClass().getName());
            System.out.println("-------------特殊值!------------");
        }
        return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    /**
     * where
     *      AND #{leftTableName}.#{targetColumn} = #{targetValue}
     * @param element
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean sqlMapExampleWhereClauseElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        XmlElement afterWhere = new XmlElement("if");
        afterWhere.addAttribute(new Attribute("test", "leftJoin != null"));
        afterWhere.addElement(new TextElement("and ${leftJoin.leftTableName}.${leftJoin.targetColumn} = #{leftJoin.targetValue}"));

        XmlElement elementWhere = (XmlElement) element.getElements().get(0);
        elementWhere.addElement(afterWhere);
        return true;
    }

    /**
     * 修改Base_Column_List中的sql,使其有表名
     * @param element
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean sqlMapBaseColumnListElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        StringBuffer contentBf = new StringBuffer();
        for (Element textElement : element.getElements()) {
            contentBf.append(((TextElement)textElement).getContent());
        }

        // 1.获取原有的内容然后加以修改
        String content = contentBf.toString();
        content = getTableName(introspectedTable) + "." + content;
        content = content.replaceAll(", ",", "+getTableName(introspectedTable)+".");

        // 2.注释原有的sql
        element.addElement(0, new TextElement("/*"));
        element.addElement(new TextElement("*/"));

        // 3.加上修改后的内容
        element.addElement(0, new TextElement(content));

        return true;
    }

    private void generatorLeftJoinField(TopLevelClass topLevelClass){
        FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType("com.example.demo.common.dto.LeftJoinDTO");

        String s = "leftJoin";
        Field f = new Field();
        f.setName(s);
        f.setVisibility(JavaVisibility.PRIVATE);
        f.setType(fullyQualifiedJavaType);
        StringBuffer stringBuffer = new StringBuffer("/**\n");
        stringBuffer.append("\t * left join #{leftTableName} on #{leftTableName}.#{leftColumn} = #{rightTableName}.#{rightColumn}\n");
        stringBuffer.append("\t *\n");
        stringBuffer.append("\t * where\n");
        stringBuffer.append("\t *      AND #{leftTableName}.#{targetColumn} = #{targetValue}\n");
        stringBuffer.append("\t*/");
        f.addJavaDocLine(stringBuffer.toString());
        topLevelClass.addField(f);

        topLevelClass.addImportedType(fullyQualifiedJavaType);

        Method setMethod = new Method();
        setMethod.setVisibility(JavaVisibility.PUBLIC);
        setMethod.setName("set"+s.substring(0,1).toUpperCase()+s.substring(1));
        setMethod.addParameter(new Parameter(fullyQualifiedJavaType, s));
        setMethod.addBodyLine("this."+s+" = "+s+";");
        topLevelClass.addMethod(setMethod);

        Method getMethod = new Method();
        getMethod.setVisibility(JavaVisibility.PUBLIC);
        getMethod.setReturnType(fullyQualifiedJavaType);
        getMethod.setName("get"+s.substring(0,1).toUpperCase()+s.substring(1));
        getMethod.addBodyLine("return "+s+";");
        topLevelClass.addMethod(getMethod);

        Method withMethod = new Method();
        withMethod.setVisibility(JavaVisibility.PUBLIC);
        withMethod.setReturnType(topLevelClass.getType());
        withMethod.setName("with"+s.substring(0,1).toUpperCase()+s.substring(1));
        withMethod.addParameter(new Parameter(fullyQualifiedJavaType, s));
        withMethod.addBodyLine("this."+s+" = "+s+";");
        withMethod.addBodyLine("return this;");
        topLevelClass.addMethod(withMethod);
    }


    private String getTableName(IntrospectedTable introspectedTable){
        return introspectedTable.getTableConfiguration().getTableName();
    }
}
