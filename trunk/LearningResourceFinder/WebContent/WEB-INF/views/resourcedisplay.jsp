<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib tagdir="/WEB-INF/tags/lrftag/" prefix="lrftag" %>
<%@ taglib uri='/WEB-INF/tags/lrf.tld' prefix='lrf'%>
<html>
<head>
<link rel="stylesheet" type="text/css"	href="/css/int/resource-image-gallery.css" />
<script type="text/javascript" src="/js/int/addImageUrlGallery.js"></script>
<script type="text/javascript" src="/js/int/imageGallery-sortable.js"></script>
<script type="text/javascript" src="/js/int/problemReport.js"></script>
<script type="text/javascript" src="/js/int/addResourceFavorite.js"></script>
<script type="text/javascript">
 	$(document).ready(function() {
 		$.fn.editable.defaults.mode = 'inline';
 	    $('.editableField').editable({   
 	    	  emptytext: '? ?',
 	    	  send: 'always',  // http://stackoverflow.com/a/20661423/174831
 	    	  mode: 'popup',
 	    	  type: 'text',
 	    	  url: '/ajax/resourceeditfieldsubmit',
 	    	  pk: '${resource.id}',
 	 		  success: function(response) {
 				location.reload();
 		      }
		});

 	    $('.editableFieldInline').editable({   
	    	  emptytext: '? ?',
	    	  send: 'always',  // http://stackoverflow.com/a/20661423/174831
	    	  mode: 'inline',
	    	  type: 'text',
	    	  url: '/ajax/resourceeditfieldsubmit',
	    	  pk: '${resource.id}',
	 		  success: function(response) {
				location.reload();
		      }
		});

 	   
 	    $('.nonurleditpop').popoverWithAutoHideForPrivilege("Pour modifier une url, il faut �tre connect� et avoir un niveau 4 de contribution.");

 	    $('.nonCompetenceLinkPop').popoverWithAutoHideForPrivilege("Pour lier (et d�lier) une comp�tence et une ressource, il faut �tre connect� et avoir un niveau 4 de contribution." );

 	    $('.nonimageeditpop').popoverWithAutoHideForPrivilege("Pour ajouter/retirer/modifier une image, il faut �tre connect� et avoir un niveau 3 de contribution." );

 	    $(".noAddProblemPop").popoverWithAutoHideForPrivilege("Pour signaler un probl�me, il faut �tre connect�.");
 	    
 	    $(".noneditresource").popoverWithAutoHideForPrivilege("Pour modifier ce champ, il faut �tre connect� et avoir un niveau 3 de contribution." );

	});
 	

 	function onUrlAddClick(){
 		$("#modalUrlResource").modal("show");
 	}
 	function onUrlEditClick(id, url, name) {
 		$("#urlResourceIdField").attr("value", id);
 		$("#urlField").attr("value", url);
 		$("#nameField").attr("value", name);
 		$("#modalUrlResource").modal("show");
 	}

 	function onAddImageClick(){
 		$("#modalImageGalerieResource").modal("show");
 	}
 	

 	
 	function onCompetenceRemoveClick(competenceid, resourceid){
 		$("#competenceHiddenField").attr("value", competenceid);
 		$("#modalConfirmDeleteCompetence").modal("show");	
 	}
 	function onUrlRemoveClick(id) {
 		$("#urlResourceHiddenField").attr("value", id);
 	    $("#modalConfirmDeleteResource").modal("show");	
 	}
 	
 	

 	function onAddCompetenceClick(){
 		$("#modalCompetence").modal("show");
 	}
 	function onCompetenceRemoveClick(competenceid, resourceid){
 		$("#competenceHiddenField").attr("value", competenceid);
 		$("#modalConfirmDeleteCompetence").modal("show");	
 	}

	</script>

