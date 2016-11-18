/**
 * @Title: CooperatorController.java
 * @Package tech.youmu.ckl.web.controller
 * 
 * @author yjh
 * @date 2016年9月23日 下午11:07:29
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
import tech.youmu.ckl.domain.Hotel;
import tech.youmu.ckl.query.HotelQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IHotelService;

/**
 * @author yjh
 *
 */
@Controller
@RequestMapping("/cooperatorHotel")
public class CooperatorHotelController {
	
	@Autowired
    private IHotelService hotelService;
	
	@RequestMapping("/index.do")
    public String index(String cmd, Long id, Model model){
        
        if(StringUtils.equals("save", cmd)) {
            return "cooperator/co_hotel_edit";
        }
        
        if(StringUtils.equals("update", cmd)) {
            model.addAttribute("hotel", hotelService.getById(id));
            return "cooperator/co_hotel_edit";
        }
        
        return "cooperator/co_hotel";
    }
	 
	@RequestMapping("/list.do")
    @ResponseBody
    public PageList<Hotel> cooperatorList(HotelQuery query){
        query.setType(2);
        return hotelService.getPageList(query);
    }
	
	@RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult edit(@RequestParam(value="cover_img", required=false) MultipartFile coverImg, @RequestParam("outside_imgs") MultipartFile[] outsideImgs, Hotel hotel){
        if(hotel.getType()!=2) {
            return AjaxResult.fail("该商家不为合作商家！");
        }
        try {
            if(hotel.getId() == null) {
                hotelService.saveCooperator(coverImg, outsideImgs, hotel);
                AjaxResult r = AjaxResult.success("添加成功！");
                r.setData(hotel.getId());
                return r;
            }else {
                hotelService.updateCooperator(coverImg, outsideImgs, hotel);
                return AjaxResult.success("修改成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("添加失败！");
        }
    }
}
