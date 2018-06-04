package com.qh.dao.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qh.dao.BaseDao;
import com.qh.model.base.Function;
import com.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* 系统功能模块配置，一级菜单、二级菜单、子功能权限配置
* @创建人 			王云川
* @创建日期 		2017-09-19
* @修改人 			王云川
* @修改日期 		2017-09-19
* @版本号 			1.0.0
* @版权所有 		XX科技
*/
@Repository
public class FunctionDao extends BaseDao {
	/**
	 * 获取数据并显示到界面上去,将无法直接看懂的参数直接转化为对应的数据
	 * @param id
	 * @return
	 */
	public JSON getJsonList(Integer id,Map<String, String> s){
		JSONArray array = new JSONArray();
		List<Function> list = new ArrayList<Function>();
		if(id !=null ) {
			StringBuffer sb = new StringBuffer();
			if(!StringUtil.isNull(s.get("name")))
				sb.append(" and functionName like '%"+s.get("name")+"%' ");
			if(!StringUtil.isNull(s.get("no")))
				sb.append(" and id = "+s.get("no"));
			list = this.getList("from Function where parent.id = ? order by orderNo", new Object[]{id});
		}else
			return null;
		for(Function f:list){
			JSONObject obj = new JSONObject();
			obj.put("type",f.getType()==0?"一级菜单":(f.getType()==1?"二级菜单":(f.getType()==2?"功能":"错误")));
			if(f.getParent()!=null) {
				obj.put("parent", f.getParent().getFunctionName());
			}else
				obj.put("parent","无");
			obj.put("functionName",f.getFunctionName());
			obj.put("status",f.getStatus()?"启用":"禁用");
			obj.put("orderNo",f.getOrderNo());
			obj.put("validate",f.getValidate()?"是":"否");
			obj.put("url",f.getUrl());
			obj.put("cache",f.getValidate()?"启用":"禁用");
			obj.put("isShow",f.getIsShow()==1?"启用":"禁用");
			obj.put("actionRule",f.getActionRule());
			obj.put("id",f.getId());
			array.add(obj);
		}
//		array = JSON.parseArray(ZjUtils.JsonU.toJsonString(list));
		JSONObject object = new JSONObject();
		object.put("code",0);
		object.put("msg","");
		object.put("count",list.size());
		object.put("data",array);
		object.put("data",array);
		return object;
	}


	/**
	 * 获取所有一二级菜单，并排除ID为1的顶部菜单
	 * @param status	启用状态 0：禁用，1：启用
	 * @param isShow	是否显示 0:隐藏，1：显示
	 * @return
	 */
	public List<Function> getMenuList(Boolean status,Byte isShow){
		String hql="from Function where id != 1 and type in (0,1)";
		List<Object> params=new ArrayList<Object>();
		if(status != null){
			hql+=" and status=?";
			params.add(status);
		}
		if(isShow != null){
			hql+=" and isShow=?";
			params.add(isShow);
		}
		hql+=" order by orderNo asc";
		return this.getList(hql,params.toArray());
	}

	/**
	 * 获取功能模块
	 * @param type	类型0:一级菜单，1：二级菜单，2：功能权限
	 * @param status	启用状态 0：禁用，1：启用
	 * @param isShow	是否显示 0:隐藏，1：显示
	 * @return
	 */
	public List<Function> getList(Byte type,Boolean status,Byte isShow){

		String hql="from Function where id != 1 ";
		List<Object> params=new ArrayList<Object>();
		if(type != null){
			hql+=" and type=?";
			params.add(type);
		}
		if(status != null){
			hql+=" and status=?";
			params.add(status);
		}
		if(isShow != null){
			hql+=" and isShow=?";
			params.add(isShow);
		}
		hql+=" order by orderNo asc";
		return this.getList(hql,params.toArray());
	}

	/**
	 * 根据状态获取id不为1的数据
	 * @param status
	 * @return
	 */
	public List<Function> getListByStatus(Boolean status,Byte type){
		List<Object> params=new ArrayList<Object>();
		String hql = "from Function where id != 1 ";
		if(status != null){
			hql += " and status = ? ";
			params.add(status);
		}
		if(type != null){
			hql += " and type = ? ";
			params.add(type);
		}
		hql += " order by orderNo asc";
		return this.getList(hql,params.toArray());
	}

	/**														
	 * 增加对象												
	 * @param o	需要增加的对象									
	 * @return	返回增加的对象								
	 */														
	public Object save(Function o) {
		return super.save(o);								
	}														
															
	/**														
	 * 删除对象												
	 * @param o 需要删除的对象									
	 * @return	返回 true或false 是否删除成功					
	 */														
	public boolean delete(Function o) {
		return super.delete(o);								
	}			
	
	/**														
	 * 更新对象												
	 * @param o 需要更新的对象									
	 * @return	返回更新的对象								
	 */														
	public boolean update(Function o) {
		return super.update(o);								
	}		
	
															
}