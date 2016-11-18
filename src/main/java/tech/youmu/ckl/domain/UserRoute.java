package tech.youmu.ckl.domain;

import java.util.Date;

public class UserRoute {
    private Long id;

    private Long userId;

    private Long routeId;
    
    private String routeName;

    private String startStationName;

    private String endStationName;
    
    private String longtitude;
    
    private String latitude;
    
    private Integer status;

    private Boolean isDel;

    private String createTime;

    private String modifyTime;

    public UserRoute() {
    }
    
    public UserRoute(Long userId, Long routeId, Integer status) {
        this.userId = userId;
        this.routeId = routeId;
        this.status = status;
    }

    public UserRoute(Long userId, Long routeId, String startStationName, String endStationName,String longtitude,String latitude, Integer status) {
        this.userId = userId;
        this.routeId = routeId;
        this.status = status;
        this.startStationName =startStationName;
        this.endStationName = endStationName;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getStartStationName() {
        return startStationName;
    }

    public void setStartStationName(String startStationName) {
        this.startStationName = startStationName;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public void setEndStationName(String endStationName) {
        this.endStationName = endStationName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    
    
}