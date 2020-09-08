package com.Project.bean;
import java.util.Date;


public class User {
			private String  userId;
			private String	name;
			private String address;
			private String mobileNo;
			private String occupation;
			private  Date dob;
			private String password;
			
			public User(){
			}

			public User(String userId, String name, String address,
					String mobileNo, String occupation, Date date,
					String password) {
				super();
				this.userId = userId;
				this.name = name;
				this.address = address;
				this.mobileNo = mobileNo;
				this.occupation = occupation;
				this.dob = date;
				this.password = password;
			}

			public String getUserId() {
				return userId;
			}

			public void setUserId(String userId) {
				this.userId = userId;
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

			public String getMobileNo() {
				return mobileNo;
			}

			public void setMobileNo(String mobileNo) {
				this.mobileNo = mobileNo;
			}

			public String getOccupation() {
				return occupation;
			}

			public void setOccupation(String occupation) {
				this.occupation = occupation;
			}

			public Date getDob() {
				return dob;
			}

			public void setDob(Date dob) {
				this.dob = dob;
			}

			public String getPassword() {
				return password;
			}

			public void setPassword(String password) {
				this.password = password;
			}

			public String toString() {
				return "User [userId=" + userId + ", name=" + name
						+ ", address=" + address + ", mobileNo=" + mobileNo
						+ ", occupation=" + occupation + ", dob=" + dob
						+ "]";
			}

}