package com.Project.Client;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.Project.BussinessLogic.AdminService;
import com.Project.BussinessLogic.CustomerService;
import com.Project.BussinessLogic.ManagerService;
import com.Project.BussinessLogic.UserService;
import com.Project.Dao.AdminDao;
import com.Project.bean.Account;
import com.Project.bean.Role;
import com.Project.bean.Transaction;
import com.Project.bean.User;
import com.fun.Encryption;

public class Client {

	private static Scanner s;

	public static void main(String[] args) throws SQLException, ParseException, IOException {
		boolean quit = false;
		double balance;
		int i = 3;
		int temp = 3;
		String userId ="";
		String password = "";
		boolean p = false;
		String fromDate = "";
		String toDate = "";
	
		
		
		UserService uService = new UserService();
		User user = new User();
		ManagerService mService = new ManagerService();
		
		System.out.println("\t--------------------Welcome to Online Banking System!!!----------------------");
		System.out.println("Please enter your credentials:");

		do {
			Scanner sc = new Scanner(System.in);
			s = new Scanner(System.in);
			System.out.print("User id:");
			int	userid = sc.nextInt();
			System.out.print("Password:");
			password = s.nextLine();
			userId = Integer.toString(userid);
			temp--;
			user.setUserId(userId);
			user.setPassword(password);
			p = false;
			p = uService.checkValidUser(user);
			if (p == false)
				System.out.println("You have " + (temp)
						+ " attempts left,Please enter the correct choice:");
			else
				break;
		} while (temp > 0);

		if (temp == 0) {
			System.out.println("You have exceeded your attempts");
			System.exit(0);
		}
		Role role = new Role();
		if (p == true) {
			role = uService.RetrieveRole(userId);
			System.out.println("Welcome " + role.getRoleName());

		} else {
			System.out.println("Please give correct credentials!!!");
			System.out.println("Incorrect userid/password");
		}
		Scanner sc3= new Scanner(System.in);
		System.out.println("Do you want to change your password:");
		System.out.println("Press y to change");
		if (sc3.nextLine().equalsIgnoreCase("y")) {
			String pass = sc3.nextLine();
			String password1 = Encryption.encrypt(pass);
			boolean a = true;
			if (a == uService.UpdatePassword(user, password1)) {
				System.out.println("Your Password is Changed.");

			}
		}
		String roletype = role.getRoleName();
		if (roletype.equals("Customer")) {
			int choice = 0;
			temp = 3;
			do {
				do {

					try {

						System.out
								.println("Press 1 for Viewing Balance, Press 2 for Withdrawal, "

										+ "Press 3 for Deposit, Press 4 for viewing Transaction HIstory,"
										+ "Press 5 for exit");
						temp--;
						choice = s.nextInt();

					} catch (InputMismatchException e) {
						i--;
						System.out
								.println("You have "
										+ (i)+ " attempsts left,Please enter the correct choice:");
						// throw e;
					}
					if (i > temp) {
						break;
					}
				} while (i > 0);

				if (i == 0) {
					System.out.println("You have exceeded your attempts");
					System.exit(0);
				}

				CustomerService cusService = new CustomerService();
				switch (choice) {
				case 1:
					System.out.println("Your Balance is "
							+ cusService.getCustomerBalance(userId));
					break;
				case 2:
					
					double cusBal = cusService.getCustomerBalance(userId);
					if (cusBal <= 1000)
						System.out.println("The minimum balance of Rs.1000 is required."
								+ "You cannot withdraw given amount.");
					System.out.println("Enter the amount to be withdrawn :");
					double amount1 = s.nextDouble();
					 if (amount1 <= cusBal - 1000)
						balance = cusService
								.CustomerWithdrawal(userId, amount1);
					else
						System.out.println("Insufficient Balance!!!");

					break;
				case 3:
					System.out.println("Enter the amount to be deposited:");
					double amount = s.nextDouble();
					balance = cusService.CustomerDeposit(userId, amount);
					break;
				case 4:
					Scanner sc = new Scanner(System.in);
					System.out
							.println("Enter the dates to view the Transaction Statement");
					System.out.println("Enter the FromDate as yyyy-mm-dd:");
					fromDate = sc.nextLine();
					Date fromDate1 = Date.valueOf(fromDate);
					System.out.println("Enter the ToDate as yyyy-mm-dd:");
					toDate = sc.nextLine();
					Date toDate1 = Date.valueOf(toDate);
					if (fromDate.compareTo (toDate) > 0) {
						System.out.println("Please enter valid dates.");
						break;
					}

					List<Transaction> transaction = new ArrayList<Transaction>();
					transaction = cusService.ViewTranscationHistoryBetweenDates(userId,fromDate1, toDate1);
					for (Transaction t : transaction) {
						System.out.println(t);
					}
					System.out.println("Do you want to view the transaction History in a file?");
					System.out.println("Press y to view:");
					String y = sc.nextLine();
					if(y.equalsIgnoreCase("y"))
						try {
							cusService.ViewTranscationHistoryInFile(transaction,Integer.parseInt(userId));
							TimeUnit.MINUTES.sleep(1);
					       // FileWriter fwOb = new FileWriter(, false); 
					        FileWriter pwOb = new FileWriter("TransactionHistory.csv");
					        pwOb.write("");
					        pwOb.close();
					       // fwOb.close();

						
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					break;

				case 5:
					quit = true;

				default:
					System.out.println("Please enter correct option");
					}
			
			} while (!quit);

		} else if (roletype.equals("Admin")) {
			
			do{
				Scanner sc1=new Scanner(System.in);
				quit = false;
				Random rand=new Random();
				AdminDao adDao=new AdminDao();
				AdminService adServ = new AdminService();
				Account account = new Account(Integer.toString(rand.nextInt(100000)),1000.0);
				Role role1 = new Role("1","Customer");
				
				System.out.println("Press 1 to create Account, Press 2 for Deletion of Account,"
						+ "Press 3 for quit");
				int choice = s.nextInt();
				switch(choice){
				case 1:
					System.out.println("Enter name:");
					String name=sc1.nextLine();
					System.out.println("Enter address:");
					String address=sc1.nextLine();
					System.out.println("Enter mobileno:");
					String mobileno =sc1.nextLine();
					System.out.println("Enter occupation:");
					String occupation=sc1.nextLine();
					System.out.println("Enter dob:");
					String dob1=sc1.nextLine();
				//	SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd");
					Date dob =Date.valueOf(dob1);
					System.out.println("Enter password");
					String password12=sc1.nextLine();
					User user1=new User("",name,address,mobileno,occupation,dob,password12);
					p=adServ.AddCustomer(user1,account,role1);		
					System.out.println("New customer created.");
					break;
				case 2:
					System.out.println("Enter the userId to be Deleted:");
					int userId1=s.nextInt();
					p=adServ.DeleteCustomer(userId1);
					if(p==true)
						System.out.println("Customer "+userId1+" deleted successfully");
					else
						System.out.println("Userid is invaild, can't delete");
					break;
				case 3:
					quit = true;
					break;
				default:
					System.out.println("Please enter correct option");
				}
			}while(!quit);
				
	

		} else if (roletype.equals("Manager")) {
			do{
			quit = false;	
			
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Press 1 view customer details , press 2 for account details,"
					+ "press 3 for quit");
			int choice =s.nextInt();
			switch(choice){
			case 1:
				System.out.println("Please enter the userid to view user details:");
				int userID = sc2.nextInt();
				user =	mService.getCustomerDetails(userID);
				System.out.println(user);
				break;
			case 2:
				System.out.println("Please enter the pattern to view thw Account details:");
				String substring = sc2.nextLine();
				user = mService.getCustomerAccountDetailsStringPattern(substring);
			case 3:
				quit = true;
				break;
			}
		}while(!quit);
		
			s.close();
		}
	}
}
