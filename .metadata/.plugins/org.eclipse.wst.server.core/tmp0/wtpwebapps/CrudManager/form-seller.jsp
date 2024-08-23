<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="base-head.jsp"%>
		<meta charset="UTF-8">
		<title>CRUD MANAGER - ${action eq "insert" ? "ADICIONAR " : "EDITAR "} VENDEDOR</title>
	</head>
	<body>
		<%@include file="nav-menu.jsp"%>
		
		<div id="container" class="container-fluid">
			<h3 class="page-header">${action eq "insert" ? "Adicionar " : "Editar "} Vendedor</h3>
			
			<form action="${pageContext.request.contextPath}/seller/${action}" method="POST">
				<input type="hidden" value="${sellerEdit.getId()}" name="sellerId">
				
				<div class="row">
					<div class="form-group col-md-6">
						<label for="content">Nome</label>
							<input type="text" class="form-control" id="seller_name" name="seller_name" 
							  autofocus="autofocus" placeholder="Nome do Vendedor" 
							  required oninvalid="this.setCustomValidity('Por favor, informe o nome do vendedor.')"
							  oninput="setCustomValidity('')"
							  value="${sellerEdit.getName()}"
							  >
					</div>
					
					
					
					<div class="form-group col-md-6">
						<label for="content">Email</label>
							<input type="email" class="form-control" id="seller_email" name="seller_email" 
							   autofocus="autofocus" placeholder="Email do Vendedor" 
							   required oninvalid="this.setCustomValidity('Por favor, informe o email do vendedor.')"
							   oninput="setCustomValidity('')"
							   value="${sellerEdit.getEmail()}"
							   >
					</div>
					
					
				
					<div class="form-group col-md-6">
						<label for="content">Telefone</label>
							<input type="number" class="form-control" id="seller_fone" name="seller_fone" 
								  autofocus="autofocus" placeholder="Telefone do Vendedor" 
								  required oninvalid="this.setCustomValidity('Por favor, informe o telefone do vendedor.')"
								  oninput="setCustomValidity('')"
								  value="${sellerEdit.getFone()}"
								  >
					</div>
					
					<div class="form-group col-md-6">
							<label for="seller_company">Empresa</label>
							<select id="seller_company" class="form-control selectpicker" name="seller_company" 
								    required oninvalid="this.setCustomValidity('Por favor, informe a empresa.')"
								    oninput="setCustomValidity('')">
							  <option value="" disabled ${not empty sellerEdit ? "" : "selected"}>Selecione uma empresa</option>
							  <c:forEach var="company" items="${companies}">
							  	<option value="${company.getId()}" 
							  		${sellerEdit.getCompany().getId() eq company.getId() 
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
						<a href="${pageContext.request.contextPath}/sellers" class="btn btn-default">Cancelar</a>
						<button type="submit" class="btn btn-primary">${action eq "insert" ? "Criar " : "Editar "} Vendedor</button>
					</div>
				</div>
			</form>
			
		</div>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript">
		 $(document).ready(function()) {
			 setTimeout(function() {
			 	$("#alert").slideUp(500);
			 }, 5000);
			 
			 
		 });
		</script>
	</body>
</html>