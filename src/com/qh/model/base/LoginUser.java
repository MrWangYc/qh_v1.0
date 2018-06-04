package com.qh.model.base;

import com.qh.model.user.Company;
import com.qh.model.user.Employee;
import com.qh.model.user.Organs;
import com.qh.model.user.Position;

import java.io.Serializable;

/**
 *	用户会话对象，登录保存一些用户的相关数据、及权限数据
 *
 * @创建人 			王云川
 * @创建日期 		2017-03-22
 * @修改人 			王云川
 * @修改日期 		2017-03-22
 * @版本号 			1.0.0
 * @版权所有 		XX科技
*/
public class LoginUser implements Serializable{
	private static final long serialVersionUID = 6410783924937159403L;
	/** 客户端类型  1.pc 2.移动端 */
	private byte clientType;
	/** 用户数据 */
	private Employee employee = null;
	/** 用户主题 */
	private String theme ;
	/** 所属部门 */
	private Organs sysOrgan;
	/** 所属公司 */
	private Company company;
	/** 用户角色 */
	private Position position;
	
	public LoginUser() {
		
	}

    public byte getClientType() {
        return clientType;
    }

    public void setClientType(byte clientType) {
        this.clientType = clientType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Organs getSysOrgan() {
        return sysOrgan;
    }

    public void setSysOrgan(Organs sysOrgan) {
        this.sysOrgan = sysOrgan;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
