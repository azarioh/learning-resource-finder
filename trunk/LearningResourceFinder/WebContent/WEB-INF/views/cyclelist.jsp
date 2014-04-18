<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib tagdir="/WEB-INF/tags/lrftag/" prefix="lrf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cycle</title>
</head>
<body>

<lrf:breadcrumb linkactive="Liste des cycles">
		<lrf:breadcrumbelement label="Home" link="home" />
	</lrf:breadcrumb>

<div class="container">
   <h1>Les cycles</h1>

 
   <ul>
    <c:forEach items= "${cyclelist}" var="cycle">
       <li><a href="<c:url value='/cycle?id=${cycle.id}'/>">${cycle.name}</a> </li>
    </c:forEach>
  </ul>
  <br/>
  

  <a href="cyclecreate">Add</a>
</div>
</body>
</html>