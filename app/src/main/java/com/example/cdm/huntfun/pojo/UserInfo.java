package com.example.cdm.huntfun.pojo;

public class UserInfo {
	private Integer userId;
	private String userName;
	private Boolean sex;
	private String photo;
	private String qianming;
	private String psd;

	public UserInfo(Integer userId){
		this.userId=userId;
	}

	public UserInfo(Integer userId,String userName){
		this.userId=userId;
		this.userName=userName;
	}
	public UserInfo(Integer userId, String userName, Boolean sex,
			String photo, String qianming, String psd) {
		this.userId = userId;
		this.userName = userName;
		this.sex = sex;
		this.photo = photo;
		this.qianming = qianming;
		this.psd = psd;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getQianming() {
		return qianming;
	}
	public void setQianming(String qianming) {
		this.qianming = qianming;
	}
	public String getPsd() {
		return psd;
	}
	public void setPsd(String psd) {
		this.psd = psd;
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName
				+ ", sex=" + sex + ", photo=" + photo + ", qianming="
				+ qianming + ", psd=" + psd + "]";
	}
}
