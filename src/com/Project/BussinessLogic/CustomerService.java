package com.Project.BussinessLogic;

import java.sql.SQLException;

import com.Project.Dao.CustomerDao;
import com.Project.bean.Customer;

public class CustomerService {
	
			public boolean checkValidCustomer(Customer customer) throws SQLException{
				CustomerDao customerDao=new CustomerDao();
				return CustomerDao.checkValidCustomer(customer);
				
			}
	
	
	
	
		public double CustomerDeposit(){ //deposited amount is returned for admin to verify
			return 0;
			}
		public double CustomerWithdrawal(){ //Withdrawal amount verified by admin
			return 0;
			
		}
		public double ViewBalance(){ //Customer's final balance
			return 0;
			
		}
		public void ViewTranscationHistory(){
			
		}
}
