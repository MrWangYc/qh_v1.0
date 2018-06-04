package com.qh.dao.base;

import com.qh.dao.BaseDao;
import com.qh.model.base.OnlineUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*	登录日期
* @创建人 			王云川
* @创建日期 		2017-03-22
* @修改人 			王云川
* @修改日期 		2017-03-22
* @版本号 			1.0.0
* @版权所有 		XX科技
*/
@Repository
public class OnlineUserDao extends BaseDao {



	/**														
	 * 增加对象												
	 * @param o	需要增加的对象									
	 * @return	返回增加的对象								
	 */														
	public Object save(OnlineUser o) {
		return super.save(o);								
	}														
															
	/**														
	 * 删除对象												
	 * @param o 需要删除的对象									
	 * @return	返回 true或false 是否删除成功					
	 */														
	public boolean delete(OnlineUser o) {
															
		return super.delete(o);								
	}														
															
															
	/**														
	 * 更新对象												
	 * @param o 需要更新的对象									
	 * @return	返回更新的对象								
	 */														
	public boolean update(OnlineUser o) {
															
		return super.update(o);								
	}

	/**
	 * 清空所有在线用户
	 */
	public List<OnlineUser> getListByOnline(){
		List<OnlineUser> list=getList("from OnlineUser where status=true",null);
		return list;
	}


	public OnlineUser getObjectBySid(String sId){
		OnlineUser ou = (OnlineUser)getObject("from OnlineUser where sId=? and status=true",new Object[]{sId});
		return ou;
	}




}