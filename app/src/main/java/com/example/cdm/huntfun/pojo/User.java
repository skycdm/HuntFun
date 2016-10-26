package com.example.cdm.huntfun.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
	private Integer userId;
	private String userSex;
	private String userName;
	private String userRealName;
	private String userPwd;
	private String phone;
	private String userEmail;
	private String userIdcard;
	private String userIntroduce;
	private String imageUrl;;
	
	public User(){}
	public User(Integer userId, String userSex,String userRealName,String userPwd, String phone,
			String userEmail, String userIdcard, String userIntroduce,
			String imageUrl,String userName) {
		super();
		this.userId = userId;
		this.userSex=userSex;
		this.userRealName = userRealName;
		this.userPwd=userPwd;
		this.phone = phone;
		this.userEmail = userEmail;
		this.userIdcard = userIdcard;
		this.userIntroduce = userIntroduce;
		this.imageUrl = imageUrl;
		this.userName=userName;
	}
	
	
	public User(Integer userId) {
		this.userId = userId;
	}

	public User(Integer userId, String userName, String userRealName,
				String userPwd, String phone, String imageUrl, String userSex) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userRealName = userRealName;
		this.userPwd = userPwd;
		this.phone = phone;
		this.imageUrl = imageUrl;
		this.userSex = userSex;
	}
	
		
	public User(String userName, String userRealName, String userPwd,
				String phone, String imageUrl, String userSex) {
		super();
		this.userName = userName;
		this.userRealName = userRealName;
		this.userPwd = userPwd;
		this.phone = phone;
		this.imageUrl = imageUrl;
		this.userSex = userSex;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserIdcard() {
		return userIdcard;
	}
	public void setUserIdcard(String userIdcard) {
		this.userIdcard = userIdcard;
	}
	public String getUserIntroduce() {
		return userIntroduce;
	}
	public void setUserIntroduce(String userIntroduce) {
		this.userIntroduce = userIntroduce;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(this.userId);
		dest.writeString(this.userSex);
		dest.writeString(this.userName);
		dest.writeString(this.userRealName);
		dest.writeString(this.userPwd);
		dest.writeString(this.phone);
		dest.writeString(this.userEmail);
		dest.writeString(this.userIdcard);
		dest.writeString(this.userIntroduce);
		dest.writeString(this.imageUrl);
	}

	protected User(Parcel in) {
		this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
		this.userSex = in.readString();
		this.userName = in.readString();
		this.userRealName = in.readString();
		this.userPwd = in.readString();
		this.phone = in.readString();
		this.userEmail = in.readString();
		this.userIdcard = in.readString();
		this.userIntroduce = in.readString();
		this.imageUrl = in.readString();
	}

	public static final Creator<User> CREATOR = new Creator<User>() {
		@Override
		public User createFromParcel(Parcel source) {
			return new User(source);
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};
}
