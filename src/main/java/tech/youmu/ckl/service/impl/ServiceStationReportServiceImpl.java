/**
 * Project Name:ckl
 * File Name:ServiceStationReportServiceImpl.java
 * Copyright (c) 成都友木科技 2016
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

import tech.youmu.ckl.domain.ServiceStationReport;
import tech.youmu.ckl.mapper.ServiceStationReportMapper;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.ServiceStationReportQuery;
import tech.youmu.ckl.service.IServiceStationReportService;

/**
 * <p>Title:ServiceStationReportServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月19日上午11:57:33</p>
 * <p>Description:服务实现类</p>
 */
@Service
public class ServiceStationReportServiceImpl extends BaseServiceImpl<ServiceStationReport> implements IServiceStationReportService {
    
	private ServiceStationReportMapper serviceStationReportMapper;
	
    /**
	 * @param
	 */
	@Autowired
	public ServiceStationReportServiceImpl(ServiceStationReportMapper serviceStationReportMapper) {
		super(serviceStationReportMapper);
		this.serviceStationReportMapper = serviceStationReportMapper;
	}

	

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IServiceStationReportService#findPageList(tech.youmu.ckl.query.ServiceStationReportQuery)
     */
    @Override
    public PageList<ServiceStationReport> findPageList(ServiceStationReportQuery query) {
        PageList<ServiceStationReport> pageList = new PageList<>();
        long count  = serviceStationReportMapper.getServiceStationReportsCountByQuery(query);
        if(count > 0) {
            pageList.setTotal(count);
            pageList.setRows(serviceStationReportMapper.findServiceStationReports(query));
        }
        return pageList;
    }

	/* (non-Javadoc)
	 * @see tech.youmu.ckl.service.IServiceStationReportService#download(tech.youmu.ckl.query.ServiceStationReportQuery, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void download(ServiceStationReportQuery baseQuery, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// 表头内容
        String[] heads = { "服务站名称", "服务站地址", "饭店收入", "酒店收入", "汽车维护收入", "汽车修理收入", "道路救援收入" , "总收入","评分" };
        
        OutputStream os = null;
        SXSSFWorkbook wb = null;
        try {
            
            // 下载保证查询后的所有数据
            baseQuery.setPageSize(Integer.MAX_VALUE);
            PageList<ServiceStationReport> pageList = this.findPageList(baseQuery);
            // 行(由于这里设置的页面显示数据是最大尺寸，因此，查询到的结果也是所有的数据，导出的文件也应该是全部的数据)
            List<ServiceStationReport> rows = pageList.getRows();
            // 把List<Order>变成List<String[]>
            List<String[]> list = new ArrayList<String[]>();
            for (ServiceStationReport report : rows) {
                String[] strings = new String[heads.length];
                strings[0] = report.getStationName() == null ? "" : report.getStationName() ;
                strings[1] = report.getStationAddress() == null ? "" : report.getStationAddress();
                strings[2] = report.getRestaurantIncome() == null ? "" : report.getRestaurantIncome().toString();
                strings[3] = report.getHotelIncome() == null ? "" : report.getHotelIncome().toString();
                strings[4] = report.getCarCareIncome() == null ? "" : report.getCarCareIncome().toString();
                strings[5] = report.getCarRepairIncome() == null ? "" : report.getCarRepairIncome().toString();
                strings[6] = report.getRoadSideIncome() == null ? "" : report.getRoadSideIncome().toString();
                strings[7] = report.getAllIncome() == null ? "" : report.getAllIncome().toString();
                strings[8] = report.getAvgStar() == null ? "" : report.getAvgStar().toString();
                
                // 最关键的方法
                list.add(strings);
            }
            // 下载相关处理
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
            // 时间戳
            String timestamp = sdf.format(new Date());
            // 返回的文件名
            String fileName = "服务站财务报表_"+ timestamp +".xlsx";
            
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
