/**
 * @Title: IUserService.java
 * @Package tech.youmu.ckl.service
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 上午10:57:38
 * @version V1.0
 */

package tech.youmu.ckl.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.CarPartInfo;
import tech.youmu.ckl.app.vo.CarPartTypeInfo;
import tech.youmu.ckl.domain.CarPart;
import tech.youmu.ckl.domain.CarPartStockIncome;
import tech.youmu.ckl.domain.Restaurant;

/**
 * 
 * <p>Title:ICarPartService</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年9月14日下午2:33:14</p>
 * <p>Description:汽车配件</p>
 */
public interface ICarPartService extends IBaseService<CarPart>{

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月14日下午2:38:12;</p>
     *	<p>Description: 汽车配件类型信息;</p>
     *  @return
     */
    List<CarPartTypeInfo> findCarPartTypeInfo();

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月14日下午2:44:59;</p>
     *	<p>Description: 获取配件信息;</p>
     *  @return
     */
    List<CarPartInfo> findCarPartInfo(Long carPartTypeId,String key);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月18日上午11:19:36;</p>
     *	<p>Description: 扫码;</p>
     *  @param code
     *  @return
     */
    CarPartInfo scanQRCode(String code);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月19日下午3:48:53;</p>
     *	<p>Description: 批量扫码;</p>
     *  @param codes
     *  @return
     */
    List<CarPartInfo> batchScanQRodeByCarPart(String[] codes);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年11月3日下午4:29:32;</p>
     *	<p>Description: 导入零部件;</p>
     *  @param file
     */
    void importCarPats(MultipartFile file);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年11月3日下午4:37:18;</p>
     *	<p>Description: 创建导出的模板;</p>
     *  @return
     */
    HSSFWorkbook createExcelTemplate();

}
