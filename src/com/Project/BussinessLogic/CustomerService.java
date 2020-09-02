package com.Project.BussinessLogic;

import java.sql.SQLException;

import com.Project.Dao.CustomerDao;
import com.Project.bean.Customer;

public class CustomerService {
	
			public boolean checkValidCustomer(Customer customer) throws SQLException{
				CustomerDao customerDao=new CustomerDao();
				return CustomerDao.checkValidCustomer(customer);
				
			}
			
			public Customer getCustomerDetails(String customerID) throws SQLException{ //Customer's final balance
				CustomerDao customerDao=new CustomerDao();
				Customer customer=customerDao.getCustomerDetails(customerID);
				return customer;
				
			}
	
	
		public double CustomerDeposit(double amount){ //deposited amount is returned for admin to verify
			
			
			return 0;
			}
		public double CustomerWithdrawal(double amount){ //Withdrawal amount verified by admin
			return 0;
			
		}
		
		public void ViewTranscationHistory(){
			
		}

		public boolean UpdatePassword(Customer customer, String pass) throws SQLException {
			CustomerDao customerDao=new CustomerDao();
			return customerDao.UpdatePassword(customer,pass);
			
		}
}
