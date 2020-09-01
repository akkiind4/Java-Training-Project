package com.Project.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
		public static Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			return connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/obs","root","admin");
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return connection;
		}
		public static void CloseDatabaseObjects(ResultSet resultset,PreparedStatement preparedStatement,Connection connection){
			try{
				if(resultset!=null && !resultset.isClosed())
					resultset.close();
				if(preparedStatement!=null && !preparedStatement.isClosed())
					preparedStatement.close();
				if(connection!=null && !connection.isClosed())
					connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
}
