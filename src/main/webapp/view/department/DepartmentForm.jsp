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
			<a href="department_read">List All Department</a>
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
        <c:choose>
            <c:when test="${not empty departmentForm.id}">
                <form action="department_update" method="post">
            </c:when>
            <c:when test="${empty departmentForm.id}">
                <form action="department_create" method="post">
            </c:when>
        </c:choose>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
                    <c:choose>
                        <c:when test="${not empty departmentForm.id}">
                            Edit Department
                        </c:when>
                        <c:when test="${empty departmentForm.id}">
                            Add New Department
                        </c:when>
                    </c:choose>
            	</h2>
            </caption>
                <input type="hidden" name="id" value="<c:out value='${departmentForm.id}' />" />
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45" value="<c:out value='${departmentForm.name}' />"/>
				</td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
