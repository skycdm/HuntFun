package com.example.cdm.huntfun.pojo;

import java.sql.Timestamp;

public class Good {
	private int zanId;
	private UserInfo userinfo;
	private int  dynamicId;
	private Timestamp zanTime;
	public  int getZanId() {
		return zanId;
	}
	public void setZanId( int zanId) {
		this.zanId = zanId;
	}
	public UserInfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
	public int getDynamicId() {
		return dynamicId;
	}
	public void setDynamicId(int dynamicId) {
		this.dynamicId = dynamicId;
	}
	public Timestamp getZanTime() {
		return zanTime;
	}
	public void setZanTime(Timestamp zanTime) {
		this.zanTime = zanTime;
	}
	public Good(UserInfo userinfo, int dynamicId,
				Timestamp zanTime) {
		super();

		this.userinfo = userinfo;
		this.dynamicId = dynamicId;
		this.zanTime = zanTime;
	}
	

}