<STYLE type="text/css">
.largerTextArea {
	width: 500px;
}
</STYLE>
<title>${resource.name}</title>
</head>
<body>
	<lrftag:breadcrumb linkactive="${resource.name}">
		<lrftag:breadcrumbelement label="Home" link="/home" />
		<c:if test="${user != null}">
			<lrftag:breadcrumbelement label="Ressource" link="/ressourcelist/${user.userName}" />
		</c:if>
	</lrftag:breadcrumb>
	<div class="container">
		<div class="row">
   	       <div class="col-md-10">
		      <h1><a id="title"  ${canEdit==true ? " href='#' class='editableFieldInline'" : " class='noneditresource'"}>${resource.name}</a></h1>
		   </div>
   	       <div class="col-md-2 text-right">
					<lrftag:rating id="${resource.id}" title="${resource.name}"
						scoreResource="${resource.avgRatingScore}"
						scoreUser="${mapRating[resource].score}"
						countRating="${resource.countRating}" canvote="${current.canVote}" />
					
					<lrftag:favorite isFavorite="${isFavorite}" idResource="${resource.id}" />
		   </div>
        </div>
        
        
		<div class="row">
   	       <div class="col-md-6">
 			   <a id="description" ${canEdit==true ? " href='#' class='editableFieldInline'" : " class='noneditresource'"}  data-type="textarea" data-inputclass="largerTextArea">${resource.description}</a>
		   </div>
   	       <div class="col-md-6">
	   		   <c:forEach items="${resource.urlResources}" var="urlResource">
   	              <div class="row">
   	                <c:choose>
   	                 <c:when test="${oneUrlHasAName}">  <%-- One extra column for the URL's name --%>
   	                  <div class="col-md-3 text-right">
							<c:if test="${urlResource.name != null}">${urlResource.name}</c:if>
   	                  </div>
   	                  <div class="col-md-9">
   	                 </c:when>
   	                 <c:otherwise>  <%-- Full width for the URLs --%>
   	                  <div class="col-md-12">
   	                 </c:otherwise>
   	                </c:choose>
							<a href="${urlResource.url}" target="_blank" id="urlresource"data-type="text">${urlResource.url}</a>
							<span style="float:none; font-size:15px"   title="Modifier cette URL" class="glyphicon glyphicon-pencil close addToolTip
							  <c:choose>
							        <c:when test="${canEditUrl}">
							          " onclick="onUrlEditClick(${urlResource.id},'${urlResource.url}','${urlResource.name}')"> 
							        </c:when>
							        <c:otherwise>
							          nonurleditpop">
							        </c:otherwise>
							  </c:choose>
							</span>
							
						    <button type="button" style="float:none;" title="Retirer cette URL" class="close addToolTip
						    <c:choose>
							        <c:when test="${canEditUrl}">
							            " style="float:none;" onclick="onUrlRemoveClick(${urlResource.id})"
							        </c:when>
							        <c:otherwise>
							            nonurleditpop"  
							        </c:otherwise>
							</c:choose>
							>&times;</button>
   	                  </div>
			       </div>  <%-- end row --%>
	           </c:forEach>		                
	           <div class="row">
   	                <c:choose>
   	                 <c:when test="${oneUrlHasAName}">  <%-- One extra column for the URL's name --%>
			              <div class="col-md-3">
			              </div>
			              <div class="col-md-9">
   	                 </c:when>
   	                 <c:otherwise>  <%-- Full width for the URLs --%>
   	                      <div class="col-md-12">
   	                 </c:otherwise>
   	                </c:choose>
			           <span  class="glyphicon glyphicon-plus close addToolTip ${canEditUrl==false ? "nonurleditpop":""}"	 ${canEditUrl==true ? "onclick='onUrlAddClick()'":""}  style="float:none; font-size:15px" 
			              title="ajouter une url (certaines ressources ont plusieurs liens, par exemple l'un pour l'�nonc� et l'autre pour la solution s'ils sont dans des documents diff�rents; ou bien un lien principal vers la ressource et un lien vers une vid�o montrant l'utilisation de la ressource en classe)"></span>
	              </div>
	           </div>
	           
	           <br/><br/>
	           
     		   <c:if test="${listMyPlayListsWithThisResource != null}">
					Mes s�quences contenant cette ressource:<br/>
					<c:forEach items="${listMyPlayListsWithThisResource}" var="playlist">
						<lrftag:playlist playlist="${playlist}"/>
                    </c:forEach>
                    <br/> 
			   </c:if>
			   <c:if test="${listMyPlayListWithoutThisResource != null}">
					<a id="addToPlayList" href='#' class='editableField' data-type='select' data-source="${listMyPlayListWithoutThisResource}">Ajouter � une de mes s�quences</a><br/> 
			   </c:if>
			   <c:if test="${listOtherPeoplePlayListsWithThisResource != null}">
					S�quences d'autres utilisateurs contenant cette ressource:<br/>
					<c:forEach items="${listOtherPeoplePlayListsWithThisResource}" var="playlist">
						<lrftag:playlist playlist="${playlist}"/>
                   	</c:forEach>
			   </c:if>
			   <br />
			   <br />
				
		   </div>
		</div>  <%-- end row --%>


   	    <div class="row">
   	        <div class="col-md-6">
		        <div class="panel panel-default">
		           <div class="panel-body">
				   	    <div class="row">
				   	        <div class="col-md-3">
							    <a id="format"  ${canEdit==true? "href='#' class='editableField' data-type='select' data-emptytext='?format?'":" class='noneditresource'"} data-source="${dataEnumFormat}">${resource.format.description}</a>
				            </div>						
				   	        <div class="col-md-3">
								<a id="nature" ${canEdit==true? "href='#' class='editableField' data-type='select' data-emptytext='?nature?'": " class='noneditresource'"}
								      data-source="${dataEnumNature}">${resource.nature.description}</a>
				            </div>						
				   	        <div class="col-md-3">
								<a id="topic" ${canEdit==true ? " href='#' class='editableField' data-type='select' data-emptytext='?mati�re?'": " class='noneditresource'"} data-source="${dataEnumTopic}">${resource.topic.description}</a>
				            </div>						
				   	        <div class="col-md-3">
								<a id="platform" ${canEdit==true? "href='#' class='editableField' data-type='select'  data-emptytext='?plate-forme?'":" class='noneditresource'"} data-source="${dataEnumPlatform}">${resource.platform.name}</a>
				            </div>						
						</div>  <%-- end row --%>
				   	    <div class="row">
				   	        <div class="col-md-3">
							   <a id="duration" ${canEdit==true? "href='#' class='editableField' data-type='text'":" class='noneditresource'"}> ${resource.duration}</a> minutes
				            </div>						
				   	        <div class="col-md-3">
							    <a id="language" ${canEdit==true ? "href='#' class='editableField' data-type='select' data-emptytext='?langue?'":" class='noneditresource'"} data-source="${dataEnumLanguage}"> ${resource.language.description}</a>
				            </div>						
				   	        <div class="col-md-3">
                                <%-- !!!!!!!!!!!!!!!!!!! PUT min/max Cycle selection here (Resource.minCycle)  John 2014-04 --%>
				            </div>						
				   	        <div class="col-md-3">
								<a id="advertising" ${canEdit==true ? "href='#' class='editableField' data-type='select' data-emptytext='?publicit�?'":" class='noneditresource'"} data-source="[{value:'false',text:'Non'},{value:'true',text:'Oui'}]">
									<c:if test="${resource.advertising == true}">
		    							pub
									</c:if>
									<c:if test="${resource.advertising == false}">
		    							sans pub
									</c:if>
								 </a>
				            </div>						
						</div>  <%-- end row --%>
				   	    <div class="row">
				   	        <div class="col-md-3">
							     <a href="/user/${resource.createdBy.userName}" class="addToolTip" title="contributeur">${resource.createdBy.fullName}</a>
				            </div>						
				   	        <div class="col-md-3 col-md-offset-9">
	     						<a id="author" ${canEdit==true ? "href='#' class='editableField' data-emptytext='?auteur?'":" class='noneditresource'"}> ${resource.author}</a>
				            </div>						
						</div>  <%-- end row --%>
					</div> <%-- end panel body --%>
				</div>  <%-- end panel --%>
            </div>						
		</div>  <%-- end row --%>


        <c:forEach items="${resource.competences}" var="competence">
           <lrf:competencepath competence="${competence}"/>
           <button type="button" style="float:none;"  class="close
	           <c:choose>
			        <c:when test="${canLinkToCompetence}">
			          " onclick="onCompetenceRemoveClick(${competence.id})"> 
			        </c:when>
			        <c:otherwise>
			          nonCompetenceLinkPop">
			        </c:otherwise>
			  </c:choose>
		   &times;</button>
           <br/>                           
        </c:forEach>
 
        <c:if test="${empty resource.competences}">aucune comp�tence li�e</c:if>
        <span class='glyphicon glyphicon-plus close addToolTip  ${canLinkToCompetence==false ? "nonCompetenceLinkPop'" :  "' onclick='onAddCompetenceClick()'"} style="float:none; font-size:15px" title="Ajouter une comp�tence" ></span>  
 
        <c:if test="${not empty youtubeVideoId}">  <%-- This resource's first URL has been detected as being a youtube url => we embed the video in the page (it's better for SEO to not have people systematically leave our site) --%>
          <style type="text/css">  <%-- to have a responsive layout - See more at: http://avexdesigns.com/responsive-youtube-embed/#sthash.fkIODW9M.dpuf   --%>
            .video-container {
			    position: relative;
			    padding-bottom: 56.25%;
			    padding-top: 30px; height: 0; overflow: hidden;
			}
			 
			.video-container iframe,
			.video-container object,
			.video-container embed {
			    position: absolute;
			    top: 0;
			    left: 0;
			    width: 100%;
			    height: 100%;
			}
		  </style>	
          <div class="video-container"> 
              <iframe width="420" height="315" src="//www.youtube.com/embed/${youtubeVideoId}?rel=0" frameborder="0" allowfullscreen></iframe>
          </div> 
        </c:if>	
     	<br /> <br />



		<h4>Images &nbsp; &nbsp; &nbsp;
        <span  class='glyphicon glyphicon-plus close addToolTip ${canEditUrl==false ? "nonimageeditpop":""}'	 ${canEditUrl==true ? "onclick='onAddImageClick()'":""}  style="float:none; font-size:15px" title="ajouter une image (typiquement une capture d'�cran)"></span>
        </h4>
		<%@ include file="resourceimagegallery.jsp"%>
   
   		<br /> <br />
   		
   		
        <h4>Probl�mes &nbsp; &nbsp; &nbsp;
	        <a class='glyphicon glyphicon-exclamation-sign addToolTip ${canAddProblem ? "' href='#modalProblemReport' data-toggle='modal'" : " noAddProblemPop'"} 
			  style="cursor:pointer; text-decoration:initial; line-height:20px; font-size:25px; color:#CCC"
			  title="Signaler un probl�me pour cette ressource..."></a>
		</h4>
	    <lrftag:problemreport title="${resource.name}"	resourceid="${resource.id}" />
		<%@ include file="problemlist.jsp" %>

		<br /> <br />

		


