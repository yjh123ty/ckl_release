/**
 * @Title: Department.java
 * @Package tech.youmu.ckl.domain
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-Administrator
 * @date 2016年8月8日 下午1:04:31
 * @version V1.0
 */

package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 部门
 * @author yjh
 *
 */
public class Department {
	private Long id;
	private String name;		//部门名称
	private Integer status;		//部门状态（0：正常，-1：停用）
	private String intro;		//描述
	private Date createTime;	//录入时间
	private Date modifyTime;    //修改时间
	private Boolean isDel; //是否删除  ， 默认不删除
	
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
	
	@JsonFormat(pattern = "yyyy.MM.dd", timezone = "GMT+8")
	public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
    public Boolean getIsDel() {
        return isDel;
    }
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
    public Date getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    @Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", status=" + status + ", intro=" + intro + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
	}
	

}
