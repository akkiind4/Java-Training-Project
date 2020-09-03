package com.Project.bean;

public class Account {
	private String accno;
	private double balance;
	public Account(){
}
	public Account(String accno, double balance) {
		super();
		this.accno = accno;
		this.balance = balance;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String toString() {
		return "Account [accno=" + accno + ", balance=" + balance + "]";
	}
}
