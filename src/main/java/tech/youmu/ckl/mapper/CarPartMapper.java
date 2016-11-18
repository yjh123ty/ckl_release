package tech.youmu.ckl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.CarPartInfo;
import tech.youmu.ckl.app.vo.CarPartTypeInfo;
import tech.youmu.ckl.domain.CarPart;
import tech.youmu.ckl.domain.CarPartType;
import tech.youmu.ckl.domain.OrderServiceDetail;

/**
 * 
 * <p>Title:CarPartMapper</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年9月14日下午2:34:47</p>
 * <p>Description:汽车配件</p>
 */
public interface CarPartMapper extends BaseMapper{

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月14日下午2:39:13;</p>
     *	<p>Description: 汽车配件类型信息;</p>
     *  @return
     */
    List<CarPartTypeInfo> findCarPartTypeInfo();

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月14日下午2:45:44;</p>
     *	<p>Description: TODO;</p>
     *  @return
     */
    List<CarPartInfo> findCarPartInfo(@Param("carPartTypeId")Long carPartTypeId,@Param("key")String key);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月12日下午3:21:52;</p>
     *	<p>Description: TODO;</p>
     *  @param name
     *  @param sn
     *  @return
     */
    int checkCount(CarPart carPart);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月18日上午11:20:53;</p>
     *	<p>Description: 扫码;</p>
     *  @param code
     *  @return
     */
    CarPartInfo getCarPartInfoByCode(String code);

    List<String> findPic(@Param("list")List<OrderServiceDetail> orderServiceDetails);

    List<CarPart> findCarPartByCodes(@Param("list")List<String> codes);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年11月3日下午4:31:07;</p>
     *	<p>Description: 批量导入;</p>
     *  @param carParts
     */
    void importCarParts(@Param("list") List<Map<String, Object>> carParts);
    
}