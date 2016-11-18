/**
 * @Title: PerformanceQuery.java
 * @Package tech.youmu.ckl.query
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月19日 上午10:46:08
 * @version V1.0
 */

package tech.youmu.ckl.query;

/**
 * 员工绩效查询对象
 * @author youmu-yjh
 * 
 */
public class PerformanceQuery extends BaseQuery {
    private String keywords;    //关键词  (员工账号，姓名，职位) 
    private String searchTime;     //查询时间
    private Long deptId;        //部门
    
    public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public Long getDeptId() {
        return deptId;
    }
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
    public String getSearchTime() {
        return searchTime;
    }
    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }
    
    
    
    
}
