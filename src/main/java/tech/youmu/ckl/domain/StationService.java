package tech.youmu.ckl.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Title:StationService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月24日下午4:37:42</p>
 * <p>Description:站点服务 酒店服务   饭店服务   洗车服务 三大类</p>
 */
@SuppressWarnings("serial")
public class StationService implements Serializable{
    
    private Long id;
   
    /**
     * 该服务的服务类型，服务类型(1-酒店，2-饭店，3-汽车保养, 4-零部件维修 ，5-道路救援, 6娱乐,7汽车销售, 8卫生间,9停车)
     */
    private ServiceType serviceType;
    
    /**
     *  服务的提供站点
     */
    private Station station;
    
    /**
     * 描述 服务的描述
     */
    private String intro;
    
    /**
     * 洗车的价格
     */
    private BigDecimal price;
    
    /**
     * 是否删除
     */
    private Boolean isDel;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 修改时间
     */
    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}