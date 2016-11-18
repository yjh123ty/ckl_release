/** 
* 2015-11-21 
* User.java 
* author:Zack Chan
*/ 
package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户信息")
public class UserInfo {
	
	@ApiModelProperty(value="等级")
	private int level;
	
	@ApiModelProperty(value="徽章数量")
	private int badgeCount;
	
	@ApiModelProperty(value="积分")
	private int integral;
	
	@ApiModelProperty(value="下一个等级的积分")
    private int nextLevelIntegral;
	
	@ApiModelProperty(value="昵称")
	private String nickName;
	
	@ApiModelProperty(value="头像")
	private String headIcon;
	
	@ApiModelProperty(value="性别（1：女，2：男）")
	private int sex;
	
	@ApiModelProperty(value="姓名")
	private String realName;
	
	@ApiModelProperty(value="身份证号")
	private String identityCard;
	
	@ApiModelProperty(value="账号")
	private String accountNumber;
	
	@ApiModelProperty(value="钱包金额")
	private double balance;
	
	@ApiModelProperty(value="是否设置支付密码")
    private boolean isSetPayPassword;
	
	@ApiModelProperty(value="好友数量")
	private int friendCount;
	
	@ApiModelProperty(value="收藏服务站数量")
	private int collectStationCount;
	
	@ApiModelProperty(value="待付款数量")
	private int awaitPayCount;
	
	@ApiModelProperty(value="待服务数量")
	private int awaitServiceCount;
	
	@ApiModelProperty(value="待完成数量")
	private int awaitFinishCount;
	
	@ApiModelProperty(value="待评价数量")
	private int awaitEvaluateCount;
	
	@ApiModelProperty(value="是否可以参加分销，false不能，true-能")
    private boolean isAttend;
	
	@ApiModelProperty(value="是否有汽车，false不能，true-能")
    private boolean isCar;
	
	@ApiModelProperty(value="是否允许陌生人对讲，1-允许，0-不允许")
    private boolean isAllow;


	public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBadgeCount() {
		return badgeCount;
	}

	public void setBadgeCount(int badgeCount) {
		this.badgeCount = badgeCount;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getHeadIcon() {
		return headIcon;
	}

	public void setHeadIcon(String headIcon) {
		this.headIcon = headIcon;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getFriendCount() {
		return friendCount;
	}

	public void setFriendCount(int friendCount) {
		this.friendCount = friendCount;
	}

	public int getCollectStationCount() {
		return collectStationCount;
	}

	public void setCollectStationCount(int collectStationCount) {
		this.collectStationCount = collectStationCount;
	}

	public int getAwaitPayCount() {
		return awaitPayCount;
	}

	public void setAwaitPayCount(int awaitPayCount) {
		this.awaitPayCount = awaitPayCount;
	}

	public int getAwaitServiceCount() {
		return awaitServiceCount;
	}

	public void setAwaitServiceCount(int awaitServiceCount) {
		this.awaitServiceCount = awaitServiceCount;
	}

	public int getAwaitFinishCount() {
		return awaitFinishCount;
	}

	public void setAwaitFinishCount(int awaitFinishCount) {
		this.awaitFinishCount = awaitFinishCount;
	}

	public int getAwaitEvaluateCount() {
		return awaitEvaluateCount;
	}

	public void setAwaitEvaluateCount(int awaitEvaluateCount) {
		this.awaitEvaluateCount = awaitEvaluateCount;
	}

    public int getNextLevelIntegral() {
        return nextLevelIntegral;
    }

    public void setNextLevelIntegral(int nextLevelIntegral) {
        this.nextLevelIntegral = nextLevelIntegral;
    }

    public boolean getIsSetPayPassword() {
        return isSetPayPassword;
    }

    public void setIsSetPayPassword(boolean isSetPayPassword) {
        this.isSetPayPassword = isSetPayPassword;
    }

    public boolean getIsAttend() {
        return isAttend;
    }

    public void setIsAttend(boolean isAttend) {
        this.isAttend = isAttend;
    }

    public boolean getIsCar() {
        return isCar;
    }

    public void setIsCar(boolean isCar) {
        this.isCar = isCar;
    }

	public boolean getIsAllow() {
		return isAllow;
	}

	public void setIsAllow(boolean isAllow) {
		this.isAllow = isAllow;
	}
    
    

	
}
