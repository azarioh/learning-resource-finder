  <%@ taglib uri='/WEB-INF/tags/lrf.tld' prefix='lrf'%>
<html>
<head>
  <script  src="js/int/competenceTree.js"></script>
  
  <style>
    .clickable {
        color: black;
        opacity:0.5;
    }
  
    .clickable:hover {
        cursor : pointer;
        opacity:1;
    }
  
  </style> 
</head>

<body>
<div class="container">
 <lrf:competencetree root="${root}"/> 
 
  <!-- Button trigger modal -->

 
  <!-- Modal move -->
  <div class="modal fade" id="competenceMoveModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
     <form action="competencemovesubmit" method="post">
       <div class="modal-content">
      
         <div class="modal-header">
    		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
         	<h4 class="modal-title">D�placer une cat�gorie</h4>
         </div>
         
        <div class="modal-body">
	        <div class="form-group">
	          <label for="codeField">Code parent</label>
			  <input type="text" class="form-control" name="codeField" id="codeField" placeholder="Code"  maxlength="8" required>
			  <span class="help-block">Code de la cat�gorie qui sera parent apr�s le d�placement.</span>
			</div>
			<input type="hidden" class="form-control" name="hiddenFieldMoveCompetency" id="hiddenFieldMoveCompetency" value=""> <%-- Value us set by javascript --%>
		</div>
		
		<div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
          <button  type="submit" class="btn btn-primary" value="Deplace" >D�placer la cat�gorie</button>
        </div>
       </div><!-- /.modal-content -->
    </form>
  </div><!-- /.modal-dialog -->
 </div><!-- /.modal -->
 
 <!-- Modal add -->
  <div class="modal fade" id="competenceEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
       <form id="competenceForm">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title" id="competenceModalTitle">Ajouter une cat�gorie</h4>
        </div>
        <div class="modal-body">
        
            <input type="hidden" id="hiddenField" />  <!-- name and value will be filled via javascript -->
			<div class="form-group">
			<div class="form-group">
			  <label for="code" class="control-label">Code</label>
			  <input type="text" class="form-control" id="codeedit" name="code" placeholder="Code"  maxlength="8" style="width:100px;" required>
			   <span class="help-inline help-block" id="codeedithelp"><%-- room for an error message added by JavaScript --%></span> 
			</div>
			  <label for="name">Intitul�</label>
			  <input type="text" class="form-control" id="nameedit" name="name" placeholder="Intitul�" maxlength="255" required> 
			</div>
			<div class="form-group">
			  <label for="description">Description</label>
			  <textarea class="form-control" rows="3" id="descriptionedit" name="description" placeholder="Description"></textarea>
			</div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
          <button type="submit" class="btn btn-primary" id="addsubmit">Enregistrer</button>
        </div>
       </form>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->
    </div>
  
  
  <!-- Modal delete -->
  <div class="modal fade" id="competenceRemoveModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">�tes vous s�r?</h4>
        </div>
		<div class="alert alert-danger">Attention : Cela supprimera d�finitivement la Cat�gorie !</div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
          <button type="button" class="btn btn-primary" id="removesubmit">Supprimer Cat�gorie</button>
        </div>
        
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->
 </body> 
 </html> 