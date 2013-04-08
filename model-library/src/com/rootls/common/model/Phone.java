package com.rootls.common.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @className:Phone.java
 * @classDescription:手机类
 * @author:luowei
 * @createTime:2012-2-3
 */
@Entity
@Table(name = "phone")
public class Phone extends IdEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "platformId", nullable = false)
	private Platform platform;		//平台
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brandId", nullable = false)
	private Phonebrand phonebrand;	//品牌
	@Column(name = "phoneName", nullable = false, length = 50)
	private String phoneName;		//机型名
	@Column(name = "phoneResolution", length = 100)
	private String phoneResolution;	//分辨率
	@Column(name = "phoneImage", nullable = false, length = 200)
	private String phoneImage;		//机型图片路径
	@Column(name = "hotPhone")
	private Integer hotPhone;		//热度
	@Column(name = "phoneDesc")
	private Integer phoneDesc;		//排序
	@Column(name = "phoneHeadName",length = 10)
	private String phoneHeadName;	//机型简称
	@Temporal(TemporalType.DATE)
	@Column(name = "phoneAddDate", length = 10)
	private Date phoneAddDate;		//添加日期
	@Column(name = "phoneStatus", nullable = false, length = 11)
	private int phoneStatus;		//机型状态


	/** default constructor */
	public Phone() {
	}

	/** minimal constructor */
	public Phone(Platform platform, Phonebrand phonebrand,
			String phoneName, String phoneResolution, String phoneImage,
			String phoneHeadName) {

		this.platform = platform;
		this.phonebrand = phonebrand;
		this.phoneName = phoneName;
		this.phoneResolution = phoneResolution;
		this.phoneImage = phoneImage;
		this.phoneHeadName = phoneHeadName;
	}

	/** full constructor */
	public Phone(int id,Platform platform, Phonebrand phonebrand,
			String phoneName, String phoneResolution, String phoneImage,
			Integer hotPhone, Integer phoneDesc, String phoneHeadName,
			Date phoneAddDate) {
		this.id=id;
		this.platform = platform;
		this.phonebrand = phonebrand;
		this.phoneName = phoneName;
		this.phoneResolution = phoneResolution;
		this.phoneImage = phoneImage;
		this.hotPhone = hotPhone;
		this.phoneDesc = phoneDesc;
		this.phoneHeadName = phoneHeadName;
		this.phoneAddDate = phoneAddDate;
	}



	public Platform getPlatform() {
		return this.platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}


	public Phonebrand getPhonebrand() {
		return this.phonebrand;
	}

	public void setPhonebrand(Phonebrand phonebrand) {
		this.phonebrand = phonebrand;
	}

	
	public String getPhoneName() {
		return this.phoneName;
	}

	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}

	
	public String getPhoneResolution() {
		return this.phoneResolution;
	}

	public void setPhoneResolution(String phoneResolution) {
		this.phoneResolution = phoneResolution;
	}

	
	public String getPhoneImage() {
		return this.phoneImage;
	}

	public void setPhoneImage(String phoneImage) {
		this.phoneImage = phoneImage;
	}

	
	public Integer getHotPhone() {
		return this.hotPhone;
	}

	public void setHotPhone(Integer hotPhone) {
		this.hotPhone = hotPhone;
	}

	
	public Integer getPhoneDesc() {
		return this.phoneDesc;
	}

	public void setPhoneDesc(Integer phoneDesc) {
		this.phoneDesc = phoneDesc;
	}

	
	public String getPhoneHeadName() {
		return this.phoneHeadName;
	}

	public void setPhoneHeadName(String phoneHeadName) {
		this.phoneHeadName = phoneHeadName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "phoneAddDate", length = 10)
	public Date getPhoneAddDate() {
		return this.phoneAddDate;
	}

	public void setPhoneAddDate(Date phoneAddDate) {
		this.phoneAddDate = phoneAddDate;
	}

	public int getPhoneStatus() {
		return phoneStatus;
	}

	public void setPhoneStatus(int phoneStatus) {
		this.phoneStatus = phoneStatus;
	}
	

}