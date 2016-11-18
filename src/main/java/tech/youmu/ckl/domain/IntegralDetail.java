package tech.youmu.ckl.domain;

import java.util.Date;

public class IntegralDetail {
    private Long id;

    private Integer integral;

    private String remark;

    private Long userId;

    private String createTime;

    private String modifyTime;

    private Boolean isDel;

    public IntegralDetail() {
    }
    
    public IntegralDetail(Long userId, String remark, Integer integral) {
            this.userId = userId;
            this.remark = remark;
            this.integral = integral;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
}