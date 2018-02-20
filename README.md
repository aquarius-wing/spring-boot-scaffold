# spring-boot-scaffold
spring boot/mybatis/rapid-generator
# 特色
- 支持左连接的MBG
- 配置全局错误拦截
- druid的sql日志打印
- 配置访问静态资源
- 配置跨域访问
# 准备
## 修改项目中的mysql配置
1. resources/application.yml,数据库相关
2. 如果需要生成model/mapper/service层
    1. 运行com.example.demo.common.util.AESUtil的main方法(把keys改为自己的数据库密码)
    2. 修改resources/generator.properties
    3. 运行com.example.demo.Generator
## 新建对应的数据库和表
- 复制test/resources/sql文件中的sql语句
# 运行
- 可直接运行DemoApplication
- 也可将spring-boot-demo:war exploded部署到intellij的tomcat
# 运行结果
如果数据和sql文件一致的话
运行后网页上会显示
> hello yonghao lo ! 

# 2018-02-20更新
- 原有的代码生成,被mybatis-generator取代
- CommentGenerator支持将数据库中的remark添加到swagger生成的文档中
- LeftJoinPlugin支持在Example中添加一个属性leftJoin来进行左连接操作
- service层改为采用项目名:zheng中的方案
- 其他些许代码参照项目名:zheng

> 项目名:zheng,地址为https://github.com/shuzheng/zheng

# 2017-11-30更新
- query中的排序支持直接写字符串,从而支持多字段自定义排序,
比如直接给sortStr赋值`"score desc courseid"`,就可以按照"成绩降序,课程id升序"
- query支持左连接,比如要根据用户角色来查找用户,就可以生成"左连接对象"
    - `LeftJoinDTO leftJoinDTO = new LeftJoinDTO("user_role","uid","user","id","role",1);`
    - 相当于是`left join user_role on user_role.uid = user.id where user_role.role = 1
    - 目前只支持"相等"判断
- service层自动生成
