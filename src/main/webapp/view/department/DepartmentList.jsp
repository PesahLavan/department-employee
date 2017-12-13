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
    <a href="department_input">Add New Department</a>
</h2>
<div>
    <table class="centre">
        <caption class="h2-tag">List of Department</caption>
        <tr>
            <th class="border">Name</th>
            <th class="border">Actions</th>
        </tr>
        <c:forEach var="departments" items="${departments}">
            <tr>
                <td class="border" >${departments.name}</td>
                <td class="border">
                    <a href="department_input_update?id=${departments.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="department_delete?id=${departments.id}">Delete</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="employee_read?departmentId=${departments.id}">View Employee</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
