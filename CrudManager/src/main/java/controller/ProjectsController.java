package controller;

	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;

	import jakarta.servlet.ServletException;
	import jakarta.servlet.annotation.WebServlet;
	import jakarta.servlet.http.HttpServlet;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;
	import model.Company;
	import model.ModelException;
	import model.Seller;
	import model.dao.CompanyDAO;
	import model.dao.DAOFactory;
	import model.dao.SellerDAO;

	@WebServlet(urlPatterns = {"/projects", "/project/form", "/project/insert", "/project/update", "/project/delete"})
	public class ProjectsController extends HttpServlet {
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String action = req.getRequestURI();
			
			switch (action) {
			case "/crud-manager/project/form": {
				loadCompanies(req);
				req.setAttribute("action", "insert");
				ControllerUtil.forward(req, resp, "/form-project.jsp");
				break;
			}
			case "/crud-manager/project/update": {
				loadCompanies(req);
				req.setAttribute("action", "update");
				ControllerUtil.forward(req, resp, "/form-project.jsp");
				break;
			}
			default:
				listProject(req);
				
				ControllerUtil.transferSessionMessagesToRequest(req);
				
				ControllerUtil.forward(req, resp, "/projects.jsp");
			}
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String action = req.getRequestURI();
			
			switch (action) {
			case "/crud-manager/project/insert": {
				insertSeller(req);
				ControllerUtil.redirect(resp, req.getContextPath() + "/projects");
				break;
			}
			case "/crud-manager/project/update": {
				updateProject(req);
				ControllerUtil.redirect(resp, req.getContextPath() + "/projects");
				break;
			}
			case "/crud-manager/project/delete": {
				String projectIdStr = req.getParameter("id");
				String projectName = req.getParameter("entityName");
				int projectId = Integer.parseInt(projectIdStr);
				
				SellerDAO dao = DAOFactory.createDAO(SellerDAO.class);
				
				try {
					if (dao.delete(new Seller(sellerId))) {
						ControllerUtil.sucessMessage(req, "Vendedor " + sellerName + " excluido com sucesso");
					} else {
						ControllerUtil.errorMessage(req, "Vendedor " + sellerName + " não pode ser excluido");
					}
				} catch (ModelException e) {
					ControllerUtil.errorMessage(req, "Erro ao excluir vendedor");
				} finally {
					ControllerUtil.redirect(resp, req.getContextPath() + "/sellers");
				}
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + action);
			}
		}

		private void updateSeller(HttpServletRequest req) {
			String sellerIdStr = req.getParameter("sellerId");
			int sellerId = Integer.parseInt(sellerIdStr);
			Seller seller = createSeller(req, sellerId);
			
			SellerDAO dao = DAOFactory.createDAO(SellerDAO.class);
			
			try {
				if(dao.update(seller)) {
					ControllerUtil.sucessMessage(req, "Vendedor '" + seller.getName() + "' alterado com sucesso.");
				} else {
					ControllerUtil.errorMessage(req, "Vendedor '" + seller.getName() + "' não pode ser alterado.");
				}
			} catch (ModelException e) {
				e.printStackTrace();
				ControllerUtil.errorMessage(req, "Erro ao alterar dados do vendedor");
			}
		}

		private void insertSeller(HttpServletRequest req) {
			Seller seller = createSeller(req, 0);
			
			SellerDAO dao = DAOFactory.createDAO(SellerDAO.class);
			
			try {
				if(dao.save(seller)) {
					ControllerUtil.sucessMessage(req, "Vendedor '" + seller.getName() + "' salvo com sucesso.");
				} else {
					ControllerUtil.errorMessage(req, "Vendedor '" + seller.getName() + "' não pode ser salvo.");
				}
			} catch (ModelException e) {
				ControllerUtil.errorMessage(req, "Erro ao salvar dados do vendedor");
			}
		}

		private Seller createSeller(HttpServletRequest req, int sellerId) {
			String sellerName = req.getParameter("seller_name");
			String sellerEmail = req.getParameter("seller_email");
			String sellerFone = req.getParameter("seller_fone");
			String sellerCompany = req.getParameter("seller_company");
			int sellerCompanyId = Integer.parseInt(sellerCompany);
			Seller seller;
			
			if(sellerId == 0) {
				seller = new Seller();
			}else {
				seller = new Seller(sellerId);
			}
			
			seller.setName(sellerName);
			seller.setEmail(sellerEmail);
			seller.setFone(sellerFone);
			seller.setCompany(new Company(sellerCompanyId));
			return seller;
		}

		private void loadCompanies(HttpServletRequest req) {
			List<Company> companies = new ArrayList<>();
			CompanyDAO dao = DAOFactory.createDAO(CompanyDAO.class);
			try {
				companies = dao.listAll();
			} catch (ModelException e) {
				ControllerUtil.errorMessage(req, "Erro ao carregar dados das empresas");
			}
			
			req.setAttribute("companies", companies);
		}

		private void listSellers(HttpServletRequest req) {
			SellerDAO dao = DAOFactory.createDAO(SellerDAO.class);
			
			List<Seller> sellers = new ArrayList<>();
			
			try {
				sellers = dao.listAll();
			} catch(ModelException e) {
				ControllerUtil.errorMessage(req, "Erro ao carregar dados dos vendedores");
			}
			
			req.setAttribute("sellers", sellers);
		}
		
		private void loadSeller(HttpServletRequest req) {
			String sellerIdStr = req.getParameter("sellerId");
			int sellerId = Integer.parseInt(sellerIdStr);
			
			SellerDAO dao = DAOFactory.createDAO(SellerDAO.class);
			Seller seller = new Seller();
			
			try {
				seller = dao.findById(sellerId);
			} catch (ModelException e) {
				ControllerUtil.errorMessage(req, "Erro ao carregar dados do vendedor");
			}
			
			req.setAttribute("sellerEdit", seller);
		}
	}

}
