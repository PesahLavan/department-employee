<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Department and Employee Application</title>
</head>
<body>
<center>
    <h1> Employees of the department "<c:out value="${nameDepartment}" />"</h1>
    <h2>
        <a href="department_read">List All Department</a>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="employee_input?departmentId=<c:out value="${departmentId}"/>">Add New Employee</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5" datapagesize="45">
        <caption><h2>Employee list of the department "<c:out value="${nameDepartment}" />"</h2></caption>
        <tr>
            <th>Name</th>
            <th>Number</th>
            <th>Email</th>
            <th>Birthday</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td><c:out value="${employee.name}" /></td>
                <td><c:out value="${employee.number}" /></td>
                <td><c:out value="${employee.email}" /></td>
                <td><c:out value="${employee.birthDate}" /></td>
                <td>
                    <a href="employee_input_update?id=<c:out value='${employee.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="employee_delete?id=<c:out value='${employee.id}'/>">Delete</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
