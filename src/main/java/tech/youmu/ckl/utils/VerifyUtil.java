package tech.youmu.ckl.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class VerifyUtil {
    
    public static boolean checkSysPassword(String password){
        Pattern pattern = Pattern.compile("^(\\w){6,20}$");
        return pattern.matcher(password).matches();
    }
    
    public static boolean isNotEmptyMultipartFiles(MultipartFile... files){
        boolean flag = true;
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                flag = !file.isEmpty();
            }
        }else {
            flag = false;
        }
        return flag;
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月26日上午11:28:38;</p>
     *	<p>Description: 是否是jpg图片;</p>
     *  @param fileName
     *  @return
     */
    public static boolean isJPGFile(MultipartFile... files) {
        boolean flag = true;
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                if(!StringUtils.equals("image/jpeg", file.getContentType())) {
                    flag = flag && false;
                }
            }
        }else {
            flag = flag && false;
        }
        return flag;
    }

	/**
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月24日上午11:05:53;</p>
	 *	<p>Description: 手机好验证;</p>
	 *  @param str
	 *  @return
	 */
	public static boolean isMobile(String str) { 
		Pattern p = null;
		Matcher m = null;
		boolean b = false; 
		p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches(); 
		return b;
	}
}
