package com.qh.model.user;

import com.qh.model.BaseModel;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.util.Date;

/**
 *	部门表
 *
 * @创建人 			王云川
 * @创建日期 		2017-03-22
 * @修改人 			王云川
 * @修改日期 		2017-03-22
 * @版本号 			1.0.0
 * @版权所有 		XX科技
*/ 
@SuppressWarnings("serial")
@Entity
@Table(name = "u_organs")
public class Organs extends BaseModel {

    /**
     * ID
     */
    private Integer id;
    /**
     * 部门0,片区1，门店2
     */
    private byte type;
    /**
     * 所属公司
     */
//    private Company company;
    /**
     * 机构类型
     */
    public Organs parent;
    /**
     * 机构名称
     */
    private String name;
    /**
     * 机构编码
     */
    private String code;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 顺序号
     */
    private Integer orderNo;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 创建人
     */
    private String createEmp;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Column(length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 100)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(length = 500)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(length = 10)
    public String getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp;
    }
    /*@ManyToOne
    @JoinColumn(name = "company")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }*/
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound
    @JoinColumn(name = "p_id")
    public Organs getParent() {
        return parent;
    }

    public void setParent(Organs parent) {
        this.parent = parent;
    }

}