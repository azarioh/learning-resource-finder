<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib tagdir="/WEB-INF/tags/lrftag/" prefix="restag"%>

<div id="resource-image-gallery">
	<ul id="list-photos">
	<c:forEach var="i" begin="1" end="${resource.numberImage}" step="1">
		<li id="${resource.id}-${i}">
	      <img src="gen/resource/resized/large/${resource.id}-${i}.jpg" alt="image" />
	    </li>
	</c:forEach>
	</ul>
</div>