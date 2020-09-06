package com.Project.Dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.Project.bean.Transaction;
import com.Project.factory.ConnectionFactory;

public class CustomerDao {

	/*
	 * public User getCustomerDetails(String userId) throws SQLException{
	 * Connection connection = null; PreparedStatement preparedStatement = null;
	 * ResultSet resultset = null;
	 * 
	 * try{ connection = ConnectionFactory.getConnection(); preparedStatement =
	 * connection.prepareStatement("select * from user where userid = ?");
	 * preparedStatement.setString(1,userId);
	 * resultset=preparedStatement.executeQuery(); while(resultset.next()){ User
	 * user=new
	 * User(resultset.getString(1),resultset.getString(2),resultset.getString
	 * (3), resultset.getString(4),resultset.getString(5),resultset.getDate(6),
	 * resultset.getString(7)); return user;
	 * 
	 * User.setCustomerBalance(customerBalance); } }catch(SQLException e){ throw
	 * e; }finally{ //ConnectionFactory.CloseDatabaseObjects(resultset,
	 * preparedStatement, connection); }
	 * 
	 * return null;
	 * 
	 * }
	 */
	public Double getCustomerBalance(String userId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;

		try {
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("select balance from account where userid = ?");
			preparedStatement.setInt(1,Integer.parseInt(userId));
			resultset = preparedStatement.executeQuery();
			while (resultset.next())
				return resultset.getDouble(1);
		} catch (SQLException e) {
			throw e;
		} finally {
			// ConnectionFactory.CloseDatabaseObjects(resultset,
			// preparedStatement, connection);
		}

		return null;

	}

	public double CustomerDeposit(String userid, double credited)
			throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		double avlbalance = 0;
		int accountno = 0;
		int count = 0;
		Date date = new Date();
		// SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
		java.sql.Date transactiontime = new java.sql.Date(date.getTime());

		try {

			connection = ConnectionFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from account where userid = ?");
			preparedStatement.setInt(1, Integer.parseInt(userid));
			resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				avlbalance = resultset.getDouble(4);
				accountno = resultset.getInt(3);
			}
			avlbalance = avlbalance + credited;
			String SQL_INSERT = "INSERT INTO transaction(userid,accountno,credited,transactionDate,avlbalance)"
					+ "VALUES(?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(SQL_INSERT);

			preparedStatement.setInt(1, Integer.parseInt(userid));
			preparedStatement.setInt(2, accountno);
			preparedStatement.setDouble(3, credited);
			preparedStatement.setDate(4,new java.sql.Date(new Date().getTime()));
			preparedStatement.setDouble(5, avlbalance);
			count = preparedStatement.executeUpdate();

			preparedStatement = connection.prepareStatement("update account set balance = ? where userid=?");

			preparedStatement.setInt(2, Integer.parseInt(userid));
			preparedStatement.setDouble(1, avlbalance);
			count = preparedStatement.executeUpdate();

