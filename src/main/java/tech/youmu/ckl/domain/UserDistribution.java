package tech.youmu.ckl.domain;

import java.util.Date;

public class UserDistribution {
    private Long id;

    private Long userId;

    private Long parentLv1;

    private Long parentLv2;

    private Long parentLv3;

    private Boolean isDel;

    public UserDistribution() {
    }

    public UserDistribution(Long userId,Long parentLv1,Long parentLv2,Long parentLv3) {
           this.userId = userId;
           this.parentLv1 =parentLv1;
           this.parentLv2 = parentLv2;
           this.parentLv3 = parentLv3;
    }

    public UserDistribution(Long userId,Long parentLv1) {
        this.userId = userId;
        this.parentLv1 =parentLv1;
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

    public Long getParentLv1() {
        return parentLv1;
    }

    public void setParentLv1(Long parentLv1) {
        this.parentLv1 = parentLv1;
    }

    public Long getParentLv2() {
        return parentLv2;
    }

    public void setParentLv2(Long parentLv2) {
        this.parentLv2 = parentLv2;
    }

    public Long getParentLv3() {
        return parentLv3;
    }

    public void setParentLv3(Long parentLv3) {
        this.parentLv3 = parentLv3;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

}