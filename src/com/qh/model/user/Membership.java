
package com.qh.model.user;

import com.qh.model.BaseModel;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *	会员卡
 *
 * @创建人 			王云川
 * @创建日期 		2017-03-22
 * @修改人 			王云川
 * @修改日期 		2017-03-22
 * @版本号 			1.0.0
 * @版权所有 		XX科技
*/ 
@SuppressWarnings("serial")
@Entity
@Table(name = "f_membership")
public class Membership extends BaseModel {
	/** ID */
	private Integer id;
	/**卡号*/
	private String cardNo;
	/** 姓名 */
	private String name;
	/** 姓名拼音 */
	private String pinyin;
	/** 性别 0男 1女*/
	private byte sex;
	/** 创建日期 */
	private Date createDate;
	/** 证件号码 */
	private String certificateNo;
	/** 出生日期 */
	private Date birthday;
	/** 照片 */
	private String photoPath;
	/** 备注 */
	private String remarks;
	/** 联系电话 */
	private String phone;
	/**是否是主卡 0 主卡 1 配偶 2 父母 3 子女 4 其他*/
	private String type;

	/**
	 * 1 成人
	 * 2 儿童
	 */
	private String cType;

	//成人
	//身高
	private String height;
	//体重
	private String weight;

	//血压
	private String bloodP;
	//血脂
	private String bloodF;
	//血糖
	private String bloodS;


	//儿童
	//出生体重
	private String birthWeight;
	//身长
	private String birthHeight;
	//是否足月
	private String ifMonth;

	//健康状况
	private String healthStatus;
	//遗传病史
	private String healthH;
	//过敏史
	private String allergicH;


	private Membership parent;

	private List<Membership> childCodes = new ArrayList<Membership>();

	private Employee createEmployee;
	/**更新*/
	private Date updateDate;
	private Employee updateEmp;

	/**状态 0禁用 1启用**/
	private String status;

	public Membership(){
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(length = 30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}
	@Column(length = 20)
	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(length = 20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(updatable=false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(length=100)
	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Column(length = 250)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(length=100)
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name = "createEmployee")
	public Employee getCreateEmployee() {
		return createEmployee;
	}

	public void setCreateEmployee(Employee createEmployee) {
		this.createEmployee = createEmployee;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@ManyToOne
	@JoinColumn(name = "updateEmp")
	public Employee getUpdateEmp() {
		return updateEmp;
	}

	public void setUpdateEmp(Employee updateEmp) {
		this.updateEmp = updateEmp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ManyToOne
	@NotFound
	@JoinColumn(name = "parentId")
	public Membership getParent() {
		return parent;
	}

	public void setParent(Membership parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy(value = "id ASC")
	public List<Membership> getChildCodes() {
		return childCodes;
	}

	public void setChildCodes(List<Membership> childCodes) {
		this.childCodes = childCodes;
	}

	public String getcType() {
		return cType;
	}

	public void setcType(String cType) {
		this.cType = cType;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}

	public String getBloodP() {
		return bloodP;
	}

	public void setBloodP(String bloodP) {
		this.bloodP = bloodP;
	}

	public String getBloodF() {
		return bloodF;
	}

	public void setBloodF(String bloodF) {
		this.bloodF = bloodF;
	}

	public String getBloodS() {
		return bloodS;
	}

	public void setBloodS(String bloodS) {
		this.bloodS = bloodS;
	}

	public String getBirthWeight() {
		return birthWeight;
	}

	public void setBirthWeight(String birthWeight) {
		this.birthWeight = birthWeight;
	}

	public String getBirthHeight() {
		return birthHeight;
	}

	public void setBirthHeight(String birthHeight) {
		this.birthHeight = birthHeight;
	}

	public String getIfMonth() {
		return ifMonth;
	}

	public void setIfMonth(String ifMonth) {
		this.ifMonth = ifMonth;
	}

	public String getHealthH() {
		return healthH;
	}

	public void setHealthH(String healthH) {
		this.healthH = healthH;
	}

	public String getAllergicH() {
		return allergicH;
	}

	public void setAllergicH(String allergicH) {
		this.allergicH = allergicH;
	}
}