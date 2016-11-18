package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName: Employee
 * @Description: 员工
 * @author youmu-yjh
 * @date 2016年8月2日 下午2:23:28
 *
 */
public class Employee {
    
    /**
     * 冻结
     */
    public static final Integer EMPLOYEE_STATUS_FREEZE = -1;
    
    /**
     * 开通
     */
    public static final Integer EMPLOYEE_STATUS_INVOKE = 0;
    
	private Long id;               //id
	private String mobile;		   //员工账号（电话号码）
	private String password;	   //密码
	private String realName;	   //真实姓名
	private String empNumber;      //员工工号
	
	/**
	 * @see Employee#EMPLOYEE_STATUS_FREEZE
	 * @see Employee#EMPLOYEE_STATUS_INVOKE
	 */
	private Integer status;		   //状态：-1-冻结 ； 0-开通
	private String headIcon;		   //头像
	private Boolean sex;		   //0-女；1-男；
	
	private String lastIP;		   //上次访问IP
	private Date lastVisit;		   //上次访问时间
	private Department dept;	   //关联部门
	private Long deptId;
	private Long roleId;
	private SysRole role;		   //员工角色
	private Long orderId;
	private Long jobId;
	private JobTitle jobTitle;	   //职称
	private Long stationId;
	private Station station;	   //所属服务站
	private Date createTime; 	   //注册时间
	private Date modifyTime;       //修改时间
    private Boolean isDel;         //是否删除  ， 默认不删除
    
    
    private BigDecimal salaryInCount;//已获工资总额
    private Integer perfInCount;	//已获得绩效总分
    private BigDecimal keInCount;		//总服务刻数
    private Integer numInCount;		//总订单数
    
    private Integer salaryLevel;    //薪点级别
    
    /**
     * 管理员管理多个服务站
     */
    private List<Station> stations = new ArrayList<>(0);
    
    public Employee() {
    }
    
    public Employee(Long employeeId, Long orderId) {
            this.id =employeeId;
            this.orderId =orderId;
    }
    public List<Station> getStations() {
        return stations;
    }
    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
    
    public Integer getSalaryLevel() {
        return salaryLevel;
    }
    public void setSalaryLevel(Integer salaryLevel) {
        this.salaryLevel = salaryLevel;
    }
    public Integer getNumInCount() {
		return numInCount;
	}
	public void setNumInCount(Integer numInCount) {
		this.numInCount = numInCount;
	}
	public BigDecimal getSalaryInCount() {
        return salaryInCount;
    }
    public void setSalaryInCount(BigDecimal salaryInCount) {
        this.salaryInCount = salaryInCount;
    }
    public Integer getPerfInCount() {
        return perfInCount;
    }
    public void setPerfInCount(Integer perfInCount) {
        this.perfInCount = perfInCount;
    }
    public BigDecimal getKeInCount() {
        return keInCount;
    }
    public void setKeInCount(BigDecimal keInCount) {
        this.keInCount = keInCount;
    }

    private Double avgStar; 
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
   
    public String getHeadIcon() {
        return headIcon;
    }
    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }
    public String getLastIP() {
        return lastIP;
    }
    public void setLastIP(String lastIP) {
        this.lastIP = lastIP;
    }
    
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
    public Date getLastVisit() {
        return lastVisit;
    }
    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }
    public Department getDept() {
        return dept;
    }
    public void setDept(Department dept) {
        this.dept = dept;
    }
    public SysRole getRole() {
        return role;
    }
    public void setRole(SysRole role) {
        this.role = role;
    }
    public JobTitle getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }
    public Station getStation() {
        return station;
    }
    public void setStation(Station station) {
        this.station = station;
    }
    
    
    public Double getAvgStar() {
        return avgStar;
    }
    public void setAvgStar(Double avgStar) {
        this.avgStar = avgStar;
    }
    @JsonFormat(pattern = "yyyy.MM.dd", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
    public Date getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    
    public Boolean getSex() {
        return sex;
    }
    public void setSex(Boolean sex) {
        this.sex = sex;
    }
    public Boolean getIsDel() {
        return isDel;
    }
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
    public String getEmpNumber() {
        return empNumber;
    }
    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }
	
	public Long getDeptId() {
        return deptId;
    }
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
    
    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    public Long getStationId() {
        return stationId;
    }
    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }
    
    public Long getJobId() {
        return jobId;
    }
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
    
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    @Override
    public String toString() {
        return "Employee [id=" + id + ", mobile=" + mobile + ", password=" + password + ", realName=" + realName + ", empNumber=" + empNumber + ", status=" + status + ", headIcon=" + headIcon + ", sex=" + sex + ", lastIP=" + lastIP + ", lastVisit=" + lastVisit + ", dept=" + dept + ", role=" + role + ", jobTitle=" + jobTitle + ", station=" + station + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + ", salaryInCount=" + salaryInCount + ", perfInCount=" + perfInCount + ", keInCount=" + keInCount + ", numInCount=" + numInCount + ", salaryLevel=" + salaryLevel + ", stations=" + stations + ", avgStar=" + avgStar + "]";
    }

    
}
