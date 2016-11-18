/**
 * @Title: EmployeeAttendanceMapper.java
 * @Package tech.youmu.ckl.mapper
 * 
 * @author yjh
 * @date 2016年10月7日 下午6:09:23
 * @version V1.0
 */

package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.TodayAttendanceInfo;
import tech.youmu.ckl.domain.EmployeeAttendance;
import tech.youmu.ckl.query.EmployeeAttendanceQuery;

/**
 * 员工每日考勤记录
 * @author yjh
 *
 */
public interface EmployeeAttendanceMapper extends BaseMapper<EmployeeAttendance>{

	/**
	 * 获取当月某位员工的打卡明细记录
	 */
    List<EmployeeAttendance> getDetailsByEmpIdAndRecordMonth(EmployeeAttendanceQuery query);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月17日下午2:40:47;</p>
     *	<p>Description: TODO;</p>
     *  @param employeeId
     *  @param date
     *  @return
     */
    List<TodayAttendanceInfo> findTodayAttendanceInfo(@Param("employeeId")Long employeeId,@Param("date") String date);

    boolean isTodayAttendance(@Param("employeeId")Long employeeId, @Param("stationId")Long stationId, @Param("date")String date);
}
