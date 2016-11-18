package tech.youmu.ckl.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.impl.common.IOUtil;

public class ConfigUtil {

    private static String LOCAL_DOMAIN;
	/**
	 * 图片服务器地址
	 */
	private static String IMG_HOST;

	private static String IMG_UPLOAD_PATH;
	
	/**
	 * 短信
	 */
	private static String SMS_TYPE;
	
	private static String SMS_APPKEY;

	private static String SMS_SECRET;
	
	private static String SMS_SIGN;
	
	/**
	 * 模板
	 */
	private static String SMS_TEMPLATE_CODE_REGIST;
	private static String SMS_TEMPLATE_SOS;
	private static String SMS_TEMPLATE_WARN_PAY;
	
	/**
	 * map
	 */
	private static String MAP_KEY;
	
	private static String MAP_URL;
	
	/**
	 * 支付宝appid
	 * 
	 */
	private static String ALIPAY_APP_ID;
	
	/**
	 * 回调地址
	 */
	private static String ALIPAY_NOTIFY_URL;
	private static String ALIPAY_PARTNER;
	private static String ALIPAY_PUBLIC_KEY;
	private static String ALIPAY_PRIVATE_KEY;
	
	/**
	 * 微信
	 */
	private static String WEIXIN_APP_ID;
	private static String WEIXIN_PARTNER_ID;
	private static String WEIXIN_NOTIFY_URL;	
	private static String WEIXIN_KEY;   
	
	/**
	 * 信鸽
	 */
	private static Long XINGE_ACCESS_ID;  
	private static String XINGE_SECRET_KEY; 
	
	/**
     * 荣云
     */
    private static String RONG_APP_KEY;  
    private static String RONG_APP_SECRET; 
    
    /**
     * 阿里云
     */
    private static String ALIYUN_APP_KEY;  
    private static String ALIYUN_APP_SECRET; 
	
	/**
	 * 环信
	 */
	private static String EASEMOB_ORG_NAME; 
	private static String EASEMOB_APP_NAME; 
	private static String EASEMOB_CLIENT_ID; 
	private static String EASEMOB_CLIENT_SECRET; 
	
	/**
	 * 进销存商品老化提前提醒的天数
	 */
	private static String REMIND_OLD_DAYS;
	
	static {
		Properties prop = new Properties();
		InputStream in = ConfigUtil.class.getResourceAsStream("/config.properties");
		try {
			prop.load(in);
			LOCAL_DOMAIN = prop.getProperty("local_domain").trim();
			IMG_HOST = prop.getProperty("img_host").trim();
			IMG_UPLOAD_PATH = prop.getProperty("img_upload_path").trim();
			SMS_TYPE = prop.getProperty("sms_type").trim();
			SMS_APPKEY = prop.getProperty("sms_appkey").trim();
			SMS_SECRET = prop.getProperty("sms_secret").trim();
			SMS_SIGN = new String(prop.getProperty("sms_sign").getBytes("ISO-8859-1"),"utf-8").trim();
			SMS_TEMPLATE_CODE_REGIST = prop.getProperty("sms_template_code_regist").trim();
			SMS_TEMPLATE_SOS = prop.getProperty("sms_template_sos").trim();
			SMS_TEMPLATE_WARN_PAY = prop.getProperty("sms_template_warn_pay").trim();
			MAP_KEY = prop.getProperty("map_key").trim();
			MAP_URL = prop.getProperty("map_url").trim();
			ALIPAY_APP_ID = prop.getProperty("alipay_app_id").trim();
			ALIPAY_NOTIFY_URL = prop.getProperty("alipay_notify_url").trim();
			ALIPAY_PARTNER = prop.getProperty("alipay_partner").trim();
			ALIPAY_PUBLIC_KEY = prop.getProperty("alipay_public_key").trim();
			ALIPAY_PRIVATE_KEY = prop.getProperty("alipay_private_key").trim();
			WEIXIN_APP_ID = prop.getProperty("weixin_app_id").trim();
			WEIXIN_PARTNER_ID = prop.getProperty("weixin_partner_id").trim();
			WEIXIN_NOTIFY_URL = prop.getProperty("weixin_notify_url").trim();
			WEIXIN_KEY = prop.getProperty("weixin_key").trim();
			XINGE_ACCESS_ID = Long.valueOf(prop.getProperty("xinge_access_id").trim());
			XINGE_SECRET_KEY = prop.getProperty("xinge_secret_key").trim();
			EASEMOB_ORG_NAME = prop.getProperty("easemob_org_name").trim();
			EASEMOB_APP_NAME = prop.getProperty("easemob_app_name").trim();
			EASEMOB_CLIENT_ID = prop.getProperty("easemob_client_id").trim();
			EASEMOB_CLIENT_SECRET = prop.getProperty("easemob_client_secret").trim();
			RONG_APP_KEY = prop.getProperty("rong_app_key").trim();
			RONG_APP_SECRET = prop.getProperty("rong_app_secret").trim();
			ALIYUN_APP_KEY = prop.getProperty("aliyun_app_key").trim();
			ALIYUN_APP_SECRET = prop.getProperty("aliyun_app_secret").trim();
			REMIND_OLD_DAYS = prop.getProperty("remind_old_days").trim();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
		    IOUtils.closeQuietly(in);
		}
	}

