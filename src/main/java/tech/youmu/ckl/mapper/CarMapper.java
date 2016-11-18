package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.app.vo.CarInfo;
import tech.youmu.ckl.domain.UserCar;

/**
 * 
 * <p>Title:CarMapper</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月23日下午3:56:28</p>
 * <p>Description:TODO</p>
 */
public interface CarMapper extends BaseMapper{

	List<CarInfo> findCarInfo(Long userId);

	void updateByUserId(UserCar userCar);

    CarInfo getDefaultCarInfo(Long userId);

}