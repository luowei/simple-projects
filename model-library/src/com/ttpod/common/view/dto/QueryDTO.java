package com.ttpod.common.view.dto;
/**
 * @className:QueryDTO.java
 * @classDescription:查询dto
 * @author:xiayingjie
 * @createTime:2011-5-23
 */

public class QueryDTO {
	private String key;
	private int pageNo;
	private String order;
	private String startDate;
	private String endDate;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
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
	
}
