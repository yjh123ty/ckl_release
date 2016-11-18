/**
 * @Title: EmployeeAttendanceInfoQuery.java
 * @Package tech.youmu.ckl.query
 * 
 * @author yjh
 * @date 2016年10月7日 下午6:44:09
 * @version V1.0
 */

package tech.youmu.ckl.query;

/**
 * 考勤记录表的查询对象
 * @author yjh
 *
 */
public class EmployeeAttendanceInfoQuery extends BaseQuery {
	private String keywords;
	private String searchTimeStr;
    public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public String getSearchTimeStr() {
        return searchTimeStr;
    }
    public void setSearchTimeStr(String searchTimeStr) {
        this.searchTimeStr = searchTimeStr;
    }

	
	
	
}
