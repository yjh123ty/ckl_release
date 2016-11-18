/**
 * @Title: OrderInfo.java
 * @Package tech.youmu.ckl.domain
 * 
 * @author yjh
 * @date 2016年8月28日 下午10:09:59
 * @version V1.0
 */

package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yjh
 *	订单信息历史表
 */
public class OrderInfoHistory {
    private Long id;
	private Long empId;	               //员工id
	private Integer num;	           //当日完成订单数量
	private BigDecimal totalAmount;    //当月员工完成总金额
	private Double avgStar;            //平均获得评分星数
	private String recordMonth;        //统计的月份
	private Boolean isDel;             //是否删除
	private Date createTime;           //创建日期
	private Date modifyTime;           //修改日期
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getEmpId() {
        return empId;
    }
    public void setEmpId(Long empId) {
        this.empId = empId;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
   
    public Double getAvgStar() {
		return avgStar;
	}
	public void setAvgStar(Double avgStar) {
		this.avgStar = avgStar;
	}
	public String getRecordMonth() {
        return recordMonth;
    }
    public void setRecordMonth(String recordMonth) {
        this.recordMonth = recordMonth;
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
        return "OrderInfoHistory [id=" + id + ", empId=" + empId + ", num=" + num + ", totalAmount=" + totalAmount + ", avgStar=" + avgStar + ", recordMonth=" + recordMonth + ", isDel=" + isDel + ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
    }
    
    
	
	
}
