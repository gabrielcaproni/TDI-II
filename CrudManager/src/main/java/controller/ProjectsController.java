package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Company;
import model.ModelException;
import model.Project;
import model.dao.CompanyDAO;
import model.dao.DAOFactory;
import model.dao.ProjectDAO;

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
                loadProject(req);
                loadCompanies(req);
                req.setAttribute("action", "update");
                ControllerUtil.forward(req, resp, "/form-project.jsp");
                break;
            }
            default:
                listProjects(req);
                ControllerUtil.transferSessionMessagesToRequest(req);
                ControllerUtil.forward(req, resp, "/projects.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getRequestURI();

        switch (action) {
            case "/crud-manager/project/insert": {
                insertProject(req);
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

                ProjectDAO dao = DAOFactory.createDAO(ProjectDAO.class);

                try {
                    if (dao.delete(new Project(projectId))) {
                        ControllerUtil.sucessMessage(req, "Projeto " + projectName + " excluído com sucesso.");
                    } else {
                        ControllerUtil.errorMessage(req, "Projeto " + projectName + " não pôde ser excluído.");
                    }
                } catch (ModelException e) {
                    ControllerUtil.errorMessage(req, "Erro ao excluir projeto.");
                } finally {
                    ControllerUtil.redirect(resp, req.getContextPath() + "/projects");
                }
                break;
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + action);
        }
    }

    private void updateProject(HttpServletRequest req) {
        String projectIdStr = req.getParameter("projectId");
        int projectId = Integer.parseInt(projectIdStr);
        Project project = createProject(req, projectId);

        ProjectDAO dao = DAOFactory.createDAO(ProjectDAO.class);

        try {
            if (dao.update(project)) {
                ControllerUtil.sucessMessage(req, "Projeto '" + project.getName() + "' alterado com sucesso.");
            } else {
                ControllerUtil.errorMessage(req, "Projeto '" + project.getName() + "' não pôde ser alterado.");
            }
        } catch (ModelException e) {
            e.printStackTrace();
            ControllerUtil.errorMessage(req, "Erro ao alterar dados do projeto.");
        }
    }

    private void insertProject(HttpServletRequest req) {
        Project project = createProject(req, 0);

        ProjectDAO dao = DAOFactory.createDAO(ProjectDAO.class);

        try {
            if (dao.save(project)) {
                ControllerUtil.sucessMessage(req, "Projeto '" + project.getName() + "' salvo com sucesso.");
            } else {
                ControllerUtil.errorMessage(req, "Projeto '" + project.getName() + "' não pôde ser salvo.");
            }
        } catch (ModelException e) {
            ControllerUtil.errorMessage(req, "Erro ao salvar dados do projeto.");
        }
    }

    private Project createProject(HttpServletRequest req, int projectId) {
        String projectName = req.getParameter("name");
        String projectDescription = req.getParameter("description");
        String projectStartDateStr = req.getParameter("start_date");
        String projectEndDateStr = req.getParameter("end_date");
        String projectCompany = req.getParameter("company");
        int projectCompanyId = Integer.parseInt(projectCompany);

        Project project;
        if (projectId == 0) {
            project = new Project();
        } else {
            project = new Project(projectId);
        }

        project.setName(projectName);
        project.setDescription(projectDescription);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formato da data esperado
            Date projectStartDate = dateFormat.parse(projectStartDateStr);
            project.setStart_date(projectStartDate);
        } catch (ParseException e) {
            e.printStackTrace();
            // Tratar o erro ou lançar uma exceção apropriada
        }
        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formato da data esperado
            Date projectEndDate = dateFormat.parse(projectEndDateStr);
            project.setStart_date(projectEndDate);
        } catch (ParseException e) {
            e.printStackTrace();
            // Tratar o erro ou lançar uma exceção apropriada
        }
        
        project.setCompany(new Company(projectCompanyId));

        return project;
    }

    private void loadCompanies(HttpServletRequest req) {
        List<Company> companies = new ArrayList<>();
        CompanyDAO dao = DAOFactory.createDAO(CompanyDAO.class);
        try {
            companies = dao.listAll();
        } catch (ModelException e) {
            ControllerUtil.errorMessage(req, "Erro ao carregar dados das empresas.");
        }

        req.setAttribute("companies", companies);
    }

    private void listProjects(HttpServletRequest req) {
        ProjectDAO dao = DAOFactory.createDAO(ProjectDAO.class);
        List<Project> projects = new ArrayList<>();

        try {
            projects = dao.listAll();
        } catch (ModelException e) {
            ControllerUtil.errorMessage(req, "Erro ao carregar dados dos projetos.");
        }

        req.setAttribute("projects", projects);
    }

    private void loadProject(HttpServletRequest req) {
        String projectIdStr = req.getParameter("projectId");
        int projectId = Integer.parseInt(projectIdStr);

        ProjectDAO dao = DAOFactory.createDAO(ProjectDAO.class);
        Project project = new Project();

        try {
            project = dao.findById(projectId);
        } catch (ModelException e) {
            ControllerUtil.errorMessage(req, "Erro ao carregar dados do projeto.");
        }

        req.setAttribute("projectEdit", project);
    }
}
