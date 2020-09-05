package com.Project.BussinessLogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.Project.Dao.AdminDao;
import com.Project.bean.User;

public class AdminService{
		
	
	public boolean AddCustomer(User user)throws SQLException,IOException{
		AdminDao adDao = new AdminDao();
		return adDao.AddCustomer(user);
	}
	
}
