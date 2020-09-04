package com.Project.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Project.bean.Role;
import com.Project.bean.User;
import com.Project.factory.ConnectionFactory;

public class UserDao {

	public  boolean checkValidUser(User user) throws SQLException {
		//Customer customer = new Customer();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String temp="select * from user where userid = ? and password = ?";
		try{
		connection = ConnectionFactory.getConnection();
		
		preparedStatement=connection.prepareStatement(temp);
		preparedStatement.setString(1,user.getUserId());
		preparedStatement.setString(2,user.getPassword());	
			 resultset = preparedStatement.executeQuery();
//			  java.sql.ResultSetMetaData rsmd = resultset.getMetaData();
//			  System.out.println(rsmd);
		
	if(resultset.next()){
			
			return true;
		}else{
			
		return false;
		}
		}catch(SQLException e){
			throw e;
		}
		finally {
		//ConnectionFactory.CloseDatabaseObjects(resultset, preparedStatement, connection);
		}
	}

	public Role RetrieveRole(String userId)throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		Role role = new Role();
		try{
		connection = ConnectionFactory.getConnection();
		 preparedStatement = connection.prepareStatement("select roleid from userrole where userid = ?");
				 preparedStatement.setString(1,userId);
				 resultset=preparedStatement.executeQuery();
				 while(resultset.next()){
					 role.setRoleId(resultset.getString(1));
				 }
			preparedStatement = connection.prepareStatement("select rolename from role where roleid=?");
			preparedStatement.setString(1,role.getRoleId());
			resultset=preparedStatement.executeQuery();
					 while(resultset.next()){
						 role.setRoleName(resultset.getString(1));
					 }
					 return role;
					
				 }
	catch(SQLException e){
		throw e;
	}finally{
		//ConnectionFactory.CloseDatabaseObjects(resultset, preparedStatement, connection);
	}
	}
	public boolean UpdatePassword(User user,String newPassword)throws SQLException{ //deposited amount is returned for admin to verify
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try{
		connection = ConnectionFactory.getConnection();
		String custId=user.getUserId();
		String template = "update user set password = ? where userid = ?";
		preparedStatement=connection.prepareStatement(template);
		preparedStatement.setString(1, newPassword);
		preparedStatement.setString(2, custId);
		int count = preparedStatement.executeUpdate();
		if(count>0){
			return true;
		}
		return false;
		} catch (SQLException e) {
			
		throw e;
		//e.printStackTrace();
		
		}
		finally {
		//ConnectionFactory.CloseDatabaseObjects(resultset, preparedStatement, connection);
		}
		
		
	}
}
