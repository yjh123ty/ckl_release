package tech.youmu.ckl.utils;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;


/**
* 生成excel视图，可用excel工具打开或者保存
* 由ViewController的return new ModelAndView(viewExcel, model)生成
*/
public class ExportExcelUtil extends AbstractExcelView {   
   
    public void buildExcelDocument(Map<String,Object> model, HSSFWorkbook workbook,   
            HttpServletRequest request, HttpServletResponse response)   
            throws Exception {  
    	
    	String excelName = model.get("title")+".xls";
		// 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename="+ new String( excelName.getBytes("gb2312"), "ISO8859-1" ));	
		List stuList = (List) model.get("list");   
		String[] columnNames = (String[]) model.get("columnNames");
		String[] dbColumnNames =(String[]) model.get("dbColumnNames");
		
		// 产生Excel表头
		HSSFSheet sheet = workbook.createSheet("excel");
		HSSFRow header = sheet.createRow(0); // 第0行
		// 产生标题列
		for(int i=0;i<columnNames.length;i++){
			header.createCell((short) i).setCellValue(columnNames[i]);
		}

		// 填充数据
		int rowNum = 1;
		for (Iterator iter = stuList.iterator(); iter.hasNext();) {
			Object element =  iter.next();
			HSSFRow row = sheet.createRow(rowNum++);
			for(int i=0;i<dbColumnNames.length;i++){
				row.createCell((short) i).setCellValue(String.valueOf(getValueByName(dbColumnNames[i],element)));
			}
			
		}

    }   
    private Object getValueByName(String fieldName, Object o) {  
        try {    
        	Object value = null;
        	if(o instanceof Map){
        		value =  ((Map) o).get(fieldName);
        		if(value==null){
                    return 0;
                }
        	}else{
	            String firstLetter = fieldName.substring(0, 1).toUpperCase();    
	            String getter = "get" + firstLetter + fieldName.substring(1);    
	            Method method = o.getClass().getMethod(getter, new Class[] {});
	            value = method.invoke(o, new Object[] {});   
        	}
            if(value==null){
            	return "";
            }
            return value;    
        } catch (Exception e) {    
        	e.printStackTrace();
            return null;    
        }    
    }   
}
