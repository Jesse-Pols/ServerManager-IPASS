package de.jonashackt.springbootvuejs.dao;

import java.util.ArrayList;
import java.util.List;

import de.jonashackt.springbootvuejs.domain.Dienst;

public class DienstOracleDaoImpl extends OracleBaseDao {
	
	private static final String BASE_URL = "de.jonashackt.springbootvuejs.dao.DienstOracleDaoImpl.java";
	
	public List<Dienst> findAll() {
		System.out.println("Searching...");
		List<Dienst> diensten = new ArrayList<Dienst>();
		
		try {
			
			ps = dbConnection.prepareStatement(
					"SELECT id, name, key, relevance FROM dienst");
			rs = ps.executeQuery();
			
			System.out.println("Found something?");
			
			while (rs.next()) {
				Dienst dienst = new Dienst(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				if (rs.getString(4) != null)
					dienst.setRelevance(rs.getString(4));
				diensten.add(dienst);
			}
			
			System.out.println("Jep");
			rs.close();
			ps.close();
			
		} catch (Exception ex)
		{ System.out.println(BASE_URL + ".findAll() Failed: " + ex); }
		
		return diensten;
	}

	public String findKeyById(int id) {
		
		String key = "";
		
		try {

			ps = dbConnection.prepareStatement(
				"SELECT key FROM dienst WHERE id=?");
			ps.setInt(1,id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				key = rs.getString(1);
			}

		} catch (Exception ex) 
		{ System.out.println(BASE_URL + ".findKeyById() Failed: " + ex); }
		
		return key;

	}

	public boolean save(Dienst dienst) {		

		try {
			
			ps = dbConnection.prepareStatement(
					"INSERT INTO dienst VALUES (null,?,?,?)");
			ps.setString(1, dienst.getName());
			ps.setString(2, dienst.getKey());

			ps.setString(3, dienst.getRelevance());
			ps.executeQuery();
			ps.close();
			
		} catch (Exception ex)
		{ System.out.println(BASE_URL + ".save() Failed: " + ex); return false; }
		
		return true;
		
	}

	public boolean update(Dienst dienst) {

		System.out.println(dienst);

		try {
			
			ps = dbConnection.prepareStatement(
					"UPDATE dienst SET name=?, key=?, relevance=? WHERE id=?");
			ps.setString(1, dienst.getName());
			ps.setString(2, dienst.getKey());
			ps.setString(3, dienst.getRelevance());
			ps.setInt(4, dienst.getId());		
			ps.executeQuery();
			ps.close();

		} catch (Exception ex)
		{ System.out.println(BASE_URL + ".change() Failed: " + ex); return false; }

		return true;

	}

	public boolean delete(Dienst dienst) {

		try {

			ps = dbConnection.prepareStatement(
					"DELETE FROM dienst WHERE id=?");
			ps.setInt(1, dienst.getId());
			ps.executeQuery();
			ps.close();

		} catch (Exception ex)
		{ System.out.println(BASE_URL + ".delete() Failed: " + ex); return false; }

		return true;

	}

}
