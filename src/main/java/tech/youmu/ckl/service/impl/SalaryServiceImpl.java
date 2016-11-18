/**
 * Project Name:ckl
 * File Name:SalaryServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.domain.Salary;
import tech.youmu.ckl.mapper.BaseMapper;
import tech.youmu.ckl.mapper.SalaryMapper;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.SalaryQuery;
import tech.youmu.ckl.service.ISalaryService;

/**
 * <p>Title:SalaryServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月9日下午2:41:49</p>
 * <p>Description:TODO</p>
 */
@Service
public class SalaryServiceImpl extends BaseServiceImpl<Salary> implements ISalaryService{
    
    private SalaryMapper salaryMapper;

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月9日下午2:42:06;</p>
     *	<p>Description: TODO;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public SalaryServiceImpl(SalaryMapper salaryMapper) {
        super(salaryMapper);
        this.salaryMapper = salaryMapper;
    }
    
    @Override
    public void download(SalaryQuery baseQuery,HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // 表头的内容
        String[] heads = { "员工账号", "员工姓名", "所在部门", "岗位类别","基本工资(元)","绩效月份","获得奖金(元)","实发工资(元)" };
        
        OutputStream os = null;
        SXSSFWorkbook wb = null;
        try {
            // 下载保证查询后的所有数据
            baseQuery.setPageSize(Integer.MAX_VALUE);
            PageList<Salary> pageList = this.getPageList(baseQuery);
            // 行(由于这里设置的页面显示数据是最大尺寸，因此，查询到的结果也是所有的数据，导出的文件也应该是全部的数据)
            List<Salary> rows = pageList.getRows();
            // 把List<Employee>变成List<String[]>
            List<String[]> list = new ArrayList<String[]>();
            for (Salary salary : rows) {
                String[] strings = new String[heads.length];
                Employee emp = salary.getEmployee();
                if(emp == null){
                    strings[0] = null;
                    strings[1] = null;
                    strings[2] = null;
                    strings[3] = null;
                    strings[4] = null;
                    strings[5] = null;
                    strings[6] = null;
                    strings[7] = null;
                }else{
                    strings[0] = emp.getMobile() == null?"":emp.getMobile();
                    strings[1] = emp.getRealName()== null?"":emp.getRealName();
                    
                    if(emp.getDept()==null){
                        strings[2] = "";
                      }
                      strings[2] = emp.getDept().getName();
                      
                      if(emp.getJobTitle() == null){
                        strings[3] = "";
                      }
                      strings[3] = emp.getJobTitle().getName() == null ? "" : emp.getJobTitle().getName();
                      strings[4] = salary.getBaseSalary()==null ? "" : salary.getBaseSalary().toString();
                      strings[5] = salary.getRecordMonth()==null?"" : salary.getRecordMonth();
                      strings[6] = salary.getBouns()==null?"":salary.getBouns().toString();
                      strings[7] = salary.getRealSalary()==null?"":salary.getRealSalary().toString();
                }
                
                
                list.add(strings);
            }
            // 下载相关处理
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
            // 时间戳
            String timestamp = sdf.format(new Date());
            // 返回的文件名
            String fileName = "工资明细_"+ timestamp +".xlsx";
            
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

}
