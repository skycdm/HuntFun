package com.example.cdm.huntfun.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;

/**
 * Created by cdm on 2016/10/21.
 */
public class Evaluate implements Parcelable {
    private Integer evaluateId;
    private Activity activity;
    private User user;
    private Timestamp creatTime;
    private String evaluateContent;

    public Evaluate(Integer evaluateId, Activity activity, User user, Timestamp creatTime, String evaluateContent) {
        this.evaluateId = evaluateId;
        this.activity = activity;
        this.user = user;
        this.creatTime = creatTime;
        this.evaluateContent = evaluateContent;
    }

    public Evaluate(Activity activity, User user, Timestamp creatTime, String evaluateContent) {
        this.activity = activity;
        this.user = user;
        this.creatTime = creatTime;
        this.evaluateContent = evaluateContent;
    }

    public Integer getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Integer evaluateId) {
        this.evaluateId = evaluateId;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.evaluateId);
        dest.writeParcelable(this.activity, flags);
        dest.writeParcelable(this.user, flags);
        dest.writeSerializable(this.creatTime);
        dest.writeString(this.evaluateContent);
    }

    protected Evaluate(Parcel in) {
        this.evaluateId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.activity = in.readParcelable(Activity.class.getClassLoader());
        this.user = in.readParcelable(User.class.getClassLoader());
        this.creatTime = (Timestamp) in.readSerializable();
        this.evaluateContent = in.readString();
    }

    public static final Creator<Evaluate> CREATOR = new Creator<Evaluate>() {
        @Override
        public Evaluate createFromParcel(Parcel source) {
            return new Evaluate(source);
        }

        @Override
        public Evaluate[] newArray(int size) {
            return new Evaluate[size];
        }
    };
}
