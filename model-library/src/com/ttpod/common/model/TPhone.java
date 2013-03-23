package com.ttpod.common.model;

import java.util.Date;

import com.ttpod.common.model.UserInfo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @className:TPhone.java
 * @classDescription:手机类
 * @author:tangxiping
 * @createTime:2012-2-3
 */
@Entity
@Table(name = "tphone")
public class TPhone extends IdEntity implements java.io.Serializable {
	private static final long serialVersionUID = -9081197655997972396L;
	@Column(name = "phoneName", length = 50)
	private String phoneName;		//机型名称
	@Column(name = "phonePic", length = 100)
	private String phonePic;		//机型图片路径
	@Column(name = "phonePrice",length = 11)
	private String phonePrice;		//机型价格
	@Column(name = "phoneModel", length = 50)
	private String phoneModel;		//手机制式
	@Column(name = "phoneView", length = 80)
	private String phoneView;		//外形
	@Column(name = "phoneSize", length = 80)
	private String phoneSize;		//屏幕大小
	@Column(name = "phoneTouchType", length = 100)
	private String phoneTouchType;	//触屏类型
	@Column(name = "phoneCamera", length = 100)
	private String phoneCamera;		//摄相头类型
	@Column(name = "phoneOs", length = 50)
	private String phoneOs;			//操作系统
	@Column(name = "phoneMemery", length = 80)
	private String phoneMemery;		//内存
	@Column(name = "phoneBatter", length = 50)
	private String phoneBatter;		//电池容量
	@Column(name = "phoneHot", length = 11)
	private String phoneHot;		//评分
	@Column(name = "phoneStatus")
	private int phoneStatus;		//状态
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="addDate")
	private Date addDate;			//添加日期
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="verifyDate")
	private Date verifyDate;		//审核日期
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.LAZY ,optional = false)
    @JoinColumn(name = "userId") 
	private UserInfo userInfo;		//用户信息
	@Column(name = "brandName", length = 40)
	private String brandName;		//品牌名

	/** default constructor */
	public TPhone() {
	}

	/** full constructor */
	public TPhone(String phoneName, String phonePic, String phonePrice,
			String phoneModel, String phoneView, String phoneSize,
			String phoneTouchType, String phoneCamera, String phoneOs,
			String phoneMemery, String phoneBatter, String phoneHot) {
		this.phoneName = phoneName;
		this.phonePic = phonePic;
		this.phonePrice = phonePrice;
		this.phoneModel = phoneModel;
		this.phoneView = phoneView;
		this.phoneSize = phoneSize;
		this.phoneTouchType = phoneTouchType;
		this.phoneCamera = phoneCamera;
		this.phoneOs = phoneOs;
		this.phoneMemery = phoneMemery;
		this.phoneBatter = phoneBatter;
		this.phoneHot = phoneHot;
	}

	public String getPhoneName() {
		return this.phoneName;
	}

	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}

	
	public String getPhonePic() {
		return this.phonePic;
	}

	public void setPhonePic(String phonePic) {
		this.phonePic = phonePic;
	}

	
	public String getPhonePrice() {
		return this.phonePrice;
	}

	public void setPhonePrice(String phonePrice) {
		this.phonePrice = phonePrice;
	}

	
	public String getPhoneModel() {
		return this.phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	
	public String getPhoneView() {
		return this.phoneView;
	}

	public void setPhoneView(String phoneView) {
		this.phoneView = phoneView;
	}

	
	public String getPhoneSize() {
		return this.phoneSize;
	}

	public void setPhoneSize(String phoneSize) {
		this.phoneSize = phoneSize;
	}

	
	public String getPhoneTouchType() {
		return this.phoneTouchType;
	}

	public void setPhoneTouchType(String phoneTouchType) {
		this.phoneTouchType = phoneTouchType;
	}

	
	public String getPhoneCamera() {
		return this.phoneCamera;
	}

	public void setPhoneCamera(String phoneCamera) {
		this.phoneCamera = phoneCamera;
	}

	
	public String getPhoneOs() {
		return this.phoneOs;
	}

	public void setPhoneOs(String phoneOs) {
		this.phoneOs = phoneOs;
	}

	
	public String getPhoneMemery() {
		return this.phoneMemery;
	}

	public void setPhoneMemery(String phoneMemery) {
		this.phoneMemery = phoneMemery;
	}

	
	public int getPhoneStatus() {
		return phoneStatus;
	}

	public void setPhoneStatus(int phoneStatus) {
		this.phoneStatus = phoneStatus;
	}

	public String getPhoneBatter() {
		return this.phoneBatter;
	}

	public void setPhoneBatter(String phoneBatter) {
		this.phoneBatter = phoneBatter;
	}

	
	public String getPhoneHot() {
		return this.phoneHot;
	}

	public void setPhoneHot(String phoneHot) {
		this.phoneHot = phoneHot;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	

}