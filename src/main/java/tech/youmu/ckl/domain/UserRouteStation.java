package tech.youmu.ckl.domain;

import java.util.Date;

public class UserRouteStation {
    private Long id;

    private Long userRouteId;

    private Long stationId;
    
    private Integer status;

    private Boolean isDel;

    private String createTime;

    private String modifyTime;
    
    public UserRouteStation() {
    }

    public UserRouteStation(Long userRouteId, Long stationId,Integer status) {
        this.userRouteId = userRouteId;
        this.stationId = stationId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserRouteId() {
        return userRouteId;
    }

    public void setUserRouteId(Long userRouteId) {
        this.userRouteId = userRouteId;
    }


    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
    
    
}