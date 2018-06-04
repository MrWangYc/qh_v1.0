package com.qh.service.user;

import com.alibaba.fastjson.JSON;
import com.qh.dao.user.CategoryDao;
import com.qh.dao.user.EmployeeDao;
import com.qh.model.user.Category;
import com.qh.model.user.Employee;
import com.qh.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
 * @版权所有
 */
@Service
public class CategoryService extends BaseService {
	@Autowired
	private CategoryDao baseDao;// 功能模块DAO

	public JSON getJsonArray(Map<String, String> s, Integer page, Integer limit) throws IOException {
		return baseDao.getJsonList(s,page,limit);
	}

	/**
	 * 根据状态获取对应的列表
	 * @param status
	 * @return
	 */
	public List<Category> getListByType(String status){
		return baseDao.getListByType(status);
	}

	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public Category getObject(Integer id){
		return (Category) baseDao.getObject(Category.class,id);
	}

	/**
	 * 添加系统功能EmployeeEmployee
	 * @param object 对象
	 * @return
	 */
	public Category save(Category object) {
		baseDao.save(object);
		return object;
	}




	/**
	 * 修改系统功能
	 * @param object 对象
	 * @return
	 */
	public Category update(Category object) {
		/*Employee o=new Employee();
		BeanCopyUtil.copy(o,baseDao.getObject(Employee.class, object.getId()));*/
		baseDao.update(object);
		return object;
	}

	/**
	 * 删除系统功能
	 * @param object 需要删除的新闻公告
	 */
	public void delete(Category object) {
		baseDao.delete(object);
	}

}