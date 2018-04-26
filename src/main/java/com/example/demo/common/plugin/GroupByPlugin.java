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

public class GroupByPlugin extends PluginAdapter {
    Logger logger = LoggerFactory.getLogger(GroupByPlugin.class);

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    /**
     * 在Example类中添加groupBy属性
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        generatorGroupByField(topLevelClass);

        return true;
    }

    /**
     * 在selectByExample里添加必要的sql以结合groupBy
     * @param element
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        for (int i = 0; i < element.getElements().size(); i++) {
            Element subElement = element.getElements().get(i);
            /*
                from conf
                <if test="leftJoin != null">
                  left join ${leftJoin.leftTableName} on ${leftJoin.leftTableName}.${leftJoin.leftColumn} = ${leftJoin.rightTableName}.${leftJoin.rightColumn}
                </if>
                <if test="_parameter != null">
                  <include refid="Example_Where_Clause" />
                </if>
                <if text="groupBy != null>
                    group by #{groupBy}
                </if>
                <if test="orderByClause != null">
                  order by ${orderByClause}
                </if>
             */
            // 找到orderByClause
            if(subElement.getClass().equals(XmlElement.class)){
                XmlElement subXmlElement = (XmlElement)subElement;
                if(subXmlElement.getElements().size() > 0) {
                    // 如果有子元素 
                    Element subSubFirstElement = subXmlElement.getElements().get(0);
                    if (subSubFirstElement.getClass().equals(TextElement.class)) {
                        String content = ((TextElement) subSubFirstElement).getContent();
                        if (content.startsWith("order")) {
                            // 找到了当前元素为<if test="orderByClause != null">
                            XmlElement beforOrder = new XmlElement("if");
                            beforOrder.addAttribute(new Attribute("test", "groupBy != null"));
                            beforOrder.addElement(new TextElement("group by ${groupBy}"));

                            // 在当前位置插入,即在<if test="orderByClause != null">前
                            element.addElement(i, new XmlElement(beforOrder));
                            i ++;
                        }
                    }
                }
                continue;
            }
        }
        return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    /**
     * 1.生成属性
     * 2.set/get/with方法
     * @param topLevelClass
     */
    private void generatorGroupByField(TopLevelClass topLevelClass){
        FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType("java.lang.String");

        String s = "groupBy";
        Field f = new Field();
        f.setName(s);
        f.setVisibility(JavaVisibility.PRIVATE);
        f.setType(fullyQualifiedJavaType);
        StringBuffer stringBuffer = new StringBuffer("/**\n");
        stringBuffer.append("\t * group by #{groupBy}\n");
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
