package com.qh.service;

import com.qh.dao.base.OnlineUserDao;
import com.qh.model.user.Employee;
import com.qh.model.base.LoginUser;
import com.qh.model.base.OnlineUser;
import com.util.DateUtil;
import com.util.IpAddressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 登录服务类
 * @创建人 王云川
 * @创建日期 2017-09-25
 * @修改人 王云川
 * @修改日期 2017-09-25
 * @版本号 1.0.0
 * @版权所有 XX科技
 */
@Service
public class LoginService extends BaseService {
	@Autowired
	private OnlineUserDao baseDao;

	/**
	 * 用户登录
	 * @param userName	用户名称
	 * @param password	密码
	 * @param type		登录类型(1:PC端,2移动端)
	 * @return
	 */
	public LoginUser userLogin(String userName, String password, Byte type){
		LoginUser loginUser = null;
		if(userName == null ||  "".equals(userName) || password == null ||  "".equals(password)) return loginUser;

		//获取用户对象
		Employee emp= (Employee) getBaseDao().getObject("from Employee where phone=? and password=?",new Object[]{userName, password});
		if(emp == null) return loginUser;

		//登录成功，设置登录信息
		loginUser=new LoginUser();
		loginUser.setClientType(type);//设置客户端类型
		loginUser.setEmployee(emp);//登录用户
		loginUser.setCompany(emp.getCompany());//用户所属部门
//		loginUser.setCompany(emp.getCompany());//设置用户公司
		loginUser.setPosition(emp.getPosition());
		return loginUser;
	}

	/**
	 * 添加登录日志
	 * @param type	类型1:PC,2:移动端
	 * @param sId	sessionID
	 * @param empId	人员ID
	 * @param ip		登录的ip地址
	 * @return
	 */
	public OnlineUser saveLog(Byte type, String sId, Integer empId, String ip){
		OnlineUser onlineUser=new OnlineUser();
		onlineUser.setType(type);
		onlineUser.setsId(sId);
		onlineUser.setEmpId(empId);
		onlineUser.setStatus(true);
		onlineUser.setTitle("");
		onlineUser.setIp(ip);
		onlineUser.setTitle(IpAddressUtils.getAllName(ip));
		onlineUser.setLogin(new Date());
		baseDao.save(onlineUser);
		return onlineUser;
	}

	/**
	 * 清空所有在线用户
	 */
	public void excuteClearOnlineUser(){
		List<OnlineUser> list=baseDao.getListByOnline();
		for(OnlineUser ou:list){
			userLonger(ou);
		}
	}

	/**
	 * 根据session ID 获取在线用户
	 * @param sId	session
	 * @return
	 */
	public OnlineUser getObjectBySid(String sId){
		return baseDao.getObjectBySid(sId);
	}


	/**
	 * 用户下线
	 * @param ou
	 * @return
	 */
	public OnlineUser userLonger(OnlineUser ou){
		if(ou != null){
			String msg = "共在线" + DateUtil.dateDiff(new Date(), ou.getLogin());
			ou.setLastReq(new Date());
			ou.setStatus(false);
			ou.setReqInfo(msg);
			baseDao.update(ou);
		}
		return ou;
	}
}