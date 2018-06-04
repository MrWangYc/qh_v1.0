package com.qh.dao.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qh.dao.BaseDao;
import com.qh.model.user.Category;
import com.qh.model.user.Employee;
import com.util.DateUtil;
import com.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URLDecoder;
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
* @版权所有
*/
@Repository
public class CategoryDao extends BaseDao {
	/**
	 * 获取数据并显示到界面上去,将无法直接看懂的参数直接转化为对应的数据
	 * @param s
	 * @return
	 */
	public JSON getJsonList(Map<String, String> s, Integer page, Integer limit)throws IOException {
		JSONArray array = new JSONArray();
		List<Category> list = new ArrayList<Category>();
		List<Category> count = new ArrayList<Category>();
		StringBuffer sb = new StringBuffer();
		if(!StringUtil.isNull(s.get("name")))
			sb.append(" and name like '%"+ URLDecoder.decode(s.get("name"),"UTF-8")+"%' ");
		if(!StringUtil.isNull(s.get("phone")))
			sb.append(" and Phone like '%"+ URLDecoder.decode(s.get("phone"),"UTF-8")+"%' ");
		list = this.getList("from Category where 1=1 "+sb.toString()+" order by createDate desc ",null,(page-1)*limit,limit);
		count = this.getList("from Category where 1=1 "+sb.toString()+" ",null);
		for(Category f:list){
			JSONObject obj = new JSONObject();
			obj.put("id",f.getId());
			obj.put("name",f.getName());
			obj.put("status","1".equals(f.getStatus())?"启用":"禁用");
			obj.put("createEmp",f.getCreateEmployee()!=null?f.getCreateEmployee().getName():"");
			obj.put("type","1".equals(f.getType())?"次数卡":("2".equals(f.getType())?"月卡":("3".equals(f.getType())?"季卡":"年卡")));
			obj.put("createTime", DateUtil.format(f.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
			array.add(obj);
		}
		JSONObject object = new JSONObject();
		object.put("code",0);
		object.put("msg","");
		object.put("count",count.size());
		object.put("data",array);
		return object;
	}

	public List<Category> getListByIdPwd(String id,String pwd){
		List<Category> list = new ArrayList<Category>();
		list = this.getList("from Category where phone = '"+id+"' and Password = '"+pwd+"'  order by sorts ",null);
		return list;
	}

	/**
	 * 根据类型获取职务列表
	 * @param status
	 * @return
	 */
	public List<Category> getListByType(String status){
		String hql = "from Category where  1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(!"".equals(status)){
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
	public Object save(Category o) {
		return super.save(o);								
	}														
															
	/**														
	 * 删除对象												
	 * @param o 需要删除的对象									
	 * @return	返回 true或false 是否删除成功					
	 */														
	public boolean delete(Category o) {
		return super.delete(o);								
	}			
	
	/**														
	 * 更新对象												
	 * @param o 需要更新的对象									
	 * @return	返回更新的对象								
	 */														
	public boolean update(Category o) {
		return super.update(o);								
	}		
	
															
}