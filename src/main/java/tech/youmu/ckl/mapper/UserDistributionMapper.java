package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.app.vo.RecommendInfo;
import tech.youmu.ckl.app.vo.TeamInfo;
import tech.youmu.ckl.domain.UserDistribution;

public interface UserDistributionMapper extends BaseMapper{


    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月10日下午5:51:30;</p>
     *	<p>Description: 团队信息;</p>
     *  @param userId
     *  @return
     */
    List<TeamInfo> findTeamInfo(Long userId);

    int getTeamNumber(Long userId);

    UserDistribution getByUserId(Long userId);

}