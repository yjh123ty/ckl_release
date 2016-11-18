/**
 * @Title: UserMapper.java
 * @Package tech.youmu.ckl.mapper
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 上午10:54:48
 * @version V1.0
 */

package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.EmployeeInfo;
import tech.youmu.ckl.app.vo.LastMonthPerformanceInfo;
import tech.youmu.ckl.app.vo.ReceiveOrderInfo;
import tech.youmu.ckl.app.vo.UserInfo;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.domain.SysRole;

/**
 * @ClassName: EmployeeMapper
 * @Description: 员工的数据操作mapper
 * @author youmu-yjh
 * @date 2016年8月2日 上午10:09:55
 *
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    
    
    void saveEmployeeSysMessages(Employee employee);
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年10月21日下午5:44:39;</p>
     *	<p>Description: 根据菜单id和员工id判断是否拥有该业务的访问权限;</p>
     *  @param menuId
     *  @param employeeId
     *  @return
     */
    List<Employee> getRoleMenusById(@Param("menuId")Long menuId,@Param("employeeId")Long employeeId);
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年10月21日下午5:58:01;</p>
     *	<p>Description: 获取所有的员工-角色-权限 视图;</p>
     *  @return
     */
    List<Employee> getRoleMenusInfo();

	/**
	 * 根据员工名和密码进行登录验证
	 * @param mobile
	 * @param password
	 * @return
	 */
	public Employee checkEmpLogin(String mobile);

	/**
	 * 禁用员工
	 * @param id
	 */
	public void disableByEmpId(Long id);

	/**
	 * 开通员工
	 * @param id
	 */
	public void invokeByEmpId(Long id);


	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月19日上午10:08:07;</p>
	 *	<p>Description: 根据电话类型查询员工;</p>
	 *  @param mobile 电话
	 */
	public Employee getByPhoneNumber(@Param("mobile")String mobile);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月19日下午3:14:50;</p>
	 *	<p>Description: TODO;</p>
	 *  @param userId
	 *  @return
	 */
	public UserInfo getEmployeeInfoByUserId(Integer userId);
	
	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年9月9日下午3:27:45;</p>
	 *	<p>Description: 通过订单查询员工平均星数;</p>
	 *  @return
	 */
    public List<Employee> findAvgStarByOrder();

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月9日下午3:36:14;</p>
     *	<p>Description: 批量更新员工平均星数;</p>
     *  @param employees
     */
    public void batchUpdateEmployeeAvgStar(@Param("list")List<Employee> employees);

    
    /**
     * <p>Author:yjh;</p>
     * <p>Description: 根据员工id查询详情;</p>
     * @return
     */
    public Employee getEmployeeInfoById(Long id);
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年10月8日下午3:42:07;</p>
     *	<p>Description: 导入时，判断员工号和电话号码是否已经存在;</p>
     *  @param mobile
     *  @param realName
     */
    public Employee findEmployeeByMobileOrNum(@Param("mobile")String mobile,@Param("empNumber")String empNumber);
    
    
    /**
     * saveAndGetId(保存一个员工并返回该员工的主键)
    */
   public void saveAndGetId(Employee employee);

   public void saveEmployeeStations(Employee employee);
   
   public Employee getWithStationsById(Long id);

   public void deleteEmployeeStationsById(Employee employee);

   /**
    * 
    *  <p>Author:xiongchuan;</p>
    *  <p>Date:2016年10月14日下午2:22:08;</p>
    *	<p>Description: TODO;</p>
    *  @param employeeId
    *  @return
    */
   public EmployeeInfo getEmployeeInfo(Long employeeId);

   
   public void update(Employee employee);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月20日上午11:15:48;</p>
     *	<p>Description: TODO;</p>
     *  @param i
     *  @return
     */
    public List<Employee> findEmployeesByJobType(int i);

    void setEmployeeOrderId(@Param("empId")Long empId,@Param("orderId")Long orderId,@Param("numInCount")Integer numInCount);

    ReceiveOrderInfo getReceiveOrderInfo(Long employeeId);
}
