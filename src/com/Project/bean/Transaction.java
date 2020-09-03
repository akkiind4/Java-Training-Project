package com.Project.bean;

import java.util.Date;

public class Transaction {
		private double amount;
		private Date transactionTime;
		private double avlBalance;
		public Transaction(){
		}
		public Transaction(double amount, Date transactionTime,
				double avlBalance) {
			super();
			this.amount = amount;
			this.transactionTime = transactionTime;
			this.avlBalance = avlBalance;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public Date getTransactionTime() {
			return transactionTime;
		}
		public void setTransactionTime(Date transactionTime) {
			this.transactionTime = transactionTime;
		}
		public double getAvlBalance() {
			return avlBalance;
		}
		public void setAvlBalance(double avlBalance) {
			this.avlBalance = avlBalance;
		}
		public String toString() {
			return "Transaction [amount=" + amount + ", transactionTime="
					+ transactionTime + ", avlBalance=" + avlBalance + "]";
		}
		
}
