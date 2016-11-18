/**
 * @Title: IRouteService.java
 * @Package tech.youmu.ckl.service
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月17日 下午4:39:34
 * @version V1.0
 */

package tech.youmu.ckl.service;

import java.util.List;

import tech.youmu.ckl.app.vo.CurrentUserRouteInfo;
import tech.youmu.ckl.app.vo.UserRouteInfo;

/**
 * 
 * <p>Title:IUserRouteService</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年9月22日上午11:03:23</p>
 * <p>Description:TODO</p>
 */

public interface IUserRouteService {

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月22日上午11:05:15;</p>
     *	<p>Description: 用户当前行程;</p>
     *  @param userId
     *  @return
     */
    CurrentUserRouteInfo getCurrentUserRouteInfo(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月22日上午11:25:53;</p>
     *	<p>Description: 开始导航;</p>
     *  @param userId
     *  @param routeId
     * @return 
     */
    long startNavigate(Long userId, Long routeId,Long stationId,String lng,String lat);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月22日下午1:42:59;</p>
     *	<p>Description: ;</p>
     *  @param userId
     *  @param stationId
     * @return 
     */
    long endNavigate(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月22日下午2:23:11;</p>
     *	<p>Description: 结束行程;</p>
     *  @param userId
     * @return 
     */
    long endUserRoute(Long userId);

    List<UserRouteInfo> findUserRouteInfo(Integer page, Integer rows, Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月22日下午4:14:27;</p>
     *	<p>Description: 删除行程;</p>
     *  @param userRouteId
     */
    void deleteUserRoute(Long userRouteId);

    long continueNavigate(Long userId,Long stationId);

}
