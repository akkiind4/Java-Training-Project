package com.Project.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.Project.bean.Transaction;
import com.Project.factory.ConnectionFactory;


public class CustomerDao {

/*	public User getCustomerDetails(String userId) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		
	try{
			connection = ConnectionFactory.getConnection();
			 preparedStatement = connection.prepareStatement("select * from user where userid = ?");
					 preparedStatement.setString(1,userId);
					 resultset=preparedStatement.executeQuery();
					 while(resultset.next()){
	User user=new User(resultset.getString(1),resultset.getString(2),resultset.getString(3),
				resultset.getString(4),resultset.getString(5),resultset.getDate(6),
				resultset.getString(7));
						return user;
	
						 User.setCustomerBalance(customerBalance);
					 }
		}catch(SQLException e){
			throw e;
		}finally{
			//ConnectionFactory.CloseDatabaseObjects(resultset, preparedStatement, connection);
		}
		
		return null;
		
	}*/
	public Double getCustomerBalance(String userId) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		
	try{
			connection = ConnectionFactory.getConnection();
			 preparedStatement = connection.prepareStatement("select balance from account where userid = ?");
					 preparedStatement.setString(1,userId);
					 resultset=preparedStatement.executeQuery();
					 while(resultset.next())
						return resultset.getDouble(1);
		}catch(SQLException e){
			throw e;
		}finally{
			//ConnectionFactory.CloseDatabaseObjects(resultset, preparedStatement, connection);
		}
		
		return null;
		
	}

	public double CustomerDeposit(String userid,double credited)throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		double avlbalance =0;
		String accountno="";
		int count =0;
		Date date = new Date();
		//SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
		java.sql.Date transactiontime = new java.sql.Date(date.getTime());
		
		try{

			connection = ConnectionFactory.getConnection();
			 preparedStatement = connection.prepareStatement("select * from account where userid = ?");
					 preparedStatement.setString(1,userid);
					 resultset=preparedStatement.executeQuery();
					 while(resultset.next()){
					 avlbalance=resultset.getDouble(3);
					 accountno = resultset.getString(2);
					 }
					 avlbalance = avlbalance + credited;
					String SQL_INSERT ="INSERT INTO transaction(userid,accountno,credited,transactiontime,avlbalance)"
							+ "VALUES(?,?,?,?,?)";
			preparedStatement =connection.prepareStatement(SQL_INSERT);
	
			preparedStatement.setString(1,userid);
			preparedStatement.setString(2,accountno);
			preparedStatement.setDouble(3,credited);
			preparedStatement.setDate(4,new java.sql.Date(new Date().getTime()));
			preparedStatement.setDouble(5,avlbalance);
			 count = preparedStatement.executeUpdate();
		
		preparedStatement=connection.prepareStatement("update account set balance = ? where userid=?");
	
		preparedStatement.setString(2,userid);
		preparedStatement.setDouble(1,avlbalance);
					count =	preparedStatement.executeUpdate();
			 
		return avlbalance;	
		}catch(SQLException e){
				throw e;
			}finally{
				//ConnectionFactory.CloseDatabaseObjects(resultset, preparedStatement, connection);
			}

	}

	public double CustomerWithdrawal(String userid, double debited) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		double avlbalance =0;
		String accountno="";
		int count =0;
		Date date = new Date();
		//SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
		java.sql.Date transactiontime = new java.sql.Date(date.getTime());
		
		try{

			connection = ConnectionFactory.getConnection();
			 preparedStatement = connection.prepareStatement("select * from account where userid = ?");
					 preparedStatement.setString(1,userid);
					 resultset=preparedStatement.executeQuery();
					 while(resultset.next()){
					 avlbalance=resultset.getDouble(3);
					 accountno = resultset.getString(2);
					 }
					
					 avlbalance = avlbalance - debited;
					String SQL_INSERT ="INSERT INTO transaction(userid,accountno,debited,transactiontime,avlbalance)"
							+ "VALUES(?,?,?,?,?)";
			preparedStatement =connection.prepareStatement(SQL_INSERT);
	
			preparedStatement.setString(1,userid);
			preparedStatement.setString(2,accountno);
			preparedStatement.setDouble(3,debited);
			preparedStatement.setDate(4,new java.sql.Date(new Date().getTime()));
			preparedStatement.setDouble(5,avlbalance);
			 count = preparedStatement.executeUpdate();
		
		preparedStatement=connection.prepareStatement("update account set balance = ? where userid=?");
	
		preparedStatement.setString(2,userid);
		preparedStatement.setDouble(1,avlbalance);
					count =	preparedStatement.executeUpdate();
			 
		return avlbalance;	
		}catch(SQLException e){
				throw e;
			}finally{
				//ConnectionFactory.CloseDatabaseObjects(resultset, preparedStatement, connection);
			}
	}
	

/*	public List<Transaction> ViewTransactionHistory(String userId)throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		List<Transaction> transaction;
	
		try{
		connection = ConnectionFactory.getConnection();
			 preparedStatement = connection.prepareStatement("select * from transaction where userid = ?");
			 preparedStatement.setString(1,userId);
			 
					 resultset = preparedStatement.executeQuery();
					
			 transaction= new ArrayList<>();
			 while(resultset.next()){
				
				 Transaction t = new Transaction(resultset.getDouble(5),
						 resultset.getDouble(6),resultset.getDate(7),resultset.getDouble(8));
				 transaction.add(t);
				}
			 return transaction;
				}catch(SQLException e){
					throw e;
				}finally{
					//ConnectionFactory.CloseDatabaseObjects(resultset, preparedStatement, connection);
				}*/
		
		
public List<Transaction> ViewTranscationHistoryBetweenDates(String userId,String fromDate,String toDate)throws SQLException{
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultset = null;
	List<Transaction> transaction;

	try{
		
	connection = ConnectionFactory.getConnection();
	preparedStatement = connection.prepareStatement("SELECT * FROM transaction where transactiontime between ? and ? and userid=?");
	 	preparedStatement.setString(3,userId);
		 preparedStatement.setDate(1,java.sql.Date.valueOf(fromDate));
		 preparedStatement.setDate(2,java.sql.Date.valueOf(toDate));
		 
		 resultset = preparedStatement.executeQuery();
				
		 transaction= new ArrayList<>();
		 while(resultset.next()){
			
			 Transaction t = new Transaction(resultset.getDouble(5),
					 resultset.getDouble(6),resultset.getDate(7),resultset.getDouble(8));
			 transaction.add(t);
			}
		 return transaction;
			}catch(SQLException e){
				throw e;
			}finally{
				//ConnectionFactory.CloseDatabaseObjects(resultset, preparedStatement, connection);
			}
	
	
	

}
}
