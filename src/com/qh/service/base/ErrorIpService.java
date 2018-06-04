package com.qh.service.base;

import com.qh.dao.base.ErrorIpDao;
import com.qh.model.base.ErrorIp;
import com.qh.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*	错误Ip记录
* @创建人 			王云川
* @创建日期 		2017-09-29
* @修改人 			王云川
* @修改日期 		2017-09-29
* @版本号 			1.0.0
* @版权所有 		XX科技
*/

@Service
public class ErrorIpService extends BaseService {
	@Autowired
	private ErrorIpDao baseDao;

	public ErrorIp getObjectByIpDate(String ip,String date){
		return baseDao.getObjectByIpDate(ip,date);
	}

	/**
	 * 保存
	 * @param o	需要增加的对象
	 * @return	返回增加的对象
	 */
	public ErrorIp save(ErrorIp o){
		baseDao.save(o);
		return o;
	}
	/**
	 * 修改
	 * @param o 需要更新的对象
	 * @return	返回更新的对象
	 */
	public ErrorIp update(ErrorIp o){
		baseDao.update(o);
		return o;
	}
	
	
	/**
	 * 删除
	 * @param o 需要更新的对象			
	 * @return	返回更新的对象
	 */
	public boolean delete(ErrorIp o){
		return baseDao.delete(o);
	}

	/**
	 * 删除全部数据
	 * @return
	 */
	public Integer delete(){
		return baseDao.delete();
	}

	public ErrorIpDao getBaseDao() {
		return baseDao;
	}


}