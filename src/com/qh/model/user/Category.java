
package com.qh.model.user;

import com.qh.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

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
@Table(name = "f_category")
public class Category extends BaseModel {
	/** ID */
	private Integer id;
	/** 名称 */
	private String name;
	/** 创建日期 */
	private Date createDate;
	/**类别  1次数卡 2月卡 3季卡 4年卡*/
	private String type;
	/**总次数*/
	private Integer num;
	/**金额*/
	private Float amount;
	private Employee createEmployee;
	/**更新*/
	private Date updateDate;
	private Employee updateEmp;

	/**状态 0禁用 1启用**/
	private String status;

	public Category(){
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

	@Column(updatable=false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@ManyToOne
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
}