package com.example.cdm.huntfun.pojo;

public class Myself {
	private int id;
	 private Integer judgeNum;
	 private Integer fansNum;
	 private Integer followNum;
	 private String backgroundId;
	 private String headId;
	 private Integer likeNum;
	 private String psw;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getJudgeNum() {
		return judgeNum;
	}
	public void setJudgeNum(Integer judgeNum) {
		this.judgeNum = judgeNum;
	}
	public Integer getFansNum() {
		return fansNum;
	}
	public void setFansNum(Integer fansNum) {
		this.fansNum = fansNum;
	}
	public Integer getFollowNum() {
		return followNum;
	}
	public void setFollowNum(Integer followNum) {
		this.followNum = followNum;
	}
	public String getBackgroundId() {
		return backgroundId;
	}
	public void setBackgroundId(String backgroundId) {
		this.backgroundId = backgroundId;
	}
	public String getHeadId() {
		return headId;
	}
	public void setHeadId(String headId) {
		this.headId = headId;
	}
	public Integer getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public Myself(int id, Integer judgeNum, Integer fansNum, Integer followNum,
			String backgroundId, String headId, Integer likeNum, String psw) {
		super();
		this.id = id;
		this.judgeNum = judgeNum;
		this.fansNum = fansNum;
		this.followNum = followNum;
		this.backgroundId = backgroundId;
		this.headId = headId;
		this.likeNum = likeNum;
		this.psw = psw;
	}

}
