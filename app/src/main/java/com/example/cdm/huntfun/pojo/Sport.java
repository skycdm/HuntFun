package com.example.cdm.huntfun.pojo;

public class Sport {
	private int id;
	private String uname;
	private String sportName;
	private String timeEnd;
	private String timeBegin;
	private String place;
	private String img;
	private String num;
	private String theme;
	private String money;
	private String type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getSportName() {
		return sportName;
	}
	public void setSportName(String sportName) {
		this.sportName = sportName;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public String getTimeBegin() {
		return timeBegin;
	}
	public void setTimeBegin(String timeBegin) {
		this.timeBegin = timeBegin;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Sport(int id, String uname, String sportName, String timeEnd,
				 String timeBegin, String place, String img, String num,
				 String theme, String money, String type) {
		super();
		this.id = id;
		this.uname = uname;
		this.sportName = sportName;
		this.timeEnd = timeEnd;
		this.timeBegin = timeBegin;
		this.place = place;
		this.img = img;
		this.num = num;
		this.theme = theme;
		this.money = money;
		this.type = type;
	}

	public Sport(int id, String uname, String sportName, String timeEnd, String timeBegin, String place, String num, String theme, String money, String type) {
		this.id = id;
		this.uname = uname;
		this.sportName = sportName;
		this.timeEnd = timeEnd;
		this.timeBegin = timeBegin;
		this.place = place;
		this.num = num;
		this.theme = theme;
		this.money = money;
		this.type = type;
	}
}
