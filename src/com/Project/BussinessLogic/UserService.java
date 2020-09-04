package com.Project.BussinessLogic;

import java.sql.SQLException;

import com.Project.Dao.CustomerDao;
import com.Project.Dao.UserDao;
import com.Project.bean.Role;
import com.Project.bean.User;

public class UserService {
	
	
	public boolean checkValidUser(User user) throws SQLException{
		UserDao userDao=new UserDao();
		return userDao.checkValidUser(user);
		
	}
	public Role RetrieveRole(String userId)throws SQLException{
		UserDao userDao=new UserDao();
		return userDao.RetrieveRole(userId);
	}
	public boolean UpdatePassword(User user, String password) throws SQLException {
		UserDao userDao=new UserDao();
		return userDao.UpdatePassword(user,password);
		
	}
	
}
