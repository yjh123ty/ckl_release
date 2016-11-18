package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.CurrentUserRouteInfo;
import tech.youmu.ckl.app.vo.UserRouteInfo;
import tech.youmu.ckl.domain.Station;
import tech.youmu.ckl.domain.UserRoute;
import tech.youmu.ckl.domain.UserRouteStation;

public interface UserRouteMapper {

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月21日下午5:45:22;</p>
     *	<p>Description: 获取当前路线经过的驿站;</p>
     *  @param routeId
     *  @param userId
     *  @return
     */
    Station getPassUserRouteStation(Long userId);
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月22日上午10:35:08;</p>
     *	<p>Description: 获取我的当前行程;</p>
     *  @param userId
     *  @return
     */
    UserRoute getCurrentUserRoute(Long userId);

    void save(UserRoute userRoute);

    void saveUserRouteStation(UserRouteStation userRouteStation);

    void update(UserRoute currentUserRoute);
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月22日下午4:01:46;</p>
     *	<p>Description: 我的行程;</p>
     *  @param i
     *  @param rows
     *  @param userId
     *  @return
     */
    List<UserRouteInfo> findUserRouteInfo(@Param("startRows")Integer startRows, @Param("rows")Integer rows, @Param("userId")Long userId);
   
    UserRoute getById(Long id);
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月23日下午4:39:13;</p>
     *	<p>Description: 导航结束到达的站点/即将到达的;</p>
     *  @param userId
     *  @return
     */
    Station getArriveUserRouteStation(Long userId);
    
    void updateUserRouteStationByUserRouteId(Long userRouteId);
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月26日上午11:06:22;</p>
     *	<p>Description: 获取最后一个站点;</p>
     *  @param userId
     *  @return
     */
    Station getLastUserRouteStation(Long userId);

    
}