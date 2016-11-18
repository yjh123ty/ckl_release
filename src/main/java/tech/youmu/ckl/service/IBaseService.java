/**
 * @Title: IBaseService.java
 * @Package tech.youmu.ckl.service
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月5日 下午1:51:42
 * @version V1.0
 */

package tech.youmu.ckl.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.PageList;

/**
  * @ClassName: IBaseService
  * @Description: 一些对象公共的操作
  * @author youmu-zh
  * @date 2016年8月5日 下午1:51:42
  *
  */
public interface IBaseService<T> {
	/**
	  * getById(通过id查询T对象)
	  *
	  * @param id 主键
	  * @return id 对应的T对象
	 */
	public T getById(Long id);
	
	/**
	 * 
	  * save(添加一个T)
	  *  	注意： T的资源不能为空
	  * @param t T对象
	  * @return boolean 是否添加成功 
	 */
	public boolean save(T t);
	
	/**
	 * 
	  * updateById(通过id来修改T)
	  *		注意： T的资源不能为空
	  * @param t T对象
	  * @boolean 是否修改成功
	 */
	public boolean updateById(T t);
	
	/**
	 * 
	  * deleteById(通过id来删除T)
	  *
	  * @param id Tid
	 * @return 
	  * @boolean 是否删除成功
	 */
	public boolean deleteById(Long id);
	
	/**
	 * 
	  * getAll(查询所有的T)
	  *
	  * @return T列表
	 */
	public List<T> getAll();
	
	/**
	  * getCount(根据查询条件来查询的 分页用的数据总条数)
	  * @return int 根据条件查询的条数
	 */
	public long getCount(BaseQuery query);
	
	/**
	 * getCount(根据查询条件来查询  一页数据)
	 * @return PageList<T>  根据条件查询的数据分页对象
	 */
	public PageList<T> getPageList(BaseQuery query);
	
	/**
	 *  下载xlsx文件
	 * @param heads	表头
	 * @param list	数据
	 * @return
	 * @throws Exception
	 */
	SXSSFWorkbook download(String[] heads, List<String[]> list) throws Exception;
	
}
