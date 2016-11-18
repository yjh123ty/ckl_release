package tech.youmu.ckl.service;

import java.util.List;

import tech.youmu.ckl.app.vo.RecommendInfo;
import tech.youmu.ckl.app.vo.TeamInfo;

/**
 * 
 * <p>Title:IUserDistributionService</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年10月10日上午11:51:23</p>
 * <p>Description:TODO</p>
 */
public interface IUserDistributionService {

    public RecommendInfo getRecommendInfo(Long userId);

    public List<TeamInfo> findTeamInfo(Long userId);
}