<%-- MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS  --%>
<%-- MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS  --%>
<%-- MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS  --%>
<%-- MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS  --%>
<%-- MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS  --%>
<%-- MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS   MODALS  --%>

		<!-- Modal : ADD URL -->
		<div class="modal fade" id="modalUrlResource" tabindex="-1"role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close closeModal"
							data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Ajouter une URL</h4>
					</div>
					<form method="post" action="/urlresourceeditsubmit"
						enctype="multipart/form-data"
						class="form-horizontal formUrlResource">
						<div class="modal-body">

							<div class="form-group">
								<label class="col-lg-2 control-label">Intitul� (optionnel): </label>
								<div class="col-lg-10">
									<input type="text" class="form-control" id="nameField"	name="name" />
									<span class="help-block">indication de la nature de cette url suppl�mentaire.<br/>
									   ex: "solutions", "vid�o de cette activit� en classe", "version �ditable", etc.</span>
								</div>
							</div>

							<div class="form-group">
								<label class="col-lg-2 control-label" >Url : </label>
								<div class="col-lg-10">
									<input type="text" class="form-control" id="urlField"	name="url" placeholder="http://..." required />
								</div>
							</div>
						</div>

						<div class="modal-footer">
							<input type="hidden" name="resourceid" value="${resource.id}" /> <%-- used for create --%>
							<input id="urlResourceIdField" type="hidden" name="urlresourceid" />	<%-- Value set via JavaScript -- used for edit --%>
							<button type="button" class="btn btn-default closeModal" data-dismiss="modal">Annuler</button>
							<input class="btn btn-primary" type="submit" value="Enregistrer" />
						</div>
					</form>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

		<!-- Modal : ADD IMAGE -->
		<div class="modal fade" id="modalImageGalerieResource" tabindex="-1"role="dialog" aria-labelledby="Ajouter une image" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close closeModal"
							data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Ajouter une image � la galerie</h4>
					</div>
					<form method="post" action="/resource/imageadd"
						enctype="multipart/form-data"
						class="form-horizontal formUrlGallery">
						<div class="modal-body">

							<div class="form-group">
								<label class="col-lg-4 control-label" style="text-align: left;">
									<input type="radio" name="rdFrom" value="computer"
									class="radioComputer" id="inputComputer" checked="checked" />
									Depuis l'ordinateur
								</label>
								<div class="col-lg-8">
									<input type="file" name="file" value="Parcourir..."
										class="inputSource" id="inputFile" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-lg-4 control-label" style="text-align: left;">
									<input type="radio" name="rdFrom" value="url" class="radioUrl" />
									Depuis un lien
								</label>
								<div class="col-lg-8">
									<input type="hidden" name="strUrl" value="http://..."
										class="inputSource form-control" id="inputUrl" />
								</div>
							</div>

							<p id="response">${response}</p>

						</div>

						<div class="modal-footer">
							<input type="hidden" name="idResource" id="idResource"
								value="${resource.id}" />
							<button type="button" class="btn btn-default closeModal"
								data-dismiss="modal">Fermer</button>
							<button type="submit" name="btnPicture"
								class="btn btn-primary closeModal btnSubmit">Sauver
								l'image</button>
						</div>
					</form>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</div>
	
	<!-- Modal : URL REMOVE CONFIRM  -->
   <div class="modal fade" id="modalConfirmDeleteResource">
	   <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">Confirmation </h4>
	      </div>
	      <div class="modal-body">
	        <p>Voulez-vous supprimer cette URL?</p>
	      </div>
	      <div class="modal-footer">
	       <form action="/removeurlresource">
	        <input id="urlResourceHiddenField" type="hidden" name="id" value="" />  <%-- Filled by JavaScript --%>
	        <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
	        <button type="submit" class="btn btn-primary">Supprimer</button>
	       </form>
	      </div>
	    </div><!-- /.modal-content -->
	   </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
    <!-- Modal : ADD COMPETENCE -->
		<div class="modal fade" id="modalCompetence" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close closeModal"
							data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Placer la ressource dans une comp�tence</h4>
					</div>
					<form method="post" action="/competenceaddtoresourcesubmit"  role="form">
						<div class="modal-body">
						    <div class="form-group">
								<row>
								  <div class="col-lg-4">
								    <label for="codeField">Code:</label>
								
									<input type="text" class="form-control" id="codeField"	name="code" />
								  </div>
								</row>
								<br/><br/><br/>
								<div class="help-block">Code de la comp�tence dans laquelle vous d�sirez placer la ressource.<br/>
								   Astuce: affichez la liste des comp�tences dans un autre onglet de votre navigateur.</div>
							</div>
						</div>

						<div class="modal-footer">
							<input type="hidden" name="resourceid" value="${resource.id}" />
							<button type="button" class="btn btn-default closeModal" data-dismiss="modal">Annuler</button>
							<input class="btn btn-primary" type="submit" value="Enregistrer" />
						</div>
					</form>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
		
		
		<!-- Modal : COMPETENCE REMOVE CONFIRM  -->
   <div class="modal fade" id="modalConfirmDeleteCompetence">
	   <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">Confirmation</h4>
	      </div>
	      <div class="modal-body">
	        <p>Voulez-vous retirer cette comp�tence de la ressource ?</p>
	      </div>
	      <div class="modal-footer">
	       <form action="/removecompetencefromresource">
	        <input id="competenceHiddenField" type="hidden" name="competenceid" />  <%-- Filled by JavaScript --%>
	        <input id="resourceHiddenField" type="hidden" name="resourceid" value="${resource.id}" />
	        <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
	        <button type="submit" class="btn btn-primary">Retirer</button>
	       </form>
	      </div>
	   </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
	
</body>
</html>