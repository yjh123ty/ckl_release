package tech.youmu.ckl.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarPartCodeUtil {
    
    private static final String CODE_REGEX="-";
    
    public static List<String> getCartPartCodes(String[] codes){
        List<String> cartPartCodes = new ArrayList();
        for(String code :codes){
            cartPartCodes.add(getCartPartCode(code));
        }
        return cartPartCodes;
    }
    
    public static String getCartPartCode(String code){
        String cartPartCode="";
        try{
            cartPartCode = codeSplit(code)[0];
        }catch (Exception e) {
            e.printStackTrace();
        }
        return cartPartCode;
    }
    
    public static Double getInPrice(String code){
        Double inPrice = 0d;
        try{
            inPrice =  Double.valueOf(codeSplit(code)[1]);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return inPrice;
    }
    
    private static String[] codeSplit(String code){
        return code.split(CODE_REGEX);
    }
    
    
    public static Map<String,List<String>> getGroupCartPartCode(String[] codes){
        Map<String,List<String>> map = new HashMap<>();
        for(String code:codes){
            String cartPartCode = getCartPartCode(code);
            List<String> codeList=map.get(cartPartCode);
            if(codeList==null){
                codeList = new ArrayList<>();
            }
            codeList.add(code);
            map.put(cartPartCode, codeList);
        }
        return map;
    }
}
