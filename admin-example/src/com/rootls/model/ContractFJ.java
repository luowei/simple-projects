package com.rootls.model;

import java.io.Serializable;
import java.util.Date;

import com.rootls.bean.UserCache;

public class ContractFJ implements Serializable{
	private String userName;
	private String companyName;
	private Integer id;
	//公司id
	private Integer gid;
	//合同id
	private Integer sid;
	//合同编号
	private String htBianhao;
	//标题
	private String title;
	//文件名
	private String fileName;
	//真实存放路径
	private String realPath;
	//附件类型
	private Integer type;
	//合同类型
	private Integer htType;
	//跟踪人ID
	private Integer trackId;
	//跟踪人
	private String trackName;
	//操作人ID
	private Integer optionId;
	//操作人
	private String optionName;
	//合同开始时间
	private Date startDate;
	//合同结束时间
	private Date endDate;
	public ContractFJ() {
		super();
	}
	public ContractFJ(Integer id, Integer gid, Integer sid, String htBianhao,
			String title, String fileName, String realPath, Integer type,
			Integer htType, Integer trackId, String trackName,
			Integer optionId, String optionName, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.gid = gid;
		this.sid = sid;
		this.htBianhao = htBianhao;
		this.title = title;
		this.fileName = fileName;
		this.realPath = realPath;
		this.type = type;
		this.htType = htType;
		this.trackId = trackId;
		this.trackName = trackName;
		this.optionId = optionId;
		this.optionName = optionName;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getHtBianhao() {
		return htBianhao;
	}
	public void setHtBianhao(String htBianhao) {
		this.htBianhao = htBianhao;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRealPath() {
		return realPath;
	}
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getHtType() {
		return htType;
	}
	public void setHtType(Integer htType) {
		this.htType = htType;
	}
	public Integer getTrackId() {
		return trackId;
	}
	public void setTrackId(Integer trackId) {
		this.trackId = trackId;
	}
	public String getTrackName() {
		if(trackId!=null && !trackId.equals("")){
			if(UserCache.getUserMap().get(trackId)!=null){
				return UserCache.getUserMap().get(trackId).getRealName();
			}
		}else{
		}
		return trackName;
	}
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
	public Integer getOptionId() {
		return optionId;
	}
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
