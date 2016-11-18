package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.app.vo.RecommendInfo;
import tech.youmu.ckl.app.vo.TeamInfo;
import tech.youmu.ckl.mapper.UserDistributionMapper;
import tech.youmu.ckl.service.IUserDistributionService;
import tech.youmu.ckl.utils.ImageURLUtil;

/**
 * 
 * <p>Title:UserDistributionServiceImpl</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年10月10日上午11:52:13</p>
 * <p>Description:TODO</p>
 */
@Service
public class UserDistributionServiceImpl implements IUserDistributionService {

    @Autowired
    private UserDistributionMapper userDistributionMapper;

    @Override
    public RecommendInfo getRecommendInfo(Long userId) {
        RecommendInfo recommendInfo = new RecommendInfo();
        int teamNumber = userDistributionMapper.getTeamNumber(userId);
        recommendInfo.setTeamNumber(teamNumber);
        return recommendInfo;
    }

    @Override
    public List<TeamInfo> findTeamInfo(Long userId) {
        List<TeamInfo> teamInfos =  userDistributionMapper.findTeamInfo(userId);
        for(TeamInfo teamInfo:teamInfos){
            teamInfo.setHeadIcon(ImageURLUtil.fillPath(teamInfo.getHeadIcon()));
        }
        return teamInfos;
    }


}
