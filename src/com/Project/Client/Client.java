package com.Project.Client;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.Project.BussinessLogic.CustomerService;
import com.Project.BussinessLogic.UserService;
import com.Project.bean.Role;
import com.Project.bean.User;

public class Client {

	private static Scanner sc;
	
	public static void main(String[] args) throws SQLException {
			boolean quit=false;
			double balance; int i=3;
			UserService uService = new UserService();
			CustomerService cusService = new CustomerService();
			Role role = new Role();
			User user = new User();
			System.out.println("Welcome to Online Banking System!!!");
			System.out.println("Please enter your credentials:");
			 sc = new Scanner(System.in);
				System.out.println("Please enter your customer id.");
				String userId = sc.nextLine();
				System.out.print("Please enter your password : ");
				String password = sc.nextLine();
				user.setUserId(userId);
				user.setPassword(password);
				boolean p=false;
				try {
					p = uService.checkValidUser(user);
				} catch (SQLException e) {
	
				e.printStackTrace();
			}
				
				if(p==true)
				{ 
					role=uService.RetrieveRole(userId);
					System.out.println("Welcome "+ role.getRoleName());
					
				}
				else {
					System.out.println("Please give correct credentials!!!");
					System.out.println("Incorrect customerid/password");
				}
				String roletype=role.getRoleName();
				if(roletype.equals("Customer")){
					do{
						
						System.out.println("Press 1 for Viewing Balance, Press 2 for Withdrawal, "
					
				+ "Press 3 for Deposit, Press 4 for viewing Transaction HIstory,Press 5 for exit");
						
					int choice =0;
					do{
					try{
						 choice=sc.nextInt();
					}catch(InputMismatchException e){
						System.out.println("You have "+(i-1)+" attempsts left,Please enter the correct choice:");
						//throw e;
					} i--;
						}while(i>0);
					if(i==0){
						System.out.println("You have exceeded your attempts");
						System.exit(0);
					}
					switch (choice){
					case 1:
					System.out.println("Your Balance is "+cusService.getCustomerBalance(userId));
					break;
					case 3:
						System.out.println("Enter the amount to be deposited:");
						double amount = sc.nextDouble();
						balance=cusService.CustomerDeposit(userId,amount);
					break;
					case 5:
						quit=true;
					
					}
					}while(!quit);
				}
					else if(roletype.equals("Admin")){
					System.out.println("Press 1 to create Account, Press 2 for Deletion of Account");
				}else if(roletype.equals("Manager")){
					System.out.println("Press 1 view customer details , press 2 for account details");
				}
				
				Scanner sc1= new Scanner(System.in);
			System.out.println("Do you want to change your password:");
			System.out.println("Press y to change");
			if(sc1.nextLine().equalsIgnoreCase("y")){
			String pass=sc1.nextLine();
			boolean a=true;
				if(a==uService.UpdatePassword(user,pass))
			{ 
				System.out.println("Your Password is Changed.");
				
			}
			}
		}
}
