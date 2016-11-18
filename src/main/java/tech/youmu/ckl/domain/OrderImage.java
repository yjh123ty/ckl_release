package tech.youmu.ckl.domain;

public class OrderImage {
    private Long id;

    private String url;

    private Long orderId;

    private Boolean isDel;

    public OrderImage() {
    }

    public OrderImage(Long orderId, String url) {
           this.orderId = orderId;
           this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

}