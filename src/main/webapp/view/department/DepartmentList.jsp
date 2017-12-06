<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Department and Employee Application</title>
</head>
<body>

	<center>
		<h1>Department Management</h1>
        <h2>
            <a href="department_input">Add New Department</a>
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Department</h2></caption>
            <tr>
                <%--<th>ID</th>--%>
                <th>Name</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="departments" items="${departments}">
                <tr>
                    <td><c:out value="${departments.name}" /></td>
                    <td>
                    	<a href="department_input_update?id=<c:out value='${departments.id}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="department_delete?id=<c:out value='${departments.id}' />">Delete</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="employee_read?departmentId=<c:out value='${departments.id}' />">View Employee</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
