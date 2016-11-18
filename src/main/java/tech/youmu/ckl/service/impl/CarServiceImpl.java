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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.app.vo.CarIllegallyDetailInfo;
import tech.youmu.ckl.app.vo.CarIllegallyInfo;
import tech.youmu.ckl.app.vo.CarInfo;
import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.UserCar;
import tech.youmu.ckl.mapper.CarMapper;
import tech.youmu.ckl.service.ICarService;
import tech.youmu.ckl.service.component.BadgeComponent;
import tech.youmu.ckl.utils.AliyunUtil;

/**
 * 
 * <p>Title:CarServiceImpl</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月23日下午3:55:45</p>
 * <p>Description:TODO</p>
 */
@Service
public class CarServiceImpl  implements ICarService {
	
	
	@Autowired
	private CarMapper carMapper;
	
	@Autowired
	private BadgeComponent badgeComponent;

	@Override
	public List<CarInfo> findCarInfo(Long userId) {
		return carMapper.findCarInfo(userId);
	}

	@Override
	public void save(UserCar userCar) {
	    badgeComponent.carBadge(userCar.getUserId());
		carMapper.save(userCar);
	}

	@Override
	public void update(UserCar userCar) {
		carMapper.update(userCar);
	}

	@Override
	public void delete(Long carId) {
		carMapper.update(new UserCar(carId,StatusConst.TRUE));
	}

	@Override
	public void setDefaultCar(Long carId,Long userId) {
	    UserCar userCar = new UserCar(userId,carId,StatusConst.FALSE);
		carMapper.updateByUserId(userCar);
		userCar.setIsDefault(StatusConst.TRUE);
		carMapper.update(userCar);
	}

    @Override
    public CarInfo getDefaultCarInfo(Long userId) {
        return carMapper.getDefaultCarInfo(userId);
    }

    @Override
    public CarIllegallyInfo getCarIllegallyInfo(String plateNumber, String engineNumber, String frameNumber) {
        CarIllegallyInfo carIllegallyInfo = AliyunUtil.getCarIllegallyInfo(plateNumber, engineNumber, frameNumber);
        if(carIllegallyInfo!=null&&carIllegallyInfo.getRecords()!=null&&carIllegallyInfo.getRecords().size()>0){
            int totalDegree = 0;
            int totalMoney=0;
            for(CarIllegallyDetailInfo carIllegallyDetailInfo:carIllegallyInfo.getRecords()){
                totalDegree += carIllegallyDetailInfo.getDegree();
                totalMoney += carIllegallyDetailInfo.getMoney();
            }
            carIllegallyInfo.setTotalDegree(totalDegree);
            carIllegallyInfo.setTotalMoney(totalMoney);
        }
        return carIllegallyInfo;
    }



}
