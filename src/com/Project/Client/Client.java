package com.Project.Client;

import java.sql.SQLException;
import java.util.Scanner;

import com.Project.BussinessLogic.CustomerService;
import com.Project.bean.Customer;

public class Client {

	public static void main(String[] args) {
		CustomerService cusServ= new CustomerService();
		Customer customer = new Customer();
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
			boolean p=false;
			try {
				p = cusServ.checkValidCustomer(customer);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(p==true)
			{
				System.out.println("Welcome "+ customer.getName());
				System.out.println("your account balance is : "+ cusServ.ViewBalance());
			}
			else {
				System.out.println("Bad customerid/password");
			}
			break;
		default:
			System.out.println("Bad choice, exiting.");
			break;
		}
	}

}
