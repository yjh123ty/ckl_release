package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "好友信息")
public class UserFriendInfo {

	@ApiModelProperty(value="id")
    private long id;
	
	@ApiModelProperty(value="好友id,即friendId")
    private long userId;

    @ApiModelProperty(value="好友名称")
	private String userName;
	
	@ApiModelProperty(value="好友头像")
	private String headIcon;
	
	@ApiModelProperty(value="备注")
	private String note;
	
	@ApiModelProperty(value="是否是好友")
    private boolean isFriend;
	
	@ApiModelProperty(value="好友等级")
    private String level;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadIcon() {
		return headIcon;
	}

	public void setHeadIcon(String headIcon) {
		this.headIcon = headIcon;
	}

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(boolean isFriend) {
        this.isFriend = isFriend;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

  

	
	

    
}