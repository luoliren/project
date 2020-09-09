<%--
  Created by IntelliJ IDEA.
  User: 18400
  Date: 2020/3/11
  Time: 16:17
 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>查询所有的账户信息</h3>
   <c:forEach items="${list}" var="account">
    ${account.name}

   </c:forEach>
</body>
</html>
