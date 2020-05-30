package com.bookseller;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserValidationService {
	
	@Autowired
	DAO dao;
	
	
	public boolean isUserValid(String username, String password) throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream(dao.propFileName);;
		if(is!=null) {
			dao.properties.load(is);
		}
		
		final String url = dao.properties.getProperty("url")+"/bookseller";
		final String user = dao.properties.getProperty("username");
		final String pass = dao.properties.getProperty("password");
		
		try{
			
			DAO.connection = DriverManager.getConnection(url, user, pass);
			if(DAO.connection!=null) 
				System.out.println("Connected to PostgreSQL!");
			else
				System.out.println("connection failure!");
			DAO.statement = DAO.connection.createStatement();
			DAO.resultSet = DAO.statement.executeQuery("SELECT * FROM public.user");
			while(DAO.resultSet.next()) {
				if(DAO.resultSet.getString("username").equals(username)) {
					
					if(DAO.resultSet.getString("password").equals(password)) 
						
						return true;
				}
				else {					
					return false;
				}
			}
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
	        try {
	            if (DAO.connection != null) {
	            	DAO.connection.close();
	            }
	            if (DAO.statement != null) {
	            	DAO.statement.close();
	            }
	        } catch (SQLException sqlee) {
	            sqlee.printStackTrace();
	        }
	    }
		return false;	
	}
}
