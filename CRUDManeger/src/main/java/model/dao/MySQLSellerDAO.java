package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.ModelException;
import model.Post;
import model.Seller;

public class MySQLSellerDAO implements SellerDAO{

	@Override
	public boolean save(Seller seller) throws ModelException {
		DBHandler db = new DBHandler();
		
		String sqlInsert = "INSERT INTO sellers VALUES "
				+ " (DEFAULT, ?, ?, ?, ?);";
		
		db.prepareStatement(sqlInsert);
		db.setString(1, seller.getName());
		db.setString(2, seller.getEmail());
		db.setString(3, seller.getFone());
		db.setInt(2, seller.getId());
		  
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean update(Seller seller) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlUpdate = "UPDATE sellers "
						 + "SET name = ?,"
						 + "SET email = ?,"
						 + "SET fone = ?,"
						 + "company_id = ?"
						 + "WHERE id = ?";
		
		db.prepareStatement(sqlUpdate);
		db.prepareStatement(sqlUpdate);
		db.setString(1, seller.getName());
		db.setString(2, seller.getEmail());
		db.setString(3, seller.getFone());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Seller seller) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlDelete = " DELETE FROM sellers "
		         + " WHERE id = ?;";

		db.prepareStatement(sqlDelete);		
		db.setInt(1, seller.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public List<Seller> listAll() throws ModelException {
		
		DBHandler db = new DBHandler();
		
		List<Seller> seller = new ArrayList<Seller>();
			
		// Declara uma instrução SQL
		String sqlQuery = " SELECT c.id AS company_id, p.*, "
				        + " p.post_date "
				        + " FROM users u "
				        + " INNER JOIN posts p "
				        + " ON u.id = p.user_id "
				        + " ORDER BY content";
		
		db.createStatement();
	
		db.executeQuery(sqlQuery);

		while (db.next()) {
			Post p = createPost(db);
			
			posts.add(p);
		}
		
		return posts;
	}

	@Override
	public Seller findById(int id) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
