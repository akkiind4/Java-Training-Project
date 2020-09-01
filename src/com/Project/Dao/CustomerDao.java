package com.Project.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Project.bean.Customer;
import com.Project.factory.ConnectionFactory;

public class CustomerDao {

	public static boolean checkValidCustomer(Customer customer) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try{
		connection = ConnectionFactory.getConnection();
		preparedStatement=connection.prepareStatement("select * from customer where customerId = ? and customerPwd = ?");
		preparedStatement.setString(1,customer.getCustomerID());
		preparedStatement.setString(2,customer.getCustomnerPwd());
		resultset = preparedStatement.executeQuery();
		if(resultset.next()){
			return true;
		}
		return false;
		}catch(SQLException e){
			throw e;
		}
		finally {
			ConnectionFactory.CloseDatabaseObjects(resultset, preparedStatement, connection);
		}
	}
	public boolean UpdatePassword(Customer customer,String newPassword){ //deposited amount is returned for admin to verify 
		Connection connection = null; 
		PreparedStatement preparedStatement = null; 
		ResultSet resultset = null; 
		try{ 
			connection = ConnectionFactory.getConnection(); 
			String custId=customer.getCustomerID(); 
			String template = "update Customer set password = ? where custId = ?"; 
			preparedStatement=connection.prepareStatement(template); 
			preparedStatement.setString(1, newPassword); 
			preparedStatement.setString(2, custId); 
			preparedStatement.executeBatch(); 
			} 
		catch (SQLException e) 
		{ // TODO Auto-generated catch block 
			e.printStackTrace(); 
			return true;
			} 
		finally 
		{ 
			ConnectionFactory.CloseDatabaseObjects(resultset, preparedStatement, connection); 
			} 
		return false; 
		}	
	}

