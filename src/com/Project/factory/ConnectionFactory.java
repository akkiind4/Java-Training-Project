package com.Project.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
	
private static Connection connection;

	static
	{
		//load the driver
		
		
		//get the db connection
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/obs","root","root");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//db url
	}

	public static Connection getConnection() {
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
