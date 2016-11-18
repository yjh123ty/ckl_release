/**
 * Project Name:ckl
 * File Name:HotelRoomController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.HotelRoom;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.service.IHotelRoomService;
import tech.youmu.ckl.utils.VerifyUtil;

/**
 * <p>
 * Title:HotelRoomController
 * </p>
 * @author zh
 * @version v1.0
 *          <p>
 *          Date:2016年9月8日下午6:34:31
 *          </p>
 *          <p>
 *          Description:酒店房间控制器
 *          </p>
 */
@Controller
@RequestMapping("/hotelroom")
public class HotelRoomController {

    @Autowired
    private IHotelRoomService hotelRoomService;
    
    private Logger logger  = LoggerFactory.getLogger(getClass());
    
    @RequestMapping("/index.do")
    public String index(String cmd) {
        if (StringUtils.equals(cmd, "edit")) {
            return "hotel_room/hotel_room_edit";
        } else {
            return "hotel_room/hotel_room";
        }
    }

    /**
     * <p>
     * Author:zh;
     * </p>
     * <p>
     * Date:2016年9月9日下午3:10:19;
     * </p>
     * <p>
     * Description: 展示一个酒店下面所有的房间类型的数据;
     * </p>
     * @param cmd
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public List<HotelRoom> list(Long id) {
        return hotelRoomService.getHotelRooms(id);
    }

    /**
     * <p>
     * Author:zh;
     * </p>
     * <p>
     * Date:2016年9月9日下午3:10:19;
     * </p>
     * <p>
     * Description: 酒店房间编辑
     * </p>
     * @param cmd
     * @return
     */
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult edit(@RequestParam(value="cover_img",required=false) MultipartFile coverImg, @RequestParam("images") MultipartFile[] images, HotelRoom room) {
        try {
            if (room.getId() == null) {
                /*if(coverImg == null || ArrayUtils.isEmpty(images)) {
                    return AjaxResult.fail("必须上传图片");
                }*/
                if(coverImg == null || coverImg.isEmpty()){
                    return AjaxResult.fail("封面图片不能为空");
                }
                if(!VerifyUtil.isNotEmptyMultipartFiles(images)){
                    return AjaxResult.fail("房间图片不能为空");
                }
                hotelRoomService.save(coverImg, images, room);
                return AjaxResult.success("房间类型添加成功");
            } else {
                hotelRoomService.update(coverImg, images, room);
                return AjaxResult.success("房间类型修改成功");
            }
        } catch (BizExecption e) {
            logger.error("饭店房间编辑异常：", e);
            return AjaxResult.fail(e.getMessage());
        }catch (Exception e) {
            logger.error("饭店房间编辑异常：", e);
            return AjaxResult.fail("系统异常");
        }
    }

    /**
     * <p>
     * Author:zh;
     * </p>
     * <p>
     * Date:2016年9月9日下午3:10:19;
     * </p>
     * <p>
     * Description: 删除一个酒店房间;
     * </p>
     * @param cmd
     * @return
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    public AjaxResult delete(Long id) {
        try {
            if (id != null) {
                hotelRoomService.delete(id);
            }
            return AjaxResult.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
}
