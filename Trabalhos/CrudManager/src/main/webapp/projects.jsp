<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="base-head.jsp"%>
		<title>CRUD MANAGER - PROJETOS</title>
	</head>
	<body>
		<%@include file="modal.html"%>
		<%@include file="nav-menu.jsp"%>
		
		<div id="container" class="container-fluid">
		
			<div id="alert" style="${not empty message ? 'display: block;' : 'display: none;'}" class="alert alert-dismissable ${alertType eq 1 ? 'alert-success' : 'alert-danger'}">
			  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			  ${message}
			</div>
			
			<div id="top" class="row">
	 			<div class="col-md-3">
			        <h3>Projetos</h3>
			    </div>
			 
			    <div class="col-md-6">
			        <div class="input-group h2">
			            <input name="data[search]" class="form-control" id="search" type="text" placeholder="Pesquisar projetos">
			            <span class="input-group-btn">
			                <button class="btn btn-danger" type="submit">
			                    <span class="glyphicon glyphicon-search"></span>
			                </button>
			            </span>
			        </div>
			    </div>
			    
			    <div class="col-md-3">
			        <a href="/crud-manager/project/form" class="btn btn-danger pull-right h2"><span class="glyphicon glyphicon-plus" /></span>&nbspAdicionar projeto</a>
			    </div>
			    
			    
     		</div>
     		
     		<hr />
     		
     		<div id="list" class="row">
     			<div class="table-responsive col-md-12">
     				<table class="table table-striped table-hover" cellspacing="0" cellpadding="0">
     					<thead>
			                <tr>
			                    <th>Nome</th>
			                    <th>descrição</th>
			                    <th>Data de início</th>
			                    <th>Data de Conclusão</th>
			                    <th>Empresa</th>
			                    <th>Editar</th>
			                    <th>Excluir</th>
			                 </tr>
		            	</thead>
		            	<tbody>
		            		<c:forEach var="project" items="${projects}">
			            		<tr>
				                    <td>${project.getName()}</td>
				                    <td>${project.getDescription()}</td>
				                    <td>${project.getStart_date()}</td>
				                    <td>${project.getEnd_date()}</td>
				                    <td>${project.getCompany().getName()}</td>				                    
				                    
				                    <td class="actions">
				                        <a class="btn btn-info btn-xs" 
				                           href="${pageContext.request.contextPath}/project/update?projectId=${project.getId()}" >
				                           <span class="glyphicon glyphicon-edit"></span>
				                        </a>
				                    </td>
				                    
				                    <td class="actions">
				                        <a class="btn btn-danger btn-xs modal-remove"
				                           data-project-id="${project.getId()}" 
				                           data-project-name="${project.getName()}" data-toggle="modal" 
				                           data-target="#delete-modal"  href="#"><span 
				                           class="glyphicon glyphicon-trash"></span></a>
				                    </td>
				                </tr>
		            		</c:forEach>
		            	</tbody>
     				</table>
     			</div>
     		</div>
     		
     		<div id="bottom" class="row">
     		<div class="col-md-12">
		        <ul class="pagination">
		            <li class="disabled"><a>&lt; Anterior</a></li>
		            <li class="disabled"><a>1</a></li>
		            <li><a href="#">2</a></li>
		            <li><a href="#">3</a></li>
		            <li class="next"><a href="#" rel="next">Próximo &gt;</a></li>
		        </ul><!-- /.pagination -->
		    </div>
     	</div>
     		
		</div>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
			    // fecha o alert após 3 segundos
			    setTimeout(function() {
			        $("#alert").slideUp(500);
			    }, 3000);
			     
			    $(".modal-remove").click(function () {
		            var projectName = $(this).attr('data-project-name');
		            var projectId = $(this).attr('data-project-id');
		            $(".modal-body #hiddenValue").text("o projeto '" + projectName + "'");
		            $("#id").attr( "value", projectId);
		            $("#entityName").attr("value", projectName);
		            $("#form").attr( "action","project/delete");
		        })
			});
		</script>
	</body>
</html>