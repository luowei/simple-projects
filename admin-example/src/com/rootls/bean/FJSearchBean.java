package com.rootls.bean;

public class FJSearchBean {
	String mtype;
	String startDate;
	String endDate;
	String htType;
	String[] type;
	String conditionName;
	String conditionFlag;
	String conditionValue;
	String trackName;
	String order;
	String page;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getHtType() {
		return htType;
	}
	public void setHtType(String htType) {
		this.htType = htType;
	}
	public String[] getType() {
		return type;
	}
	public void setType(String[] type) {
		this.type = type;
	}
	public String getConditionName() {
		return conditionName;
	}
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	public String getConditionFlag() {
		return conditionFlag;
	}
	public void setConditionFlag(String conditionFlag) {
		this.conditionFlag = conditionFlag;
	}
	public String getConditionValue() {
		return conditionValue;
	}
	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}
	public String getTrackName() {
		return trackName;
	}
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public FJSearchBean(String startDate, String endDate, String htType,
			String[] type, String conditionName, String conditionFlag,
			String conditionValue, String trackName, String order, String page) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.htType = htType;
		this.type = type;
		this.conditionName = conditionName;
		this.conditionFlag = conditionFlag;
		this.conditionValue = conditionValue;
		this.trackName = trackName;
		this.order = order;
		this.page = page;
	}
	public FJSearchBean() {
		super();
	}
	public FJSearchBean(String mtype, String startDate, String endDate,
			String htType, String[] type, String conditionName,
			String conditionFlag, String conditionValue, String trackName,
			String order, String page) {
		super();
		this.mtype = mtype;
		this.startDate = startDate;
		this.endDate = endDate;
		this.htType = htType;
		this.type = type;
		this.conditionName = conditionName;
		this.conditionFlag = conditionFlag;
		this.conditionValue = conditionValue;
		this.trackName = trackName;
		this.order = order;
		this.page = page;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

}
