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

import tech.youmu.ckl.app.vo.CarIllegallyInfo;
import tech.youmu.ckl.app.vo.CarInfo;
import tech.youmu.ckl.domain.UserCar;

/**
 * 
 * <p>Title:ICarService</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月23日下午3:55:21</p>
 * <p>Description:车辆</p>
 */
public interface ICarService {

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月24日下午1:04:51;</p>
	 *	<p>Description: TODO;</p>
	 *  @param userId
	 *  @return
	 */
	List<CarInfo> findCarInfo(Long userId);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月24日下午1:04:47;</p>
	 *	<p>Description: TODO;</p>
	 *  @param carBean
	 */
	void save(UserCar userCar);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月24日下午1:04:44;</p>
	 *	<p>Description: TODO;</p>
	 *  @param carBean
	 */
	void update(UserCar userCar);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月24日下午1:04:41;</p>
	 *	<p>Description: TODO;</p>
	 *  @param carId
	 */
	void delete(Long carId);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月24日下午1:04:37;</p>
	 *	<p>Description: TODO;</p>
	 *  @param carId
	 *  @param userId
	 */
	void setDefaultCar(Long carId, Long userId);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年9月14日下午2:06:37;</p>
	 *	<p>Description: 获取用户默认车辆信息;</p>
	 *  @param userId
	 *  @return
	 */
    CarInfo getDefaultCarInfo(Long userId);

    CarIllegallyInfo getCarIllegallyInfo(String plateNumber, String engineNumber, String frameNumber);



}
