/**
 * @Title: UserServiceImpl.java
 * @Package tech.youmu.ckl.service.impl
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 上午10:58:35
 * @version V1.0
 */

package tech.youmu.ckl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.CarPartInfo;
import tech.youmu.ckl.app.vo.CarPartTypeInfo;
import tech.youmu.ckl.domain.CarPart;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.CarPartMapper;
import tech.youmu.ckl.service.ICarPartService;
import tech.youmu.ckl.utils.CarPartCodeUtil;
import tech.youmu.ckl.utils.ImageURLUtil;
import tech.youmu.ckl.utils.ImportExcelUtils;
import tech.youmu.ckl.utils.LogUtils;

/**
 * 
 * <p>Title:CarPartServiceImpl</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年9月14日下午2:33:52</p>
 * <p>Description:TODO</p>
 */
@Service
public class CarPartServiceImpl extends BaseServiceImpl<CarPart>  implements ICarPartService {
    
    private CarPartMapper carPartMapper;
    
    @Autowired
    public CarPartServiceImpl(CarPartMapper carPartMapper) {
        super(carPartMapper);
        this.carPartMapper = carPartMapper;
    }
    
    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.impl.BaseServiceImpl#save(java.lang.Object)
     */
    @Override
    public boolean save(CarPart t) {
        if(carPartMapper.checkCount(t) != 0){
            throw new BizExecption("商品名称或编号已经存在");
        }
        return super.save(t);
    }
    
    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.impl.BaseServiceImpl#updateById(java.lang.Object)
     */
    @Override
    public boolean updateById(CarPart t) {
        if(carPartMapper.checkCount(t) != 0){
            throw new BizExecption("商品名称或编号已经存在");
        }
        return super.updateById(t);
    }
    

    @Override
    public List<CarPartTypeInfo> findCarPartTypeInfo() {
        return carPartMapper.findCarPartTypeInfo();
    }

    @Override
    public List<CarPartInfo> findCarPartInfo(Long carPartTypeId,String key) {
        List<CarPartInfo> carPartInfos =  carPartMapper.findCarPartInfo(carPartTypeId,key);
        for(CarPartInfo carPartInfo : carPartInfos){
            carPartInfo.setPic(ImageURLUtil.fillPath(carPartInfo.getPic()));
        }
        return carPartInfos;
    }

    @Override
    public CarPartInfo scanQRCode(String code) {
        String cartPartCodes = CarPartCodeUtil.getCartPartCode(code);
        CarPartInfo carPartInfo = carPartMapper.getCarPartInfoByCode(cartPartCodes);
        if(carPartInfo == null){
            throw new BizExecption("没有该商品");
        }
        carPartInfo.setNumber(1);
        return carPartInfo;
    }

    @Override
    public List<CarPartInfo> batchScanQRodeByCarPart(String[] codes) {
        List<String> cartPartCodes = CarPartCodeUtil.getCartPartCodes(codes);
        List<CarPart> carParts = carPartMapper.findCarPartByCodes(cartPartCodes);
        if(carParts == null||carParts.size()==0){
            throw new BizExecption("没有商品");
        }
        List<CarPartInfo> carPartInfos = new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();
        for(String code:codes){
            String cartPartCode = CarPartCodeUtil.getCartPartCode(code);
            List<String> codeList=map.get(cartPartCode);
            if(codeList==null){
                codeList = new ArrayList<>();
            }
            codeList.add(code);
            map.put(cartPartCode, codeList);
        }
        for(CarPart carPart:carParts){
            List<String> codeList = map.get(carPart.getSn());
            CarPartInfo carPartInfo =new CarPartInfo(carPart.getId(),carPart.getName(),carPart.getSalePrice(),carPart.getPic(),codeList,codeList.size());
            carPartInfos.add(carPartInfo);
        }
        return carPartInfos;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ICarPartService#importCarPats(org.springframework.web.multipart.MultipartFile)
     */
    @Override
    public void importCarPats(MultipartFile file) {
        // 批量导入零部件
        List<Map<String, Object>> carParts = ImportExcelUtils.readExcel(file, new String[]{
                "name",
                "sn",
                "typeId",
                "model",
                "unitId",
                "centreOutPrice",
                "salePrice",
                "oldMonths",
        });
        LogUtils.getInstance(getClass()).debugBean(carParts);
        if(carParts != null && carParts.size() > 0) {
            carPartMapper.importCarParts(carParts);
        }
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ICarPartService#createExcelTemplate()
     */
    @Override
    public HSSFWorkbook createExcelTemplate() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        String[] columnNames = new String[]{
                "零部件名称",
                "零部件类别编码",
                "分类编号",
                "型号",
                "计量单位编号",
                "中央仓库出库价格",
                "销售价格",
                "老化月份数"
        };
        // 产生Excel表头
        HSSFSheet sheet = workbook.createSheet("零部件");
        HSSFRow header = sheet.createRow(0); // 第0行
        header.setHeight((short)400);
        HSSFCellStyle headerstyle = workbook.createCellStyle();
        headerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        headerstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        headerstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        headerstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        headerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerstyle.setWrapText(false);
        headerstyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置背景色
        headerstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setBoldweight((short)10);
        font.setFontHeightInPoints((short) 12);//设置字体大小
        headerstyle.setFont(font);
        
        // 产生标题列
        for(int i=0;i<columnNames.length;i++){
            HSSFCell cell = header.createCell(i);
            cell.setCellStyle(headerstyle);
            cell.setCellValue(columnNames[i]);
            sheet.setColumnWidth(i, columnNames[i].length()*800);
        }
        return workbook;
    }
}
