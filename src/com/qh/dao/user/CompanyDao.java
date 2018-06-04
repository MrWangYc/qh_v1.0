package com.qh.dao.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qh.dao.BaseDao;
import com.qh.model.user.Company;
import com.qh.model.user.Position;
import com.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* 公司dao
* @创建人 			王云川
* @创建日期 		2017-09-19
* @修改人 			王云川
* @修改日期 		2017-09-19
* @版本号 			1.0.0
* @版权所有 		XX科技
*/
@Repository
public class CompanyDao extends BaseDao {
	/**
	 * 获取数据并显示到界面上去,将无法直接看懂的参数直接转化为对应的数据
	 * @param s
	 * @return
	 */
	public JSON getJsonList(Map<String, String> s){
		JSONArray array = new JSONArray();
		List<Company> list = new ArrayList<Company>();
		StringBuffer sb = new StringBuffer();
		if(!StringUtil.isNull(s.get("name")))
			sb.append(" and name like '%"+s.get("name")+"%' ");
		list = this.getList("from Company where 1=1 "+sb.toString(),null);

		for(Company f:list){
			JSONObject obj = new JSONObject();
			obj.put("name",f.getName());
			obj.put("shortName",f.getShortName());
			obj.put("status","1".equals(f.getStatus())?"启用":"禁用");
			obj.put("id",f.getId());
			array.add(obj);
		}
		JSONObject object = new JSONObject();
		object.put("code",0);
		object.put("msg","");
		object.put("count",list.size());
		object.put("data",array);
		return object;
	}

	/**
	 * 根据类型获取职务列表
	 * @param status
	 * @return
	 */
	public List<Company> getListByStatus(String status){
		String hql = "from Company where  1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(status!=null){
			hql += " and status = ? ";
			params.add(status);
		}
		return this.getList(hql,params.toArray());
	}

	/**														
	 * 增加对象												
	 * @param o	需要增加的对象									
	 * @return	返回增加的对象								
	 */														
	public Object save(Position o) {
		return super.save(o);								
	}														
															
	/**														
	 * 删除对象												
	 * @param o 需要删除的对象									
	 * @return	返回 true或false 是否删除成功					
	 */														
	public boolean delete(Position o) {
		return super.delete(o);								
	}			
	
	/**														
	 * 更新对象												
	 * @param o 需要更新的对象									
	 * @return	返回更新的对象								
	 */														
	public boolean update(Position o) {
		return super.update(o);								
	}		
	
															
}