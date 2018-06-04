package com.qh.model.base;

import javax.persistence.*;

/**
 *分配 菜单功能权限
 *
 * @创建人 王云川
 * @创建日期 2017-09-20
 * @修改人 王云川
 * @修改日期 2017-09-20
 * @版本号 1.0.1
 * @版权所有 XX科技
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "b_a_fun")
public class AssignFunction {
	/** ID */
	private Integer id;
	/** 拥有职务 */
	private Integer positionId;
	/** 功能id */
	private Function fun;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPositionId() {
		return positionId;
	}


	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	@ManyToOne
	public Function getFun() {
		return fun;
	}

	public void setFun(Function fun) {
		this.fun = fun;
	}

}
