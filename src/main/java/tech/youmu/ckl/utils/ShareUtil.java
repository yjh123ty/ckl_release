package tech.youmu.ckl.utils;

public class ShareUtil {

    public static String getTrackShareUrl(Long trackId){
        StringBuffer buffer = new StringBuffer();
        buffer.append(ConfigUtil.getLocalDomain());
        buffer.append("/route/remote/showTrackHtml");
        buffer.append("?trackId=").append(trackId);
        return buffer.toString();
    }
    
    public static String getTravelNoteShareUrl(Long travelNoteId){
        StringBuffer buffer = new StringBuffer();
        buffer.append(ConfigUtil.getLocalDomain());
        buffer.append("/route/remote/showTravelNoteHtml");
        buffer.append("?travelNoteId=").append(travelNoteId);
        return buffer.toString();
    }
    
    public static String getCompanionTopicShareUrl(Long companionTopicId){
        StringBuffer buffer = new StringBuffer();
        buffer.append(ConfigUtil.getLocalDomain());
        buffer.append("/riders/remote/showCompanionTopicHtml");
        buffer.append("?companionTopicId=").append(companionTopicId);
        return buffer.toString();
    }

    public static String getPromotionCodeShareUrl(long rechargeComboId, Long userId) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(ConfigUtil.getLocalDomain());
        buffer.append("/user/remote/showPromotionCodeHtml");
        buffer.append("?rechargeComboId=").append(rechargeComboId);
        buffer.append("&userId=").append(userId);
        return buffer.toString();
    }

    public static String getForumTopicShareUrl(Long forumTopicId) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(ConfigUtil.getLocalDomain());
        buffer.append("/riders/remote/showForumTopicHtml");
        buffer.append("?forumTopicId=").append(forumTopicId);
        return buffer.toString();
    }
}
