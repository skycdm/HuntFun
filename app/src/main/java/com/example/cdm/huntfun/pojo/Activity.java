package com.example.cdm.huntfun.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;
import java.util.List;

public class Activity implements Parcelable {
	
	private Integer activityId;
	private String activityLable;
	private String activityTheme;
	private Timestamp activityBeginTime;
	private Timestamp activityEndTime;
	private Timestamp activityEndTimeSign;
	private String activityAddress;
	private String activityDesc;
	private String activityCare;
	private String activityImgurl;
	private Double activityCost;
	private Integer activityMaxPeopleNumber;
	private String activityTrip;
	private String gather;
	private String phone;
	
	private User user;
	private List<User> joiner;
	private Boolean isLiuDian;
	private Boolean isHeavy;
	private Boolean isSpecial;

	public Activity(String activityLable, String activityTheme, Timestamp activityBeginTime, Timestamp activityEndTime, Timestamp activityEndTimeSign, String activityAddress, String activityDesc, String activityCare, String activityImgurl, Double activityCost, Integer activityMaxPeopleNumber, String activityTrip, String gather, String phone, Boolean isLiuDian, User user) {
		this.activityLable = activityLable;
		this.activityTheme = activityTheme;
		this.activityBeginTime = activityBeginTime;
		this.activityEndTime = activityEndTime;
		this.activityEndTimeSign = activityEndTimeSign;
		this.activityAddress = activityAddress;
		this.activityDesc = activityDesc;
		this.activityCare = activityCare;
		this.activityImgurl = activityImgurl;
		this.activityCost = activityCost;
		this.activityMaxPeopleNumber = activityMaxPeopleNumber;
		this.activityTrip = activityTrip;
		this.gather = gather;
		this.phone = phone;
		this.isLiuDian = isLiuDian;
		this.user=user;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getActivityLable() {
		return activityLable;
	}

	public void setActivityLable(String activityLable) {
		this.activityLable = activityLable;
	}

	public String getActivityTheme() {
		return activityTheme;
	}

	public void setActivityTheme(String activityTheme) {
		this.activityTheme = activityTheme;
	}

	public Timestamp getActivityBeginTime() {
		return activityBeginTime;
	}

	public void setActivityBeginTime(Timestamp activityBeginTime) {
		this.activityBeginTime = activityBeginTime;
	}

	public Timestamp getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(Timestamp activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	public Timestamp getActivityEndTimeSign() {
		return activityEndTimeSign;
	}

	public void setActivityEndTimeSign(Timestamp activityEndTimeSign) {
		this.activityEndTimeSign = activityEndTimeSign;
	}

	public String getActivityAddress() {
		return activityAddress;
	}

	public void setActivityAddress(String activityAddress) {
		this.activityAddress = activityAddress;
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

	public String getActivityImgurl() {
		return activityImgurl;
	}

	public void setActivityImgurl(String activityImgurl) {
		this.activityImgurl = activityImgurl;
	}

	public Double getActivityCost() {
		return activityCost;
	}

	public void setActivityCost(Double activityCost) {
		this.activityCost = activityCost;
	}

	public Integer getActivityMaxPeopleNumber() {
		return activityMaxPeopleNumber;
	}

	public void setActivityMaxPeopleNumber(Integer activityMaxPeopleNumber) {
		this.activityMaxPeopleNumber = activityMaxPeopleNumber;
	}

	public String getActivityTrip() {
		return activityTrip;
	}

	public void setActivityTrip(String activityTrip) {
		this.activityTrip = activityTrip;
	}

	public String getGather() {
		return gather;
	}

	public void setGather(String gather) {
		this.gather = gather;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getJoiner() {
		return joiner;
	}

	public void setJoiner(List<User> joiner) {
		this.joiner = joiner;
	}

	public Boolean getLiuDian() {
		return isLiuDian;
	}

	public void setLiuDian(Boolean liuDian) {
		isLiuDian = liuDian;
	}

	public Boolean getHeavy() {
		return isHeavy;
	}

	public void setHeavy(Boolean heavy) {
		isHeavy = heavy;
	}

	public Boolean getSpecial() {
		return isSpecial;
	}

	public void setSpecial(Boolean special) {
		isSpecial = special;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(this.activityId);
		dest.writeString(this.activityLable);
		dest.writeString(this.activityTheme);
		dest.writeSerializable(this.activityBeginTime);
		dest.writeSerializable(this.activityEndTime);
		dest.writeSerializable(this.activityEndTimeSign);
		dest.writeString(this.activityAddress);
		dest.writeString(this.activityDesc);
		dest.writeString(this.activityCare);
		dest.writeString(this.activityImgurl);
		dest.writeValue(this.activityCost);
		dest.writeValue(this.activityMaxPeopleNumber);
		dest.writeString(this.activityTrip);
		dest.writeString(this.gather);
		dest.writeString(this.phone);
		dest.writeParcelable(this.user, flags);
		dest.writeTypedList(this.joiner);
		dest.writeValue(this.isLiuDian);
		dest.writeValue(this.isHeavy);
		dest.writeValue(this.isSpecial);
	}

	protected Activity(Parcel in) {
		this.activityId = (Integer) in.readValue(Integer.class.getClassLoader());
		this.activityLable = in.readString();
		this.activityTheme = in.readString();
		this.activityBeginTime = (Timestamp) in.readSerializable();
		this.activityEndTime = (Timestamp) in.readSerializable();
		this.activityEndTimeSign = (Timestamp) in.readSerializable();
		this.activityAddress = in.readString();
		this.activityDesc = in.readString();
		this.activityCare = in.readString();
		this.activityImgurl = in.readString();
		this.activityCost = (Double) in.readValue(Double.class.getClassLoader());
		this.activityMaxPeopleNumber = (Integer) in.readValue(Integer.class.getClassLoader());
		this.activityTrip = in.readString();
		this.gather = in.readString();
		this.phone = in.readString();
		this.user = in.readParcelable(User.class.getClassLoader());
		this.joiner = in.createTypedArrayList(User.CREATOR);
		this.isLiuDian = (Boolean) in.readValue(Boolean.class.getClassLoader());
		this.isHeavy = (Boolean) in.readValue(Boolean.class.getClassLoader());
		this.isSpecial = (Boolean) in.readValue(Boolean.class.getClassLoader());
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
