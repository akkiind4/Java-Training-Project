package com.Project.BussinessLogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.Project.Dao.AdminDao;
import com.Project.bean.Account;
import com.Project.bean.Role;
import com.Project.bean.User;

public class AdminService{
		
	
	public boolean AddCustomer(User user,Account account,Role role)throws SQLException,IOException{
		AdminDao adDao = new AdminDao();
		return adDao.AddCustomer(user,account,role);
	}
	
	public boolean DeleteCustomer(int userid) throws SQLException{
		AdminDao adDao = new AdminDao();
		return adDao.DeleteCustomer(userid);
	}
	
}
