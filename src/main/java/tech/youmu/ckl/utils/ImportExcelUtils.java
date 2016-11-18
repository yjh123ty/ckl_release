/**
 * Project Name:ckl
 * File Name:ImportExcelUtils.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.exception.BizExecption;

/**
 * <p>Title:ImportExcelUtils</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月22日下午3:19:19</p>
 * <p>Description:TODO</p>
 */
public class ImportExcelUtils {
    
private static Logger logger = LoggerFactory.getLogger(ImportExcelUtils.class);
    
    public static final String EXCEL_2003_CONENT_TYPE = "application/vnd.ms-excel";
    
    public static final String EXCEL_2007_CONENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年11月3日上午10:22:49;</p>
     *  <p>Description: 读取excel 列的顺序和属性的顺序一致;</p>
     *  @param file excel 文件
     *  @param clz excel 读取后的类型
     *  @param properties 对应的那些文件
     *  @return
     *  @throws IOException
     */
    public static List<Map<String, Object>> readExcel(MultipartFile file, String[] properties) {
       return readExcel(file, new int[properties.length], properties);
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年11月3日上午10:22:49;</p>
     *  <p>Description: 读取excel 列的位置的顺序和属性的顺序一致;</p>
     *  @param file excel 文件
     *  @param clz excel 读取后的类型
     *  @param columns  读取表中的那些列
     *  @param properties 对应的那些文件
     *  @return
     *  @throws IOException
     */
    public static List<Map<String, Object>> readExcel(MultipartFile file, int[] columnNumbers, String[] properties) {
        List<Map<String, Object>> list = Collections.emptyList();
        InputStream in;
        try {
            in = file.getInputStream();
            if(EXCEL_2003_CONENT_TYPE.equals(file.getContentType())) {
                logger.debug("read 2003 excel...");
                list = readXLS(in, columnNumbers, properties);
            }else {
                logger.debug("read 2007 excel...");
                list = readXLSX(in, columnNumbers, properties);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("excel read error...", e);
        }
        return list;
    }
    
    /**
     * 
     *  <p>Author:zh;</p>
     *  <p>Date:2016年11月3日上午10:31:33;</p>
     *  <p>Description: 读取2003;</p>
     *  @param file
     *  @param clz
     *  @param columns
     *  @param properties
     *  @return
     */
    public static List<Map<String, Object>> readXLS(InputStream in, int[] columnNumbers, String[] properties) throws Exception{
        List<Map<String, Object>> result = new ArrayList<>();
        // 读取文件
        HSSFWorkbook workbook = new HSSFWorkbook(in);
        
        try {
            // 表的数量
            int sheetCount = workbook.getNumberOfSheets();
            for (int i =0; i<sheetCount; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int rowNum = sheet.getLastRowNum();
                // 遍历表填充json对象
                for (int j = 1; j <=rowNum; j++) {
                   HSSFRow row = sheet.getRow(j);
                   
                   Map<String, Object> map = new HashMap<String, Object>();
                   for (int k=0; k<columnNumbers.length; k++) {
                       HSSFCell cell = row.getCell(k);
                       int type = cell.getCellType();
                       switch (type) {
                        case Cell.CELL_TYPE_BLANK:
                            // nothing to do
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            map.put(properties[k], cell.getBooleanCellValue());
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            map.put(properties[k], cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            double doubleValue = cell.getNumericCellValue();
                            if(doubleValue % 1 == 0){
                                map.put(properties[k], (int)doubleValue);
                            }else {
                                map.put(properties[k], doubleValue);
                            }
                            break;
                        case Cell.CELL_TYPE_ERROR:
                            // nothing to do...
                            break;
                        default:
                            map.put(properties[k], cell.getStringCellValue());
                            break;
                       }
                   }
                   result.add(map);
                }
            }
        } finally{
           IOUtils.closeQuietly(in);
        }
        
        // 将json对象转换为真实的对象
        return result;
    }
    
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年11月3日上午10:31:46;</p>
     *  <p>Description: 读取2007;</p>
     *  @param file
     *  @param clz
     *  @param columns
     *  @param properties
     *  @return
     */
    public static List<Map<String,Object>> readXLSX(InputStream in, int[] columnNumbers, String[] properties) throws Exception{
        List<Map<String, Object>> result = new ArrayList<>();
        // 读取文件
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        
        try {
            // 表的数量
            int sheetCount = workbook.getNumberOfSheets();
            for (int i =0; i<sheetCount; i++) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                int rowNum = sheet.getLastRowNum();
                // 遍历表填充json对象
                for (int j = 1; j <=rowNum; j++) {
                   XSSFRow row = sheet.getRow(j);
                   
                   Map<String, Object> map = new HashMap<String, Object>();
                   for (int k=0; k<columnNumbers.length; k++) {
                       XSSFCell cell = row.getCell(k);
                       int type = cell.getCellType();
                       switch (type) {
                        case Cell.CELL_TYPE_BLANK:
                            // nothing to do
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            map.put(properties[k], cell.getBooleanCellValue());
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            map.put(properties[k], cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            double doubleValue = cell.getNumericCellValue();
                            if(doubleValue % 1 == 0){
                                map.put(properties[k], (int)doubleValue);
                            }else {
                                map.put(properties[k], doubleValue);
                            }
                            break;
                        case Cell.CELL_TYPE_ERROR:
                            // nothing to do...
                            break;
                        default:
                            map.put(properties[k], cell.getStringCellValue());
                            break;
                       }
                   }
                   result.add(map);
                }
            }
        } finally{
           IOUtils.closeQuietly(in);
        }
        
        // 将json对象转换为真实的对象
        return result;
    }
    
    public static List<String[]> importFile(MultipartFile upload) throws Exception {
        List<String[]> list = new ArrayList<String[]>();
        String fileType = "";
        try {
               String fileName = upload.getOriginalFilename();
               fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
           } catch (Exception e) {
               fileType = "";
               e.printStackTrace();
           }
           if (!fileType.toLowerCase().equals("xlsx") && !fileType.toLowerCase().equals("xls")) {
                throw new BizExecption("导入的文件格式不正确!");
           }
           
           //导入的是2007版的
           else if(fileType.toLowerCase().equals("xlsx")){
               // 创建读取对象
               Workbook workbook = new XSSFWorkbook(upload.getInputStream());
               
               // 获取表
               Sheet sheet = workbook.getSheetAt(0);
               // System.out.println("总的行数:" + sheet.getLastRowNum());
               // 去掉表头的标题，直接从数据行开始读取
               for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                   // 获取表里面的行对象
                   Row row = sheet.getRow(i);
                   // 获取当前行有多少列
                   short cellNum = row.getLastCellNum();
                   // 初始化获取的String数组
                   String[] strings = new String[cellNum];
                   // System.out.println("每行的列数:" + row.getLastCellNum());
                   for (int j = 0; j < cellNum; j++) {
                       // 获取单元格对象
                       Cell cell = row.getCell(j);
                       if (cell != null) {
                           if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                               // 返回布尔类型的值
                               strings[j] = String.valueOf(cell.getBooleanCellValue());
                           } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                               // 返回数值类型的值,需要转换一下
                               DecimalFormat df = new DecimalFormat("0"); 
                               strings[j] = df.format(cell.getNumericCellValue());
                           } else {
                               // 返回字符串类型的值
                               strings[j] = String.valueOf(cell.getStringCellValue());
                           }
                       }
                   }
                   // 保存完一条记录，即是完成对一个object对象的设置，即是数据库中的一条记录,将其存入list中
                   list.add(strings);
               }
           }
           
           //导入的是2003版的
           else if(fileType.toLowerCase().equals("xls")){
               // 创建读取对象
               HSSFWorkbook  workbook = new HSSFWorkbook(upload.getInputStream());
               // 获取表
               HSSFSheet  sheet = workbook.getSheetAt(0);
               
               // 去掉表头的标题，直接从数据行开始读取
               for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                   // 获取表里面的行对象
                   HSSFRow row = sheet.getRow(i);
                   // 获取当前行有多少列
                   short cellNum = row.getLastCellNum();
                   // 初始化获取的String数组
                   String[] strings = new String[cellNum];
                   // System.out.println("每行的列数:" + row.getLastCellNum());
                   for (int j = 0; j < cellNum; j++) {
                       // 获取单元格对象
                       HSSFCell cell = row.getCell(j);
                       if (cell != null) {
                           if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
                               // 返回布尔类型的值
                               strings[j] = String.valueOf(cell.getBooleanCellValue());
                           } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                               // 返回数值类型的值,需要转换一下
                               DecimalFormat df = new DecimalFormat("0"); 
                               strings[j] = df.format(cell.getNumericCellValue());
                           } else {
                               // 返回字符串类型的值
                               strings[j] = String.valueOf(cell.getStringCellValue());
                           }
                       }
                   }
                   // 保存完一条记录，即是完成对一个object对象的设置，即是数据库中的一条记录,将其存入list中
                   list.add(strings);
               }
           }
           
           return list;
    }
}
