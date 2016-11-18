/**
 * @Title: BaseServiceImpl.java
 * @Package tech.youmu.ckl.service.impl
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月5日 下午1:57:21
 * @version V1.0
 */

package tech.youmu.ckl.service.impl;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.BaseMapper;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IBaseService;

/**
  * @ClassName: BaseServiceImpl
  * @Description: 服务基类
  * @author youmu-zh
  * @date 2016年8月5日 下午1:57:21
  *
  */

public abstract class BaseServiceImpl<T> implements IBaseService<T> {
	
	private BaseMapper<T> baseMapper;
	
	/**
	  * 创建一个新的实例 BaseServiceImpl. 
	  */
	public BaseServiceImpl(BaseMapper<T> baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public T getById(Long id) {
		return baseMapper.getById(id);
	}

	@Override
	public boolean save(T t) {
		return baseMapper.save(t) == 1;
	}

	@Override
	public boolean updateById(T t) {
		return baseMapper.updateById(t) == 1;
	}

	@Override
	public boolean deleteById(Long id) {
		return baseMapper.deleteById(id) == 1;
	}
	

	@Override
	public List<T> getAll() {
		return baseMapper.getAll();
	}
	
	/**
	  * @see tech.youmu.ckl.service.IBaseService#getCount(tech.youmu.ckl.query.BaseQuery)
	  */
	@Override
	public long getCount(BaseQuery query) {
		return baseMapper.getCountByQuery(query);
	}
	
	/**
	  * @see tech.youmu.ckl.service.IBaseService#getPageList(tech.youmu.ckl.query.BaseQuery)
	  */
	@Override
	public PageList<T> getPageList(BaseQuery query) {
		// 创建一个空的页列表
		PageList<T>  pageList = new PageList<>();
		// 根据分页查询条件  查询记录数
		long count = baseMapper.getCountByQuery(query);
		
		// 判断记录数 大于0 查询 页的数据 放入分页列表对象中
		if(count > 0) {
			pageList.setTotal(count);
			pageList.setRows(baseMapper.getByQuery(query));
		}
		return pageList;
	}
	
	@Override
	public SXSSFWorkbook download(String[] heads, List<String[]> list) throws Exception {
		// 创建一个对象内存
		SXSSFWorkbook wb = new SXSSFWorkbook();
		
		//--------------- 样式对象   -------------------------
		XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle(); 
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);  // 垂直   
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);             // 水平
        
        /**字体begin*/
//        style.setFillForegroundColor();    
        //生成一个字体
        XSSFFont font= (XSSFFont) wb.createFont();
        font.setFontHeightInPoints((short)12);
        font.setBoldweight((short)18);         //字体增粗
       //把字体应用到当前的样式
        style.setFont(font);
        /**字体end*/
      //----------------------------------------
		// 创建一个表
		Sheet sh = wb.createSheet();
		// 表中的第一行
		Row row0 = sh.createRow(0);
		for (int cellnum = 0; cellnum < heads.length; cellnum++) {
			// 创建表里面的第一个行，并对第一行中的列进行循环赋值
			// 处理单元格:列的索引是固定
			Cell cell = row0.createCell(cellnum);
			// 设置样式
			cell.setCellStyle(style);
			// 赋值
			cell.setCellValue(heads[cellnum]);
		}
		// 查询的数据
		for (int rownum = 0; rownum < list.size(); rownum++) {
			// 创建表里面的行,从第二行开始
			Row row = sh.createRow(rownum + 1);
			// 处理每一行的列==String[]
			String[] strings = list.get(rownum);
			for (int cellnum = 0; cellnum < strings.length; cellnum++) {
				// 处理单元格
				Cell cell = row.createCell(cellnum);
				cell.setCellValue(strings[cellnum]);
			}
		}
		return wb;
	}
	
	
}
