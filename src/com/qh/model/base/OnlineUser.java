package com.qh.model.base;

import com.qh.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 *	用户在线记录，及登录情况记录
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
@Table(name = "b_online")
public class OnlineUser extends BaseModel {
	private Integer id;
	/** 客户端类型  1.pc 2.移动端 */
	private Byte type;
	/**session Id*/
	private String sId;
	/**员工*/
	private Integer empId;
	/**在线状态*/
	private Boolean status;
	/**登录者描述*/
	private String title;
	/**IP地址描述*/
	private String ip;
	/**登录时间*/
	private Date login;
	/**最后活动时间*/
	private Date lastReq;
	/**操作记录*/
	private String reqInfo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length = 300)
	public String getTitle() {
		return title;
	}

	public Date getLogin() {
		return login;
	}
	public Date getLastReq() {
		return lastReq;
	}
	@Column(length=3000)
	public String getReqInfo() {
		return reqInfo;
	}
	public void setReqInfo(String reqInfo) {
		this.reqInfo = reqInfo;
	}

	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public void setLogin(Date login) {
		this.login = login;
	}
	public void setLastReq(Date lastReq) {
		this.lastReq = lastReq;
	}

	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Column(length = 200)
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}


}