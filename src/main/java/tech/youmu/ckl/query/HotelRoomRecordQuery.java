/**
 * Project Name:ckl
 * File Name:HotelRoomRecordQuery.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.query;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>Title:HotelRoomRecordQuery</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月12日上午10:30:03</p>
 * <p>Description:酒店房间记录的查询对象</p>
 */
public class HotelRoomRecordQuery {
    
    private Integer currentPage = 1;
    
    private Integer pageSize = 10;
    
    private Long total = 0L;
    
    /**
     * 开始日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    
    /**
     * 结束日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    
    private Long hotelId;

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月12日下午5:01:45;</p>
     *	<p>Description: 酒店的id;</p>
     *  @return
     */
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
    
    public Date getStart(){
        if(startDate == null) {
            return DateUtils.addDays(new Date(), (this.currentPage -1) * pageSize) ;
        }else {
            return DateUtils.addDays(startDate, (this.currentPage -1) * pageSize) ;
        }
    }
    
    public Date getEnd(){
        return DateUtils.addDays(getStart(), this.pageSize) ;
    }
}
