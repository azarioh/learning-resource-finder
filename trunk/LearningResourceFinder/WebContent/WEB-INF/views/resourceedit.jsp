<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>

<title>Insert title here</title>
<style type="text/css" media="screen">
	label
	{
	    display:block;
	    width:100px; 
	    float:left;
	}
	textarea
	{
		width: 400px;
		height: 25Opx;
	}
</style>
</head>
<body>
<div class="container">

	<h1>Resource Create / Edit :</h1> 
	<form:form modelAttribute="resource" method="post" action='<%=response.encodeUrl("resourceeditsubmit")%>'>
		<label for="name">Name :</label>               <form:input path="name" id="name" /> <form:errors path="name" /><br />
		<label for="description">Description :</label> <form:textarea path="description" id="description" /> <form:errors path="description" /><br />
		<form:hidden path="id" />
		<input type="submit" value="Save" />
	</form:form>
	
	<br />
	<a href="ressourcelist">home page</a>
</div>	
</body>
</html>