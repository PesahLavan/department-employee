<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Department and Employee Application</title>
</head>
<body>
	<center>
		<h1>Employee Management of the department "<c:out value="${nameDepartment}" />"</h1>
        <h2>
            <a href="employee_read?departmentId=<c:out value='${departmentId}'/>">
                Employee list of the department "<c:out value="${nameDepartment}" />"
            </a>
        </h2>
	</center>
    <div align="center">
        <c:if test="${requestScope.errors != null}">
            <p id="errors" style="color: red">
                    Error(s)!
                <table align="center">
                <c:forEach var="error" items="${requestScope.errors}">
                    <tr>
                        <td style="color: red">${error}</td>
                    </tr>
                </c:forEach>
                </table>
            </p>
        </c:if>
		<c:if test="${not empty employeeForm.id}">
			<form action="employee_update" method="post">
        </c:if>
        <c:if test="${empty employeeForm.id}">
			<form action="employee_create" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${not empty employeeForm.id}">
                        Edit Employee to <c:out value="${nameDepartment}" />
                    </c:if>
                    <c:if test="${empty employeeForm.id}">
                        Add New Employee to <c:out value="${nameDepartment}" />
                    </c:if>
                </h2>
            </caption>
        			<input type="hidden" name="id" value="<c:out value='${employeeForm.id}' />" />
            <tr>
                <th>Name: </th>
                <td>
                	<input type="text" name="name" size="45" value="<c:out value='${employeeForm.name}'/>" />
                </td>
            </tr>
            <tr>
                <th>Name Department : </th>
                <td>
                    <select name="departmentId">
                        <option value="${departmentId}" selected="${departmentId}">${nameDepartment}</option>
                        <c:forEach var="department" items="${departments}">
                            <option value="${department.id}" ${department.id == selected ? 'selected="selected"' : ''}>${department.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
			<tr>
				<th>Number : </th>
				<td>
					<input type="text" name="number" size="45" value="<c:out value='${employeeForm.number}'/>"	/>
				</td>
			</tr>
			<tr>
				<th>Email : </th>
				<td>
                    <input type="text" name="email" size="45" value="<c:out value='${employeeForm.email}'/>"	/>

				</td>
			</tr>
			<tr>
				<th>Birthday (yyyy-mm-dd) : </th>
				<td>
					<input type="text" name="birthday" size="45"  value="<c:out value='${employeeForm.birthDate}'/>" />
				</td>
			</tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save"/>
            	</td>
            </tr>
        </table>
        </form>
    </div>
</body>
</html>
