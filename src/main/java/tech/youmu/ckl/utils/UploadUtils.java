/**
 * @Title: UploadUtils.java
 * @Package tech.youmu.ckl.utils
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月18日 下午3:22:52
 * @version V1.0
 */

package tech.youmu.ckl.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

/**
 * @ClassName: UploadUtils
 * @Description: 文件上传的工具类
 * @author youmu-zh
 * @date 2016年8月18日 下午3:22:52
 *
 */
public class UploadUtils {
    
    private static Logger log=Logger.getLogger(CheckCodeUtil.class);
	
    /**
     * 图片类型
     */
    public static final String IMG_TYPE = ".jpg";
	
	public static final String ROUTE_PATH = "/upload/route/";
	
	/**
     * 头像
     */
    public static final String HEAD_USER_ICON_PATH = "/upload/user/headIcon/";
    
    /**
     * 头像
     */
    public static final String HEAD_EMPLOYEE_ICON_PATH = "/upload/employee/headIcon/";
	
	/**
	 * 站点图片
	 */
	public static final String STATION_IMG = "/upload/station/imgs/";
	
    /**
     * 站点酒店封面图片
     */
    public static final String HOTEL_COVER_IMG_PATH = "/upload/station/hotel/cover/";
    
    /**
     * 站点酒店房间封面图片
     */
    public static final String HOTEL_ROOM_COVER_IMG_PATH = "/upload/station/hotel/room/cover/";
    
    /**
     * 站点饭店封面图片
     */
    public static final String RESTURANT_COVER_IMG_PATH = "/upload/station/resturant/cover/";
	
    /**
     * 站点酒店内部图片
     */
    public static final String HOTEL_INNER_IMGS_PATH = "/upload/station/hotel/inner_imgs/";
    
    /**
     * 酒店外部图片
     */
    public static final String HOTEL_OUTSIDE_IMGS_PATH = "/upload/station/hotel/outside_imgs/";
    
    /**
     * 站点酒店房间图片
     */
    public static final String HOTEL_ROOM_IMGS_PATH = "/upload/station/hotel/room_imgs/";
    
    /**
     * 帖子
     */
    public static final String TOPIC_PATH = "/upload/topic/";
    
    /**
     * 站点饭店
     */
    public static final String RESTURANT_IMGS_PATH = "/upload/station/restaurant/imgs/";
    
    /**
     * 站点饭店套餐图
     */
    public static final String RESTURANT_COMBO_IMGS_PATH = "/upload/station/restaurant/combo_imgs/";
    
    /**
     * 站点饭店菜品图
     */
    public static final String RESTURANT_DISHES_IMG_PATH = "/upload/station/restaurant/dishes_img/";
    
    
    /**
     * 转账凭证
     */
    public static final String TRANSFER_IMG = "/upload/transfer/imgs/";
    
    
    /**
     * 游记
     */
    public static final String TRAVEL_NOTE = "/upload/travel/note/";
    
    /**
     * 路线攻略的图片
     */
    public static final String ROUTE_GUIDE_IMG_PATH = "/upload/route/guide/imgs/";
    
    public static final String TEMPLATE_TRAVEL_CONTENT_IMG_PATH = "/upload/travel/note/template/";
    
    /**
     * 商品
     */
    public static final String CARPART_IMG = "/upload/carpart/imgs/";
    
    /**
     * 车辆保养项目图片
     */
    public static final String CAR_CARE_ITEM = "/upload/carcare/item/";
    
    
    
