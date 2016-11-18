package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "好友申请信息")
public class UserFriendApplyInfo {
    
    @ApiModelProperty(value="申请id")
    private long userFriendApplyId;

    @ApiModelProperty(value="id")
    private long userId;

    @ApiModelProperty(value="名字")
    private String userName;
    
    @ApiModelProperty(value="头像")
    private String headIcon;

    @ApiModelProperty(value="状态 1-待确认，2-已同意")
    private int status;

    @ApiModelProperty(value="说明备注")
    private String remark;

    public long getUserFriendApplyId() {
        return userFriendApplyId;
    }

    public void setUserFriendApplyId(long userFriendApplyId) {
        this.userFriendApplyId = userFriendApplyId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    

}