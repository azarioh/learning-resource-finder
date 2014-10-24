<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<footer>
	<section id="footer-main">
		<div class="container">
			<div class="row">
				<div class="col-md-3">
				    <br/>
				    <br/>
				    <br/>
				    <br/>
					<a href="/"><img alt="logo ToujoursPlus" src="/images/ToujoursPlus-logo-footer.png"></a>
				    <br/>
				    <br/>
					<p>ToujoursPlus.be permet aux élèves et professeurs de trouver
						facilement les meilleures cours, jeux éducatifs et vidéos sur
						internet. Il facilite la pédagogie différenciée et le rattrapage
						scolaire.</p>
				</div>
				<div class="col-md-3 col-md-offset-1">
					<h4>Contribuer</h4>

					<p><a class="addResourceLink">Ajouter une ressource </a></p>

					<p><a href="<c:url value='/rights'/>" class="">Droits</a></p>
					
				    <c:if test="${current.user != null}">
				        <p><a href="<c:url value='/ressourcelist/${current.user.userName}'/>" >Mes ressources</a></p>
					</c:if>
					   
				    <p><a href="<c:url value='/contribution'/>" >Améliorer des ressources</a></p>
				    <br/>
				    <br/>
				    <br/>
				    <br/>
				    <br/>
				    <br/>
				    <br/>
					<div id="version" style="font-size:xx-small">v ${p_version}</div>

				</div>
				<div class="col-md-3">
					 <h4>Catégories</h4>
				    
					  <c:forEach items="${applicationScope.cache.cycles}" var="cycle" varStatus="cycleStatus">
					      <c:if test="${cycleStatus.count == 4}">
					      	<br/>
					      </c:if>
						  <p><a href="<c:url value='/cycle/${cycle.id}/${cycle.slug}'/>">${cycle.description}</a> </p>
					  </c:forEach>
                      <br/>
                       
                      <lrf:conditionDisplay privilege="MANAGE_COMPETENCE">
                    	  <p><a href="<c:url value='/cyclelist'/>">Gestion des Cycles</a> </p>
                    	  <br/> 
                      </lrf:conditionDisplay>
				</div>
				<div class="col-md-2">
					<h4>Séquences</h4>
					<a href="/playlist/all">Toutes les séquences</a>
					
					<br/><br/><br/><br/><br/><br/><br/><br/>
					
					<div style="text-align:right;">
					    <a href="https://www.facebook.com/be.toujoursplus"><span class="addToolTip glyphicon icon-facebook" style="font-size:21px;" data-toggle="tooltip" title="notre page facebook"></span></a>
					    <br/><br/><br/>
					    
					    <a href="/contact">Contactez-nous</a>
					</div>
				</div>
      		</div>
      	</div>
	</section>
</footer>