    public static String uploadFile(MultipartFile file, String basePath) {
        if (file == null) {
            return null;
        }
        String path = ConfigUtil.getImgUploadPath()+basePath;
        String fileName = UUID.randomUUID().toString() + IMG_TYPE;
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        // 保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  basePath +fileName;
       
       
    }
    
    
    public static List<String> uploadFiles(MultipartFile[] files, String basePath) {
        List<String> urls = new ArrayList<>();
        if (files == null) {
            return urls;
        }
        for(MultipartFile file:files){
            urls.add(uploadFile(file, basePath));
        }
        return urls;
    }

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年9月2日下午3:28:12;</p>
	 *	<p>Description: 上传文件并创建缩略图;</p>
	 *  @param file
	 *  @param basePath
	 *  @return
	 */
	public static List<String> uploadFileThumbnail(MultipartFile file,String basePath) {
		if (file == null) {
			return null;
		}
		String path = ConfigUtil.getImgUploadPath() +basePath;
		String fileName = UUID.randomUUID().toString() + IMG_TYPE;
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> imgPaths = new ArrayList<>();
		imgPaths.add(basePath +fileName);
		imgPaths.add(createThumbnail(targetFile, basePath, 200, 200));
		return imgPaths;
	}
	
	/**
	 *  <p>Author:zh;</p>
	 *  <p>Date:2016年10月19日上午10:51:27;</p>
	 *	<p>Description: 上传固定大小图片;</p>
	 *  @param multipartFile
	 *  @param basePath
	 *  @param width
	 *  @param height
	 *  @return
	 *  @throws Exception
	 */
	public static String uploadThumbnail(MultipartFile multipartFile, String basePath, int width, int height) throws RuntimeException{
	    log.info("按固定尺寸上传缩略图");
	    String path = ConfigUtil.getImgUploadPath()+basePath;
	    String fileName = UUID.randomUUID().toString() +IMG_TYPE;
	    try {
	        File dir = new File(path);
	        if(!dir.exists()) {
	            dir.mkdirs();
	        }
	        File targetFile = new File(dir, fileName);
	        InputStream in = multipartFile.getInputStream();
            Thumbnails.of(in).size(width, height).toFile(targetFile);
            IOUtils.closeQuietly(in);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("缩略图上传失败!");
        }
	   return basePath + fileName;
	}
	
	public static String uploadThumbnail(MultipartFile multipartFile, String basePath, double scale) throws RuntimeException{
	    log.info("按固定尺寸上传缩略图");
        String path = ConfigUtil.getImgUploadPath()+basePath;
        String fileName = UUID.randomUUID().toString() +IMG_TYPE;
        try {
            File dir = new File(path);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            File targetFile = new File(dir, fileName);
            InputStream in = multipartFile.getInputStream();
            Thumbnails.of(in).scale(scale).toFile(targetFile);
            IOUtils.closeQuietly(in);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("缩略图上传失败!");
        }
	    return basePath+fileName;
	}
	
	
	/**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月2日下午2:08:49;</p>
     *  <p>Description: 创建缩略图;</p>
     *  @param file
     *  @param basePath
     *  @param width
     *  @param height
     */
    public static String createThumbnail(File file,String basePath,int width,int height) {
        log.info("创建缩略图");
        String thumbnailFileName = UUID.randomUUID().toString() + IMG_TYPE;
        String thumbnailPath=  ConfigUtil.getImgUploadPath()+basePath+File.separator +thumbnailFileName;
        try {
            Thumbnails.of(file.getPath()).size(width, height).toFile(thumbnailPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return basePath+thumbnailFileName;
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月2日下午3:20:31;</p>
     *	<p>Description: 按比例压缩;</p>
     *  @param file
     *  @param basePath
     *  @param scale
     *  @return
     */
    public static String createThumbnail(File file,String basePath,float scale) {
        log.info("创建缩略图");
        String thumbnailFileName = UUID.randomUUID().toString() + IMG_TYPE;
        String thumbnailPath=  ConfigUtil.getImgUploadPath()+basePath+File.separator +thumbnailFileName;
        try {
            Thumbnails.of(file.getPath()).scale(scale).toFile(thumbnailPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return basePath+thumbnailFileName;
    }

	public static String uploadVedio(MultipartFile file, String basePath) {
		if (file == null) {
			return null;
		}

		String path = ConfigUtil.getImgUploadPath() + basePath;
		String fileName = UUID.randomUUID().toString() + ".mp4";
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return basePath.endsWith("/") ? basePath +fileName :basePath + "/" + fileName;

	}
}
