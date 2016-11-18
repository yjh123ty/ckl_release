package tech.youmu.ckl.domain;

public class CommissionPointDetail {
    private Long id;

    private Long userId;

    private String rechargeUserMobile;

    private Long userBillId;

    private String rechargeComboName;

    private Boolean isBalance;

    private Double pointCount;

    private Integer status;

    private Boolean isDel;
    
    public CommissionPointDetail(Long userId,String rechargeUserMobile,Long userBillId,String rechargeComboName,Boolean isBalance,Double pointCount,Integer status) {
        this.userBillId = userBillId;
        this.userId = userId;
        this.rechargeComboName = rechargeComboName;
        this.rechargeUserMobile = rechargeUserMobile;
        this.isBalance = isBalance;
        this.pointCount = pointCount;
        this.status = status;
    }

    public CommissionPointDetail() {
        // TODO Auto-generated constructor stub
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




    public Long getUserBillId() {
        return userBillId;
    }

    public void setUserBillId(Long userBillId) {
        this.userBillId = userBillId;
    }

    public Boolean getIsBalance() {
        return isBalance;
    }

    public void setIsBalance(Boolean isBalance) {
        this.isBalance = isBalance;
    }

    
    public Double getPointCount() {
        return pointCount;
    }

    public void setPointCount(Double pointCount) {
        this.pointCount = pointCount;
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


    public String getRechargeUserMobile() {
        return rechargeUserMobile;
    }

    public void setRechargeUserMobile(String rechargeUserMobile) {
        this.rechargeUserMobile = rechargeUserMobile;
    }

    public String getRechargeComboName() {
        return rechargeComboName;
    }

    public void setRechargeComboName(String rechargeComboName) {
        this.rechargeComboName = rechargeComboName;
    }
    
    
    
}