package com.example.demo.common.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * 生成model中，字段增加注释
 * Created by ZhangShuzheng on 2017/1/11.
 */
public class CommentGenerator extends DefaultCommentGenerator {

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		super.addFieldComment(field, introspectedTable, introspectedColumn);
		if (introspectedColumn.getRemarks() != null && !"".equals(introspectedColumn.getRemarks())) {
			field.addJavaDocLine("/**");
			field.addJavaDocLine(" * 描述: " + introspectedColumn.getRemarks());
            field.addJavaDocLine(" * 字段: " + introspectedColumn.getActualColumnName() + "  "+introspectedColumn.getJdbcTypeName()+" ("+introspectedColumn.getLength()+")");
			// addJavadocTag(field, false);
			field.addJavaDocLine(" */");
            field.addAnnotation("@ApiModelProperty(name = \""+introspectedColumn.getRemarks()+"\")");


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
        sb.append(" * "+ remarks);
        sb.append("\n");
        sb.append(" * 实体类对应的数据表为：  ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        topLevelClass.addJavaDocLine(sb.toString());
        topLevelClass.addJavaDocLine(" * @author " + author);

        //添加时间
        topLevelClass.addJavaDocLine(" * @date " + getDateString());
        topLevelClass.addJavaDocLine(" */");
        topLevelClass.addJavaDocLine("@ApiModel(value =\"" + entityName + "\")");
    }
}
