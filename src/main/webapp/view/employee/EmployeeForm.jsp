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
<h1 style="text-align: center">Employee Management of the department "${nameDepartment}"</h1>
<h2 style="text-align: center">
    <a href="employee_read?departmentId=${departmentId}">
        Employee list of the department "${nameDepartment}"
    </a>
</h2>
<div >
    <c:if test="${requestScope.errors != null}">
        <p class="errors" >Error(s)!</p>
        <table class="margin">
            <c:forEach var="error" items="${requestScope.errors}">
                <tr>
                    <td class="color-error">${error}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${not empty employeeForm.id}">
    <form action="employee_update" method="post">
        </c:if>
        <c:if test="${empty employeeForm.id}">
        <form action="employee_create" method="post">
            </c:if>
            <table class="centre">
                <caption>
                    <c:if test="${not empty employeeForm.id}">
                        <h2> Edit Employee to ${nameDepartment}</h2>
                    </c:if>
                    <c:if test="${empty employeeForm.id}">
                        <h2> Add New Employee to ${nameDepartment}</h2>
                    </c:if>
                </caption>
                <input type="hidden" name="id" value="${employeeForm.id}" />
                <tr>
                    <th  class="border">Name: </th>
                    <td class="border">
                        <label>
                            <input type="text" name="name" size="45" value="${employeeForm.name}"/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <th class="border">Name Department : </th>
                    <td class="border">
                        <select name="departmentId">
                            <option value="${departmentId}" selected="${departmentId}">${nameDepartment}</option>
                            <c:forEach var="department" items="${departments}">
                                <option value="${department.id}" ${department.id == selected ? 'selected="selected"' : ''}>${department.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th class="border">Number : </th>
                    <td class="border">
                        <input type="text" name="number" size="45" value="${employeeForm.number}"	/>
                    </td>
                </tr>
                <tr>
                    <th class="border">Email : </th>
                    <td class="border">
                        <input type="text" name="email" size="45" value="${employeeForm.email}"	/>
                    </td>
                </tr>
                <tr>
                    <th class="border">Birthday (yyyy-mm-dd) : </th>
                    <td class="border">
                        <input type="text" name="birthday" size="45"  value="${employeeForm.birthDate}" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
