<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib tagdir="/WEB-INF/tags/lrftag/" prefix="lrftag"%>
<html>
<head>
<title>Edition d'un cycle</title>
</head>
<body>
   <c:choose>
       <c:when test="${cycle.id == null}">
			<lrftag:breadcrumb linkactive="cr�ation">
		   		<lrftag:breadcrumbelement label= "home" link="/home"/>
		   		<lrftag:breadcrumbelement label="cycles" link="/cyclelist"/>
	   		</lrftag:breadcrumb>
	  </c:when>

	  <c:otherwise>
         <lrftag:breadcrumb linkactive="�dition">
			<lrftag:breadcrumbelement label= "home" link="/home" />
			<lrftag:breadcrumbelement label="cycles" link=" /cyclelist "/>
			<lrftag:breadcrumbelement label="${cycle.name}" link="cycle?id=${cycle.id}" />
	     </lrftag:breadcrumb>
	  </c:otherwise>    
    
    </c:choose>
    
     
<div class="container">
<h1>Edit Cycle</h1>

<form:form modelAttribute="cycle" method="post" action='<%=response.encodeURL("cycleeditsubmit")%>'>
    <label>Nom</label>       <form:input maxlength="15"  path="name"/>
    <input type="hidden" name="id" value="${cycle.id}"/>
    <input type="submit" value="Sauver"/><br>
    <label style="color: red"><form:errors path="name"/></label>
 </form:form>
</div>
</body>
</html>