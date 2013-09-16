<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib tagdir="/WEB-INF/tags/lrftag/" prefix="restag"%>
<%@ taglib tagdir="/WEB-INF/tags/lrftag/" prefix="lrf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/js/int/resourceEdit.js"></script>
<title>Catalog</title>
</head>
<body>
	<lrf:breadcrumb linkactive="Liste des ressources">
		<lrf:breadcrumbelement label="Home" link="home" />
	</lrf:breadcrumb>
	<section id="resourcelist">
		<div class="container">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="sixteen columns">
						<a data-toggle="modal" href="#addResourceModal" class="btn btn-primary btn-lg">Cr�er une ressource</a> <br />
						<c:forEach items="${resourceList}" var="resource">
							<div style="float: left; position: relative; margin-top: 10px; width: 210px;">
								<restag:resource resource="${resource}"></restag:resource>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Modal -->
	<div class="modal fade" id="addResourceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<form id="addResourceForm" role="form" method="post" action="resourceaddsubmit">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"aria-hidden="true">&times;</button>
					<h4 class="modal-title">Ajouter une ressource</h4>
				</div>
				<div class="modal-body">
					<div class="form-horizontal">
						<div class="form-group">
	                        <label for="url">site</label> 
	                        <input type="text" class="form-control" id="url" name="url" placeholder="http://...">
	                        <div class="pull-right"> 
	                           <button type="button" class="btn btn-mini btn-primary" id="urlCheckButton" onclick="ajaxVerifyUrl()">V�rifier</button>
							</div>
							<span class="help-block">URL vers le site que vous d�sirez ajouter.</span>
							
						</div>

						<div id="formPart2" style="display:none;">  <%-- will not be displayed until the url is valid --%>
							<div class="form-group">
							    <label for="title">Titre</label> 
							   <input type="text" class="form-control" name="title" id="title" placeholder="titre...">
							   <span class="help-block"> 
							     utilis� dans les listes, le titre doit donner un un coup d'oeil, une id�e claire du contenu de cette ressource.
                               </span>
							</div>
							<div class="form-group">
							    <label for="title">D�scription</label> 
							   <textarea class="form-control" rows="3" id="description" name="description" placeholder="d�scription..."></textarea>
							   <span class="help-block"> 
							      la d�scription doit donner assez d'information au visiteur pour qu'il puisse d�cider, sans qu'il aille voir
                                  la ressource, si elle correspond � ce qu'il recherche.
                               </span>
							</div>
						</div>
					</div>
                  </div>
				  <div class="modal-footer" style="display: none;" id="bottomButtons">
					
					<button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
					<button type="button" class="btn btn-primary" onclick="ajaxResourceAddSubmit()" >Ajouter</button>
				  </div>
			</div>
			<!-- /.modal-content -->
		</form>
	    </div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal --> 
</body>
</html>