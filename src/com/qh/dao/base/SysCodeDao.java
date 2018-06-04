package com.qh.dao.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qh.dao.BaseDao;
import com.qh.model.base.SysCode;
import com.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
*	系统基础编码（数据字典）
* @创建人 			王云川
* @创建日期 		2017-03-22
* @修改人 			王云川
* @修改日期 		2017-03-22
* @版本号 			1.0.0
* @版权所有 		XX科技
*/
@Repository
public class SysCodeDao extends BaseDao {


	/**
	 * 获取数据并显示到界面上去,将无法直接看懂的参数直接转化为对应的数据
	 * @param id
	 * @return
	 */
	public JSON getJsonList(Integer id,Map<String, String> s){
		if(id==null){
			id=1;
		}
		JSONArray array = new JSONArray();
		List<SysCode> list = new ArrayList<SysCode>();
		if(id !=null ) {
			StringBuffer sb = new StringBuffer();
			if(!StringUtil.isNull(s.get("name")))
				sb.append(" and name like '%"+s.get("name")+"%'");
			if(!StringUtil.isNull(s.get("no")))
				sb.append(" and id = "+s.get("no"));
			list = this.getList("from SysCode where parent.id = ? "+sb.toString(), new Object[]{id});
		}else
			return null;
		for(SysCode f:list){
			JSONObject obj = new JSONObject();
			if(f.getParent()!=null) {
				obj.put("parent", f.getParent().getName());
			}else
				obj.put("parent","无");
			obj.put("name",f.getName());
			obj.put("orderNo",f.getOrderNo());
			obj.put("param1",f.getParam1());
			obj.put("param2",f.getParam2());
			obj.put("remarks",f.getRemarks());
			obj.put("status",f.getStatus()==0?"停用":"启用");
			obj.put("id",f.getId());
			array.add(obj);
		}
		JSONObject object = new JSONObject();
		object.put("code",0);
		object.put("msg","");
		object.put("count",list.size());
		object.put("data",array);
		object.put("data",array);
		return object;
	}



	/**														
	 * 增加对象												
	 * @param o	需要增加的对象									
	 * @return	返回增加的对象								
	 */														
	public Object save(SysCode o) {
															
		return super.save(o);								
	}														
															
	/**														
	 * 删除对象												
	 * @param o 需要删除的对象									
	 * @return	返回 true或false 是否删除成功					
	 */														
	public boolean delete(SysCode o) {				
															
		return super.delete(o);								
	}														
															
															
	/**														
	 * 更新对象												
	 * @param o 需要更新的对象									
	 * @return	返回更新的对象								
	 */														
	public boolean update(SysCode o) {				
															
		return super.update(o);								
	}	
	
	/**
	 * 根据父亲ID获取列表
	 * @param id
	 * @return
	 */
	public List<SysCode> getListByParentId(Integer id){
		if(id==null)
			id=1;
		String hql ="from SysCode where parent.id = ? ";
		List<SysCode> list = getList(hql,new Object[]{id});
		return list;
	}
	
	/**
	 * 根据字典状态获取数据字典
	 * @param status	为空：所有状态，0：停用的，1：启用的
	 * @return
	 */
	public List<SysCode> getListByStatus(Byte status){
		String hql="from SysCode where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(status != null){
			hql+=" and status=?";
			params.add(status);
		}
		hql+="order by parent.id asc,orderNo asc,id asc";
		return getList(hql,params.toArray());
		
	}
															
}