/**
 * @Title: IEmployeeService.java
 * @Package tech.youmu.ckl.service
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月23日 上午11:19:39
 * @version V1.0
 */

package tech.youmu.ckl.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.EmployeeInfo;
import tech.youmu.ckl.app.vo.EmployeeTokenInfo;
import tech.youmu.ckl.app.vo.LastMonthPerformanceInfo;
import tech.youmu.ckl.app.vo.ReceiveOrderInfo;
import tech.youmu.ckl.app.vo.UserInfo;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.domain.SysRole;
import tech.youmu.ckl.query.EmployeeQuery;

/**
 * 
 * @author youmu-yjh
 * 
 */
public interface IEmployeeService extends IBaseService<Employee>{
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
    *  <p>Date:2016年8月19日下午3:14:50;</p>
    *  <p>Description: TODO;</p>
    *  @param userId
    *  @return
    */
   public UserInfo getEmployeeInfoByUserId(Integer userId);

    /**
     * <p>Author:yjh;</p>
     * @param mobile
     * @return
     */
    public Employee checkEmpLogin(String mobile);
    
    public void saveByChecking(Employee employee);
    
    /**
     * <p>Author:yjh;</p>
     * <p>Description: 根据员工id查询详情;</p>
     * @param id
     * @return
     */
    public Employee getEmployeeInfoById(Long id);
    
    public void download(EmployeeQuery baseQuery,HttpServletRequest request,
            HttpServletResponse response) throws Exception;
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月22日上午11:58:06;</p>
     *	<p>Description: 导入员工表;</p>
     *  @param importFile
     * @throws Exception 
     */
    public void importExcelFile(MultipartFile importFile);
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月22日下午1:58:02;</p>
     *	<p>Description: 下载导入模板;</p>
     *  @param request
     *  @param response
     *  @throws Exception
     */
    public void downloadTemplate(HttpServletRequest request,
            HttpServletResponse response) throws Exception;

    
    
    //------------------------------------------------------------------
    /**
     * saveEmployeeWithStations(添加一个员工并为该员工设置服务站)
     */
   void saveEmployeeWithStations(Employee employee);

   
   Employee getWithStationsById(Long id);

   /**
     * updateEmployeeWithStations(更新员工记录 和其 关联的权限)
     *
     * @param role
     */
   void updateEmployeeWithStations(Employee employee);
   
   /**
    * 
    *  <p>Author:xiongchuan;</p>
    *  <p>Date:2016年10月14日上午9:28:00;</p>
    *	<p>Description: 退出登陆;</p>
    *  @param token
    */
   public void logout(String token);

   /**
    * 
    *  <p>Author:xiongchuan;</p>
    *  <p>Date:2016年10月14日上午9:36:04;</p>
    *	<p>Description: TODO;</p>
    *  @param mobile
    *  @param password
    */
   public void updatePassword(Long employeeId, String password,String oldPassword);

   /**
    * 
    *  <p>Author:xiongchuan;</p>
    *  <p>Date:2016年10月14日上午9:36:11;</p>
    *	<p>Description: TODO;</p>
    *  @param employeeId
    *  @param password
    */
   public void verifyPassword(Long employeeId, String password);

   public boolean doVerifyCode(String mobile, String verifyCode);

   /**
    * 
    *  <p>Author:xiongchuan;</p>
    *  <p>Date:2016年10月14日下午2:21:26;</p>
    *	<p>Description: TODO;</p>
    *  @param employeeId
    *  @return
    */
   public EmployeeInfo getEmployeeInfo(Long employeeId);
   
   /**
    * 根据员工账号或者员工工号查询员工信息
    * @param mobile
    * @param empNumber
    * @return
    */
   public Employee findEmployeeByMobileOrNum(String mobile,String empNumber);


   /**
    * 
    *  <p>Author:xiongchuan;</p>
    *  <p>Date:2016年10月17日上午11:23:23;</p>
    *	<p>Description: TODO;</p>
    *  @param employeeId
    *  @param headIcon
    *  @return
    */
   public String uploadHeadIcon(Long employeeId, MultipartFile headIcon);
   
   public void update(Employee employee);

   /**
    * 
    *  <p>Author:xiongchuan;</p>
    *  <p>Date:2016年10月18日上午10:16:08;</p>
    *	<p>Description: TODO;</p>
    *  @param mobile
    *  @param password
    *  @param verifyCode
    */
   public void updatePassword(String mobile, String password, String verifyCode);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月20日上午11:14:44;</p>
     *	<p>Description: 根据职位类型查询相应的员工列表;</p>
     *  @param i
     *  @return
     */
    public List<Employee> findEmployeesByJobType(int i);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月25日上午10:28:52;</p>
     *	<p>Description: TODO;</p>
     *  @param employeeId
     *  @return
     */

    public ReceiveOrderInfo getReceiveOrderInfo(Long employeeId);

}

	