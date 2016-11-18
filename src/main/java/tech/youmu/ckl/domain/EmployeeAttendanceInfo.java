/**
 * @Title: Attendance.java
 * @Package tech.youmu.ckl.domain
 * 
 * @author yjh
 * @date 2016年10月7日 下午5:55:48
 * @version V1.0
 */

package tech.youmu.ckl.domain;

import java.util.Date;

/**
 * 员工每月考勤记录表
 * @author yjh
 *
 */
public class EmployeeAttendanceInfo {
	private Long id;
	
	/**
	 * 员工id
	 */
	private Employee employee;
	
	/**
	 * 当月总上班天数
	 */
	private Integer days;
	
	/**
	 * 记录月份
	 */
	private String recordMonth;
	
	private Date createTime;
	
	private Date modifyTime;
	
	private Boolean isDel;

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

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getRecordMonth() {
		return recordMonth;
	}

	public void setRecordMonth(String recordMonth) {
		this.recordMonth = recordMonth;
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

	@Override
	public String toString() {
		return "EmployeeAttendanceInfo [id=" + id + ", employee=" + employee + ", days=" + days + ", recordMonth="
				+ recordMonth + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
	}
	
	
	
}
