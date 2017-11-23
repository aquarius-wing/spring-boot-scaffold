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
> hello jack ma , yanhong li !
