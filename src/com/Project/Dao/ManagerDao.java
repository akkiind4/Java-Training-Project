package com.Project.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.Project.bean.User;
import com.Project.factory.ConnectionFactory;


public class ManagerDao {

	public User getCustomerDetails(int userId) throws SQLException{
		  Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultset = null;
			User user =null;
	 try{ 
		  connection = ConnectionFactory.getConnection();
		  preparedStatement = connection.prepareStatement("select * from user where userid = ?");
	  preparedStatement.setInt(1,userId);
	  resultset=preparedStatement.executeQuery();
	  while(resultset.next()){
		user=new User(String.valueOf(resultset.getInt(1)),resultset.getString(2),resultset.getString
	  (3), resultset.getString(4),resultset.getString(6),resultset.getDate(7),
	  "****"); 
	 }
	  return user;
	  }catch(SQLException e){
		  throw e; 
		  }finally{ //ConnectionFactory.CloseDatabaseObjects(resultset,preparedStatement, connection); }
			  
	  }
	
	}
	public User getCustomerAccountDetailsStringPattern(String pattern) throws SQLException{
		  Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultset = null;
			User user =null;
			ArrayList<Integer> uid = new ArrayList<>();
			Map<Integer,ArrayList<String>> namelist=new HashMap<>();
			
	 try{ 
		 int id;
		  connection = ConnectionFactory.getConnection();
		  preparedStatement = connection.prepareStatement("select userid,name from user where name like ?");
	  preparedStatement.setString(1,"%"+pattern+"%");
	  resultset=preparedStatement.executeQuery();
	  while(resultset.next()){
		  id=resultset.getInt(1);
		    uid.add(id);
		    ArrayList<String> list = new ArrayList<>();
		  	list.add(resultset.getString(2));	 
		  	namelist.put(id,list);
		  }
	
	  for(int i:uid){
		  connection = ConnectionFactory.getConnection();
		  int accountno;
		  double balance;
		  preparedStatement = connection.prepareStatement("select accountno,balance from account where userid = ?");
		  preparedStatement.setInt(1,i);
		  resultset=preparedStatement.executeQuery();
		  while(resultset.next()){
			ArrayList<String> s= namelist.get(i);
			  accountno=resultset.getInt(1);
			  balance=resultset.getDouble(2);
			  s.add(String.valueOf(accountno)+"\t"+String.valueOf(balance));
			  namelist.put(i,(s));	  
		  }
	  }
	  Set<Entry<Integer,ArrayList<String>>>kms=namelist.entrySet();
		for(Entry<Integer,ArrayList<String>>e:kms)
		{
			System.out.println(e.getKey()+" "+e.getValue());
		}
		
		  return user;
	  }catch(SQLException e){
		  throw e; 
		  }finally{ //ConnectionFactory.CloseDatabaseObjects(resultset,preparedStatement, connection); }
			  
	  }
	
	}
	 
}
