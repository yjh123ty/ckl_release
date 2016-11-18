/**
 * @Title: FiveCountRule.java
 * @Package tech.youmu.ckl.domain
 * 
 * @author yjh
 * @date 2016年9月7日 下午10:34:19
 * @version V1.0
 */

package tech.youmu.ckl.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 五星好评额外奖励规则
 * @author yjh
 *
 */
public class FiveCountRule {
	/**
	 * 规则id
	 */
	private Long id;
	
	/**
	 * 每月五星好评总数
	 */
	private Integer fiveCount;
	
	/**
	 * 好评数对应分数
	 */
	private Integer point;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	
	/**
	 * 是否删除
	 */
	private Boolean isDel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFiveCount() {
		return fiveCount;
	}

	public void setFiveCount(Integer fiveCount) {
		this.fiveCount = fiveCount;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	@JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
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

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	@Override
	public String toString() {
		return "FiveCountRule [id=" + id + ", fiveCount=" + fiveCount + ", point=" + point + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
	}
	
	
}
