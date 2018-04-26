package com.example.demo.common.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;

public class ModelWithMethod extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        for (Field field : topLevelClass.getFields()) {
            if ("serialVersionUID".equals(field.getName())){
                // 不生成序列化id的with方法
                continue;
            }
            String s = field.getName();
            FullyQualifiedJavaType fullyQualifiedJavaType = field.getType();
            Method withMethod = new Method();
            withMethod.setVisibility(JavaVisibility.PUBLIC);
            withMethod.setReturnType(topLevelClass.getType());
            withMethod.setName("with"+s.substring(0,1).toUpperCase()+s.substring(1));
            withMethod.addParameter(new Parameter(fullyQualifiedJavaType, s));
            withMethod.addBodyLine("this."+s+" = "+s+";");
            withMethod.addBodyLine("return this;");
            topLevelClass.addMethod(withMethod);
        }
        return true;
    }
}
