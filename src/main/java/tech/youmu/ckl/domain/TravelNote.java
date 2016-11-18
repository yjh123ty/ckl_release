package tech.youmu.ckl.domain;

import java.util.List;

public class TravelNote {
    private Long id;

    private String title;

    private Long userRouteId;
    
    private Boolean isDel;

    private String createTime;

    private String modifyTime;

    private Double distance;

    private List<String> suitNames;

    private Integer dayCount;

    private String departTime;
    
    private Double capitaCost;

    private Long userId;
    
    private String userMobile;

    private String routeImg;
    
    private String routeName;
    
    private Long routeId;

    private List<TravelNoteContent> travelNoteContents;

    public TravelNote() {
    }
    
    public TravelNote(Long id, String title, Integer dayCount, Double capitaCost) {
           this.id =id;
           this.title = title;
           this.dayCount =dayCount;
           this.capitaCost = capitaCost;
    }

    public TravelNote(Long userRouteId) {
        this.userRouteId = userRouteId;
    }

    public TravelNote(String title,Double distance,Integer dayCount,Double capitaCost,String routeImg,Long userRouteId, String departTime,Long userId) {
        this.userRouteId = userRouteId;
        this.departTime =departTime;
        this.userId =userId;
        this.title = title;
        this.distance = distance;
        this.dayCount = dayCount;
        this.capitaCost = capitaCost;
        this.routeImg = routeImg;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserRouteId() {
        return userRouteId;
    }

    public void setUserRouteId(Long userRouteId) {
        this.userRouteId = userRouteId;
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



    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getCapitaCost() {
        return capitaCost;
    }

    public void setCapitaCost(Double capitaCost) {
        this.capitaCost = capitaCost;
    }

    public List<TravelNoteContent> getTravelNoteContents() {
        return travelNoteContents;
    }

    public void setTravelNoteContents(List<TravelNoteContent> travelNoteContents) {
        this.travelNoteContents = travelNoteContents;
    }

    public List<String> getSuitNames() {
        return suitNames;
    }

    public void setSuitNames(List<String> suitNames) {
        this.suitNames = suitNames;
    }

    public String getRouteImg() {
        return routeImg;
    }

    public void setRouteImg(String routeImg) {
        this.routeImg = routeImg;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }
    
    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    
    

}