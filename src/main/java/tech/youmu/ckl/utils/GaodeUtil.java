package tech.youmu.ckl.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tech.youmu.ckl.app.vo.NavigateRouteInfo;

public class GaodeUtil {
    
    private static Logger log=LoggerFactory.getLogger(GaodeUtil.class);

   

    private static final String LOCATION_MOTHED="geocode/geo";
    
    private static final String DISTANCE_MOTHED="distance";
    
    private static final String DRIVING_MOTHED="direction/driving";
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月19日下午2:13:52;</p>
     *	<p>Description: 根据地址获取经纬度;</p>
     *  @param address
     *  @return 经度,纬度
     */
    public static String getLocationByAddress(String address){
        Map<String,Object> param = new HashMap();
        param.put("key", ConfigUtil.getMapKey());
        param.put("output", "JSON");
        param.put("address", address);
        try{
            String jsonStr = HttpUtil.postHttp(ConfigUtil.getMapURL()+LOCATION_MOTHED, param);
            log.info(jsonStr);
            JSONObject json = JSONObject.fromObject(jsonStr);
            JSONArray jsonArray = (JSONArray) json.get("geocodes");
            JSONObject json0 = (JSONObject) jsonArray.get(0);
            return json0.getString("location");
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
            
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月20日下午1:39:29;</p>
     *	<p>Description: 根据地址获取经纬度坐标;</p>
     *  @return Double[0] longtitude 经度   Double[1] latitude 纬度 不能解析 经纬度都返回 0.000000
     */
    public static String[] getCoordinateByAddress(String address){
        String[] coordinate = {"0.000000", "0.000000"};
        try {
            if(StringUtils.isNoneBlank(address)) {
                String gpsAddress = getLocationByAddress(address);
                if(gpsAddress != null) {
                    String[] locations = gpsAddress.split(",");
                    coordinate[0] = locations[0];
                    coordinate[1] = locations[1];
                    return coordinate;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("地址转换失败~ 错误信息："+e.getMessage());
        }
        return coordinate;
    }
    
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月19日下午3:26:30;</p>
     *	<p>Description: 获取坐标行驶距离 ;</p>
     *  @param origins
     *  @param destination
     *  @return
     */
    public static List<Double> getDistances(String origins,String destination,Integer type){
        List<Double> distances = new ArrayList();
        Map<String,Object> param = new HashMap();
        param.put("key", ConfigUtil.getMapKey());
        param.put("output", "JSON");
        param.put("type", type);
        param.put("origins", origins);
        param.put("destination", destination);
        try{
            String jsonStr = HttpUtil.postHttp(ConfigUtil.getMapURL()+DISTANCE_MOTHED, param);
            log.info(jsonStr);
            JSONObject json = JSONObject.fromObject(jsonStr);
            JSONArray jsonArray = json.getJSONArray("results");
            for(int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                double distance = jsonObject.getDouble("distance");
                distances.add(distance);
            }
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return distances;
        }
        return distances;
        
    }
    
    public static NavigateRouteInfo getNavigateRouteInfo(String origin,String destination,Integer strategy){
        NavigateRouteInfo recommendRouteInfo = new NavigateRouteInfo();
        Map<String,Object> param = new HashMap();
        param.put("key", ConfigUtil.getMapKey());
        param.put("output", "JSON");
        param.put("strategy", strategy);
        param.put("origin", origin);
        param.put("destination", destination);
        try{
            String jsonStr = HttpUtil.postHttp(ConfigUtil.getMapURL()+DRIVING_MOTHED, param);
            log.info(jsonStr);
            JSONObject json = JSONObject.fromObject(jsonStr);
            JSONObject route = json.getJSONObject("route");
            JSONArray paths = route.getJSONArray("paths");
            JSONObject path =paths.getJSONObject(0);
            int time = path.getInt("duration");
            double distance = path.getDouble("distance")/1000;
            double toll = path.getDouble("tolls");
            recommendRouteInfo.setTime(change(time));
            recommendRouteInfo.setToll(toll);
            recommendRouteInfo.setDistance(distance);
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return recommendRouteInfo;
        
    }
    
    public static String change(int second){
        int h = 0;
        int d = 0;
        int s = 0;
        int temp = second%3600;
             if(second>3600){
               h= second/3600;
                    if(temp!=0){
               if(temp>60){
               d = temp/60;
            if(temp%60!=0){
               s = temp%60;
            }
            }else{
               s = temp;
            }
           }
          }else{
              d = second/60;
           if(second%60!=0){
              s = second%60;
           }
          }

         return h+"时"+d+"分"+s+"秒";
       }


    public static void main(String[] args) {
        getDistances("116.481028,39.989643|114.481028,39.989643|115.481028,39.989643", "114.465302,40.004717", 1);
    }

}
