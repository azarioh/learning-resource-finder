<%@ tag body-content="scriptless" isELIgnored="false" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ attribute name="resource" type="learningresourcefinder.model.Resource" %>
<%@ attribute name="closeUrl" type="java.lang.String" required="false"%>


<div style="display:inline-block; vertical-align:middle; width: 200px; height: 200px; margin-right:20px; margin-bottom:20px;"
     class="panel panel-default">
  <div class="panel-heading">
       <a href="/resource/${resource.shortId}/${resource.slug}" class="lead">${resource.name}</a>
  </div>

  <div class="panel-body" style="position:relative;">
     <p><small>${resource.description}</small></p>

     <c:if test="${resource.numberImage >= 1}">
       <img src="/gen/resource/resized/small/${resource.id}-1.jpg" alt="${resource.name}" />
     </c:if>

	 <c:if test='${! empty closeUrl}'>
		<a href="<c:url value='${closeUrl}'/>">
		   <button type="button" class="addToolTip close" style="position:absolute; top:-20px; right:5px;"
		           data-toggle="tooltip" title="retirer cette ressource de la s�quence">&times;</button>
		</a>
	 </c:if>	     
  </div>
</div>
