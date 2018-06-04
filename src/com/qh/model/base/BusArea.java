
package com.qh.model.base;

import com.qh.model.BaseModel;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *城市片区
 *
 * @创建人 王云川
 * @创建日期 2017-09-20
 * @修改人 王云川
 * @修改日期 2017-09-20
 * @版本号 1.0.1
 * @版权所有 XX科技
 */

@Entity
@Table(name = "b_bus_area")
public class BusArea extends BaseModel {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 类型 (0：城市 1城区 2片区)
     */
    private byte type;
    /**
     * 名称
     */
    private String name;
    /**
     * 首字母--用于城市选择时排序
     */
    private String initials;
    /**
     *全拼
     */
    private String FullSpell;
    /**
     * 拼音缩写
     */
    private String acronym;
    /**
     * 序号
     */
    private Integer orderNo;
    /**
     * 父节点ID
     */
    private BusArea parent;


    private List<BusArea> childCodes = new ArrayList<BusArea>();

    public BusArea() {
        super();
    }

    public BusArea(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @Column(length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 20)
    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    /**
     * 获取上级节点对象
     *
     * @return
     */
    @ManyToOne
    @NotFound
    @JoinColumn(name = "parentId")
    public BusArea getParent() {
        return parent;
    }

    public void setParent(BusArea parent) {
        this.parent = parent;
    }
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy(value = "id ASC,orderNo asc")
    public List<BusArea> getChildCodes() {
        return childCodes;
    }

    public void setChildCodes(List<BusArea> childCodes) {
        this.childCodes = childCodes;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Column(length = 1)
    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    @Column(length = 80)
    public String getFullSpell() {
        return FullSpell;
    }

    public void setFullSpell(String fullSpell) {
        FullSpell = fullSpell;
    }
}