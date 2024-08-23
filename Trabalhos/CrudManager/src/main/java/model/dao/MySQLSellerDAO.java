package model.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.ModelException;
import model.Seller;

public class MySQLSellerDAO implements SellerDAO{

	@Override
	public boolean save(Seller seller) throws ModelException {
		DBHandler db = new DBHandler();
		
		String sqlInsert = "INSERT INTO sellers VALUES " + " (DEFAULT, ?, ?, ?, ?);";
		
		db.prepareStatement(sqlInsert);
		db.setString(1, seller.getName());
		db.setString(2, seller.getEmail());
		db.setString(3, seller.getFone());
		db.setInt(4, seller.getCompany().getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean update(Seller seller) throws ModelException {
		DBHandler db = new DBHandler();
		
		String sqlUpdate = "UPDATE sellers "
						   + "SET name = ?, "
						   + "email = ?, "
						   + "fone = ?, "
						   + "company_id = ? "
						 + "WHERE id = ?"; 
		
		db.prepareStatement(sqlUpdate);
		
		db.setString(1, seller.getName());
		db.setString(2, seller.getEmail());
		db.setString(3, seller.getFone());
		db.setInt(4, seller.getCompany().getId());
		db.setInt(5, seller.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Seller seller) throws ModelException {
		DBHandler db = new DBHandler();
		
		String sqlDelete = "DELETE FROM sellers "
						 + "WHERE id = ?;";
		
		db.prepareStatement(sqlDelete);
		db.setInt(1, seller.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public List<Seller> listAll() throws ModelException {
		DBHandler db = new DBHandler();
		
		List<Seller> sellers = new ArrayList<Seller>();
		
		String sqlQuery = "SELECT * FROM sellers";
		
		db.createStatement();
		
		db.executeQuery(sqlQuery);
		
		while (db.next()) {
			Seller s = createSeller(db);
			sellers.add(s);
		}
		
		return sellers;
	}

	@Override
	public Seller findById(int id) throws ModelException {
		DBHandler db = new DBHandler();
		
		String sqlQuery = "SELECT * FROM sellers WHERE id = ?;";
		
		db.prepareStatement(sqlQuery);
		db.setInt(1, id);
		db.executeQuery();
		
		Seller s = null;
		while(db.next()) {
			s = createSeller(db);
			break;
		}
		
		return s;
	}
	
	public Seller createSeller(DBHandler db) throws ModelException {
		Seller s = new Seller(db.getInt("id"));
		s.setName(db.getString("name"));
		s.setEmail(db.getString("email"));
		s.setFone(db.getString("fone"));
		CompanyDAO companyDAO = DAOFactory.createDAO(CompanyDAO.class);
		Company c = companyDAO.findById(db.getInt("company_id"));
		s.setCompany(c);
		
		return s;
	}

}
