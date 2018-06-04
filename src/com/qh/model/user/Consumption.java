
package com.qh.model.user;

import com.qh.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 *	消费情况登记
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
@Table(name = "f_consumption")
public class Consumption extends BaseModel {
	/** ID */
	private Integer id;

	/**办卡信息*/
	private MemCate memCate;

	private Date  cDate;

	private String remark;

	/**创建信息*/
	private Employee createEmp;
	private Date createDate;

	public Consumption(){
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

	@ManyToOne
	public MemCate getMemCate() {
		return memCate;
	}

	public void setMemCate(MemCate memCate) {
		this.memCate = memCate;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	@Column(length = 1000)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne
	public Employee getCreateEmp() {
		return createEmp;
	}

	public void setCreateEmp(Employee createEmp) {
		this.createEmp = createEmp;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}