package com.Project.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Project.bean.User;
import com.Project.factory.ConnectionFactory;

public class AdminDao {
		
	public  boolean AddCustomer(User user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String SQL_INSERT ="INSERT INTO user(userid,name,address,mobileno,occupation,dob,password)"
				+ "VALUES(?,?,?,?,?,?,?)";
		
		int count=0;
		try{
		connection = ConnectionFactory.getConnection();
		preparedStatement=connection.prepareStatement(SQL_INSERT);
		preparedStatement.setString(1,user.getUserId());
		preparedStatement.setString(7,user.getPassword());
		preparedStatement.setString(3,user.getAddress());
		preparedStatement.setString(2,user.getName());
		preparedStatement.setString(4,user.getMobileNo());
		preparedStatement.setString(5,user.getOccupation());
		preparedStatement.setString(6,user.getDob());
			 count = preparedStatement.executeUpdate();
//			  java.sql.ResultSetMetaData rsmd = resultset.getMetaData();
//			  System.out.println(rsmd);
		
	if(count>0){
			
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
}
