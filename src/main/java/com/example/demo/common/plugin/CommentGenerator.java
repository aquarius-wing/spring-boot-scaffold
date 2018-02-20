package com.example.demo.common.plugin;

import com.example.demo.common.util.StringUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * 生成model中，字段增加注释
 * Created by Wing on 2017/1/11.
 */
public class CommentGenerator extends DefaultCommentGenerator {

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		super.addFieldComment(field, introspectedTable, introspectedColumn);
		if (introspectedColumn.getRemarks() != null && !"".equals(introspectedColumn.getRemarks())) {
			field.addJavaDocLine("/**");
			field.addJavaDocLine(" * 描述: " + introspectedColumn.getRemarks().replace("\n",""));
            field.addJavaDocLine(" * 字段: " + introspectedColumn.getActualColumnName() + "  "+introspectedColumn.getJdbcTypeName()+" ("+introspectedColumn.getLength()+")");
			field.addJavaDocLine(" */");
            if(introspectedColumn.getJdbcType() == 93) {
                field.addJavaDocLine("@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")");
            }


		}
	}

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        super.addGetterComment(method, introspectedTable, introspectedColumn);
        if(!StringUtil.isEmpty(introspectedColumn.getRemarks())) {
            method.addAnnotation("@ApiModelProperty(value = \"" + introspectedColumn.getRemarks().replaceAll("\\\"","\\\\\"").replace("\n","") + "\")");
        }
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        super.addModelClassComment(topLevelClass, introspectedTable);
        topLevelClass.addJavaDocLine("import org.springframework.format.annotation.DateTimeFormat;");
        topLevelClass.addJavaDocLine("import com.fasterxml.jackson.annotation.JsonFormat;");
        topLevelClass.addJavaDocLine("import io.swagger.annotations.ApiModel;");
        topLevelClass.addJavaDocLine("import io.swagger.annotations.ApiModelProperty;");
        StringBuilder sb = new StringBuilder();
        //添加类注释
        topLevelClass.addJavaDocLine("/**");
        sb.append(" * ");
        topLevelClass.addJavaDocLine(sb.toString());
        topLevelClass.addJavaDocLine(" * @author wing");

        //添加时间
        topLevelClass.addJavaDocLine(" * @date " + getDateString());
        topLevelClass.addJavaDocLine(" */");
        topLevelClass.addJavaDocLine("@ApiModel(value = \"" + introspectedTable.getRemarks().replace("\n","") + "\")");
    }
}
