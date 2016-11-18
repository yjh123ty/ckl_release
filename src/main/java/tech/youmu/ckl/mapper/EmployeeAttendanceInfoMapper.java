/**
 * @Title: EmployeeAttendanceInfoMapper.java
 * @Package tech.youmu.ckl.mapper
 * 
 * @author yjh
 * @date 2016年10月7日 下午7:49:16
 * @version V1.0
 */

package tech.youmu.ckl.mapper;

import tech.youmu.ckl.domain.EmployeeAttendanceInfo;

/**
 * @author yjh
 *
 */
public interface EmployeeAttendanceInfoMapper extends BaseMapper<EmployeeAttendanceInfo>{
	/**
	 * 保存每个员工每月的考勤天数
	 */
	void saveAttendanceRecord();
}
