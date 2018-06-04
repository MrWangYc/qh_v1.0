package com.qh.service.base;

import com.alibaba.fastjson.JSON;
import com.qh.dao.base.FunctionDao;
import com.qh.model.base.Function;
import com.qh.service.BaseService;
import com.util.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统功能模块配置，一级菜单、二级菜单、子功能权限配置
 * 
 * @创建人 王云川
 * @创建日期 2017-03-22
 * @修改人 王云川
 * @修改日期 2017-03-22
 * @版本号 1.0.0
 * @版权所有 XX科技
 */
@Service
public class FunctionService extends BaseService {
	@Autowired
	private FunctionDao baseDao;// 功能模块DAO

	public JSON getJsonArray(Integer id,Map<String, String> s){
		return baseDao.getJsonList(id,s);
	}

	/**
	 * 获取全部的列表
	 * @return
	 */
	public List<Function> getMenuList(Boolean status,Byte isShow){
		return baseDao.getMenuList(status,isShow);
	}

	/**
	 * 根据类型获取对应的列表
	 * @param type
	 * @return
	 */
	public List<Function> getList(Byte type,Boolean status,Byte isShow){
		return baseDao.getList(type,status,isShow);
	}

	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public Function getObject(Integer id){
		return (Function) baseDao.getObject(Function.class,id);
	}

	public List<Function> getListByStatus(Boolean status,Byte type){
		return baseDao.getListByStatus(status,type);
	}

	/**
	 * 添加系统功能
	 * @param object 对象
	 * @return
	 */
	public Function save(Function object) {
		baseDao.save(object);
		return object;
	}

	/**
	 * 修改系统功能
	 * @param object 对象
	 * @return
	 */
	public Function update(Function object) {
		Function o=(Function) baseDao.getObject(Function.class, object.getId());
		BeanCopyUtil.copy(o,object);
		baseDao.update(o);
		return object;
	}

	/**
	 * 删除系统功能
	 * @param object 需要删除的新闻公告
	 */
	public void delete(Function object) {
		baseDao.delete(object);
	}

}