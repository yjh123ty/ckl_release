/**
 * Project Name:ckl
 * File Name:RestaurantComboController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.RestaurantCombo;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.service.IRestaurantComboService;

/**
 * <p>
 * Title:RestaurantComboController
 * </p>
 * @author zh
 * @version v1.0
 *          <p>
 *          Date:2016年9月8日下午6:34:31
 *          </p>
 *          <p>
 *          Description:饭店套餐控制器
 *          </p>
 */
@Controller
@RequestMapping("/restaurantcombo")
public class RestaurantComboController {

    @Autowired
    private IRestaurantComboService restaurantComboService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    

    /**
     * <p>
     * Author:zh;
     * </p>
     * <p>
     * Date:2016年9月9日下午3:10:19;
     * </p>
     * <p>
     * Description: 展示一个饭店下面所有的饭店套餐的数据;
     * </p>
     * @param cmd
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public List<RestaurantCombo> list(Long id) {
        return restaurantComboService.findRestaurantCombos(id);
    }

    /**
     * <p>
     * Author:zh;
     * </p>
     * <p>
     * Date:2016年9月9日下午3:10:19;
     * </p>
     * <p>
     * Description: 展示一个饭店下面所有的饭店套餐的数据;
     * </p>
     * @param cmd
     * @return
     */
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult edit(@RequestParam("image")MultipartFile image, RestaurantCombo room) {
        try {
            if (room.getId() == null) {
                if(image == null || image.isEmpty()){
                    return AjaxResult.fail("套餐图片不能为空");
                }
                restaurantComboService.save(image, room);
                return AjaxResult.success("饭店套餐添加成功");
            } else {
                restaurantComboService.updateById(image,room);
                return AjaxResult.success("饭店套餐修改成功");
            }
        } catch (BizExecption e) {
            logger.error("饭店套餐编辑异常：", e);
            return AjaxResult.fail(e.getMessage());
        }catch (Exception e) {
            logger.error("饭店套餐编辑异常：", e);
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
     * Description: 删除一个饭店房间;
     * </p>
     * @param cmd
     * @return
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    public AjaxResult delete(Long id) {
        try {
            if (id != null) {
                restaurantComboService.deleteById(id);
            }
            return AjaxResult.success("删除成功");
        } catch (Exception e) {
            logger.error("套餐删除异常:", e);
            return AjaxResult.fail("系统异常");
        }
    }
}
