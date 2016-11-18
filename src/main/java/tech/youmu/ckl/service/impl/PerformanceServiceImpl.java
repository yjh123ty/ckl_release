/**
 * @Title: PerformanceServiceImpl.java
 * @Package tech.youmu.ckl.service.impl
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月19日 下午2:05:25
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

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.app.vo.LastMonthPerformanceInfo;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.domain.Performance;
import tech.youmu.ckl.mapper.PerformanceMapper;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.PerformanceQuery;
import tech.youmu.ckl.service.IPerformanceService;
import tech.youmu.ckl.utils.DateUtil;

/**
 * 员工绩效
 * @author youmu-yjh
 * 
 */
@Service
public class PerformanceServiceImpl extends BaseServiceImpl<Performance> implements IPerformanceService {

   
    private PerformanceMapper performanceMapper;
    
    /**
     * @param baseMapper
     */
    @Autowired
    public PerformanceServiceImpl(PerformanceMapper performanceMapper) {
        super(performanceMapper);
        this.performanceMapper = performanceMapper;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IPerformanceService#download(tech.youmu.ckl.query.PerformanceQuery, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void download(PerformanceQuery baseQuery, HttpServletRequest request, HttpServletResponse response) throws Exception {
      //下载报表的表头内容
      String[] heads = { "工号", "员工账号", "员工姓名", "所在部门", "职位", "所在服务站","总刻数","平均评分","分数","绩效月份" };
      
      OutputStream os = null;
      
      SXSSFWorkbook wb = null;
      
      try {
          
          // 下载保证查询后的所有数据
          baseQuery.setPageSize(Integer.MAX_VALUE);
          PageList<Performance> pageList = this.getPageList(baseQuery);
          // 行(由于这里设置的页面显示数据是最大尺寸，因此，查询到的结果也是所有的数据，导出的文件也应该是全部的数据)
          List<Performance> rows = pageList.getRows();
          // 把List<Order>变成List<String[]>
          List<String[]> list = new ArrayList<String[]>();
          for (Performance performance : rows) {
              String[] strings = new String[heads.length];
              Employee employee = performance.getEmployee();
              if(employee==null){
                  strings[0] = "";
                  strings[1] = "";
                  strings[2] = "";
                  strings[3] = "";
                  strings[4] = "";
                  strings[5] = "";
                  strings[6] = "";
                  strings[7] = "";
                  strings[8] = "";
                  strings[9] = "";
                  strings[10] = "";
              }else{
                  strings[0] = employee.getEmpNumber()==null?"":employee.getEmpNumber();
                  strings[1] = employee.getMobile() == null ? "" : employee.getMobile();
                  strings[2] = employee.getRealName() == null ? "" : employee.getRealName();
                  
                  if(employee.getDept() == null){
                      strings[3] = "";
                  }else{
                      strings[3] = employee.getDept().getName()== null ? "" : employee.getDept().getName();
                  }
                  
                  if(employee.getJobTitle() == null){
                      strings[4] = "";
                  }else{
                      strings[4] = employee.getJobTitle().getName()== null ? "" : employee.getJobTitle().getName();
                  }
                  
                  if(employee.getStation() == null){
                      strings[5] = "";
                  }else{
                      strings[5] = employee.getStation().getName()== null ? "" : employee.getStation().getName();
                  }
                  
                  strings[6] = performance.getKeInMonth() == null ? "" : performance.getKeInMonth().toString();
                  strings[7] = performance.getAvgStar() == null ? "" : performance.getAvgStar().toString();
                  strings[8] = performance.getScore() == null ? "" : performance.getScore().toString();
                  strings[9] = performance.getRecordMonth() == null ? "" : performance.getRecordMonth();
              }
              
              list.add(strings);
          }
          // 下载相关处理
          SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
          // 时间戳
          String timestamp = sdf.format(new Date());
          // 返回的文件名
          String fileName = "员工绩效报表_"+ timestamp +".xlsx";
          
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

    @Override
    public List<LastMonthPerformanceInfo> getLastMonthPerformanceInfo(Long employeeId) {
        return performanceMapper.getLastMonthPerformanceInfo(employeeId,DateUtil.getLastMonthOfToday());
    }
}
