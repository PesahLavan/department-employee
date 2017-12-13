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
    <h1 style="text-align: center"> Employees of the department "${nameDepartment}"</h1>
    <h2 style="text-align: center">
        <a href="department_read">List All Department</a>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="employee_input?departmentId=${departmentId}">Add New Employee</a>
    </h2>
<div>
    <table class="centre">
        <caption class="h2-tag">Employee list of the department "${nameDepartment}"</caption>
        <tr>
            <th class="border">Name</th>
            <th class="border">Number</th>
            <th class="border">Email</th>
            <th class="border">Birthday</th>
            <th class="border">Actions</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td class="border">${employee.name}</td>
                <td class="border">${employee.number}</td>
                <td class="border">${employee.email}</td>
                <td class="border">${employee.birthDate}</td>
                <td class="border">
                    <a href="employee_input_update?id=${employee.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="employee_delete?id=${employee.id}">Delete</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
