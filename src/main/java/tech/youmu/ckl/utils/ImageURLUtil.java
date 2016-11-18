package tech.youmu.ckl.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * <p>Title:ImageURLUtil</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月8日上午11:16:03</p>
 * <p>Description:图片URL路径</p>
 */
public class ImageURLUtil {

    public static String fillPath(String uri) {
        if (StringUtils.isNotBlank(uri)) {
            String imgHostStatic = ConfigUtil.getImgHost();
            imgHostStatic = imgHostStatic.endsWith("/") ? StringUtils.removeEnd(imgHostStatic, "/") : imgHostStatic;
            uri = StringUtils.startsWith(uri, "/") ? uri : StringUtils.join("/", uri);
            return StringUtils.join(imgHostStatic, uri);
        }
        return null;
    }

    public static List<String> fillPath(List<String> uris) {
        if(uris == null){
            return null;
        }
        if(uris.size() == 0){
            return uris;
        }
        List<String> list = new ArrayList<String>();
        for (String uri:uris) {
            list.add(fillPath(uri));
        }
        return list;
    }

}
