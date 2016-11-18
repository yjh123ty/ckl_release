package tech.youmu.ckl.domain;

public class CommissionDetail {
    private Long id;

    private Long userId;

    private Long rechargeUserId;
    
    private String describe;

    private String source;

    private Double amount;

    /**
     * 类型 1-充值,2-折让，3-超额
     */
    private Integer type;
    
    private Integer status;

    private Boolean isDel;


    public CommissionDetail(Long userId, String describe, Long rechargeUserId, String source, Double amount,Integer status,Integer type) {
        this.userId = userId;
        this.describe = describe;
        this.rechargeUserId = rechargeUserId;
        this.source = source;
        this.amount = amount;
        this.type = type;
        this.status = status;
    }

    public CommissionDetail(Long userId, String describe, String source, Integer status, Double amount, Integer type) {
        this.userId = userId;
        this.describe = describe;
        this.source = source;
        this.amount = amount;
        this.type = type;
        this.status = status;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public Long getRechargeUserId() {
        return rechargeUserId;
    }

    public void setRechargeUserId(Long rechargeUserId) {
        this.rechargeUserId = rechargeUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    
    
    
    

}