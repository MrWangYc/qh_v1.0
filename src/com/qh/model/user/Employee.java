
package com.qh.model.user;

import com.qh.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 *	员工档案,系统帐号密码
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
@Table(name = "u_emp")
public class Employee extends BaseModel {
	/** ID */
	private Integer id;
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
	/** 联系电话（登录账号） */
	private String phone;
	/** 登录密码 */
	private String password;
	/** 工作职务 */
	private Position position;
//	/**所属组织机构**/
//	public Organs sysOrgan;
	/**所属公司**/
	private Company company;
	private Employee createEmployee;

	/**状态 0禁用 1启用**/
	private String status;

	public Employee(){
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

	@Column(length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取员工所属组织机构
	 *
	 * @return
	 */
//	@ManyToOne
//	public Organs getSysOrgan() {
//		return sysOrgan;
//	}
//
//	public void setSysOrgan(Organs sysOrgan) {
//		this.sysOrgan = sysOrgan;
//	}


	@ManyToOne
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Column(length=100)
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}



	@ManyToOne
	@JoinColumn(name = "company")
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne
	public Employee getCreateEmployee() {
		return createEmployee;
	}

	public void setCreateEmployee(Employee createEmployee) {
		this.createEmployee = createEmployee;
	}
}