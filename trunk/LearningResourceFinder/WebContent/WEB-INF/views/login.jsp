﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri='/WEB-INF/tags/lrf.tld' prefix='lrf'%>
<%@ taglib tagdir="/WEB-INF/tags/lrftag/" prefix="lrftag"%>
<%@ page import ="learningresourcefinder.web.UrlUtil" %>

<!-- you can set variables starting with "p_" in the file named website_content.properties -->
<head>

<style type="text/css">
.simple-button.provider-signup.facebook {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    background-color: #185996;
    background-image: linear-gradient(to bottom, #1A60A2, #154E83);
    background-repeat: repeat-x;
    border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
    border-image: none;
    border-style: solid;
    border-width: 1px;
    color: #FFFFFF !important;
    text-shadow: none;
}
.simple-button.provider-signup {
    display: block;
    font-family: "MuseoSans300",sans-serif;
    height: 40px;
}
</style>

<script type="text/javascript">

function mPopupLogin(provid){
	
	var w = 780;
	var h = 410;
	var left = (screen.width/2)-(w/2);
    var   top = (screen.height/2)-(h/2);
    var urlLogin = "<%= UrlUtil.getAbsoluteUrl("/loginsocial?provider=") %>" + provid;
    var signin= window.open(urlLogin, "Login", "nom_popup,menubar=no, status=no, scrollbars=no, menubar=no, width="+w+", height="+h+",left=" + left + ",top=" + top);

	return false; 
	
};


$(document).ready(function(event){
		
		$("#login-facebook").click(function(){
			mPopupLogin("facebook");
		});
		
		$("#login-googleplus").click(function(){
			mPopupLogin("googleplus");
		});
});

</script>


</head>

<body>

<lrftag:pageheadertitle title="Connexion"/> 


Pour participer (voter, argumenter, etc.), vous devez vous connecter avec votre utilisateur.

<div style="padding-top:20px;">    
	<!-- SOCIAL SIGNIN - RIGHT COLUMN -->
	<div style="border-left-style: solid; border-width: 1px; float:right; padding-left:20px; width:300px;">

		<h1>Connexion via un réseau social</h1>
		<p>Vous pouvez facilement utiliser votre compte facebook, google ou autre pour vous connecter à ${p_website_name}</p>
		<!-- FACEBOOK SIGNIN -->
	
		<div>

			<a id="login-facebook" class="simple-button provider-signup facebook">
				<img class="provider-signup-img" width="24" height="24" src="https://khan-academy.appspot.com/images/facebook-24px.png">
				<span class="provider-separator"></span>
				<span class="provider-signup-text"> Login with Facebook </span>
			</a>
		</div>
		<br/>
		
		<!-- GOOGLE SIGNIN -->
		<div>
		
		<a id="login-googleplus" class="simple-button provider-signup google">
		       <img class="provider-signup-img" width="24" height="24" src="https://khan-academy.appspot.com/images/google-24px.png">
		       	<span class="provider-separator"></span>
		        <span class="provider-signup-text"> Sign in with Google </span>
		 </a>
	  </div>
		<br/>
	
	<!-- LOCAL SIGNIN - LEFT COLUMN -->
	<div style="padding-right: 20px;">
			<h1>Connexion avec votre compte ${p_website_name}</h1>
		<p>Si vous n'avez pas de compte facebook, google ou autre <br>(ou ne désirez simplement pas les utiliser ici), <br>vous pouvez vous connecter avec un pseudonyme <br>${p_website_name} (qui nécessite que vous <a href="<c:url value="register"/>">créiez un utilisateur</a> <br>au préalable)</p> 
		<%@ include file="loginfragment.jsp"%>
	</div>
	
</div>

</body>