	public static String getImgHost() {
		return IMG_HOST;
	}

	public static String getImgUploadPath() {
		return IMG_UPLOAD_PATH;
	}

	public static String getSMSType() {
		return SMS_TYPE;
	}

	public static String getSMSAppkey() {
		return SMS_APPKEY;
	}

	public static String getSMSSecret() {
		return SMS_SECRET;
	}

	public static String getSMSSign() {
		return SMS_SIGN;
	}

	public static String getSMSTemplateCodeRegist() {
		return SMS_TEMPLATE_CODE_REGIST;
	}

    public static String getMapKey() {
        return MAP_KEY;
    }


    public static String getMapURL() {
        return MAP_URL;
    }

    public static String getAlipayAppId() {
        return ALIPAY_APP_ID;
    }


    public static String getAlipayNotifyUrl() {
        return ALIPAY_NOTIFY_URL;
    }

    public static String getAlipayPartner() {
        return ALIPAY_PARTNER;
    }

    public static String getAlipayPublicKey() {
        return ALIPAY_PUBLIC_KEY;
    }

    public static String getWeixinAppId() {
        return WEIXIN_APP_ID;
    }

    public static String getWeixinPartnerId() {
        return WEIXIN_PARTNER_ID;
    }

    public static String getWeixinNotifyUrl() {
        return WEIXIN_NOTIFY_URL;
    }

    public static String getWeixinKey() {
        return WEIXIN_KEY;
    }

    public static String getAlipayPrivateKey() {
        return ALIPAY_PRIVATE_KEY;
    }

    public static Long getXingeAccessId() {
        return XINGE_ACCESS_ID;
    }

    public static String getXingeSecretKey() {
        return XINGE_SECRET_KEY;
    }

    public static String getEasemobOrgName() {
        return EASEMOB_ORG_NAME;
    }

    public static String getEasemobAppName() {
        return EASEMOB_APP_NAME;
    }

    public static String getEasemobClientId() {
        return EASEMOB_CLIENT_ID;
    }

    public static String getEasemobClientSecret() {
        return EASEMOB_CLIENT_SECRET;
    }

    public static String getLocalDomain() {
        return LOCAL_DOMAIN;
    }

    public static String getRongAppKey() {
        return RONG_APP_KEY;
    }

    public static String getRongAppSecret() {
        return RONG_APP_SECRET;
    }
    
    public static String getAliyunAppKey() {
        return ALIYUN_APP_KEY;
    }

    public static String getAliyunAppSecret() {
        return ALIYUN_APP_SECRET;
    }

    public static String getSMSTemplateSos() {
        return SMS_TEMPLATE_SOS;
    }

    public static String getSMSTemplateWarnPay() {
        return SMS_TEMPLATE_WARN_PAY;
    }

    public static int getRemindOldDays() {
        try {
            return Integer.valueOf(REMIND_OLD_DAYS);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
