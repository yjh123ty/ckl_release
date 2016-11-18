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

import tech.youmu.ckl.app.vo.TrackCoordInfo;
import tech.youmu.ckl.app.vo.TrackDetailInfo;
import tech.youmu.ckl.app.vo.TrackMonthInfo;

/**
 * 
 * <p>Title:ITrackService</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月24日下午5:40:55</p>
 * <p>Description:TODO</p>
 */
public interface ITrackService {

	List<TrackMonthInfo> findTrackMonthInfo(Long userId);

    TrackDetailInfo getTrackDetailInfo(Long trackId,Long userRouteId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月12日上午11:41:19;</p>
     *	<p>Description: 分享轨迹;</p>
     *  @param trackId
     *  @param userId
     */
    void shareTrack(Long trackId, Long userId);



}
