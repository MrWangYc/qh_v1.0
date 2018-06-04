package com.qh.dao.base;

import com.qh.dao.BaseDao;
import com.qh.model.base.AssignFunction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
*	角色权限
* @创建人 			王云川
* @创建日期 		2017-03-22
* @修改人 			王云川
* @修改日期 		2017-03-22
* @版本号 			1.0.0
* @版权所有 		XX科技
*/
@Repository
public class AssignFunctionDao extends BaseDao {
	/**
	 * 根据id获取该职务所配置的权限
	 * @param id
	 * @return
	 */
	public List<AssignFunction> getListByPositionId(Integer id,Byte type,Byte isShow){
		String hql = "from AssignFunction where 1=1 and fun.status = 1 ";
		List<Object> params=new ArrayList<Object>();
		if(id!=null){
			hql += " and positionId = ? ";
			params.add(id);
		}
		if(type!=null){
			hql += " and fun.type = ?";
			params.add(type);
		}
		if(isShow!=null){
			hql += "and fun.isShow = ?";
			params.add(isShow);
		}

		return getList(hql,params.toArray());
	}

	/**
	 * 增加对象
	 * @param o	需要增加的对象
	 * @return	返回增加的对象
	 */
	public Object save(AssignFunction o) {
		return super.save(o);
	}

	/**
	 * 删除对象
	 * @param o 需要删除的对象
	 * @return	返回 true或false 是否删除成功
	 */
	public boolean delete(AssignFunction o) {
		return super.delete(o);
	}

	/**
	 * 根据职务id删除对应的配置权限
	 * @param id
	 * @return
	 */
	public Integer deleteByPositionId(Integer id){
		return this.excuteHql("delete from AssignFunction where positionId = ?",new Object[]{id});
	}

	/**
	 * 更新对象
	 * @param o 需要更新的对象
	 * @return	返回更新的对象
	 */
	public boolean update(AssignFunction o) {
		return super.update(o);
	}

	public List<AssignFunction> getList(Integer posId){
		String hql="from AssignFunction where 1=1 and positionId=?";
		List<AssignFunction> list=getList(hql,new Object[]{posId});
		return list;
	}

}