/**
 * @Title: Performance.java
 * @Package tech.youmu.ckl.domain
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-Administrator
 * @date 2016年8月8日 下午1:04:22
 * @version V1.0
 */

package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 员工绩效
 * @author yjh
 *
 */
public class Performance {
	private Long id;                //id
	private Employee employee;		//对应的员工
	private String recordMonth;     //记录月份
	private Integer keInMonth;      //当月该员工获得的总刻数
	private Double avgStar;        //当月该员工获得的平均评分
	private Integer score;	        //绩效分数
	private Boolean isDel;          //是否删除  ， 默认不删除
	private Date createTime;        //绩效时间（到月）
	private Date modifyTime;        //修改日期
	
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public String getRecordMonth() {
        return recordMonth;
    }
    public void setRecordMonth(String recordMonth) {
        this.recordMonth = recordMonth;
    }
    
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
    public Integer getKeInMonth() {
        return keInMonth;
    }
    public void setKeInMonth(Integer keInMonth) {
        this.keInMonth = keInMonth;
    }
    
    public Double getAvgStar() {
		return avgStar;
	}
	public void setAvgStar(Double avgStar) {
		this.avgStar = avgStar;
	}
	public Integer getScore() {
        return score;
    }
    public void setScore(Integer score) {
        this.score = score;
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
    @Override
    public String toString() {
        return "Performance [id=" + id + ", employee=" + employee + ", recordMonth=" + recordMonth + ", keInMonth=" + keInMonth + ", avgStar=" + avgStar + ", score=" + score + ", isDel=" + isDel + ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
    }

    
	
	
	
}
