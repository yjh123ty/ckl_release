/**
 * Project Name:ckl
 * File Name:ProductController.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.constants.SysDicType;
import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.CarPart;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.query.CarPartQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.ICarPartService;
import tech.youmu.ckl.service.ICarPartTypeService;
import tech.youmu.ckl.service.ISysDicService;
import tech.youmu.ckl.utils.ImportExcelUtils;
import tech.youmu.ckl.utils.UploadUtils;

/**
 * <p>Title:ProductController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月20日下午12:01:21</p>
 * <p>Description:商品管理</p>
 */
@Controller
@RequestMapping("/carPart")
public class CarPartController {
    
    @Autowired
    private ICarPartService carPartService;
    
    @Autowired
    private ICarPartTypeService carPartTypeService;
    
    @Autowired 
    private ISysDicService sysDicService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月20日下午12:02:31;</p>
     *	<p>Description: 商品页面导航;</p>
     *  @return
     */
    @RequestMapping("/index.do")
    public String index(String cmd,Long id,Model model){
        model.addAttribute("types", carPartTypeService.getAll());
        
        //跳转到编辑界面
        if(StringUtils.equals("save", cmd)) {
            model.addAttribute("units",sysDicService.getDetailsByTypeName(SysDicType.CAR_PART_UNIT));
            return "carPart/edit";
        }
        
        if(StringUtils.equals("update", cmd)) {
            model.addAttribute("units",sysDicService.getDetailsByTypeName(SysDicType.CAR_PART_UNIT));
            model.addAttribute("carPart",carPartService.getById(id));
            return "carPart/edit";
        }
        
        if(StringUtils.equals("import", cmd)) {
            return "carPart/import";
        }
        
        return "carPart/list";
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月20日下午2:46:31;</p>
     *	<p>Description: 商品列表;</p>
     *  @param query
     *  @param beginTimeStr
     *  @param endTimeStr
     *  @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<CarPart> list(CarPartQuery query){
        return carPartService.getPageList(query);
    }
    
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult save(@RequestParam(value = "img" , required=false) MultipartFile img,CarPart carPart){
        AjaxResult result = new AjaxResult();
        try {
            //设置图片
            if(img != null && !img.isEmpty()){
                carPart.setPic(UploadUtils.uploadFile(img,UploadUtils.CARPART_IMG));
            }
            if(carPart.getId() == null){
                if(StringUtils.isBlank(carPart.getPic())){
                    return AjaxResult.fail("商品图片不能为空");
                }
                carPartService.save(carPart);
                result = AjaxResult.success("保存成功！");
            }else{
                carPartService.updateById(carPart);
                result = AjaxResult.success("修改成功！");
            }
        } catch (BizExecption e) {
            logger.error("零部件编辑：", e);
            result = AjaxResult.fail(e.getMessage());
        }catch (Exception e) {
            logger.error("零部件编辑：", e);
            result = AjaxResult.fail("系统异常");
        }
        return result;
    }
    
    @RequestMapping("/delete.do")
    @ResponseBody
    public AjaxResult delete(Long id){
        AjaxResult result= null;
        try{
                if(id!=null){
                    carPartService.deleteById(id);
                    result = AjaxResult.success("删除成功！");
                }else{
                    result = AjaxResult.fail("未找到该商品！");
                }
        }catch(Exception e) {
            logger.error("零部件删除异常：", e);
            result = AjaxResult.fail("系统异常");
        }
        return result;
    }
    
    @RequestMapping("/import.do")
    @ResponseBody
    public AjaxResult importCarParts(MultipartFile file){
        if(file == null || file.isEmpty()) {
            return AjaxResult.fail("参数不能为空");
        }
        try {
            carPartService.importCarPats(file);
            return AjaxResult.success("导入成功");
        } catch (Exception e) {
           logger.error("零部件导入失败：", e);
           return AjaxResult.fail("系统异常");
        }
    }
    
    @RequestMapping("/downloadExcelTemplate.do")
    public void dowloadExcelTemplate(HttpServletResponse response){
        try {
            String excelName = "零部件批量添加模板.xls";
            HSSFWorkbook workbook =  carPartService.createExcelTemplate();
            // 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
            response.setContentType("APPLICATION/OCTET-STREAM");
            try {
                response.setHeader("Content-Disposition", "attachment; filename="+ new String(excelName.getBytes("UTF-8"), "ISO8859-1" ));
            } catch (UnsupportedEncodingException e) {
                logger.error("编码转换异常：", e);
            }
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
