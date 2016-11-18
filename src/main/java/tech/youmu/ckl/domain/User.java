package tech.youmu.ckl.domain;

/**
 * @ClassName: User
 * @Description: 用户/员工
 * @author youmu-yjh
 * @date 2016年8月2日 下午2:23:28
 */
public class User {

    private Long id; //id

    private String mobile; //用户名（电话号码）

    private String password; //密码

    private String nickName; //用户昵称

    private String realName; //真实姓名

    private Integer status; //状态：-1-冻结 ； 0-正常

    private String headIcon; //头像

    private Integer integral=0; //积分

    private Integer sex; //性别（1：女，2：男）

    private Integer level; //用户评级

    private String identityCard; //身份证

    private Double balance; //账户余额(车刻丽币)

    private String payPassword; //支付密码

    private String code;//邀请码
    
    /**
     * 是否允许陌生人对讲，1-允许，0-不允许
     */
    private Boolean isAllow;
    
    /**
     * 以前是否充值过，0没有，1-充值过
     */
    private Boolean isRecharge;
    
    /**
     * 纬度
     */
    private String longtitude;
    
    /**
     * 经度
     */
    private String latitude;
    
    /**
     * 
     */
    private Boolean isAttend;

    private String createTime; //注册时间

    private String modifyTime; //修改时间

    private Boolean isDel; //是否删除
    
    private String groupId;

    public User() {}

    public User(String mobile, String createTime, Integer status, Boolean isDel) {
        this.mobile = mobile;
        this.createTime = createTime;
        this.status = status;
        this.isDel = isDel;

    }

    public User(String mobile, String password, String createTime, Integer status, Boolean isDel, String code) {
        this.mobile = mobile;
        this.password = password;
        this.createTime = createTime;
        this.status = status;
        this.isDel = isDel;
        this.code = code;
    }

    public User(Long userId, String newPassword) {
        this.id = userId;
        this.password = newPassword;
    }

    public User(Long userId, String nickName, Integer sex, String realName, String identityCard) {
        this.id = userId;
        this.nickName = nickName;
        this.sex = sex;
        this.realName = realName;
        this.identityCard = identityCard;
    }

    public User(String mobile, String createTime, int status, boolean isDel, String code) {
        this.mobile = mobile;
        this.createTime = createTime;
        this.status = status;
        this.isDel = isDel;
        this.code = code;
    }

    public User(Long userId, String lng, String lat) {
        this.id = userId;
        this.longtitude = lng;
        this.latitude = lat;
    }


    public User(Long userId) {
           this.id = userId;
    }

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getIsAllow() {
        return isAllow;
    }

    public void setIsAllow(Boolean isAllow) {
        this.isAllow = isAllow;
    }

    public Boolean getIsRecharge() {
        return isRecharge;
    }

    public void setIsRecharge(Boolean isRecharge) {
        this.isRecharge = isRecharge;
    }


    
    
    public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Boolean getIsAttend() {
        return isAttend;
    }

    public void setIsAttend(Boolean isAttend) {
        this.isAttend = isAttend;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", mobile=" + mobile + ", password=" + password + ", nickName=" + nickName + ", realName=" + realName + ", status=" + status + ", headIcon=" + headIcon + ", integral=" + integral + ", sex=" + sex + ", level=" + level + ", identityCard=" + identityCard + ", balance=" + balance + ", payPassword=" + payPassword + ", code=" + code + ", isAllow=" + isAllow + ", isRecharge=" + isRecharge + ", longtitude=" + longtitude + ", latitude=" + latitude + ", isAttend=" + isAttend + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
    }

    
}
