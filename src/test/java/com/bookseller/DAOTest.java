package com.bookseller;


import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;




public class DAOTest {

	@Test
	public void testDAO() throws IOException{
		
		DAO dao = new DAO();
		InputStream is = getClass().getClassLoader().getResourceAsStream(dao.propFileName);;
		if(is!=null) {
			dao.properties.load(is);
		}
		
		final String url = dao.properties.getProperty("url")+"/bookseller";
		final String user = dao.properties.getProperty("username");
		final String pass = dao.properties.getProperty("password");
		try {
			DAO.connection = DriverManager.getConnection(url, user, pass);
			if(DAO.connection!=null) 
				System.out.println("Connected to PostgreSQL!");
		}
		
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
	        try {
	            if (DAO.connection != null) {
	            	DAO.connection.close();
	            }
	        } catch (SQLException sqlee) {
	            sqlee.printStackTrace();
	        }
	    }
			
	}

}
