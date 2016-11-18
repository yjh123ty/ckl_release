package tech.youmu.ckl.utils;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;

/**
 * @ClassName: JsonUtils
 * @author xiongchuan
 * @date 2016年1月13日 下午3:55:39
 */
public class JsonUtils {

    /**
     * json字符串转成对象
     * @param str
     * @param type
     * @return
     */
    public static <T> T fromJson(String str, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    public static String fromJson(Map map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
