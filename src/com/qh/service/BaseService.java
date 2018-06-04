package com.qh.service;

import com.qh.dao.BaseDao;
import com.qh.model.base.LoginUser;
import com.util.Constants;
import com.util.CtxUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
* 服务基类
*
* @创建人 			王云川
* @创建日期 		2017-03-22
* @修改人 			王云川
* @修改日期 		2017-03-22
* @版本号 			1.0.0
* @版权所有 		XX科技
*/
@org.springframework.stereotype.Service
public class BaseService {
	public static Log log = LogFactory.getLog(BaseService.class);
	@Autowired
	private BaseDao baseDao;
	public BaseDao getBaseDao() {
		return baseDao;
	}


	/**
	 * 按照主键查询对象
	 * @param clazz		类
	 * @param pkId		主键
	 * @return
	 */
	public Object getObject(final Class clazz, final Object pkId) {
		return baseDao.getObject(clazz, pkId);
	}



	/**
	 * 根据功能ID，判断此职务是否有这个功能权限
	 * @param functionId 功能ID
	 * @return
	 */
	public static boolean auth(Integer functionId){
		boolean result=false;
		LoginUser loginUser=CtxUtil.getLoginUser();
		if((CtxUtil.getPostionFunSet().containsKey(loginUser.getPosition().getId()) && CtxUtil.getPostionFunSet().get(loginUser.getPosition().getId()).contains(functionId)) || (loginUser.getEmployee().getPhone() !=null && loginUser.getEmployee().getPhone().equals(Constants.ADMIN_PHONE))){
			result=true;
		}
		return result;
	}

}