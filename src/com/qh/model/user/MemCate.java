
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
@Table(name = "f_mem_cate")
public class MemCate extends BaseModel {
	/** ID */
	private Integer id;

	/**会员信息*/
	private Membership membership;

	/**活动信息*/
	private Category category;

	/**金额(防止价格发生变化)*/
	private Float amount;

	private String type;

	/**根据活动信息保存以下信息**/
	/**总次数*/
	private Integer totalDegree;
	/**剩余次数*/
	private Integer remainder;
	/**开始时间*/
	private Date startDate;
	/**截止时间*/
	private Date endDate;
	/**创建信息*/
	private Date createDate;
	private Employee createEmp;

	/**状态 0禁用 1启用**/
	private String status;

	public MemCate(){
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
	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	@ManyToOne
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getTotalDegree() {
		return totalDegree;
	}

	public void setTotalDegree(Integer totalDegree) {
		this.totalDegree = totalDegree;
	}

	public Integer getRemainder() {
		return remainder;
	}

	public void setRemainder(Integer remainder) {
		this.remainder = remainder;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ManyToOne
	public Employee getCreateEmp() {
		return createEmp;
	}

	public void setCreateEmp(Employee createEmp) {
		this.createEmp = createEmp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}