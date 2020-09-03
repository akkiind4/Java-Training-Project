package com.Project.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Project.bean.Customer;
import com.Project.bean.User;
import com.Project.factory.ConnectionFactory;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class CustomerDao {

	public static boolean checkValidCustomer(User user) throws SQLException {
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
			System.out.println("$%$%$%$%$");
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
//	public User getCustomerDetails(String userId) throws SQLException{
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultset = null;
//		
//		try{
//			connection = ConnectionFactory.getConnection();
//			 preparedStatement = connection.prepareStatement("select * from customer where customerId = ?");
//					 preparedStatement.setString(1,customerID);
//					 resultset=preparedStatement.executeQuery();
//					 while(resultset.next()){
//		Customer customer=new Customer(resultset.getString(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getString(5),resultset.getString(6),resultset.getString(7));
//						return customer;
//	
////						 Customer.setCustomerBalance(customerBalance);
//					 }
//		}catch(SQLException e){
//			throw e;
//		}finally{
//			//ConnectionFactory.CloseDatabaseObjects(resultset, preparedStatement, connection);
//		}
		
//		return null;
//		
//	}
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
		
		
	}}
