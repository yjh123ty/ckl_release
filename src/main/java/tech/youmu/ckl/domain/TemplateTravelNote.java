package tech.youmu.ckl.domain;

import java.util.Date;
import java.util.List;

public class TemplateTravelNote {
    private Long id;

    private String title;

    private Long routeId;
    
    private String routeName;

    private Double distance;

    private Double capitaCost;

    private String routeImg;

    private Integer dayCount;

    private Boolean isDel;

    private String createTime;

    private String modifyTime;
    
    private List<TemplateTravelNoteContent> templateTravelNoteContents;
    
    private List<String> templateTravelNoteSuitNames;
    
    private List<RouteSuit> suits;

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

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getCapitaCost() {
        return capitaCost;
    }

    public void setCapitaCost(Double capitaCost) {
        this.capitaCost = capitaCost;
    }

    public String getRouteImg() {
        return routeImg;
    }

    public void setRouteImg(String routeImg) {
        this.routeImg = routeImg;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
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


    public List<TemplateTravelNoteContent> getTemplateTravelNoteContents() {
        return templateTravelNoteContents;
    }

    public void setTemplateTravelNoteContents(List<TemplateTravelNoteContent> templateTravelNoteContents) {
        this.templateTravelNoteContents = templateTravelNoteContents;
    }

    public List<String> getTemplateTravelNoteSuitNames() {
        return templateTravelNoteSuitNames;
    }

    public void setTemplateTravelNoteSuitNames(List<String> templateTravelNoteSuitNames) {
        this.templateTravelNoteSuitNames = templateTravelNoteSuitNames;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public List<RouteSuit> getSuits() {
        return suits;
    }

    public void setSuits(List<RouteSuit> suits) {
        this.suits = suits;
    }

    
    
    
}