package com.qh.dao.base;

import com.qh.dao.BaseDao;
import com.qh.model.base.ErrorIp;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
* 错误Ip的Dao
* @创建人 			王云川
* @创建日期 		2017-09-19
* @修改人 			王云川
* @修改日期 		2017-09-19
* @版本号 			1.0.0
* @版权所有 		XX科技
*/
@Repository
public class ErrorIpDao extends BaseDao {

	/**
	 * 根据ip获取错误Ip记录
	 * @param ip
	 * @return
	 */
	public ErrorIp getObjectByIpDate(String ip,String loginDate){
		String hql = "from ErrorIp where  1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(ip!=null){
			hql+=" and ip = ? ";
			params.add(ip);
		}
		if(loginDate!=null){
			hql += " and login = ? ";
			params.add(loginDate);
		}
		return (ErrorIp) this.getObject(hql,params.toArray());
	}

	/**														
	 * 增加对象												
	 * @param o	需要增加的对象									
	 * @return	返回增加的对象								
	 */														
	public Object save(ErrorIp o) {
		return super.save(o);								
	}														
															
	/**														
	 * 删除对象												
	 * @param o 需要删除的对象									
	 * @return	返回 true或false 是否删除成功					
	 */														
	public boolean delete(ErrorIp o) {
		return super.delete(o);								
	}

	/**
	 * 删除表里的全部数据
	 * @return
	 */
	public Integer delete(){
		return this.excuteHql("delete from ErrorIp ");
	}
	
	/**														
	 * 更新对象												
	 * @param o 需要更新的对象									
	 * @return	返回更新的对象								
	 */														
	public boolean update(ErrorIp o) {
		return super.update(o);								
	}		
	
															
}