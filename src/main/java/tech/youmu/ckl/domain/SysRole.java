package tech.youmu.ckl.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
  * @ClassName: SysRole
  * @Description: 系统的角色
  * @author youmu-zh
  * @date 2016年8月10日 下午6:16:42
  *
 */
@SuppressWarnings("serial")
public class SysRole implements Serializable {

	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 名称
	 */
	@NotBlank(message="{name.not.null}")
	private String name;
	
	/**
	 * 角色的创建时间
	 */
	private Date createTime;

	/**
	 * 角色描述
	 */
	private String intro;
	
	/**
	 * 是否已经删除 数据库 默认 false 没有删除 
	 */
	private boolean isDel = false;
	
	/**
	 * 最后修改时间
	 */
	private Date modifyTime;
	
	/**
	 * 创建空的列表
	 */
	private List<SysMenu> menus = new ArrayList<>(0);

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

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public List<SysMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<SysMenu> menus) {
		this.menus = menus;
	}

	public void setDel(boolean isDel) {
		this.isDel = isDel;
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
        return "SysRole [id=" + id + ", name=" + name + ", createTime=" + createTime + ", intro=" + intro + ", isDel=" + isDel + ", modifyTime=" + modifyTime + ", menus=" + (menus != null ? menus.subList(0, Math.min(menus.size(), maxLen)) : null) + "]";
    }
}