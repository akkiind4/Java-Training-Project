package com.Project.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Project.bean.Account;
import com.Project.bean.Role;
import com.Project.bean.User;
import com.Project.factory.ConnectionFactory;

public class AdminDao {

	public boolean AddCustomer(User user, Account account, Role role)
			throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		String SQL_INSERT = "INSERT INTO user(name,address,mobileno,occupation,dob,password)"
				+ "VALUES(?,?,?,?,?,?)";

		int count = 0;
		try {
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_INSERT);
			// preparedStatement.setString(1,user.getUserId());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setString(2, user.getAddress());
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(3, user.getMobileNo());
			preparedStatement.setString(4, user.getOccupation());
			preparedStatement.setString(5, user.getDob());
			count = preparedStatement.executeUpdate();

			SQL_INSERT = "select userid from user order by userid desc limit 1";

			int count1 = 0;
			preparedStatement = connection.prepareStatement(SQL_INSERT);
			// preparedStatement.setString(1,userid);
			resultset = preparedStatement.executeQuery();
			while (resultset.next())
				count1 = resultset.getInt(1);
			String userid = Integer.toString(count1); // inserting in account
														// table
			SQL_INSERT = "INSERT INTO account(userid,accountno,balance)"
					+ "VALUES(?,?,?)";

			preparedStatement = connection.prepareStatement(SQL_INSERT);
			// preparedStatement.setString(1,user.getUserId());
			preparedStatement.setInt(1, count1);
			preparedStatement.setInt(2, Integer.parseInt(account.getAccno()));
			preparedStatement.setDouble(3, account.getBalance());
			count = preparedStatement.executeUpdate();
			SQL_INSERT = "INSERT INTO userrole(roleid,userid)" + "VALUES(?,?)";

			preparedStatement = connection.prepareStatement(SQL_INSERT);
			// preparedStatement.setString(1,user.getUserId());
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, count1);
			count = preparedStatement.executeUpdate();

			if (count > 0) {

				return true;
			} else {

				return false;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			// ConnectionFactory.CloseDatabaseObjects(resultset,
			// preparedStatement, connection);
		}
	}

	public boolean DeleteCustomer(int userid) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;

		int count = 0;
		try {

			String SQL_INSERT = "Delete from account where userid=?";

			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_INSERT);
			preparedStatement.setInt(1, userid);
			count = preparedStatement.executeUpdate();

			SQL_INSERT = "Delete from userrole where userid=?";
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_INSERT);
			preparedStatement.setInt(1, userid);
			count = preparedStatement.executeUpdate();

			SQL_INSERT = "Delete from user where userid=?";
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_INSERT);
			preparedStatement.setInt(1, userid);
			count = preparedStatement.executeUpdate();

			if (count > 0)
				return true;
			return false;

		} catch (SQLException e) {
			throw e;
		} finally {
			// ConnectionFactory.CloseDatabaseObjects(resultset,
			// preparedStatement, connection);
		}
	}
}
