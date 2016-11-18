/**
 * Project Name:ckl
 * File Name:AdminMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.domain.Employee;

/**
 * <p>Title:AdminMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月8日下午2:17:22</p>
 * <p>Description:后台管理员数据访问接口</p>
 */
public interface AdminMapper extends BaseMapper<Employee> {

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月8日下午2:45:40;</p>
     *	<p>Description: 根据手机号查找状态可用的后台管理人员;</p>
     *  @param mobile
     *  @return
     */
    Employee checkLogin(String mobile);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月17日上午10:43:07;</p>
     *	<p>Description: 修改管理员密码;</p>
     *  @param id
     *  @param oldPassword
     *  @param newPassword
     *  @return
     */
    int changePassword(@Param("id") Long id,@Param("oldPassword") String oldPassword,@Param("newPassword") String newPassword);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月20日下午5:02:53;</p>
     *	<p>Description: 添加授权用户时判断员工是否存在;</p>
     *  @param t
     *  @return
     */
    int checkCount(Employee t);

}
