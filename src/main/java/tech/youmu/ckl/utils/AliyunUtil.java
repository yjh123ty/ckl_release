package tech.youmu.ckl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;
import tech.youmu.ckl.app.vo.CarIllegallyInfo;

public class AliyunUtil {
    private static Logger log=LoggerFactory.getLogger(AliyunUtil.class);
    
    private static final String BASE_URL="http://ali-carlaw.showapi.com/break-rules";
    
    public static CarIllegallyInfo getCarIllegallyInfo(String plateNumber, String engineNumber, String frameNumber){
        try {
            AliyunRequestUtil aliyunRequestUtil= new AliyunRequestUtil(ConfigUtil.getAliyunAppKey(), ConfigUtil.getAliyunAppSecret(),BASE_URL);
             aliyunRequestUtil.addTextPara("carCode",frameNumber);
             aliyunRequestUtil.addTextPara("carEngineCode", engineNumber);
             aliyunRequestUtil.addTextPara("carNumber", plateNumber);
            String result = new String(aliyunRequestUtil.postAsByte(),"utf-8");
            log.info("result:"+result);
            JSONObject json = JSONObject.fromObject(result);
            json.get("showapi_res_body");
            CarIllegallyInfo carIllegallyInfo =(CarIllegallyInfo) JSONObject.toBean((JSONObject) json.get("showapi_res_body"), CarIllegallyInfo.class);
            return carIllegallyInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CarIllegallyInfo();
    }
    public static void main(String[] args) {
        try{
            AliyunRequestUtil aliyunRequestUtil= new AliyunRequestUtil(ConfigUtil.getAliyunAppKey(), ConfigUtil.getAliyunAppSecret(),BASE_URL);
            aliyunRequestUtil.addTextPara("carCode","229096");
            aliyunRequestUtil.addTextPara("carEngineCode", "843764");
            aliyunRequestUtil.addTextPara("carNumber", "川A5Mq63");
           String result = new String(aliyunRequestUtil.postAsByte(),"utf-8");
           JSONObject json = JSONObject.fromObject(result);
           json.get("showapi_res_body");
           CarIllegallyInfo carIllegallyInfo =(CarIllegallyInfo) JSONObject.toBean((JSONObject) json.get("showapi_res_body"), CarIllegallyInfo.class);
           log.info(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
