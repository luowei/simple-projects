package com.ttpod.common.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.*;
/**
 * @className:Platform.java
 * @classDescription:手机平台类
 * @author:tangxiping,,luowei
 * @createTime:2012-2-3
 */
@Entity
@Table(name = "platform")
public class Platform  extends IdEntity  implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	@Column(name = "platformName", nullable = false, length = 40)
	private String platformName;		//平台名称
	@Column(name = "platformShortName", nullable = false, length = 100)
	private String platformShortName;	//平台简称
	@Column(name = "platformStatus")
	private Integer platformStatus;		//平台状态
	@Column(name = "platformInfo", length = 200)
	private String platformInfo;		//平台信息
	@Temporal(TemporalType.DATE)
	@Column(name = "platformAddDate", length = 10)
	private Date platformAddDate;		//平台添加日期
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "platform")
	private Set<Phone> phones = new HashSet<Phone>(0);	//平台包含的机型

	// Constructors

	/** default constructor */
	public Platform() {
	}

	/** minimal constructor */
	public Platform(String platformName,
			String platformShortName) {
		this.platformName = platformName;
		this.platformShortName = platformShortName;
	}

	/** full constructor */
	public Platform(String platformName,
			String platformShortName, Integer platformStatus,
			String platformInfo, Date platformAddDate, Set<Phone> phones) {
		this.platformName = platformName;
		this.platformShortName = platformShortName;
		this.platformStatus = platformStatus;
		this.platformInfo = platformInfo;
		this.platformAddDate = platformAddDate;
		this.phones = phones;
	}

	public String getPlatformName() {
		return this.platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	
	public String getPlatformShortName() {
		return this.platformShortName;
	}

	public void setPlatformShortName(String platformShortName) {
		this.platformShortName = platformShortName;
	}

	
	public Integer getPlatformStatus() {
		return this.platformStatus;
	}

	public void setPlatformStatus(Integer platformStatus) {
		this.platformStatus = platformStatus;
	}

	
	public String getPlatformInfo() {
		return this.platformInfo;
	}

	public void setPlatformInfo(String platformInfo) {
		this.platformInfo = platformInfo;
	}


	public Date getPlatformAddDate() {
		return this.platformAddDate;
	}

	public void setPlatformAddDate(Date platformAddDate) {
		this.platformAddDate = platformAddDate;
	}

	public Set<Phone> getPhones() {
		return this.phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

}