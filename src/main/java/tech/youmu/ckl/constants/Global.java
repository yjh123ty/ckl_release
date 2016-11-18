/**
 * @Title: Global.java
 * @Package tech.youmu.ckl.constants
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月17日 下午3:54:17
 * @version V1.0
 */

package tech.youmu.ckl.constants;

import java.math.BigDecimal;

/**
  * @ClassName: Global
  * @Description: 全局常量
  * @author youmu-zh
  * @date 2016年8月17日 下午3:54:17
  *
  */

public class Global {
	
	/**
	 * 全局时间格式化
	 */
	public static final String DATE_TIME_FORMAT = "yyyy.MM.dd HH:mm:ss";
	
	/**
	 * 后台管理 前台封装到 后台的时间格式化
	 */
	public static final String QUERY_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 金额计算的数字0
	 */
	public static final BigDecimal BIG_DECIMAL_ZORE = new BigDecimal(0);
	
	/**
     * 无评价的订单，默认评价视为3星
     */
    public static final Double DEFAULT_EVALUATION_THREE_STAR = 3.0D;
	
	/**
     * 数字百分化
     */
    public static final BigDecimal BIG_DECIMAL_PERCENT = new BigDecimal(0.01);
	
	/**
	 * 定义服务刻数与分数的比例 ：        1服务刻数*rate = 1分数
	 */
	public static final BigDecimal KE_RATE = new BigDecimal(0.01);
	
	/**
	 * 系统默认的服务基数,为 1
	 */
	public static final BigDecimal KE_BASE = new BigDecimal(1);
	
	/**
	 * 数字一,用于计算服务刻数
	 */
	public static final BigDecimal KE_ONE = new BigDecimal(1);
	
	public static class StationType {
	    
	    /**
	     * 景点
	     */
	    public static final int TYPE_TOURIST_STATION = 1;
	    
	    /**
	     * 车刻丽服务站
	     */
	    public static final int TYPE_SERVICE_STATION = 2;
	}
	
	/**
	 * <p>Title:ServiceType</p>
	 * @author zh
	 * @version	v1.0
	 * <p>Date:2016年8月25日上午10:52:20</p>
	 * <p>Description:用作判断的服务类型</p>
	 */
	public static class ServiceType {
	        
    	/**
    	 * 酒店服务
    	 */
	    public static final int HOTEL = 1 ; 
	    
	    /**
	     * 饭店服务
	     */
	    public static final int RESTAURANT = 2 ;
	    
	    /**
	     * 汽车维护
	     */
	    public static final int CAR_CARE = 3;
	    
	    /**
	     * 汽车维修
	     */
	    public static final int CAR_REPAIR = 4;
	    
	    /**
	     * 道路救援
	     */
	    public static final int ROAD_SIDE = 5;
	    
	    /**
         * 线下订单
         */
        public static final int OFFLINE = 6;
	    
	    /**
	     * 停车
	     */
	    public static final int PARK = 7;
	    
	    /**
	     * 卫生间
	     */
	    public static final int TOILET = 8;
	}
	
	/**
	 * 
	 * <p>Title:CarPartStockInfo</p>
	 * @author yjh
	 * @version	v1.0
	 * <p>Date:2016年9月22日上午9:56:34</p>
	 * <p>Description:入库出库类型</p>
	 */
	public static class CarPartStockInfo{
	    /**
	     * 中央仓库入库
	     */
	    public static final int STOCKTYPE_IN = 1;
	    
	    /**
	     * 中央仓库出库
	     */
	    public static final int STOCKTYPE_OUT = 2;
	    
	    /**
	     * 服务站入库
	     */
	    public static final int STOCKTYPE_IN_SERVICE = 3;
	}
	
	/**
	 * 用户账单类型
	 * @author yjh
	 *
	 */
	public static class UserBill{
		
		/**
		 * 充值
		 */
		public static final int BILL_RECHARGE = 1;
		
		/**
		 * 付款
		 */
		public static final int BILL_PAY = 2;
		
		/**
		 *提现中
		 */
		public static final int BILL_WITHDRAW = 3;
		
		/**
		 *提现成功
		 */
		public static final int BILL_WITHDRAW_OK = 4;
	}
}
