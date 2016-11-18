/**
 * @Title: EmployeeAttendanceQuery.java
 * @Package tech.youmu.ckl.query
 * 
 * @author yjh
 * @date 2016年10月7日 下午6:29:09
 * @version V1.0
 */

package tech.youmu.ckl.query;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

/**
 * 当前员工的考勤天数明细的查询对象
 * @author yjh
 *
 */
public class EmployeeAttendanceQuery extends BaseQuery{
	
	/**
	 * 员工id
	 */
	private Long employeeId;
	
	/**
	 * 考勤天数的记录月份
	 */
	private String recordMonth;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getRecordMonth() {
		return recordMonth;
	}

	public void setRecordMonth(String recordMonth) {
		this.recordMonth = recordMonth;
	}
	
	
	
}
