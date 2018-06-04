package com.qh.model.base;

import com.qh.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 *	当天输错次数过多的ip
 *
 * @创建人 			王云川
 * @创建日期 		2017-09-29
 * @修改人 			王云川
 * @修改日期 		2017-09-29
 * @版本号 			1.0.0
 * @版权所有 		XX科技
*/ 
@SuppressWarnings("serial")
@Entity
@Table(name = "b_error")
public class ErrorIp extends BaseModel {
	private Integer id;
	/**IP地址描述*/
	private String ip;
	/**记录时间*/
	private String login;
	/**解封时间*/
	private Date lastReq;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getLastReq() {
		return lastReq;
	}
	public void setLastReq(Date lastReq) {
		this.lastReq = lastReq;
	}
	@Column(length = 100)
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Column(length = 20)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}