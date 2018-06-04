package com.qh.model.base;

import com.qh.model.BaseModel;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @创建人 			王云川
 * @创建日期 		2017-09-20
 * @修改人 			王云川
 * @修改日期 		2017-09-20
 * @版本号 			1.0.0
 * @版权所有 		XX科技
*/
@SuppressWarnings("serial")
@Entity
@Table(name = "b_code")
public class SysCode extends BaseModel {
	/**ID*/
	private Integer id;
	/**名称*/
	private String name;
	/**自定义值*/
	private String param1;
	/**自定义值*/
	private String param2;
	/**顺序号*/
	private Integer orderNo;
	/**备注*/
	private String remarks;
	/**状态
	 * 0：停用
	 * 1：启用 */
	private Byte status;

	private SysCode parent;

	private List<SysCode> childCodes = new ArrayList<SysCode>();



	/**mobile使用以下属性**/
	/**
	 * 父id
	 */
	@Transient
	private Integer pid;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Column(length=500)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(length=500)
	public String getParam1() {
		return param1;
	}

	public String getParam2() {
		return param2;
	}

	@Column(length=500)
	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	/**
	 * 获取上级节点对象
	 * @return
	 */
	@ManyToOne
	@NotFound
	@JoinColumn(name = "ParentId")
	public SysCode getParent() {
		return parent;
	}

	public void setParent(SysCode parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy(value = "id ASC,orderNo asc")
	public List<SysCode> getChildCodes() {
		return childCodes;
	}

	public void setChildCodes(List<SysCode> childCodes) {
		this.childCodes = childCodes;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}


	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
}