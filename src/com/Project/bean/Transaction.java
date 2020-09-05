package com.Project.bean;

import java.util.Date;

public class Transaction {
		private double credit;
		private double debit;
		private Date transactionTime;
		private double avlBalance;
		public Transaction(){
		}
		public Transaction(double credit, double debit, Date transactionTime,
				double avlBalance) {
			super();
			this.credit = credit;
			this.debit = debit;
			this.transactionTime = transactionTime;
			this.avlBalance = avlBalance;
		}
		public double getCredit() {
			return credit;
		}
		public void setCredit(double credit) {
			this.credit = credit;
		}
		public double getDebit() {
			return debit;
		}
		public void setDebit(double debit) {
			this.debit = debit;
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
		@Override
		public String toString() {
			return "Transaction [credit=" + credit + ", debit=" + debit
					+ ", transactionTime=" + transactionTime + ", avlBalance="
					+ avlBalance + "]";
		}
}