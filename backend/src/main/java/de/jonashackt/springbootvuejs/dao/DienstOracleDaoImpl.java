package de.jonashackt.springbootvuejs.dao;

import java.util.ArrayList;
import java.util.List;

import de.jonashackt.springbootvuejs.domain.Dienst;

public class DienstOracleDaoImpl extends OracleBaseDao {
	
	private static final String BASE_URL = "de.jonashackt.springbootvuejs.dao.DienstOracleDaoImpl.java";
	
	public List<Dienst> findAll() {
		List<Dienst> diensten = new ArrayList<Dienst>();
		
		try {
			
			ps = dbConnection.prepareStatement(
					"SELECT D_ID, D_NAME, D_KEY, D_RELEVANCE FROM diensten");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Dienst dienst = new Dienst(rs.getInt(1), rs.getString(2), rs.getString(3));
				if (rs.getString(4) != null)
					dienst.setRelevance(rs.getString(4));
				diensten.add(dienst);
			}
			rs.close();
			ps.close();
			
		} catch (Exception ex)
		{ System.out.println(BASE_URL + ".findAll() Failed: " + ex); }
		
		return diensten;
	}

	public void save(Dienst dienst) {		

		try {
			
			ps = dbConnection.prepareStatement(
					"INSERT INTO diensten VALUES (?,?,?,null)");
			ps.setInt(1, dienst.getDienstId());
			ps.setString(2, dienst.getName());
			ps.setString(3, dienst.getKey());
			ps.executeQuery();
			ps.close();
			
		} catch (Exception ex)
		{ System.out.println(BASE_URL + ".save() Failed: " + ex); }
		
	}
	
    /*
	private static final String BASE_URL = "nl.hu.ipass.servermanager.dao.DienstOracleDaoImpl.java";
	
	public List<Dienst> findAll() {		
		List<Dienst> diensten = new ArrayList<Dienst>();
		
		try {
			
			ps = dbConnection.prepareStatement(
					"SELECT D_ID, D_NAME, D_KEY, D_RELEVANCE FROM diensten");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Dienst dienst = new Dienst(rs.getInt(1), rs.getString(2), rs.getString(3));
				if (rs.getString(4) != null)
					dienst.setRelevance(rs.getString(4));
				diensten.add(dienst);
			}
			rs.close();
			ps.close();
			
		} catch (Exception ex)
		{ System.out.println(BASE_URL + ".findAll() Failed: " + ex); }
		
		return diensten;
		
	}
	
	public void save(Dienst dienst) {				
		try {
			
			ps = dbConnection.prepareStatement(
					"INSERT INTO diensten VALUES (?,?,?)");
			ps.setInt(1, dienst.getDienstId());
			ps.setString(2, dienst.getName());
			ps.setString(3, dienst.getKey());
			ps.executeQuery();
			ps.close();
			
		} catch (Exception ex)
		{ System.out.println(BASE_URL + ".save(Dienst dienst) Failed: " + ex); }
		
	}
	
	public void update(Dienst dienst) {		
		try {
			
			ps = dbConnection.prepareStatement(
					"UPDATE diensten SET D_NAME=?, D_KEY=? WHERE D_ID=?");
			ps.setString(1, dienst.getName());
			ps.setString(2, dienst.getKey());
			ps.setInt(3, dienst.getDienstId());
			ps.executeQuery();
			ps.close();
			
		} catch (Exception ex)
		{ System.out.println(BASE_URL + ".update(Dienst dienst) Failed: " + ex); }
		
		
	}
	
	public void delete(Dienst dienst) {		
		try {
			
			ps = dbConnection.prepareStatement(
					"DELETE FROM diensten WHERE D_ID=?");
			ps.setInt(1, dienst.getDienstId());
			ps.executeQuery();
			ps.close();
			
		} catch (Exception ex)
		{ System.out.println(BASE_URL + ".delete(Dienst dienst) Failed: " + ex); }

	}
    */

}
