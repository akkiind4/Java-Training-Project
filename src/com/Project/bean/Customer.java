package com.Project.bean;

public class Customer extends User {
			private String customerID;
			private String accountNumber;
			private double balance;
			private String customnerPwd;
			public Customer(){
			}
			public Customer(String customerID, String accountNumber,
					double balance, String customnerPwd) {
				super();
				this.customerID = customerID;
				this.accountNumber = accountNumber;
				this.balance = balance;
				this.customnerPwd = customnerPwd;
			}
			public String getCustomerID() {
				return customerID;
			}
			public void setCustomerID(String customerID) {
				this.customerID = customerID;
			}
			public String getAccountNumber() {
				return accountNumber;
			}
			public void setAccountNumber(String accountNumber) {
				this.accountNumber = accountNumber;
			}
			public double getBalance() {
				return balance;
			}
			public void setBalance(double balance) {
				this.balance = balance;
			}
			public String getCustomnerPwd() {
				return customnerPwd;
			}
			public void setCustomnerPwd(String customnerPwd) {
				this.customnerPwd = customnerPwd;
			}
			public String toString() {
				return "Customer [customerID=" + customerID
						+ ", accountNumber=" + accountNumber + ", balance="
						+ balance + ", customnerPwd=" + customnerPwd + "]";
			}
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result
						+ ((customerID == null) ? 0 : customerID.hashCode());
				return result;
			}
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Customer other = (Customer) obj;
				if (customerID == null) {
					if (other.customerID != null)
						return false;
				} else if (!customerID.equals(other.customerID))
					return false;
				return true;
			}
			
			
}
