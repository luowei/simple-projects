package com.rootls.common.model;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @className:Phonebrand.java
 * @classDescription:手机品牌类
 * @author:tangxiping
 * @createTime:2012-2-3
 */
@Entity
@Table(name = "phonebrand")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Phonebrand extends IdEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "brandName", nullable = false, length = 40)
	private String brandName;		//品牌名称
	@Column(name = "brandImage", length = 200)
	private String brandImage;		//品牌图片路径
	@Column(name = "brandStatus", nullable = false)
	private Integer brandStatus;	//品牌状态
	@Column(name = "brandDesc")
	private Integer brandDesc;		//品牌排序
	@Temporal(TemporalType.DATE)
	@Column(name = "brandAddDate", length = 10)
	private Date brandAddDate;		//添加日期
	@OneToMany(cascade = {CascadeType.ALL,CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "phonebrand")
	private Set<Phone> phones = new HashSet<Phone>(0);	//包含的机型

	/** default constructor */
	public Phonebrand() {
	}




	/** minimal constructor */
	public Phonebrand(String brandName, String brandImage,
			Integer brandStatus) {
		this.brandName = brandName;
		this.brandImage = brandImage;
		this.brandStatus = brandStatus;
	}

	/** full constructor */
	public Phonebrand(String brandName, String brandImage,
			Integer brandStatus, Integer brandDesc, Date brandAddDate,
			Set<Phone> phones) {

		this.brandName = brandName;
		this.brandImage = brandImage;
		this.brandStatus = brandStatus;
		this.brandDesc = brandDesc;
		this.brandAddDate = brandAddDate;
		this.phones = phones;
	}

	// Property accessors
	


	
	public String getBrandName() {
		return this.brandName;
	}


	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	
	public String getBrandImage() {
		return this.brandImage;
	}

	public void setBrandImage(String brandImage) {
		this.brandImage = brandImage;
	}

	
	public Integer getBrandStatus() {
		return this.brandStatus;
	}

	public void setBrandStatus(Integer brandStatus) {
		this.brandStatus = brandStatus;
	}

	
	public Integer getBrandDesc() {
		return this.brandDesc;
	}

	public void setBrandDesc(Integer brandDesc) {
		this.brandDesc = brandDesc;
	}


	public Date getBrandAddDate() {
		return this.brandAddDate;
	}

	public void setBrandAddDate(Date brandAddDate) {
		this.brandAddDate = brandAddDate;
	}

	
	public Set<Phone> getPhones() {
		return this.phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

}