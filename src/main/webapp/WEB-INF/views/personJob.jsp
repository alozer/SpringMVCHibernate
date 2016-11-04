
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>    
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Person Job Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Edit Person Jobs
</h1>

<c:url var="addAction" value="/personJob/add/${personJob.personId}" ></c:url>
<form:form action="${addAction}" commandName="personJob">
<table>
	<c:if test="${!empty personJob.title}">
		<form:hidden path="id" />
	</c:if>
	<form:hidden path="personId" />
	<tr>
		<td>
			<form:label path="title">
				<spring:message text="Title"/>
			</form:label>
		</td>
		<td>
			<form:input path="title" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="definition">
				<spring:message text="Definition"/>
			</form:label>
		</td>
		<td>
			<form:input path="definition" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="<c:url value='/persons' />"><input type="button" value="Cancel" name="Cancel" /></a>
			
			<c:if test="${!empty personJob.title}">
				<input type="submit" value="<spring:message text="Edit PersonJob"/>" />
			</c:if>
			<c:if test="${empty personJob.title}">
				<input type="submit" value="<spring:message text="Add PersonJob"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>PersonJobs List</h3>
<c:if test="${!empty listPersonJobs}">
	<table class="tg">
	<tr>
		<th width="120">Person Job ID</th>
		<th width="80">Person ID</th>
		<th width="120">Job Title</th>
		<th width="120">Job Definition</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listPersonJobs}" var="personJob">
		<tr>
			<td>${personJob.id}</td>
			<td>${personJob.personId}</td>
			<td>${personJob.title}</td>
			<td>${personJob.definition}</td>
			<td><a href="<c:url value='/personJob/edit/${personJob.personId}/${personJob.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/personJob/remove/${personJob.personId}/${personJob.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>