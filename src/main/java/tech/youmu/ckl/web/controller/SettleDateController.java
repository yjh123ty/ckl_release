/**
 * Project Name:ckl
 * File Name:SettleDateController.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.web.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.domain.SettleDate;
import tech.youmu.ckl.service.IEmployeeService;
import tech.youmu.ckl.service.ISettleDateService;
import tech.youmu.ckl.utils.DateUtil;
import tech.youmu.ckl.utils.UserContext;

/**
 * <p>
 * Title:SettleDateController
 * </p>
 * @author yjh
 * @version v1.0
 *          <p>
 *          Date:2016年8月26日上午11:22:59
 *          </p>
 *          <p>
 *          Description:每月工资结算日管理
 *          </p>
 */
@Controller
@RequestMapping("settleDate")
public class SettleDateController {
    @Autowired
    private ISettleDateService settleDateService;

    @Resource
    private IEmployeeService employeeService;

    @RequestMapping("index.do")
    public String index(String cmd, Model model) {
        model.addAttribute("settleDate", settleDateService.getOne());
        if (StringUtils.equals("update", cmd)) {
            return "settleDate/settle_date_edit";
        }
        return "settleDate/settle_date";
    }

    /**
     * <p>
     * Author:yjh;
     * </p>
     * <p>
     * Date:2016年8月26日上午10:49:59;
     * </p>
     * <p>
     * Description: 设置每月工资结算日;
     * </p>
     * @return
     */
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult save(SettleDate settleDate) {
        AjaxResult result = null;

        //设置的日期
        Integer theDay = settleDate.getSettleDay();
        //设置是否为最后一天
        Boolean isFinal = settleDate.getIsFinal();
        //调用工具类，获取最后一天
        Integer maxDate = Integer.valueOf(DateUtil.getMaxDayOfMonth());

        try {
            //初始化
            if (isFinal == null ) {
                isFinal = false;
                settleDate.setIsFinal(isFinal);
            }
            
            /*
             * 判断是否设置正确时间：
             */
            //不是最后一天
            if(!isFinal){
                //日期为请选择或者为空，就报错
                if(theDay < 0 || theDay == null){
                    //给出错误提示
                    return result = AjaxResult.fail("请正确设置时间！", 200);
                }
                //选择了日期,将选择的日期与当前月的最大天数做比较，不能超过最大天数
                else {
                    settleDate.setSettleDay(theDay <= maxDate ? theDay : maxDate);
                }
            }
            
            //是最后一天
            else if (isFinal) {
                settleDate.setSettleDay(maxDate);
            }
                
            //设置修改人
            //Employee employee = UserContext.getUser();
            //settleDate.setEmployee(employee);
            settleDate.setEmployee(employeeService.getById(4L));
            
            if (settleDateService.getOne() == null) {
                //设置日期
                settleDate.setCreateTime(new Date());
                //添加
                settleDateService.save(settleDate);
                result = AjaxResult.success("设置成功");
            } else {
                //修改，每次只修改当前这一条记录（id == 1L），数据库sql已经做了处理
                settleDateService.updateById(settleDate);
                result = AjaxResult.success("设置成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = AjaxResult.fail("服务异常！", 200);
        }
        return result;

    }
}
