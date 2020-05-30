package com.bookseller;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public final class DAO {
	
	Properties   properties;
	final String propFileName;
	static Connection connection;
	static Statement statement;		
	static ResultSet resultSet;
	DAO(){
		properties = new Properties();
		propFileName = "config.properties";
	}
}
