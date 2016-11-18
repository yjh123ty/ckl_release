package tech.youmu.ckl.domain;


public class Track {
    private Long id;

    private Long userId;

    private String startName;

    private String endName;

    private Double distance;

    private Integer dayCount;

    private Double capitaCost;

    private Long userRouteId;

    private String routeImg;

    private Boolean isDel;

    private String createTime;

    private String modifyTime;
    
    public Track() {
    }
    
    public Track(Long userId,String startName,String endName,Double distance,Integer dayCount,Double capitaCost,Long userRouteId,String routeImg) {
        this.userId = userId;
        this.startName = startName;
        this.endName = endName;
        this.distance = distance;
        this.dayCount = dayCount;
        this.capitaCost = capitaCost;
        this.userRouteId = userRouteId;
        this.routeImg = routeImg;
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

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
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

    public Double getCapitaCost() {
        return capitaCost;
    }

    public void setCapitaCost(Double capitaCost) {
        this.capitaCost = capitaCost;
    }


    public Long getUserRouteId() {
        return userRouteId;
    }

    public void setUserRouteId(Long userRouteId) {
        this.userRouteId = userRouteId;
    }

    public String getRouteImg() {
        return routeImg;
    }

    public void setRouteImg(String routeImg) {
        this.routeImg = routeImg;
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
    
    
}