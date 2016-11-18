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

import tech.youmu.ckl.app.vo.PresentServiceInfo;
import tech.youmu.ckl.app.vo.PromotionCodeInfo;
import tech.youmu.ckl.app.vo.RechargeComboInfo;
import tech.youmu.ckl.domain.RechargeCombo;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.RechargeComboMapper;
import tech.youmu.ckl.mapper.UserMapper;
import tech.youmu.ckl.service.IRechargeComboService;
import tech.youmu.ckl.utils.QRCodeUtil;
import tech.youmu.ckl.utils.ShareUtil;
import tech.youmu.ckl.utils.UserCodeUtil;

/**
 * 
 * <p>Title:RechargeComboServiceImpl</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年10月11日上午11:33:24</p>
 * <p>Description:TODO</p>
 */
@Service
public class RechargeComboServiceImpl  extends BaseServiceImpl<RechargeCombo> implements IRechargeComboService {
	
    @Autowired
    public RechargeComboServiceImpl(RechargeComboMapper rechargeComboMapper) {
        super(rechargeComboMapper);
        this.rechargeComboMapper = rechargeComboMapper;
    }

	private RechargeComboMapper rechargeComboMapper;
	
	@Autowired
    private UserMapper userMapper;

    @Override
    public List<RechargeComboInfo> findRechargeComboInfo() {
        return rechargeComboMapper.findRechargeComboInfo();
    }

    @Override
    public List<PresentServiceInfo> findPresentServiceInfoByRechargeComboId(Long rechargeComboId) {
        return rechargeComboMapper.findPresentServiceInfoByRechargeComboId(rechargeComboId);
    }

    @Override
    public List<PresentServiceInfo> findPresentServiceInfoByPromotionCode(String promotionCode) {
        String code = promotionCode.substring(0, 1);
        return rechargeComboMapper.findPresentServiceInfoByCode(code);
    }

    @Override
    public List<PromotionCodeInfo> findPromotionCodeInfo(Long userId) {
        User user = userMapper.getById(userId);
        if(user ==null){
            throw new BizExecption("用户被冻结或者没有注册");
        }
        if(!user.getIsAttend()){
            throw new BizExecption("不满足分销计划");
        }
        if(user.getCode() == null){
            String code = UserCodeUtil.getUserCode();
            user.setCode(code);
            userMapper.update(user);
        }
        List<PromotionCodeInfo> promotionCodeInfos =  rechargeComboMapper.findPromotionCodeInfo(user.getCode());
        for(PromotionCodeInfo promotionCodeInfo:promotionCodeInfos){
            promotionCodeInfo.setPromotionQRCodeUrl(QRCodeUtil.getQRCodeUrl(promotionCodeInfo.getPromotionCode()));
            promotionCodeInfo.setShareUrl(ShareUtil.getPromotionCodeShareUrl(promotionCodeInfo.getRechargeComboId(),userId));
        }
        return promotionCodeInfos;
    }

    @Override
    public RechargeComboInfo getRechargeComboInfoByPromotionCode(String promotionCode) {
        String code = promotionCode.substring(0, 1);
        RechargeComboInfo rechargeComboInfo = rechargeComboMapper.getRechargeComboInfoByPromotionCode(code);
        if(rechargeComboInfo == null){
            throw new BizExecption("二维码错误");
        }
        return rechargeComboInfo;
    }

    @Override
    public PromotionCodeInfo getPromotionCodeInfo(Long userId, Long rechargeComboId) {
        PromotionCodeInfo promotionCodeInfo = new PromotionCodeInfo();
        User user = userMapper.getById(userId);
        RechargeCombo rechargeCombo =  rechargeComboMapper.getById(rechargeComboId);
        if(rechargeCombo !=null&&user !=null){
            promotionCodeInfo.setName(rechargeCombo.getName());
            promotionCodeInfo.setPrice(rechargeCombo.getPrice());
            promotionCodeInfo.setContent(rechargeCombo.getContent());
            promotionCodeInfo.setPromotionCode(rechargeCombo.getCode()+user.getCode());
            promotionCodeInfo.setPromotionQRCodeUrl(QRCodeUtil.getQRCodeUrl(promotionCodeInfo.getPromotionCode()));
            promotionCodeInfo.setPresentServiceInfos(rechargeComboMapper.findPresentServiceInfoByRechargeComboId(rechargeComboId));
        }
        return promotionCodeInfo;
    }
}
