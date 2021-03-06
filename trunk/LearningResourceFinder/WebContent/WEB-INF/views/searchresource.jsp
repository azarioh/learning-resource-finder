<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib tagdir="/WEB-INF/tags/lrftag/" prefix="lrftag"%>
<%@ taglib uri='/WEB-INF/tags/lrf.tld' prefix='lrf'%>
<%@ page import="learningresourcefinder.search.SearchOptions"%>

<html>
<head>
<script type="text/javascript" src="/js/int/moreResources.js"></script>

<style>
label {
	font-weight: normal;
}

.form-platform {
	position: absolute;
	top: 0px;
	left: 13em
}

.form-nature {
	position: absolute;
	top: 0px;
	left: 26em
}

.form-lang {
	position: absolute;
	top: 0px;
	left: 42em
}

.form-pub {
	position: absolute;
	top: 0px;
	left: 55em
}

.form-time {
	position: absolute;
	top: 0px;
	left: 68em
}


.filterSubBlock {
     display:inline-block;
     vertical-align:top;
     padding-right:30px;
}

.lead {
	word-wrap: break-word;
}
</style>

<script>
window.onload = addLang;
	function addLang() {
		if ($("#monMenu input:checkbox:checked").length > 0) {
			var i = 0;
			$("input[name=language]")
					.each(
							function() {
								i++;
								if (this.checked) {
									document.getElementById('lang' + i).style.display = 'inline';
								} else {
									document.getElementById('lang' + i).style.display = 'none';
								}
								$('#monMenu').hide();
							});
		} else {
			alert('Vous devez s�lectionner au moins une langue');
		}
	}
</script>
</head>


<body>
	<div class="container" style="margin-bottom:20px;">
	
	<lrftag:pageheadertitle title="Recherche ${searchOptions.searchPhrase}"/>
	
	<form:form modelAttribute="searchOptions" action="searchresourceform" method="POST" class="container" style="padding-left: 20px;">
			  <div class="filterSubBlock">
 				  	 <c:if test="${searchOptions.competence != null}">
								<lrf:competencepath competence="${searchOptions.competence}"/>
								<input id="competenceIdHiddenField" name="competenceId" value="${searchOptions.competence.id }" type="hidden"/>
				            	<button type="button" class="close" style="margin-top:-18px; margin-left:10px;" data-dismiss="alert" onclick="reinitialize()" rel="tooltip" title="Enlever la cat�gorie du filtre">&times;</button> 
				      </c:if>
				</div>
			
			<br>
		<div class="filterSubBlock">
			<fieldset>
				<c:forEach var="formats" items="${formatEnumAllValues}">
					<label class="checkbox" style="margin-bottom:3px;"> <form:checkbox path="format" value="${formats}" />${formats.description}
					</label>
				</c:forEach>
			</fieldset>
		</div>


		<div class="filterSubBlock">
			<fieldset>
				<c:forEach var="platform" items="${platformsEnumAllValues}">
					<label class="checkbox" style="margin-bottom:3px;"> <form:checkbox path="platform"	value="${platform}" />${platform.name}
					</label>
				</c:forEach>
			</fieldset>
		</div>

		<div class="filterSubBlock">
			<fieldset>
				<c:forEach var="nature" items="${natureEnumAllValues}">
					<label class="checkbox" style="margin-bottom:3px;"> <form:checkbox path="nature" value="${nature}" />${nature.description}
					</label>
				</c:forEach>
			</fieldset>
		</div>
		<div class="filterSubBlock">
			<fieldset>
				<form:input type="text" path="searchPhrase" style="width:150px; margin-top:10px" rel="tooltip" title="terme de votre recherche" />
			</fieldset>
			<fieldset> 
				<div class="dropdown" style="margin-right:33px">
				<span id="lang1" class="label label-primary" style="display: inline">Fran�ais</span> 
				<span id="lang2" class="label label-primary" style="display: none">N�erlandais</span>
				<span id="lang3" class="label label-primary" style="display: none">Allemand</span>
				<span id="lang4" class="label label-primary" style="display: none">Anglais</span>
				<span id="lang5" class="label label-primary" style="display: none">Russe</span>
					<a data-toggle="dropdown" href="#"
						onclick="$('#monMenu').show();">Changer</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"
						id="monMenu">
					
						<c:forEach var="language" items="${languagesEnumAllValues}">
							<label class="checkbox" style="margin-left: 1em">
							   <form:checkbox path="language" value="${language}" />${language.description}
							</label>
						</c:forEach>

						<input type="button" id="btnclose" class="btn btn-default"	data-dismiss="modal" style="margin-left: 1em" onclick="addLang()" value="Fermer">
					</ul>
				</div>
			</fieldset>
			<div class="filterSubBlock">
				<fieldset>
					<div class="checkbox">
 				      <label><input type="checkbox" id="optionsCheck" name="noadvertizing" value="true" <c:if test="${searchOptions.wantsNoAd}">checked</c:if> />sans pub</label>
				    </div>
					<div>
						<form:input type="text" path="maxDuration" style=" width:30px" rel="tooltip" title="dur�e maximale" /> minutes max.
					</div>
				</fieldset>
			</div>
			<div class="filterSubBlock" style="margin-left:30px;">
					<button type="submit" class="btn btn-default">Filtrer</button>
			</div>
		
		</div>
		
		<input type="hidden" id="so" name="so" value="${timeStamp}" />
		
	</form:form>
	</div>
	
	<input type="hidden" value="${tokenListOfResources}" id="tokenListOfResources"/>
	
	<section class="container" id="resourcelist">
		<c:forEach items="${resourceList}" var="resource">
				<lrftag:resource resource="${resource}"/>
		</c:forEach>
	</section>
	
	<div id="spinner">
	   	<img style="margin-left: 35%;" src="/images/spinner.gif" />
	</div>
</body>
</html>