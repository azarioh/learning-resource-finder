<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri='/WEB-INF/tags/lrf.tld' prefix='lrf'%>
<%@ taglib tagdir="/WEB-INF/tags/lrftag/" prefix="lrftag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <link rel="stylesheet" href="css/int/content.css"  type="text/css" />
</head>

<body>
    <lrftag:pageheadertitle title="Utilisateurs ayant la gommette ${badgeType.name}"/>
    
	<div style="display: inline-block; vertical-align: top;"><lrftag:badge badgeType="${badgeType}"/></div>
	<div style="display: inline-block; vertical-align: top;">
		<span style="font-size:90%; font-style:italic;">${badgeType.description}</span><br/>
	    ${badges.size()}  
	    <c:choose>
		      <c:when test="${badges.size()==0 || badges.size()==1}">utilisateur a gagné cette gommette.</c:when>
		      <c:otherwise>utilisateurs ont gagné cette gommette.</c:otherwise>
	    </c:choose>
	</div>
	
	<br/><br/>
	    
	<table style='width:100%'>    
	    <% int i = 0;%>   <%-- i to manage the colunms --%>
   		<c:forEach items="${badges}" var="badge">
    	    <c:set var="u" value="<%=i%>"/>
    	    <c:if test="${(u mod 4) == 0}"><tr></c:if>
   		    <td><lrftag:user user="${badge.user}"></lrftag:user></td>
       	    <c:if test="${(u mod 4) == 3}"></tr></c:if>
   		    <%  i++; %>
		</c:forEach>
    </table>
    

      
</body>
</html>