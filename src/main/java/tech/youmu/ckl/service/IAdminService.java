/**
 * Project Name:ckl
 * File Name:IAdminService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import tech.youmu.ckl.domain.Employee;

/**
 * <p>Title:IAdminService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月8日下午2:15:15</p>
 * <p>Description:TODO</p>
 */
public interface IAdminService extends IBaseService<Employee> {
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月8日下午2:39:28;</p>
     *  <p>
     *      Description: 
     *          根据登录信息查找登录人员
     *          以下两种情况返回空：
     *          1. 根据手机号没有找到返回空
     *          2. 根据手机号找到了该员工  并且 该员工状态为不可用返回为空
     *      ;
     *  </p>
     *  @param mobile
     *  @return
     */
    public Employee checkLogin(String mobile);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月8日下午2:58:02;</p>
     *	<p>Description: 添加登录信息;</p>
     *  @param admin
     */
    public void login(Employee admin);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月17日上午10:38:44;</p>
     *	<p>Description: TODO;</p>
     *  @param oldPassword
     *  @param newPassword
     */
    public boolean changePassword(String oldPassword, String newPassword);
}
