/**
 * Project Name:ckl
 * File Name:UserDistributionController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import java.io.FileNotFoundException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.RechargeCombo;
import tech.youmu.ckl.domain.UserCommission;
import tech.youmu.ckl.domain.UserCommissionItem;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.CommissionQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.ICommissionService;
import tech.youmu.ckl.service.IRechargeComboService;
import tech.youmu.ckl.utils.JsonUtils;

/**
 * <p>Title:UserDistributionController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月17日下午3:22:35</p>
 * <p>Description:用户分销</p>
 */
@Controller
@RequestMapping("/userDistribution")
public class UserDistributionController {
    
    @Autowired
    private ICommissionService commissionService;
    
    @Autowired
    private IRechargeComboService rechargeComboService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @RequestMapping("/index.do")
    public String index(String cmd, Long userId, Model model){
        // 折让建立分润列表展示
        if("listAward".equals(cmd)){
            return "userDistribution/award_list";
        }
        // 超额奖励展示
        if("listBeyondAward".equals(cmd)){
            return "userDistribution/beyond_award_list";
        }
        
        //  分销佣金详细列表展示
        if("listDetail".equals(cmd)){
            model.addAttribute("userId", userId);
            return "userCommission/list_detail";
        }
        
        return "userCommission/list";
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月17日下午3:33:23;</p>
     *	<p>Description: 展示用户的总佣金;</p>
     *  @param query
     *  @return
     */
    @RequestMapping("/listTotalCommission.do")
    @ResponseBody
    public PageList<UserCommission> list(BaseQuery query){
        // 直接查询
        return commissionService.findPageTotalCommissionByQuery(query);
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月17日下午3:33:23;</p>
     *  <p>Description: 展示用户的佣金明细;</p>
     *  @param query
     *  @return
     */
    @RequestMapping("/listDetail.do")
    @ResponseBody
    public PageList<UserCommissionItem> listDetail(CommissionQuery commissionQuery){
        return commissionService.findPageUserCommissionDetail(commissionQuery);
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月17日下午3:58:14;</p>
     *	<p>Description: 展示充值套餐;</p>
     *  @return
     */
    @RequestMapping("/listRechargeCombo.do")
    @ResponseBody
    public PageList<RechargeCombo> listRechargeCombo(BaseQuery query){
        return rechargeComboService.getPageList(query);
    }
}
