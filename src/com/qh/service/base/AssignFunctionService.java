package com.qh.service.base;

import com.qh.dao.base.AssignFunctionDao;
import com.qh.model.base.AssignFunction;
import com.qh.model.base.Function;
import com.qh.service.BaseService;
import com.util.BeanCopyUtil;
import com.util.CtxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class AssignFunctionService extends BaseService {
	@Autowired
	private AssignFunctionDao baseDao;// 功能模块DAO

	/**
	 * 根据职务id获取已配置的权限
	 * @param id
	 * @return
	 */
	public List<AssignFunction> getListByPositionId(Integer id,Byte type,Byte isShow){
		return baseDao.getListByPositionId(id,type,isShow);
	}

	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public AssignFunction getObject(Integer id){
		return (AssignFunction) baseDao.getObject(AssignFunction.class,id);
	}

	/**
	 * 添加系统功能PositionPosition
	 * @param object 对象
	 * @return
	 */
	public AssignFunction save(AssignFunction object) {
		baseDao.save(object);
		return object;
	}

	/**
	 * 保存数据
	 * @param funList
	 * @param positionId
	 * @return
	 */
	public String saveAssignFunction(List<Function> funList,Integer positionId){
		//删除该职务权限
		baseDao.deleteByPositionId(positionId);
		//保存职务权限
		for(Function function:funList){
			AssignFunction af = new AssignFunction();
			if(function!=null&&function.getId()!=null){
				af.setFun(function);
				af.setPositionId(positionId);
				baseDao.save(af);
			}
		}
		CtxUtil.reloadPostionFunSet(positionId);
		return "";
	}

	/**
	 * 修改系统功能
	 * @param object 对象
	 * @return
	 */
	public AssignFunction update(AssignFunction object) {
		AssignFunction o=new AssignFunction();
		BeanCopyUtil.copy(o,baseDao.getObject(AssignFunction.class, object.getId()));
		baseDao.update(o);
		return o;
	}

	/**
	 * 删除系统功能
	 * @param object 需要删除的新闻公告
	 */
	public void delete(AssignFunction object) {
		baseDao.delete(object);
	}

}