			return avlbalance;
		} catch (SQLException e) {
			throw e;
		} finally {
			// ConnectionFactory.CloseDatabaseObjects(resultset,
			// preparedStatement, connection);
		}

	}

	public double CustomerWithdrawal(String userid, double debited)
			throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		double avlbalance = 0;
		int accountno =0;
		int count = 0;
		Date date = new Date();
		// SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
		java.sql.Date transactiontime = new java.sql.Date(date.getTime());

		try {

			connection = ConnectionFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from account where userid = ?");
			preparedStatement.setInt(1, Integer.parseInt(userid));
			resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				avlbalance = resultset.getDouble(4);
				accountno = resultset.getInt(3);
			}

			avlbalance = avlbalance - debited;
			String SQL_INSERT = "INSERT INTO transaction(userid,accountno,debited,transactionDate,avlbalance)"
					+ "VALUES(?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(SQL_INSERT);

			preparedStatement.setInt(1,Integer.parseInt(userid));
			preparedStatement.setInt(2, accountno);
			preparedStatement.setDouble(3, debited);
			preparedStatement.setDate(4,
					new java.sql.Date(new Date().getTime()));
			preparedStatement.setDouble(5, avlbalance);
			count = preparedStatement.executeUpdate();

			preparedStatement = connection
					.prepareStatement("update account set balance = ? where userid=?");

			preparedStatement.setInt(2,Integer.parseInt(userid));
			preparedStatement.setDouble(1, avlbalance);
			count = preparedStatement.executeUpdate();

			return avlbalance;
		} catch (SQLException e) {
			throw e;
		} finally {
			// ConnectionFactory.CloseDatabaseObjects(resultset,
			// preparedStatement, connection);
		}
	}

	/*
	 * public List<Transaction> ViewTransactionHistory(String userId)throws
	 * SQLException{ Connection connection = null; PreparedStatement
	 * preparedStatement = null; ResultSet resultset = null; List<Transaction>
	 * transaction;
	 * 
	 * try{ connection = ConnectionFactory.getConnection(); preparedStatement =
	 * connection
	 * .prepareStatement("select * from transaction where userid = ?");
	 * preparedStatement.setString(1,userId);
	 * 
	 * resultset = preparedStatement.executeQuery();
	 * 
	 * transaction= new ArrayList<>(); while(resultset.next()){
	 * 
	 * Transaction t = new Transaction(resultset.getDouble(5),
	 * resultset.getDouble(6),resultset.getDate(7),resultset.getDouble(8));
	 * transaction.add(t); } return transaction; }catch(SQLException e){ throw
	 * e; }finally{ //ConnectionFactory.CloseDatabaseObjects(resultset,
	 * preparedStatement, connection); }
	 */

	public List<Transaction> ViewTranscationHistoryBetweenDates(String userId,
			String fromDate, String toDate) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		List<Transaction> transaction;

		try {

			connection = ConnectionFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM transaction where transactionDate between ? and ? and userid=?");
			preparedStatement.setInt(3,Integer.parseInt(userId));
			preparedStatement.setDate(1, java.sql.Date.valueOf(fromDate));
			preparedStatement.setDate(2, java.sql.Date.valueOf(toDate));

			resultset = preparedStatement.executeQuery();

			transaction = new ArrayList<>();
			while (resultset.next()) {

				Transaction t = new Transaction(resultset.getDouble(5),
						resultset.getDouble(6), resultset.getDate(7),
						resultset.getDouble(8));
				transaction.add(t);
			}
			return transaction;
		} catch (SQLException e) {
			throw e;
		} finally {
			// ConnectionFactory.CloseDatabaseObjects(resultset,
			// preparedStatement, connection);
		}

	}

	public boolean ViewTransactionHistoryInFile(List<Transaction> transaction,int userId) throws IOException, SQLException, InterruptedException {
		FileWriter f1=new FileWriter("TransactionHistory.csv");
       // BufferedWriter fw=new BufferedWriter(f1);
        Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int accountno=0;
		SimpleDateFormat d=new SimpleDateFormat("dd/mm/yyyy");
		try {

			connection = ConnectionFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT accountno FROM account where userid = ?");
			preparedStatement.setInt(1,userId);
			

			resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				 accountno=resultset.getInt(1);
			}
			
		} catch (SQLException e) {
			throw e;
		} finally {
			// ConnectionFactory.CloseDatabaseObjects(resultset,
			// preparedStatement, connection);
		}
		f1.append("UserID"+",");
		f1.append("AccNO"+",");
		f1.append("Credited"+",");
		f1.append("Debited"+",");
		f1.append("Date"+",");
		f1.append("AvailableBalance"+",");
		f1.append("\n");
				//+ "AccNo  Credited  Debited  Date  AvailableBalance\n");
        for(Transaction t:transaction){
        	String s =String.valueOf(userId)+","+String.valueOf(accountno)+","
        			+String.valueOf(t.getCredit())+","+String.valueOf(t.getDebit())+","+d.format(t.getTransactionTime())+","+t.getAvlBalance();
        	f1.append("\n"+s);
        }
        
        f1.flush();
        f1.close(); 
       /* TimeUnit.MINUTES.sleep(10);
        //FileWriter fwOb = new FileWriter("TransactionHistory.txt", false); 
        PrintWriter pwOb = new PrintWriter("TransactionHistory.csv");
        pwOb.print("");
        pwOb.close();*/
       // fwOb.close();

		
		
        
		
		return false;
		

	}
}