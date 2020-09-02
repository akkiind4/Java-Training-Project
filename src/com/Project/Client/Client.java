package com.Project.Client;

import java.sql.SQLException;
import java.util.Scanner;

import com.Project.BussinessLogic.CustomerService;
import com.Project.bean.Customer;

public class Client {

	public static void main(String[] args) throws SQLException {
			CustomerService cusServ= new CustomerService();
			Customer customer = new Customer();
			Customer customer1 = new Customer();
			System.out.println("Welcome to Online Banking System.");
			System.out.println("Press 1 if you are a customer, 2 if you are a manager, 3 if you are an admin");
			Scanner sc = new Scanner(System.in);
			Scanner sc2 = new Scanner(System.in);
			int choice =sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Welcome! Please enter your customer id.");
				String customerId = sc2.nextLine();
				System.out.print("Please enter your password : ");
				String password = sc2.nextLine();
				customer.setCustomerID(customerId);
				customer.setCustomnerPwd(password);
				String Name= customer.getName();
				customer.setName(Name);
				String Accno=customer.getAccountNumber();
				customer.setAccountNumber(Accno);
				String Balance=customer.getCustomerBalance();
				customer.setCustomerBalance(Balance);
				boolean p=false;
				try {
					p = cusServ.checkValidCustomer(customer);
				} catch (SQLException e) {
	
				e.printStackTrace();
			}
				
				if(p==cusServ.checkValidCustomer(customer))
				{ 
					customer1=cusServ.getCustomerDetails(customerId);
					System.out.println("Welcome "+ customerId);
					System.out.println("Name: "+customer1.getName()+", Accno: "+customer1.getAccountNumber()+", Balance: "+customer1.getCustomerBalance());
				}
				else {
					System.out.println("Incorrect customerid/password");
				}
				break;
			default:
				System.out.println("Bad choice, exiting.");
				break;
			}
			System.out.println("Do you want to change your password:");
			String pass=sc2.nextLine();
			boolean a=true;
			if(a==cusServ.UpdatePassword(customer1,pass))
			{ 
				System.out.println("Your Password is Changed.");
			}
		}
}
