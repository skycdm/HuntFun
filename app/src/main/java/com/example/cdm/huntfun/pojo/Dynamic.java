package com.example.cdm.huntfun.pojo;

import com.example.cdm.huntfun.pojo.UserInfo;

import java.sql.Timestamp;
import java.util.Date;

public class Dynamic {
	private int dynamicId;
	private String content;
	private String pic;
	private Timestamp publishTime;
	private UserInfo user;
	public boolean isShowAll=true;


	public Dynamic(int dynamicId,String content, String pic,
				   Timestamp publishTime, UserInfo user) {
		super();
		this.dynamicId = dynamicId;

		this.content = content;
		this.pic = pic;
		this.publishTime = publishTime;
		this.user = user;
	}
	public int getDynamicId() {
		return dynamicId;
	}
	public void setDynamicId(int dynamicId) {
		this.dynamicId = dynamicId;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Dynamic{" +
				"content='" + content + '\'' +
				", dynamicId='" + dynamicId + '\'' +
				", pic='" + pic + '\'' +
				", publishTime=" + publishTime +
				", user=" + user +
				'}';
	}
}
