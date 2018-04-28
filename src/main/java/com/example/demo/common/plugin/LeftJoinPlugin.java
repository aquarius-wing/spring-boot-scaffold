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
            System.out.println("-----------modelExampleClassGenerated:"+innerClass.getType().getShortName());
            if(innerClass.getType().getShortName().equals("GeneratedCriteria")){
                for (Method method : innerClass.getMethods()) {
                    if(method.getName().startsWith("addCriterion")){
                        // 形如andUserIdIsNull
                        System.out.println(method.getName()+":\t"+method.getBodyLines());
                        method.setVisibility(JavaVisibility.PUBLIC);
                        method.addBodyLine(0,"if(!condition.contains(\".\")) {");
                        method.addBodyLine(1,"condition = \""+getTableName(introspectedTable)+".\" + condition;");
                        method.addBodyLine(2,"}");
                    }
                }
            }
        }
        for (Method method : topLevelClass.getMethods()) {
            // 如果等于ConfExample
            if(method.getName().equals(topLevelClass.getType().getShortName())){
                // ConfExample()里加入初始化leftJoinDTOList
                method.addBodyLine("leftJoinDTOList = new ArrayList<LeftJoinDTO>();");
            }
            if(method.getName().equals("clear")){
                // clear()里加入leftJoinDTOList.clear()
                method.addBodyLine(0,"leftJoinDTOList.clear();");
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
                    XmlElement afterForEach = new XmlElement("if");
                    afterForEach.addAttribute(new Attribute("test", "leftJoin != null"));
                    afterForEach.addElement(new TextElement("left join ${leftJoin.leftTableName} on ${leftJoin.leftTableName}.${leftJoin.leftColumn} = ${leftJoin.rightTableName}.${leftJoin.rightColumn}"));

                    // <foreach collection="leftJoinDTOList" item="leftJoin">
                    XmlElement afterWhere = new XmlElement("foreach");
                    afterWhere.addAttribute(new Attribute("collection", "leftJoinDTOList"));
                    afterWhere.addAttribute(new Attribute("item", "leftJoin"));
                    afterWhere.addElement(afterForEach);

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
        /*
            <if test="leftJoin != null and leftJoin.targetColumn != null">
              and ${leftJoin.leftTableName}.${leftJoin.targetColumn} = #{leftJoin.targetValue}
            </if>
         */
        XmlElement afterForEach = new XmlElement("if");
        afterForEach.addAttribute(new Attribute("test", "leftJoin != null and leftJoin.targetColumn != null"));
        afterForEach.addElement(new TextElement("and ${leftJoin.leftTableName}.${leftJoin.targetColumn} = #{leftJoin.targetValue}"));

        // <foreach collection="leftJoinDTOList" item="leftJoin">
        XmlElement afterWhere = new XmlElement("foreach");
        if("Update_By_Example_Where_Clause".equals(element.getAttributes().get(0).getValue())){
            // 这里要加example前缀是因为接口方法中使用了@Param定义example
            afterWhere.addAttribute(new Attribute("collection", "example.leftJoinDTOList"));
        }else{
            afterWhere.addAttribute(new Attribute("collection", "leftJoinDTOList"));
        }
        afterWhere.addAttribute(new Attribute("item", "leftJoin"));
        afterWhere.addElement(afterForEach);

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
            contentBf.append(((TextElement) textElement).getContent());
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
        FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType("java.util.List");
        fullyQualifiedJavaType.addTypeArgument(new FullyQualifiedJavaType("LeftJoinDTO"));

        // 创建属性对象
        String s = "leftJoinDTOList";
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
        // 添加属性
        topLevelClass.addField(f);
        // 添加属性的引用
        topLevelClass.addImportedType(fullyQualifiedJavaType);
        topLevelClass.addImportedType("com.example.demo.common.dto.LeftJoinDTO");

        // get方法
        Method getMethod = new Method();
        getMethod.setVisibility(JavaVisibility.PUBLIC);
        getMethod.setReturnType(fullyQualifiedJavaType);
        getMethod.setName("get"+s.substring(0,1).toUpperCase()+s.substring(1));
        getMethod.addBodyLine("return "+s+";");
        topLevelClass.addMethod(getMethod);

        // 创建with方法
        Method withMethod = new Method();
        String objName = "leftJoin";
        // 可见模式为public
        withMethod.setVisibility(JavaVisibility.PUBLIC);
        // 返回类型为本类
        withMethod.setReturnType(topLevelClass.getType());
        // 方法名:withLeftJoin
        withMethod.setName("with"+objName.substring(0,1).toUpperCase()+objName.substring(1));
        // 参数为LeftJoinDTO leftJoin
        withMethod.addParameter(new Parameter(new FullyQualifiedJavaType("LeftJoinDTO"), objName));
        // 函数体
        withMethod.addBodyLine("if(null == this."+s+"){");
        withMethod.addBodyLine("this."+s+" = new ArrayList<>();");
        withMethod.addBodyLine("}");
        withMethod.addBodyLine("this."+s+".add("+objName+");");
        withMethod.addBodyLine("return this;");
        // 添加with方法
        topLevelClass.addMethod(withMethod);
    }


    private String getTableName(IntrospectedTable introspectedTable){
        return introspectedTable.getTableConfiguration().getTableName();
    }
}
