
package com.qh.model.user;


import com.qh.model.BaseModel;

import javax.persistence.*;

/**
 * 员工职务管理（权限管理）
 *
 * @创建人 			王云川
 * @创建日期 		2017-09-20
 * @修改人 			王云川
 * @修改日期 		2017-09-20
 * @版本号 			1.0.0
 * @版权所有 		XX科技
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "u_position")
public class Position extends BaseModel {
	/** ID */
	private Integer id;
	/** 职务名称 */
	private String name;
	/** 状态 1启用、0禁用、-1锁定该职务的登录帐号*/
	private byte status;
	/**数据权限 1 全部 2 本店 3 本人*/
	private String dataAuth;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getDataAuth() {
		return dataAuth;
	}

	public void setDataAuth(String dataAuth) {
		this.dataAuth = dataAuth;
	}
}