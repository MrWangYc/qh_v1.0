package com.qh.model.base;

import com.qh.model.BaseModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *	系统功能模块配置，一级菜单、二级菜单、子功能权限配置
 * @创建人 			王云川
 * @创建日期 		2017-09-19
 * @修改人 			王云川
 * @修改日期 		2017-09-19
 * @版本号 			1.0.0
 * @版权所有 		XX科技
*/
@SuppressWarnings("serial")
@Entity
@Table(name = "u_function")
public class Function  extends BaseModel {
	/** ID */
	private Integer id;
	/**父功能*/
	private Function parent;
	/** 功能名称 */
	private String functionName;
	/**类型0:一级菜单，1：二级菜单，2：功能权限*/
	private Byte type;
	/**是否缓存页面0:不缓存，1：缓存*/
	private Boolean cache;
	/** 功能地址 */
	private String url;
	/** 启用状态 0：禁用，1：启用*/
	private Boolean status;
	/**是否显示 0:隐藏，1：显示**/
	private Byte isShow;
	/** url过滤规则 */
	private String actionRule;
	/** 顺序编号 */
	private Integer orderNo;
	/** 下级子功能 */
	private List<Function> sysFunction = new ArrayList<Function>();
	/**是否验证*/
	private Boolean validate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "parent")
	public Function getParent() {
		return parent;
	}

	public void setParent(Function parent) {
		this.parent = parent;
	}

	@Column(length=20)
	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Boolean getCache() {
		return cache;
	}

	public void setCache(Boolean cache) {
		this.cache = cache;
	}

	@Column(length = 500)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}


	@Column(length=500)
	public String getActionRule() {
		return actionRule;
	}

	public void setActionRule(String actionRule) {
		this.actionRule = actionRule;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy(value = " orderNo ASC")
	public List<Function> getSysFunction() {
		return sysFunction;
	}

	public void setSysFunction(List<Function> sysFunction) {
		this.sysFunction = sysFunction;
	}

	public Boolean getValidate() {
		return validate;
	}

	public void setValidate(Boolean validate) {
		this.validate = validate;
	}

	public Byte getIsShow() {
		return isShow;
	}

	public void setIsShow(Byte isShow) {
		this.isShow = isShow;
	}
}