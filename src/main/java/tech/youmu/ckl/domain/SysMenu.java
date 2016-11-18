/**
 * @Title: SysMenu.java
 * @Package tech.youmu.ckl.domain
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 下午5:01:28
 * @version V1.0
 */
package tech.youmu.ckl.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class SysMenu implements Serializable {
	
	/**
	 * 菜单的id
	 */
	private Long id;
	
	/**
	 * 菜单的名字
	 */
	private String name;
   
	/**
	 * 菜单class样式名字
	 */
	private String iconCls;
	
	/**
	 * 菜单对应的url
	 */
	private String url;
	
	/**
	 * 菜单的描述
	 */
	private String intro;
	
	/**
	 * 子菜单列表
	 */
	private List<SysMenu> children = new ArrayList<>(0);
	
	/**
	 * 父菜单
	 */
	private SysMenu parent;
	
	/**
	 * 是否删除 默认不删除
	 */
	private Boolean isDel = false;
	
	/**
	 * 创建时间
	 * 
	 */
	private Date createTime;
	
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	
	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public String getText(){
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public SysMenu getParent() {
		return parent;
	}

	public void setParent(SysMenu parent) {
		this.parent = parent;
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

    @Override
    public String toString() {
        final int maxLen = 10;
        return "SysMenu [id=" + id + ", name=" + name + ", iconCls=" + iconCls + ", url=" + url + ", intro=" + intro + ", children=" + (children != null ? children.subList(0, Math.min(children.size(), maxLen)) : null) + ", parent=" + parent + ", isDel=" + isDel + ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
    }
}
