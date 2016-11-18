package tech.youmu.ckl.query;

import org.apache.commons.lang3.StringUtils;

public class BaseQuery {
	// 初始值
	private int currentPage = 1;
	// 当前页长
	private int pageSize = 10;
	
	// 排序字段
	private String[] sort;
	
	// 排序字段升序还是降序
	private String[] order;
	
	/**
	 * 关键词
	 */
	private String keyword;
	
    
    //起始时间，截止时间
    private String beginTimeStr;
    private String endTimeStr;
    
	public String getBeginTimeStr() {
        return beginTimeStr;
    }

    public void setBeginTimeStr(String beginTimeStr) {
        this.beginTimeStr = beginTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    // 起始位置
	public Integer getStart(){
		return (currentPage-1)*pageSize;
	}
	
	//最终位置
	public Integer getEnd(){
		return this.pageSize;
	}
	
	// 需要回显数据，getter，setter
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setRows(int rows) {
	    this.pageSize = rows;
	}
	

    //easyUI分页兼容
	public void setPage(int page){ 
		this.currentPage = page;
	}

    public void setSort(String[] sort) {
        this.sort = sort;
    }

    public void setOrder(String[] order) {
        this.order = order;
    }

    public String[] getSortItem() {
        if(sort == null || order == null || sort.length == 0 || sort.length != order.length) {
            return null;
        }
        String[] sortItem = new String[sort.length];
        for (int i = 0; i < sort.length; i++) {
            sortItem[i] = sort[i] + " " + order[i]+ " ";
        }
        return sortItem;
    }

    public String getKeyword() {
        if(StringUtils.isBlank(keyword)){
            return null;
        }
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
