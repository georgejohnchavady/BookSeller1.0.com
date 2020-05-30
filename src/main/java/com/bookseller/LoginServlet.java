package com.bookseller;


import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class LoginServlet{
	@Autowired
	private UserValidationService userValidationService;
	DAO dao = new DAO();
	
	@RequestMapping(value = "/login.do",method = RequestMethod.GET)	
	public String login() throws SQLException{
		if (DAO.connection != null) {
        	DAO.connection.close();
        }
        if (DAO.statement != null) {
        	DAO.statement.close();
        }
		return "login";		
		
	}
	
	@RequestMapping(value = "/login.do",method = RequestMethod.POST)	
	public String handleLoginRequest(ModelMap model, @RequestParam String username, @RequestParam String password) throws IOException {
	if(userValidationService.isUserValid(username, password)) {
		model.put("name", username);
		return "home_page";
	}		
	else{
		model.put("message", "Invalid Credentials!");
		return "login";
	}		
		
	}
	
}
