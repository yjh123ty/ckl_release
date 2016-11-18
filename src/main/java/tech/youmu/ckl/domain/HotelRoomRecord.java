/**
 * Project Name:ckl
 * File Name:HotelRoomRecord.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.util.Date;

/**
 * <p>
 * Title:HotelRoomRecord
 * </p>
 * @author zh
 * @version v1.0
 *          <p>
 *          Date:2016年9月12日下午3:47:34
 *          </p>
 *          <p>
 *          Description:一个酒店的房间记录
 *          </p>
 */
public class HotelRoomRecord {
    
    private Long id;
    
    private Integer onlineNum;

    private Integer offlineNum;

    private Date bookDate;

    private Date modifyTime;

    private Date createTime;

    private Boolean isDel;
    
    private HotelRoom room;
    
    public HotelRoomRecord(){
        
    }
    
    public HotelRoomRecord(Integer onlineNum, Integer offlineNum, Date bookDate, HotelRoom room) {
        this.onlineNum = onlineNum;
        this.offlineNum = offlineNum;
        this.bookDate = bookDate;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(Integer onlineNum) {
        this.onlineNum = onlineNum;
    }

    public Integer getOfflineNum() {
        return offlineNum;
    }

    public void setOfflineNum(Integer offlineNum) {
        this.offlineNum = offlineNum;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }


    public HotelRoom getRoom() {
        return room;
    }

    public void setRoom(HotelRoom room) {
        this.room = room;
    }
    
    @Override
    public String toString() {
        return "HotelRoomRecord [id=" + id + ", onlineNum=" + onlineNum + ", offlineNum=" + offlineNum + ", bookDate=" + bookDate + ", modifyTime=" + modifyTime + ", createTime=" + createTime + ", isDel=" + isDel + ", room=" + room + "]";
    }
}
