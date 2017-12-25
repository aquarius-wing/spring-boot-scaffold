# spring-boot-scaffold
spring boot/mybatis/rapid-generator
# 准备
## 修改项目中的mysql配置
1.resources/application.yml,数据库相关
2.如果需要生成dao/model/query层,修改test/resources/generator.properties
## 新建对应的数据库和表
- 复制test/resources/sql文件中的sql语句
# 运行
- 可直接运行DemoApplication
- 也可将spring-boot-demo:war exploded部署到intellij的tomcat
# 运行结果
如果数据和sql文件一致的话
运行后网页上会显示
> hello tencent ma , yonghao lo ! 

# 2017-11-30更新
- query中的排序支持直接写字符串,从而支持多字段自定义排序,
比如直接给sortStr赋值`"score desc courseid"`,就可以按照"成绩降序,课程id升序"
- query支持左连接,比如要根据用户角色来查找用户,就可以生成"左连接对象"
    - `LeftJoinDTO leftJoinDTO = new LeftJoinDTO("user_role","uid","user","id","role",1);`
    - 相当于是`left join user_role on user_role.uid = user.id where user_role.role = 1
    - 目前只支持"相等"判断
- service层自动生成
