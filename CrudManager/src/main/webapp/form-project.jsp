<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="base-head.jsp"%>
		<meta charset="UTF-8">
		<title>CRUD MANAGER - ${action eq "insert" ? "ADICIONAR " : "EDITAR "} PROJETO</title>
	</head>
	<body>
		<%@include file="nav-menu.jsp"%>
		
		<div id="container" class="container-fluid">
			<h3 class="page-header">${action eq "insert" ? "Adicionar " : "Editar "} Projeto</h3>
			
			<form action="${pageContext.request.contextPath}/project/${action}" method="POST">
				<input type="hidden" value="${project.getId()}" name="projectId">
				
				<div class="row">
					<div class="form-group col-md-6">
						<label for="project_name">Nome</label>
							<input type="text" class="form-control" id="name" name="name" 
							  autofocus="autofocus" placeholder="Nome do Projeto" 
							  required oninvalid="this.setCustomValidity('Por favor, informe o nome do projeto.')"
							  oninput="setCustomValidity('')"
							  value="${project.getName()}"
							  >
					</div>
					
					<div class="form-group col-md-6">
						<label for="description">Descrição</label>
							<textarea class="form-control" id="description" name="description" 
							  placeholder="Descrição do Projeto" 
							  required oninvalid="this.setCustomValidity('Por favor, informe a descrição do projeto.')"
							  oninput="setCustomValidity('')">${project.getDescription()}</textarea>
					</div>
				
					<div class="form-group col-md-6">
						<label for="start_date">Data de Início</label>
							<input type="date" class="form-control" id="start_date" name="start_date" 
								  required oninvalid="this.setCustomValidity('Por favor, informe a data de início do projeto.')"
								  oninput="setCustomValidity('')"
								  value="${project.getStart_date()}"
								  >
					</div>
					
					<div class="form-group col-md-6">
						<label for="end_date">Data de Término</label>
							<input type="date" class="form-control" id="end_date" name="end_date" 
								  required oninvalid="this.setCustomValidity('Por favor, informe a data de término do projeto.')"
								  oninput="setCustomValidity('')"
								  value="${project.getEnd_date()}"
								  >
					</div>

					<div class="form-group col-md-6">
						<label for="project_department">Empresa</label>
							<select id="company" class="form-control selectpicker" name="company" 
								    required oninvalid="this.setCustomValidity('Por favor, informe a empresa.')"
								    oninput="setCustomValidity('')">
							  <option value="" disabled ${not empty project ? "" : "selected"}>Selecione uma empresa.</option>
							  <c:forEach var="company" items="${companies}">
							  	<option value="${company.getId()}" 
							  		${project.getCompanyId() eq company.getId() 
							  		? "selected" : ""}>
							  		${company.getName()}
							  	</option>	
							  </c:forEach>
							</select>
					</div>
				</div>
				<hr />
				<div id="actions" class="row pull-right">
					<div class="col-md-12">
						<a href="${pageContext.request.contextPath}/projects" class="btn btn-default">Cancelar</a>
						<button type="submit" class="btn btn-primary">${action eq "insert" ? "Criar " : "Editar "} Projeto</button>
					</div>
				</div>
			</form>
			
		</div>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		</script>
	</body>
</html>
