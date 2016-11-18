package tech.youmu.ckl.query;

import java.util.ArrayList;
import java.util.List;


public class PageList<T> {
	 //总共的数据条数
	private long total = 0;
	
	
	//总共的数据 空的list 防止为空
	private List<T> rows = new ArrayList<>(0);
	
    public PageList() {
    }

    public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	@Override
	public String toString() {
		return "PageList [total=" + total + ", rows=" + rows + "]";
	}
	
    public static <K> PageList<K>  emptyPageList() {
        return new PageList<K>();
    }

}
