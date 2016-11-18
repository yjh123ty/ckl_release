package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.app.vo.CommissionInfo;
import tech.youmu.ckl.app.vo.CommissionMonthInfo;
import tech.youmu.ckl.app.vo.CommissionPointMonthInfo;
import tech.youmu.ckl.app.vo.CommissionYearDetailInfo;
import tech.youmu.ckl.app.vo.CommissionYearInfo;
import tech.youmu.ckl.domain.UserCommission;
import tech.youmu.ckl.domain.UserCommissionItem;
import tech.youmu.ckl.mapper.CommissionMapper;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.CommissionQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.ICommissionService;
import tech.youmu.ckl.utils.DateUtil;

@Service
public class CommissionServiceImpl implements ICommissionService {

    @Autowired
    private CommissionMapper commissionMapper;

    @Override
    public CommissionInfo getCommissionInfo(Long userId) {
        CommissionInfo commissionInfo = new CommissionInfo();
        String lastMonth =DateUtil.getLastMonthOfToday();
        double lastMonthTotalCommission =  commissionMapper.getLastMonthTotalCommission(userId,lastMonth);
        double lastMonthTotalPoint = commissionMapper.getLastMonthTotalPoint(userId,lastMonth);
        double yearTotalCommission = commissionMapper.getYearTotalCommission(userId,DateUtil.getYearOfToday());
        commissionInfo.setLastMonthTotalCommission(lastMonthTotalCommission);
        commissionInfo.setLastMonthTotalPoint(lastMonthTotalPoint);
        commissionInfo.setYearTotalCommission(yearTotalCommission);
        return commissionInfo;
    }

    @Override
    public List<CommissionPointMonthInfo> findCommissionPointMonthInfo(Long userId) {
        return commissionMapper.findCommissionPointMonthInfo(userId);
    }

    @Override
    public List<CommissionMonthInfo> findCommissionMonthInfo(Long userId) {
        return commissionMapper.findCommissionMonthInfo(userId);
    }

    @Override
    public List<CommissionYearInfo> findCommissionYearInfo(Long userId) {
        List<CommissionYearInfo> commissionYearInfos =  commissionMapper.findCommissionYearInfo(userId);
        for(CommissionYearInfo commissionYearInfo : commissionYearInfos){
            double totalAmount = 0;
            for(CommissionYearDetailInfo commissionYearDetailInfo:commissionYearInfo.getCommissionYearDetailInfos()){
                totalAmount +=commissionYearDetailInfo.getAmount();
            }
            commissionYearInfo.setTotalAmount(totalAmount);
        }
        return commissionYearInfos;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ICommissionService#findAllCommissionByQuery(tech.youmu.ckl.query.BaseQuery)
     */
    @Override
    public PageList<UserCommission> findPageTotalCommissionByQuery(BaseQuery query) {
        PageList<UserCommission> pageList = PageList.emptyPageList();
        long count = commissionMapper.findCountTotalCommissionByQuery(query);
        if(count >0){
            pageList.setTotal(count);
            pageList.setRows(commissionMapper.findTotalCommissionByQuery(query));
        }
        return pageList;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ICommissionService#findPageUserCommissionDetail(tech.youmu.ckl.query.CommissionQuery)
     */
    @Override
    public PageList<UserCommissionItem> findPageUserCommissionDetail(CommissionQuery commissionQuery) {
        PageList<UserCommissionItem> pageList = PageList.emptyPageList();
        long count = commissionMapper.findCountCommissionDetailByQuery(commissionQuery);
        if(count >0){
            pageList.setTotal(count);
            pageList.setRows(commissionMapper.findCommissionDetailByQuery(commissionQuery));
        }
        return pageList;
    }

}
