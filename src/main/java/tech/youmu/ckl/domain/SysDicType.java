package tech.youmu.ckl.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:SysDicType</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月30日上午10:31:25</p>
 * <p>Description:数据字典类型</p>
 */
public class SysDicType {
    
    private Long id;

    private String name;

    private String intro;

    private Date createTime;

    private Date modifyTime;
    
    private Boolean isDel;
    
    private List<SysDicDetail> details = new ArrayList<>(0);

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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
    
    

    public List<SysDicDetail> getDetails() {
        return details;
    }

    public void setDetails(List<SysDicDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "SysDicType [id=" + id + ", name=" + name + ", intro=" + intro + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
    }
}