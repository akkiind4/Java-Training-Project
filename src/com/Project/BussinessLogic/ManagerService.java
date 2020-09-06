package com.Project.BussinessLogic;

import java.sql.SQLException;

import com.Project.Dao.ManagerDao;
import com.Project.bean.User;

public class ManagerService{
		
	
	public User getCustomerDetails(int userId) throws SQLException{
		ManagerDao managerDao= new ManagerDao();
		return managerDao.getCustomerDetails(userId);
		
	}
	public User getCustomerAccountDetailsStringPattern(String pattern) throws SQLException{
		ManagerDao managerDao = new ManagerDao();
		return managerDao.getCustomerAccountDetailsStringPattern(pattern);
	}
}
