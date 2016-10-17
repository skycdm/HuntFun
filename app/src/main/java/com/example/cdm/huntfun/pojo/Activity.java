package com.example.cdm.huntfun.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;
import java.util.Date;

public class Activity implements Parcelable {
	private int activityId;
	private String activityLabel;
	private int userId;
	private String activityTheme;
	private String activityAddress;
	private Timestamp activityEndtimeSign;
	private Timestamp activityBegintime;
	private String activityImgurl;
	private String activityDesc;
	private String activityCare;
	private double activityCost;
	private int activityPeopleNumber;
	private int joinerId;
	private int is_classify;
	public Activity(int activityId, String activityLabel, int userId,String activityAddress,
			String activityTheme, Timestamp activityEndtimeSign,
			Timestamp activityBegintime, String activityImgurl, String activityDesc,
			String activityCare, double activityCost, int activityPeopleNumber,
			int joinerId, int isClassify) {
		super();
		this.activityId = activityId;
		this.activityLabel = activityLabel;
		this.userId = userId;
		this.activityTheme = activityTheme;
		this.activityAddress=activityAddress;
		this.activityEndtimeSign = activityEndtimeSign;
		this.activityBegintime = activityBegintime;
		this.activityImgurl = activityImgurl;
		this.activityDesc = activityDesc;
		this.activityCare = activityCare;
		this.activityCost = activityCost;
		this.activityPeopleNumber = activityPeopleNumber;
		this.joinerId = joinerId;
		is_classify = isClassify;
	}
	
	public String getActivityAddress() {
		return activityAddress;
	}

	public void setActivityAddress(String activityAddress) {
		this.activityAddress = activityAddress;
	}

	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getActivityLabel() {
		return activityLabel;
	}
	public void setActivityLabel(String activityLabel) {
		this.activityLabel = activityLabel;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getActivityTheme() {
		return activityTheme;
	}
	public void setActivityTheme(String activityTheme) {
		this.activityTheme = activityTheme;
	}
	public Date getActivityEndtimeSign() {
		return activityEndtimeSign;
	}
	public void setActivityEndtimeSign(Timestamp activityEndtimeSign) {
		this.activityEndtimeSign = activityEndtimeSign;
	}
	public Date getActivityBegintime() {
		return activityBegintime;
	}
	public void setActivityBegintime(Timestamp activityBegintime) {
		this.activityBegintime = activityBegintime;
	}
	public String getActivityImgurl() {
		return activityImgurl;
	}
	public void setActivityImgurl(String activityImgurl) {
		this.activityImgurl = activityImgurl;
	}
	public String getActivityDesc() {
		return activityDesc;
	}
	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}
	public String getActivityCare() {
		return activityCare;
	}
	public void setActivityCare(String activityCare) {
		this.activityCare = activityCare;
	}
	public double getActivityCost() {
		return activityCost;
	}
	public void setActivityCost(double activityCost) {
		this.activityCost = activityCost;
	}
	public int getActivityPeopleNumber() {
		return activityPeopleNumber;
	}
	public void setActivityPeopleNumber(int activityPeopleNumber) {
		this.activityPeopleNumber = activityPeopleNumber;
	}
	public int getJoinerId() {
		return joinerId;
	}
	public void setJoinerId(int joinerId) {
		this.joinerId = joinerId;
	}
	public int getIs_classify() {
		return is_classify;
	}
	public void setIs_classify(int isClassify) {
		is_classify = isClassify;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.activityId);
		dest.writeString(this.activityLabel);
		dest.writeInt(this.userId);
		dest.writeString(this.activityTheme);
		dest.writeString(this.activityAddress);
		dest.writeSerializable(this.activityEndtimeSign);
		dest.writeSerializable(this.activityBegintime);
		dest.writeString(this.activityImgurl);
		dest.writeString(this.activityDesc);
		dest.writeString(this.activityCare);
		dest.writeDouble(this.activityCost);
		dest.writeInt(this.activityPeopleNumber);
		dest.writeInt(this.joinerId);
		dest.writeInt(this.is_classify);
	}

	protected Activity(Parcel in) {
		this.activityId = in.readInt();
		this.activityLabel = in.readString();
		this.userId = in.readInt();
		this.activityTheme = in.readString();
		this.activityAddress = in.readString();
		this.activityEndtimeSign = (Timestamp) in.readSerializable();
		this.activityBegintime = (Timestamp) in.readSerializable();
		this.activityImgurl = in.readString();
		this.activityDesc = in.readString();
		this.activityCare = in.readString();
		this.activityCost = in.readDouble();
		this.activityPeopleNumber = in.readInt();
		this.joinerId = in.readInt();
		this.is_classify = in.readInt();
	}

	public static final Parcelable.Creator<Activity> CREATOR = new Parcelable.Creator<Activity>() {
		@Override
		public Activity createFromParcel(Parcel source) {
			return new Activity(source);
		}

		@Override
		public Activity[] newArray(int size) {
			return new Activity[size];
		}
	};
}
