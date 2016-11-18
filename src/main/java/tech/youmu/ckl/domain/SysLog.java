package tech.youmu.ckl.domain;

import java.util.Date;

public class SysLog {

    private Long id;
    
    /**
     * 操作用户
     */
    private String oprUserMobile;
    /**
     * 操作时间
     */
    private Date oprTime;
    
    /**
     * 操作用户ip
     */
    private String oprUserIp;
    
    /**
     * 操作用户客户端
     */
    private String userAgent;
    
    /**
     * 请求的资源地址
     */
    private String requestUri;
    
    /**
     * 操作的名称
     */
    private String oprName;
    
    /**
     *是否含有异常
     */
    private Boolean isException = false;
    
    /**
     * 操作详细
     */
    private String oprDetail;

    public Long getId() {
        return id;
    }


    public String getOprUserMobile() {
        return oprUserMobile;
    }

    public void setOprUserMobile(String oprUserMobile) {
        this.oprUserMobile = oprUserMobile;
    }

    public Date getOprTime() {
        return oprTime;
    }

    public void setOprTime(Date oprTime) {
        this.oprTime = oprTime;
    }

    public String getOprUserIp() {
        return oprUserIp;
    }

    public void setOprUserIp(String oprUserIp) {
        this.oprUserIp = oprUserIp;
    }


    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getOprName() {
        return oprName;
    }

    public void setOprName(String oprName) {
        this.oprName = oprName;
    }

    public Boolean getIsException() {
        return isException;
    }

    public void setIsException(Boolean isException) {
        this.isException = isException;
    }

    public String getOprDetail() {
        return oprDetail;
    }

    public void setOprDetail(String oprDetail) {
        this.oprDetail = oprDetail;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SysLog [id=" + id + ", oprUserMobile=" + oprUserMobile + ", oprTime=" + oprTime + ", oprUserIp=" + oprUserIp + ", userAgent=" + userAgent + ", requestUri=" + requestUri + ", oprName=" + oprName + ", isException=" + isException + ", oprDetail=" + oprDetail + "]";
    }
}