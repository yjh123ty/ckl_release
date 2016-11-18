/**
 * Project Name:ckl
 * File Name:AdminServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.Date;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.AdminMapper;
import tech.youmu.ckl.service.IAdminService;
import tech.youmu.ckl.utils.UserContext;

/**
 * <p>Title:AdminServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月8日下午2:15:44</p>
 * <p>Description:后台管理人员服务</p>
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Employee> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月8日下午2:16:15;</p>
     *	<p>Description: TODO;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        super(adminMapper);
        this.adminMapper = adminMapper;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IAdminService#checkLogin(java.lang.String)
     */
    @Override
    public Employee checkLogin(String mobile) {
        return adminMapper.checkLogin(mobile);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IEmployeeService#login(tech.youmu.ckl.domain.Employee)
     */
    @Override
    public void login(Employee admin) {
        // 设置上次登录ip
        admin.setLastIP(UserContext.getRequest().getRemoteAddr());
       // 设置上次登录时间
        admin.setLastVisit(new Date());
        adminMapper.updateById(admin);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IAdminService#changePassword(java.lang.String, java.lang.String)
     */
    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
       int row = adminMapper.changePassword(UserContext.getUser().getId(), oldPassword, newPassword);
       if(row == 1){
           return true;
       } else if(row > 1) {
           throw new RuntimeException("系统异常");
       }else {
           return false;
       }
    }
    
    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.impl.BaseServiceImpl#save(java.lang.Object)
     */
    @Override
    public boolean save(Employee t) {
        if(adminMapper.checkCount(t) > 0){
            throw new BizExecption("手机号码已经注册");
        }
        return super.save(t);
    }
    
    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.impl.BaseServiceImpl#updateById(java.lang.Object)
     */
    @Override
    public boolean updateById(Employee t) {
        if(adminMapper.checkCount(t) > 0){
            throw new BizExecption("手机号码已经注册");
        }
        return super.updateById(t);
    }

}
