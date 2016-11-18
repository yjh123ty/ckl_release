/**
 * @Title: EmployeeServiceImpl.java
 * @Package tech.youmu.ckl.service.impl
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月23日 上午11:35:20
 * @version V1.0
 */

package tech.youmu.ckl.service.impl;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.EmployeeInfo;
import tech.youmu.ckl.app.vo.EmployeeTokenInfo;
import tech.youmu.ckl.app.vo.LastMonthPerformanceInfo;
import tech.youmu.ckl.app.vo.ReceiveOrderInfo;
import tech.youmu.ckl.app.vo.UserInfo;
import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.domain.Station;
import tech.youmu.ckl.domain.SysMenu;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.EmployeeMapper;
import tech.youmu.ckl.query.EmployeeQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IEmployeeService;
import tech.youmu.ckl.utils.CheckCodeUtil;
import tech.youmu.ckl.utils.DateUtil;
import tech.youmu.ckl.utils.EmployeeTokenUtil;
import tech.youmu.ckl.utils.ImageURLUtil;
import tech.youmu.ckl.utils.ImportExcelUtils;
import tech.youmu.ckl.utils.MD5Util;
import tech.youmu.ckl.utils.UploadUtils;
import tech.youmu.ckl.utils.UserTokenUtil;

/**
 * 
 * @author youmu-yjh
 * 
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService{
    
    private EmployeeMapper empMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper empMapper) {
        super(empMapper);
        this.empMapper = empMapper;
    }
    
    @Override
    public void saveByChecking(Employee employee) {
    	if(employee == null){
    		throw new BizExecption("保存信息为空！");
    	}
        //保存
        this.saveEmployeeWithStations(employee);
    };
    
    @Override
	public Employee findEmployeeByMobileOrNum(String mobile,String empNumber){
    	return empMapper.findEmployeeByMobileOrNum(mobile, empNumber);
    }
    
    
    /* 
     * @see tech.youmu.ckl.service.IEmployeeService#disableByEmpId(java.lang.Long)
     */
    @Override
    public void disableByEmpId(Long id) {
    	Employee employee = empMapper.getById(id);
    	if(employee==null){
    	    throw new BizExecption("未找到该员工的信息！");
    	}
    	Integer status = employee.getStatus();
    	if(status!=null&&status==StatusConst.BLOCKED){
    	    throw new BizExecption("该员工已经冻结！");
    	}
        empMapper.disableByEmpId(id);
    }

    /* 
     * @see tech.youmu.ckl.service.IEmployeeService#invokeByEmpId(java.lang.Long)
     */
    @Override
    public void invokeByEmpId(Long id) {
    	Employee employee = empMapper.getById(id);
    	if(employee==null){
    		throw new BizExecption("未找到该员工的信息！");
    	}
    	Integer status = employee.getStatus();
    	if(status!=null&&status==StatusConst.ZERO){
    	    throw new BizExecption("该员工已经开通！");
    	}
        empMapper.invokeByEmpId(id);
    }


    /* 
     * @see tech.youmu.ckl.service.IEmployeeService#getEmployeeInfoByUserId(java.lang.Integer)
     */
    @Override
    public UserInfo getEmployeeInfoByUserId(Integer userId) {
        return empMapper.getEmployeeInfoByUserId(userId);
    }

    /* 
     * @see tech.youmu.ckl.service.IEmployeeService#checkEmpLogin(java.lang.String)
     */
    @Override
    public Employee checkEmpLogin(String mobile) {
        return empMapper.checkEmpLogin(mobile);
    }

	/**
	 * @see tech.youmu.ckl.service.IEmployeeService#getEmployeeInfoById(java.lang.Long)
	 */
	@Override
	public Employee getEmployeeInfoById(Long id) {
		return empMapper.getEmployeeInfoById(id);
	}
	
	@Override
	public void importExcelFile(MultipartFile upload) {
		if (upload != null) {
			List<String[]> list;
            try {
                list = ImportExcelUtils.importFile(upload);
                for (String[] strings : list) {
                    Employee employee = new Employee();
                   
                    //去重检查
                    if(empMapper.findEmployeeByMobileOrNum(strings[0], strings[1])!=null){
                        throw new BizExecption("导入数据为已存在数据");
                    }
                    employee.setEmpNumber(strings[0]);
                    employee.setMobile(strings[1]);
                    employee.setRealName(strings[2]);
                    
                    if(StringUtils.isBlank(strings[3])){
                        employee.setSalaryLevel(0);
                    }
                    Integer salaryLevel = Integer.valueOf(strings[3]);
                    if(salaryLevel>9 || salaryLevel<1){
                        throw new BizExecption("输入的值必须在1-9之间");
                    }
                    employee.setSalaryLevel(salaryLevel);
                    
                    
                    employee.setPassword(MD5Util.MD5("123456"));
                    employee.setStatus(Employee.EMPLOYEE_STATUS_INVOKE);
                    employee.setCreateTime(new Date());
                    // 数据持久化
                    empMapper.save(employee);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizExecption("导入异常！");
            }
		}else{
			throw new BizExecption("导入文件为空！");
		}
	}

	@Override
	public void downloadTemplate(HttpServletRequest request,
            HttpServletResponse response) throws Exception{
	    // 表头的内容是固定
	    String[] heads = { "工号", "员工账号", "员工姓名","薪点级别(1-9级)" };
	    OutputStream os = null;
        SXSSFWorkbook wb = null;
        try {
            List<String[]> list = new ArrayList<String[]>();
            // 返回的文件名
            String fileName = "员工导入模板.xlsx";
            byte[] fileNameByte = fileName.getBytes("GBK");
           String filename = new String(fileNameByte, "ISO8859-1");
            wb = this.download(heads, list);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename="+ filename);
            os = response.getOutputStream();
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace(); 
        } finally{  
            os.flush();  
            os.close();  
        } 
	}

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IEmployeeService#dodownload(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void download(EmployeeQuery baseQuery,HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        // 表头的内容是固定
        String[] heads = { "工号", "员工账号", "员工姓名", "所在服务站","管理的服务站", "岗位类别","薪点级别","注册时间" };
        
        OutputStream os = null;
        SXSSFWorkbook wb = null;
        try {
            // 下载保证查询后的所有数据
            baseQuery.setPageSize(Integer.MAX_VALUE);
            PageList<Employee> pageList = this.getPageList(baseQuery);
            // 行(由于这里设置的页面显示数据是最大尺寸，因此，查询到的结果也是所有的数据，导出的文件也应该是全部的数据)
            List<Employee> rows = pageList.getRows();
            // 把List<Employee>变成List<String[]>
            List<String[]> list = new ArrayList<String[]>();
            for (Employee emp : rows) {
                String[] strings = new String[heads.length];
                strings[0] = emp.getId().toString();
                strings[1] = emp.getMobile() == null?"":emp.getMobile();
                strings[2] = emp.getRealName()== null?"":emp.getRealName();
                
                Station station = emp.getStation();
                List<Station> stations = emp.getStations();
                int stationSize = stations.size();
                String stationStr = "";
                if(station==null && stationSize>0){
                    //遍历每一个服务站
                    for (int i=0;i<stationSize;i++) {
                        Station stationByManager = stations.get(i);
                        if(i == stationSize-1){
                            stationStr += stationByManager.getName();
                        }else {
                            stationStr += stationByManager.getName() + ",";
                        }
                    }
                  strings[3] = "";
                  strings[4] = stationStr;
                }else if(station!=null && stations.size()==0){
                    strings[3] = station.getName();
                    strings[4] = "";
                }else{
                    strings[3] = "";
                    strings[4] = "";
                }
                
                if(emp.getJobTitle() == null){
                  strings[5] = "";
                }
                strings[5] = emp.getJobTitle().getName() == null ? "" : emp.getJobTitle().getName();
                
                Integer salaryLevel = emp.getSalaryLevel();
                if(salaryLevel==null){
                    strings[6] = "";
                }else{
                    strings[6] = salaryLevel.toString();
                }
                
                strings[7] = emp.getCreateTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(emp.getCreateTime());
                
                list.add(strings);
            }
            // 下载相关处理
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
            // 时间戳
            String timestamp = sdf.format(new Date());
            // 返回的文件名
            String fileName = "员工表_"+ timestamp +".xlsx";
            
            byte[] fileNameByte = fileName.getBytes("GBK");
            String filename = new String(fileNameByte, "ISO8859-1");
           
            wb = this.download(heads, list);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename="+ filename);
            os = response.getOutputStream();
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace(); 
        } finally{  
            os.flush();  
            os.close();  
        } 
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IEmployeeService#saveEmployeeWithStations(tech.youmu.ckl.domain.Employee)
     */
    @Override
    public void saveEmployeeWithStations(Employee employee) {
        // 1. 添加并获取主键
        // 2. 根据主键建立和menus的一对多的关系
        if(StringUtils.isBlank(employee.getRealName())){
            throw new BizExecption("请输入姓名！");
        }
        if(StringUtils.isBlank(employee.getMobile())){
            throw new BizExecption("请输入手机号！");
        }
        empMapper.saveAndGetId(employee);
        List<Station> stations = employee.getStations();
        // 校验菜单列表是否有数据
        if(stations != null && stations.size() > 0) {
            empMapper.saveEmployeeStations(employee);
        }
        
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IEmployeeService#getWithStationsById(java.lang.Long)
     */
    @Override
    public Employee getWithStationsById(Long id) {
        return empMapper.getWithStationsById(id);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IEmployeeService#updateEmployeeWithStations(tech.youmu.ckl.domain.Employee)
     */
    @Override
    public void updateEmployeeWithStations(Employee employee) {
        /**
         * 1、更新角色的基础属性 直接调用updateById
         * 2、更新角色关联的菜单权限
         *  2.1、删除原有的关联(角色关联菜单) deleteRoleMenus(SysRole role)
         *  2.2、添加现有的关联关系 saveRoleMenus(SysRole role)
         */
        updateById(employee);
        empMapper.deleteEmployeeStationsById(employee);
        List<Station> stations = employee.getStations();
        // 校验菜单列表是否有数据
        if (stations != null && stations.size() > 0) {
            empMapper.saveEmployeeStations(employee);
        }
        
    }

    @Override
    public void logout(String token) {
        EmployeeTokenUtil.removeEmployeeTokenInfo(token);
    }

    @Override
    public void updatePassword(Long employeeId, String password,String oldPassword) {
        Employee employee = empMapper.getById(employeeId);
        if(employee == null){
            throw new BizExecption("该用户不存在");
        }
        if(!MD5Util.MD5(oldPassword).equals(employee.getPassword())){
            throw new BizExecption("旧密码错误");
        }
        employee.setPassword(MD5Util.MD5(password));
        empMapper.update(employee);
        
    }

    @Override
    public void verifyPassword(Long employeeId, String password) {
        Employee employee =  empMapper.getById(employeeId);
        if(employee == null){
            throw new BizExecption("没有该用户");
        }
        if(!MD5Util.MD5(password).equals(employee.getPassword())){
            throw new BizExecption("密码错误");
        }
    }

    @Override
    public boolean doVerifyCode(String mobile, String verifyCode) {
        Employee employee = empMapper.getByPhoneNumber(mobile);
        if(employee ==null){
            throw new BizExecption("该手机没有注册");
        }
        return CheckCodeUtil.verify(mobile, verifyCode);
    }

    @Override
    public EmployeeInfo getEmployeeInfo(Long employeeId) {
        EmployeeInfo employeeInfo= empMapper.getEmployeeInfo(employeeId);
        employeeInfo.setHeadIcon(ImageURLUtil.fillPath(employeeInfo.getHeadIcon()));
        return employeeInfo;
    }


    @Override
    public String uploadHeadIcon(Long employeeId, MultipartFile headIcon) {
        String headIconPath = UploadUtils.uploadFile(headIcon, UploadUtils.HEAD_EMPLOYEE_ICON_PATH);
        Employee employee = new Employee();
        employee.setHeadIcon(headIconPath);
        employee.setId(employeeId);
        empMapper.update(employee);
        return ImageURLUtil.fillPath(headIconPath);
    }
    
    @Override
    public void updatePassword(String mobile, String password, String verifyCode) {
        Employee employee = empMapper.getByPhoneNumber(mobile);
        if(employee == null){
            throw new BizExecption("该手机号没有注册");
        }
        if(!CheckCodeUtil.verify(mobile, verifyCode)){
            throw new BizExecption("验证码错误");
        }
        employee.setPassword(MD5Util.MD5(password));
        empMapper.update(employee);
        
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IEmployeeService#update(tech.youmu.ckl.domain.Employee)
     */
    @Override
    public void update(Employee employee) {
        if(employee == null || employee.getId() == null){
            throw new BizExecption("员工不能为空！");
        }
        empMapper.update(employee);
        
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IEmployeeService#findEmployeesByJobType(int)
     */
    @Override
    public List<Employee> findEmployeesByJobType(int i) {
        return empMapper.findEmployeesByJobType(i);
    }


    @Override
    public ReceiveOrderInfo getReceiveOrderInfo(Long employeeId) {
        ReceiveOrderInfo receiveOrderInfo =  empMapper.getReceiveOrderInfo(employeeId);
        if (receiveOrderInfo ==null) {
            return new ReceiveOrderInfo(StatusConst.FALSE);
        }
        return receiveOrderInfo;
    }
}
