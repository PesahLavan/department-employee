<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Department and Employee Application</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/image/icon.png" />
    <style type="text/css"><%@include file="/css/main.css"%></style>
</head>
<body>
<h1 style="text-align: center">Department Management</h1>
<h2 style="text-align: center">
    <a href="department_read">List All Department</a>
</h2>
<div>
    <c:if test="${requestScope.errors != null}">
        <p class="errors">Error(s)!</p>
        <table class="margin">
            <c:forEach var="error" items="${requestScope.errors}">
                <tr>
                    <td class="color-error">${error}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:choose>
    <c:when test="${not empty departmentForm.id}">
    <form action="department_update" method="post">
        </c:when>
        <c:when test="${empty departmentForm.id}">
        <form action="department_create" method="post">
            </c:when>
            </c:choose>
            <table class="centre">
                <caption>
                    <c:choose>
                        <c:when test="${not empty departmentForm.id}">
                            <h2>Edit Department</h2>
                        </c:when>
                        <c:when test="${empty departmentForm.id}">
                            <h2>Add New Department</h2>
                        </c:when>
                    </c:choose>
                </caption>
                <input type="hidden" name="id" value="${departmentForm.id}" />
                <tr>
                    <th class="border">Name: </th>
                    <td class="border">
                        <label>
                            <input type="text" name="name" size="45" value="${departmentForm.name}"/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td  colspan="2" style="text-align: center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
