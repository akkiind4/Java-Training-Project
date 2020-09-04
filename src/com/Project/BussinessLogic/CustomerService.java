package com.Project.BussinessLogic;



import java.sql.SQLException;

import com.Project.Dao.CustomerDao;
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return -1;
			}
		public double CustomerWithdrawal(double amount){ //Withdrawal amount verified by admin
			return 0;
			
		}
		
		public void ViewTranscationHistory(){
			
		}

	
}
