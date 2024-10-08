package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.ModelException;
import model.Project;

public class MySQLProjectDAO implements ProjectDAO{

	@Override
	public boolean save(Project project) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlInsert = "INSERT INTO projects VALUES (default, ?,?,?,?,?);";
		
		db.prepareStatement(sqlInsert);
		
		db.setString(1, project.getName());
		db.setString(2, project.getDescription());
		db.setString(3, project.getStart_date());
		db.setString(4, project.getEnd_date());
		db.setInt(5, project.getCompany().getId());
		  
		return db.executeUpdate() > 0;	
	}

	@Override
	public boolean update(Project project) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlUpdate = "UPDATE projects SET name = ?, description = ?, start_date = ?, end_date = ?, companies_id = ? WHERE id = ?;";
		
		db.prepareStatement(sqlUpdate);
		
		
		db.setString(1, project.getName());
		db.setString(2, project.getDescription());
		db.setString(3, project.getStart_date());
		db.setString(4, project.getEnd_date());
		db.setInt(5, project.getCompany().getId());
		db.setInt(6, project.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Project project) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlDelete = " DELETE FROM projects WHERE id = ?;";

		db.prepareStatement(sqlDelete);		
		db.setInt(1, project.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public List<Project> listAll() throws ModelException {
		
		DBHandler db = new DBHandler();
		
		List<Project> projects = new ArrayList<Project>();
			
		String sqlQuery = " SELECT * FROM projects ORDER BY name;";
		
		db.createStatement();
		db.executeQuery(sqlQuery);

		while (db.next()) {
			Project p = createProject(db);
			
			projects.add(p);
		}
		
		return projects;
	}

	@Override
	public Project findById(int id) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sql = "SELECT * FROM projects WHERE id = ?;";
		
		db.prepareStatement(sql);
		db.setInt(1, id);
		db.executeQuery();
		
		Project p = null;
		while (db.next()) {
			p = createProject(db);
			break;
		}
		
		return p;
	}
	
	private Project createProject(DBHandler db) throws ModelException {
		
		Project p = new Project(db.getInt("id"));
		
		p.setName(db.getString("name"));
        p.setDescription(db.getString("description"));
        p.setStart_date(db.getString("start_date"));
        p.setEnd_date(db.getString("end_date"));
        CompanyDAO companyDAO = DAOFactory.createDAO(CompanyDAO.class);
		Company c = companyDAO.findById(db.getInt("companies_id"));
		p.setCompany(c);
		
		return p;
	}
	
}
