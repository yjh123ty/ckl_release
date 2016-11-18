/**
 * @Title: EmployeeAttendance.java
 * @Package tech.youmu.ckl.domain
 * 
 * @author yjh
 * @date 2016年10月7日 下午6:03:19
 * @version V1.0
 */

package tech.youmu.ckl.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 员工每日考勤记录
 * @author yjh
 *
 */
public class EmployeeAttendance {
    
    /**
     * 计算半径
     */
    public static final Double RADII_DISTANCE = 2000d;
    
	private Long id;
	
	/**
	 * 员工
	 */
	private Employee employee;
	/**
	 * 考勤备注
	 */
	private String intro;
	
	/**
	 * 考勤时间
	 */
	private Date createTime;
	
	private Date modifyTime;
	
	private Boolean isDel;
	
	private Long employeeId;
	
	private Long stationId;

	public EmployeeAttendance() {
    }
	public EmployeeAttendance(Long employeeId, long stationId) {
	    this.employeeId = employeeId;
	    this.stationId= stationId;
	}

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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	@JsonFormat(pattern = "yyyy.MM.dd", timezone = "GMT+8")
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

	
	public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public Long getStationId() {
        return stationId;
    }
    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }
    @Override
	public String toString() {
		return "EmployeeAttendance [id=" + id + ", employee=" + employee + ", intro=" + intro + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
	}
	
	
}
