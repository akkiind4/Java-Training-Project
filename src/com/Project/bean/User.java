package com.Project.bean;

public class User {
			private String name;
			private String address;
			private String mobileNumber;
			private String emailId;
			
			public User(){
			}

			public User(String name, String address, String mobileNumber) {
				super();
				this.name = name;
				this.address = address;
				this.mobileNumber = mobileNumber;
				//this.emailId=emailId;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getAddress() {
				return address;
			}

			public void setAddress(String address) {
				this.address = address;
			}

			public String getMobilenumber() {
				return mobileNumber;
			}

			public void setMobilenumber(String mobilenumber) {
				this.mobileNumber = mobilenumber;
			}
			

			public String getEmailId() {
				return emailId;
			}

			public void setEmailId(String emailId) {
				this.emailId = emailId;
			}

//			public String toString() {
//				return "Person [name=" + name + ", address=" + address
//						+ ", mobilenumber=" + mobileNumber + ",emailId="+emailId+"]";
//			}
			
}
