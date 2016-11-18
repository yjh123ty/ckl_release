/**
 * @Title: JobTitle.java
 * @Package tech.youmu.ckl.domain
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-Administrator
 * @date 2016年8月11日 下午5:47:22
 * @version V1.0
 */

package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 员工职称
 * @author yjh
 *
 */
public class JobTitle {
	private Long id;           //id
	private String name;       //岗位类别
	private BigDecimal ratio;  //奖金系数
	private Date createTime;   //创建时间
	private Date modifyTime;   //修改时间
	private Boolean isDel;     //是否删除
	
	
	public BigDecimal getRatio() {
        return ratio;
    }
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
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
	
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Override
    public String toString() {
        return "JobTitle [id=" + id + ", name=" + name + ", ratio=" + ratio + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
    }
	
	
}
