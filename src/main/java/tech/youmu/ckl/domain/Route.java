/**
 * @Title: Route.java
 * @Package tech.youmu.ckl.domain
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月17日 上午11:41:05
 * @version V1.0
 */

package tech.youmu.ckl.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import tech.youmu.ckl.constants.Global;
import tech.youmu.ckl.utils.ImageURLUtil;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
  * @ClassName: Route
  * @Description: 路线
  * @author youmu-zh
  * @date 2016年8月17日 上午11:41:05
  *
  */
@SuppressWarnings("serial")
public class Route implements Serializable {
	
	private Long id;
	
	private String name;
	
	/**
	 * 录入时间
	 */
	private Date createTime;
	
	/**
	 *  录入人
	 */
	private Employee createUser;
	
	/**
	 * 路线图片 地图
	 */
	private String img;
	
	/**
	 *  游玩天数
	 */
	private Integer days;
	
	/**
	 * 行驶里程
	 */
	private Double distance;
	
	/**
	 *  消费
	 */
	private Double cost;
	
	/**
	 *  描述
	 */
	private String intro;
	
	/**
	 * 该路线的模板路线
	 */
	private Route template;
	
	/**
	 * 最近修改时间
	 */
	private Date modifyTime;
	
	/**
	 *  路线是否可用
	 */
	private Boolean isDel;
	

    /**
     * 线路站点列表
     */
    private List<Station> stations;
    
    /**
     * 路线适合人群
     */
    private List<RouteSuit> suits;
    
    private List<String> suitNames;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}


	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	public Route getTemplate() {
		return template;
	}

	public void setTemplate(Route template) {
		this.template = template;
	}

	@JsonFormat(pattern=Global.DATE_TIME_FORMAT, timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Employee getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Employee createUser) {
        this.createUser = createUser;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<RouteSuit> getSuits() {
        return suits;
    }

    public void setSuits(List<RouteSuit> suits) {
        this.suits = suits;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public List<String> getSuitNames() {
        return suitNames;
    }

    public void setSuitNames(List<String> suitNames) {
        this.suitNames = suitNames;
    }
    
    
    
}
