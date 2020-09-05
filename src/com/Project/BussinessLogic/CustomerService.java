package com.Project.BussinessLogic;



import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.Project.Dao.CustomerDao;
import com.Project.bean.Transaction;
import com.Project.bean.User;
import com.Project.bean.User;

public class CustomerService {
	
			public Double getCustomerBalance(String userId) throws SQLException{
				CustomerDao customerDao=new CustomerDao();
				return customerDao.getCustomerBalance(userId);
				
			}
	
		public double CustomerDeposit(String userId,double amount)throws SQLException{ //deposited amount is returned for admin to verify
			CustomerDao customerDao = new CustomerDao();
			try {
				return customerDao.CustomerDeposit(userId,amount);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return -1;
			}
		
		public double CustomerWithdrawal(String userId,double amount1)throws SQLException{ //deposited amount is returned for admin to verify
			CustomerDao customerDao = new CustomerDao();
			try {
				return customerDao.CustomerWithdrawal(userId,amount1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return -1;
			}
		
		/*public List<Transaction> ViewTranscationHistory(String userId) throws SQLException{
			CustomerDao customerDao = new CustomerDao();
			return customerDao.ViewTransactionHistory(userId);
			
		}*/

		public List<Transaction> ViewTranscationHistoryBetweenDates(String userId,String date1,String date2) throws SQLException{
			CustomerDao customerDao = new CustomerDao();
			return customerDao.ViewTranscationHistoryBetweenDates(userId,date1,date2);

		}	
}
