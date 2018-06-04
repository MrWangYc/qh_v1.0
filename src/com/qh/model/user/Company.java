package com.qh.model.user;

import com.qh.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 *	公司表
 * @创建人 			王云川
 * @创建日期 		2017-09-25
 * @修改人 			王云川
 * @修改日期 		2017-09-25
 * @版本号 			1.0.0
 * @版权所有 		XX科技
*/
@SuppressWarnings("serial")
@Entity
@Table(name = "u_company")
public class Company extends BaseModel {
	/** ID */
	private Integer id;
	/** 名称 */
	private String name;
	/**简称*/
	private String shortName;
	/**状态**/
	private String status;

	/**创建时间*/
	private Date createDate;
	private Employee createEmployee;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(length = 50)
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ManyToOne
	public Employee getCreateEmployee() {
		return createEmployee;
	}

	public void setCreateEmployee(Employee createEmployee) {
		this.createEmployee = createEmployee;
	}
}