/**
 * @Title: FiveCountRuleController.java
 * @Package tech.youmu.ckl.web.controller
 * 
 * @author yjh
 * @date 2016年9月7日 下午11:14:15
 * @version V1.0
 */

package tech.youmu.ckl.web.controller;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.FiveCountRule;
import tech.youmu.ckl.domain.ServiceRule;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IFiveCountRuleService;

/**
 * @author yjh
 *
 */
@Controller
@RequestMapping("/fiveCountRule")
public class FiveCountRuleController {
	
	@Autowired
	private IFiveCountRuleService fiveCountRuleService;
	
	@RequestMapping("/index.do")
	public String index(String cmd,Long id,Model model){
		//跳转到编辑界面
        if(StringUtils.equals("save", cmd)) {
            return "pointRule/fiveCountRule_edit";
        }
        
        if(StringUtils.equals("update", cmd)) {
            //根据id获取规则
            model.addAttribute("fiveCountRule", fiveCountRuleService.getById(id));
            return "pointRule/fiveCountRule_edit";
        }
		
		return "pointRule/fiveCountRule";
	}
	
	@RequestMapping("list.do")
    @ResponseBody
    public PageList<FiveCountRule> list(BaseQuery baseQuery,String beginTimeStr,String endTimeStr){
        baseQuery.setBeginTimeStr(beginTimeStr);
        baseQuery.setEndTimeStr(endTimeStr);
        return fiveCountRuleService.getPageList(baseQuery);
    }
	
	@RequestMapping("edit.do")
    @ResponseBody
    public AjaxResult save(FiveCountRule fiveCountRule){
        AjaxResult result= null;
        try{
            if(fiveCountRule.getId()==null){
                //新增一条数据，记录录入时间
            	fiveCountRule.setCreateTime(new Date());
                //添加
                fiveCountRuleService.save(fiveCountRule);
                result = AjaxResult.success("添加成功");
            }else{
                //修改
            	fiveCountRuleService.updateById(fiveCountRule);
                result = AjaxResult.success("修改成功");
            }
        }catch(Exception e) {
            e.printStackTrace();
            result = AjaxResult.fail("服务异常！" , 200);
        }
        return result;
    }
}
