package com.ttpod.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @className:TPhonebrand.java
 * @classDescription:临时手机品牌类
 * @author:tangxiping
 * @createTime:2012-1-3
 */
@Entity
@Table(name = "tphonebrand")
public class TPhonebrand extends IdEntity implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	@Column(name = "phonebrandName", length = 40)
	private String phonebrandName;		//手机品牌名
	@Column(name = "phonebrandImage", length = 100)
	private String phonebrandImage;		//品牌图片路径
	@Column(name = "relateURL", length = 200)
	private String relateURL;			//品牌关联的url
	@Column(name = "brandStatus", length = 11)
	private int brandStatus;			//品牌状态
	@Temporal(TemporalType.DATE)
	@Column(name = "brandAddDate", length = 10)
	private Date brandAddDate;			//品牌添加日期
	@Temporal(TemporalType.DATE)
	@Column(name = "verifyDate", length = 10)
	private Date verifyDate;			//审核日期
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	private UserInfo userInfo;			//用户信息

	public String getPhonebrandName() {
		return phonebrandName;
	}

	public void setPhonebrandName(String phonebrandName) {
		this.phonebrandName = phonebrandName;
	}

	public String getPhonebrandImage() {
		return phonebrandImage;
	}

	public void setPhonebrandImage(String phonebrandImage) {
		this.phonebrandImage = phonebrandImage;
	}

	public String getRelateURL() {
		return relateURL;
	}

	public void setRelateURL(String relateURL) {
		this.relateURL = relateURL;
	}

	public int getBrandStatus() {
		return brandStatus;
	}

	public void setBrandStatus(int phoneStatus) {
		this.brandStatus = phoneStatus;
	}

	public Date getBrandAddDate() {
		return brandAddDate;
	}

	public void setBrandAddDate(Date brandAddDate) {
		this.brandAddDate = brandAddDate;
	}

	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public UserInfo getUserId() {
		return userInfo;
	}

	public void setUserId(UserInfo userId) {
		this.userInfo = userId;
	}
}
