<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/23 0023
  Time: 上午 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>spring boot demo</title>
</head>
<body>
    hello <c:forEach items="${userList}" var="user" varStatus="statu">
        ${user.uname}
        <c:if test="${not statu.last}">, </c:if>
        <c:if test="${statu.last}">!</c:if>
    </c:forEach>
</body>
</html>
