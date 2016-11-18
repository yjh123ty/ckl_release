/**
 * @Title: CooperatorRestaurantController.java
 * @Package tech.youmu.ckl.web.controller
 * 
 * @author yjh
 * @date 2016年9月23日 下午11:50:40
 * @version V1.0
 */

package tech.youmu.ckl.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Restaurant;
import tech.youmu.ckl.query.RestaurantQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IRestaurantService;

/**
 * @author yjh
 *
 */
@Controller
@RequestMapping("/cooperatorRestaurant")
public class CooperatorRestaurantController {
	@Autowired
    private IRestaurantService restaurantService;
	
	@RequestMapping("/index.do")
    public String index(String cmd, Long id, Model model){
        
        if(StringUtils.equals("save", cmd)) {
            return "cooperator/co_restaurant_edit";
        }
        
        if(StringUtils.equals("update", cmd)) {
            model.addAttribute("restaurant", restaurantService.getById(id));
            return "cooperator/co_restaurant_edit";
        }
        
        return "cooperator/co_restaurant";
    }
	 
	@RequestMapping("/list.do")
    @ResponseBody
    public PageList<Restaurant> cooperatorList(RestaurantQuery query){
        query.setType(2);
        return restaurantService.getPageList(query);
    }
	
	@RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult edit(@RequestParam(value="restaurant_cover_img",required=false) MultipartFile cover,@RequestParam("restaurant_imgs") MultipartFile[] imgs, Restaurant restaurant){
        if(restaurant.getType()!=2) {
            return AjaxResult.fail("该商家不为合作商家！");
        }
        try {
            if(restaurant.getId() == null) {
                restaurantService.saveCooperator(cover, imgs, restaurant);
                AjaxResult r = AjaxResult.success("添加成功");
                return r;
            }else {
                restaurantService.updateById(cover, imgs, restaurant);
                return AjaxResult.success("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
}
