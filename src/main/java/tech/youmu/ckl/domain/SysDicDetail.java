package tech.youmu.ckl.domain;

import java.util.Date;

/**
 * <p>Title:SysDicDetail</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月30日上午10:35:57</p>
 * <p>Description:数据字典详细</p>
 */
public class SysDicDetail {
    
    private Long id;

    private String name;

    private SysDicType type;
    
    /**
     * 区分统一字典目录下的不同的详细
     */
    private Integer identity;

    private Boolean isDel;
    
    private Date createTime;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    

    public SysDicType getType() {
        return type;
    }

    public void setType(SysDicType type) {
        this.type = type;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }
}