/**
 * Project Name:ckl
 * File Name:SalaryComponent.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.component;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tech.youmu.ckl.constants.Global;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.domain.EmployeeAttendanceInfo;
import tech.youmu.ckl.domain.HistoryStationCountOrderInfo;
import tech.youmu.ckl.domain.OrderInfoHistory;
import tech.youmu.ckl.domain.Salary;
import tech.youmu.ckl.domain.SalaryBase;
import tech.youmu.ckl.domain.SalaryInflationRate;
import tech.youmu.ckl.domain.SalaryPointStandard;
import tech.youmu.ckl.domain.StarsRule;
import tech.youmu.ckl.domain.Station;
import tech.youmu.ckl.mapper.EmployeeAttendanceInfoMapper;
import tech.youmu.ckl.mapper.EmployeeMapper;
import tech.youmu.ckl.mapper.OrderMapper;
import tech.youmu.ckl.mapper.SalaryBaseMapper;
import tech.youmu.ckl.mapper.SalaryInflationRateMapper;
import tech.youmu.ckl.mapper.SalaryMapper;
import tech.youmu.ckl.mapper.SalaryPointStandardMapper;
import tech.youmu.ckl.mapper.StarsRuleMapper;
import tech.youmu.ckl.query.EmployeeAttendanceInfoQuery;
import tech.youmu.ckl.query.HistoryStationCountOrderInfoQuery;
import tech.youmu.ckl.query.OrderInfoHistoryQuery;
import tech.youmu.ckl.query.SalaryPointStandardQuery;
import tech.youmu.ckl.utils.DateUtil;

/**
 * <p>Title:SalaryComponent</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月11日上午11:03:44</p>
 * <p>Description:工资结算</p>
 */
@Component
public class SalaryComponent {
    
    private static Logger log=LoggerFactory.getLogger(SalaryComponent.class);
    
    public static int[] Order_Num_Conditions;
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private SalaryMapper salaryMapper;
    
    @Autowired
    private EmployeeMapper employeeMapper;
    
    @Autowired
    private SalaryBaseMapper salaryBaseMapper;
    
    @Autowired
    private EmployeeAttendanceInfoMapper employeeAttendanceInfoMapper;
    
    @Autowired
    private SalaryPointStandardMapper salaryPointStandardMapper;
    
    @Autowired
    private SalaryInflationRateMapper salaryInflationRateMapper;
    
    @Autowired
    private StarsRuleMapper starsRuleMapper;
    
    /**
     * 生成上月工资结算相关信息
     */
    public void initInfo(){
        /*
         * 初始工作：1、保存员工上个月完成的服务订单明细
         *        2、获取各个服务站上月完成汽车维护，汽车维修，道路救援 相关订单数量的情况
         *        3、生成上月员工考勤表
         */
        orderMapper.saveOrderInfoHis();
        orderMapper.saveStationOrderInfoHis();
        employeeAttendanceInfoMapper.saveAttendanceRecord();
        
        
        //-----------------准备日工资的结算标准条件的数组-----------------------
        List<SalaryBase> salaryBases = salaryBaseMapper.getAll();
        Integer salaryBaseSize = salaryBases.size();
        //准备一个存放完成单数判断条件的数组
        int[] orderNumConditions = new int[salaryBaseSize];
        
        //循环赋值,i即是salaryBases的第几条数据   limit i。 
        //      i代表对应在数据库中是第几条数据
        for (int i = 0;i<salaryBaseSize;i++) {
            SalaryBase salaryBase = salaryBases.get(i);
            orderNumConditions[i] = salaryBase.getOrderNum();
        }
        //对里面的内容进行从小到大的排序
        Arrays.sort(orderNumConditions);
        
        Order_Num_Conditions = orderNumConditions;
    }
    
    
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年10月11日上午11:06:21;</p>
     *  <p>Description:  
     *          每个月的10号凌晨3点钟，系统需完成以下事项：
     *          一、保存上一个月的员工完成服务情况（OrderInfoHistory）;
     *          二、计算员工工资，并生成工资表：
     *              1、基础工资（baseSalary）：统计每个服务站完成的汽车维护、维修、道路救援这三类订单的数量 --> 根据这个可以获得每个服务站员工的基本工资 再根据基本工资 * 天数 获取每个员工的月基本工资
     *              2、薪点工资(pointSalary)：根据员工的岗位类别，薪点级别，查薪点工资标准表中对应的薪点工资总和，再根据今年的年份-1 ，查询出薪点表中对应的薪点值，得出薪点工资：公式为（薪点工资总和  * 薪点值）
     *              3、绩效工资(performanceSalary)：
     *                  判断该员工是否是管理岗位：
     *                  1、若是管理岗位，则需要计算服务星数的权重：每个服务站的每个员工的（刻数 * 评星数）/ （员工的刻数总和）--> 得到该管理人员本月的服务评分
     *                  
     *                  2、若不是管理岗位，则只需要 
     *                  查询history_count_orderinfo中该员工完成的订单的总金额（总刻数），
     *                  再查询该员工所在岗位对应的月绩效奖金系数(job_title中的ratio)
     *                  再查询该员工获得服务评价平均星数 avg_star 对应的奖励比例(star_rule)
     *                  公式为：刻数 * 系数 * 服务评价星数对应的奖励比例
     *              4、保存工资数据
     *          
     *      </p>
     *
     */
    @Scheduled(cron="0 0 3 10 * ?")
    public void setEmpOrderInfoAndSalary(){
        
      //上月数据初始化
      initInfo();
      
      //1、通过考勤表，获取每个员工当月上班天数
      EmployeeAttendanceInfoQuery employeeAttendanceInfoQuery = new EmployeeAttendanceInfoQuery();
      employeeAttendanceInfoQuery.setSearchTimeStr(DateUtil.getLastMonthOfToday());
      List<EmployeeAttendanceInfo> employeeAttendanceInfos = employeeAttendanceInfoMapper.getByQuery(employeeAttendanceInfoQuery);
     
      
      //2、分别统计出每个服务站上月的日工资结算费用
      HistoryStationCountOrderInfoQuery historyQuery = new HistoryStationCountOrderInfoQuery();
      List<HistoryStationCountOrderInfo> stationOrderInfoHis = orderMapper.getStationOrderInfoHis(historyQuery);
      
          
          //遍历每一个有考勤记录的员工，生成对应的工资数据的基础工资部分
          for (EmployeeAttendanceInfo employeeAttendanceInfo : employeeAttendanceInfos) {
              
              /**
               * 获取每一个有考勤记录的员工
               */
              Long employeeId= employeeAttendanceInfo.getEmployee().getId();
              Employee employee = employeeMapper.getWithStationsById(employeeId);
              
              /**
               * 该员工的服务站的id
               */
              Long stationIdByEmployee = null;
              if(employee.getStation()!=null){
                  stationIdByEmployee = employee.getStation().getId();
              }
              
              /**
               * 该员工所管理的服务站
               */
              List<Station> stations = employee.getStations();
              
              /**
               * 上班天数
               */
              BigDecimal days = new BigDecimal(employeeAttendanceInfo.getDays());
              
              /**
               * ***************准备该员工对应的工资数据************
               */
              Salary salary = new Salary();
              salary.setEmployee(employee);
              
              //若考勤天数小于16天，则无基本工资，若大于25天，都按照25天结算
              if(employeeAttendanceInfo.getDays()<16){
                  days = Global.BIG_DECIMAL_ZORE;
              }else if (employeeAttendanceInfo.getDays()>25) {
                  days = new BigDecimal(25);
              }
              
              /**
               * 服务站对应的日工资标准，初始值为55元/天
               */
              BigDecimal baseSalaryStandard = new BigDecimal(55);
              
              /**
               * 基本工资
               */
              BigDecimal baseSalary = Global.BIG_DECIMAL_ZORE;
              
              /*
               * **************************一、计算基本工资**************************
               */
              for (HistoryStationCountOrderInfo historyStationCountOrderInfo : stationOrderInfoHis) {
                  /*
                   * 该服务站的日工资:
                   *    1、首先，获取判断日工资的结算标准
                   *    2、与该服务站的服务完成单数进行对比
                   *    3、获得该服务站对应的日工资 baseSalary
                   */
                  
                  //当前服务站订单数
                  Integer orderNum = historyStationCountOrderInfo.getOrderNum();
                  //当前服务站id
                  Long stationId = historyStationCountOrderInfo.getStationId();
                  
                  
                    //基础工资级别
                    int baseSalaryLevel = 0;
                    //服务刻数与条件里面的值做比较，若满足一项条件，则加1
                    for (int x = 0; x<Order_Num_Conditions.length ;x++) {
                        //若该服务站订单总量大于当前条件，则等级加1
                        if(orderNum >= Order_Num_Conditions[x]){
                            baseSalaryLevel ++;
                        }
                    }
                    
                  //若达到工资级别条件
                  if(baseSalaryLevel-1 >= 0){
                      //获取对应的条件
                      int orderNumCondition = Order_Num_Conditions[baseSalaryLevel-1];
                      //根据 条件获取 对应的日工资标准
                      SalaryBase salaryBase = salaryBaseMapper.getSalaryBaseByOrderNum(orderNumCondition);
                      
                      /**
                       * 服务站对应的日工资标准
                       */
                      baseSalaryStandard = salaryBase.getSalary();
                  }
                  
                  //普通员工
                  if(stationIdByEmployee != null && stationIdByEmployee == stationId && stations.size()==0){
                      //普通员工的日工资 : 日工资标准 * 天数
                      baseSalary = baseSalary.add(baseSalaryStandard.multiply(days));
                      System.err.println("该普通员工id: " +employeeId+" 的基本工资为：" + baseSalary);
                      salary.setBaseSalary(baseSalary);
                      break;
                  }
                  
                  //管理人员
                  else if(stationIdByEmployee == null  && stations.size()>0){
                      
                      
                      //遍历该员工所管理的服务站，判断是否管理当前服务站
                      for(int m=0;m<stations.size();m++){
                              //如果当前不为最后一个服务站
                              Station station = stations.get(m);
//                            System.err.println("****** 该管理员(id为："+employeeId+")，他管理的服务站id为： " + station.getId());
                              //服务站数组里的id 等于 当前服务站id
                              //    则统计该服务站的所有人员的 刻数*星数 / 该服务站总刻数
                              if(station.getId()==stationId){
                                  /**
                                   * 基本工资 累加
                                   */
                                  baseSalary = baseSalary.add(baseSalaryStandard.multiply(days));
                              }
                              
//                            /*
//                             * 判断该管理人员的服务站是否循环到了最后一个，若为最后一个，则关闭标识位;
//                             *  判断是否为最后一个服务站，有两种情况：1、服务站数组里面有多个；2、服务站数组里面仅有一个
//                             */
//                            if( (m>0 && m == stations.size()-1) || (stations.size()==1)){
//                                System.err.println("stationFlag :" + stationFlag);
//                            }
                      }
                      salary.setBaseSalary(baseSalary);
                  }
                  
              }
              
              /**
               * ***********************设置薪点工资********************
               */
              BigDecimal ponitSalary = getPonitSalaryByEmployee(employee);
              salary.setPointSalary(ponitSalary);
              
              /**
               * ***********************设置绩效工资********************      
               */
              setPerformanceSalaryByEmployee(employee,salary);
              
              //------------------------------保存工资数据-------------------------
              salary.setRecordMonth(DateUtil.getLastMonthOfToday());    //设置日期为上个月
              //保存员工工资
              salaryMapper.save(salary);
          }
          
    }
    
    
    /**
     * *************************计算绩效工资 performanceSalary **********************
     * 根据服务评定规则，获得员工获取的绩效工资比例，再计算应该获得的绩效工资
     */
    public BigDecimal getPerformanceSalaryByAvgStars(Double avgStar,BigDecimal totalAmount,BigDecimal performanceRatio){
        
        /**
         * 准备绩效工资performanceSalary
         */
        BigDecimal performanceSalary = Global.BIG_DECIMAL_ZORE;
        
        /**
         * 获取服务评价评定规则
         */
        List<StarsRule> starsRules = starsRuleMapper.getAll();
        int starsSize = starsRules.size();
        Double[] starsConditions = new Double[starsSize];
        for (int k = 0;k<starsSize;k++) {
            StarsRule starsRule = starsRules.get(k);
            starsConditions[k] = starsRule.getStar();
        }
        Arrays.sort(starsConditions);
        
        //服务获得星数级别
        int starLevel = 0;
        //星数级别与条件里面的值做比较，若满足一项条件，则加1
        for (int y = 0; y<starsConditions.length ;y++) {
            if(avgStar >= starsConditions[y]){
                starLevel ++;
            }
        }
        
        //获取对应的条件
        if(starLevel-1 >= 0){
            Double starCondition = starsConditions[starLevel-1];
            //根据 条件获取 对应的奖励规则
            StarsRule starsRule = starsRuleMapper.getRatioByStars(starCondition);
            
            /**
             * 根据评星数对应获取的绩效工资比例
             */
            BigDecimal starRatio = starsRule.getStarRatio()!=null?starsRule.getStarRatio():Global.BIG_DECIMAL_ZORE;
            
            /**
             * 计算绩效工资:该员工当月服务总刻数    * 绩效奖金比例  * 绩效工资比例（服务评价）
             */
            performanceSalary = totalAmount.multiply(performanceRatio).multiply(starRatio);
        }
        return performanceSalary;
    }
    
    
    /**
     ***************************计算薪点工资 pointSalary **********************
     *    1、查询薪点工资标准表，首先根据该员工的岗位类别获取对应的一个薪点工资数组(pointSalarys)
     *    2、再根据该员工的薪点级别，取前面的值累加
     */
    public BigDecimal getPonitSalaryByEmployee(Employee employee){
        /**
         * 准备薪点工资 pointSalary，初始为0
         */
        BigDecimal pointSalary = Global.BIG_DECIMAL_ZORE;
        
        //获取员工的岗位类别
        Long jobId = employee.getJobTitle().getId();
        if(jobId != null){
            //根据该员工的岗位类别获取对应的一个薪点工资集合(salaryPointStandards)
            SalaryPointStandardQuery query = new SalaryPointStandardQuery();
            query.setJobId(jobId);
            List<SalaryPointStandard> salaryPointStandards = salaryPointStandardMapper.getByQuery(query);
            //员工的薪点级别
            Integer salaryLevel = employee.getSalaryLevel();
            //该员工的薪点级别不为空且不为0，才有薪点工资
            if(salaryLevel!=null && salaryLevel!=0 && salaryPointStandards.size()>0){
                //根据员工薪点级别，将前面的值进行累加
                for (int j=0;j<salaryLevel;j++) {
                    SalaryPointStandard salaryPointStandard = salaryPointStandards.get(j);
                    pointSalary = pointSalary.add(salaryPointStandard.getSalary());
                }
            }
        }
        /*
         * 根据年份获取薪点值(salaryInflationRateForCount)：本年所取的薪点值，应该取当前年份的上一年
         *    1、获取当前年份的上一年
         *    2、根据获得的年份，查找薪点值表，获取该年的薪点值
         */
        /**
         * 薪点值:默认查找不到的情况，设置为1
         */
        BigDecimal salaryInflationRateForCount = new BigDecimal(1);
        //根据年份获取薪点值对象
        SalaryInflationRate salaryInflationRate = salaryInflationRateMapper.getByYear(Integer.parseInt(DateUtil.getLastYearOfToday()));
        if(salaryInflationRate != null){
            //获取对应的薪点值
            salaryInflationRateForCount = salaryInflationRate.getRatio();
        }
        //薪点工资 = 薪点累加工资 * 薪点值
        pointSalary = pointSalary.multiply(salaryInflationRateForCount);
        
        return pointSalary;
    }
    
    
    /**
     * 
     * <p>Author:yjh;</p>
     * <p>Date:2016年10月14日下午3:06:29;</p>
     * <p>Description:
     ***************************三、计算绩效工资 performanceSalary **********************
     *    1、查询该员工本月服务的总刻数
     *    2、查询该员工的绩效奖金系数
     *    3、查询员工的平均获得评分星数，放入条件数组中
     *        3.1 若为普通员工： 则需要将该员工的获得评分数与条件数组进行对比，确定获得的绩效工资比例即可
     *        3.2 若为管理人员：则需要先查询管理的服务站，若当前服务站为该管理人员管辖，
     *                     则需要两个数据:
     *                                  a、当前这个站下的非管理员工的（总刻数*平均星数），循环累加。
     *                                  b、服务站的总刻数
     * </p>         
     *  @param employee 考勤员工  
     *  @param salary 需要生成的工资对象                           
     */
    public void setPerformanceSalaryByEmployee(Employee employee,Salary salary){
        BigDecimal performanceSalary = Global.BIG_DECIMAL_ZORE;
        
        Long stationIdByEmployee = null;
        if(employee.getStation()!=null){
            stationIdByEmployee = employee.getStation().getId();
        }
        List<Station> stations = employee.getStations();
        
        //设置查询的时间为上个月
        OrderInfoHistoryQuery orderInfoHistoryQuery = new OrderInfoHistoryQuery();
        orderInfoHistoryQuery.setRecordMonth(DateUtil.getLastMonthOfToday());
        orderInfoHistoryQuery.setEmpId(employee.getId());
        //根据员工id,获取上个月的员工完成服务记录，取第1条
        List<OrderInfoHistory> orderInfoHisList = orderMapper.getOrderInfoHis(orderInfoHistoryQuery);
        
        /**
         * 绩效奖金系数
         */
        BigDecimal performanceRatio = employee.getJobTitle().getRatio()!=null?employee.getJobTitle().getRatio().multiply(Global.BIG_DECIMAL_PERCENT) : Global.BIG_DECIMAL_ZORE;
        
              //1、若有订单记录，且该员工有所在服务站，则该员工为普通员工
              if(orderInfoHisList != null && stationIdByEmployee != null && orderInfoHisList.size()>0  && stations.size() == 0){
            
                OrderInfoHistory orderInfoHistory = orderInfoHisList.get(0);
            
                /**
                 * ******************************生成工资数据*******************************
                 * 该员工 上月 的获得的平均星数:
                 *    分两种情况：1、管理该服务站的人员
                 *            2、普通员工
                 *            3、对于既没有所在服务站，又不是管理服务站的员工，基本工资设置为0
                 */
            
                /**
                 * 总服务刻数
                 */
                BigDecimal totalAmount = orderInfoHistory.getTotalAmount()!=null?orderInfoHistory.getTotalAmount() : Global.BIG_DECIMAL_ZORE;
                
                /**
                 * 普通员工的平均星数 : 若该员工没有任何评价，则默认给3星
                 */
                Double avgStar = orderInfoHistory.getAvgStar();
                if(avgStar == null){
                    avgStar = 3.0D;
                }
                
                performanceSalary = getPerformanceSalaryByAvgStars(avgStar, totalAmount, performanceRatio);
                
                /**
                 * *************************普通员工的绩效工资*************************
                 */
                salary.setPerformanceSalary(performanceSalary);
                
                
              //------------------------------处理普通员工信息--------------------------
                
                //设置员工完成总订单数
//              Integer numInCount = orderInfoHistory.getNum()==null ? 0 : orderInfoHistory.getNum();
//              employee.setNumInCount( (employee.getNumInCount()==null?0:employee.getNumInCount()) + numInCount );
                
                //设置员工的服务总刻数
                
                BigDecimal keInCount = employee.getKeInCount()==null?Global.BIG_DECIMAL_ZORE:employee.getKeInCount();
//                System.err.println("该员工id:"+ employee.getId() + "  该员工之前的总刻数为： " + keInCount);
//                System.err.println("该员工id:"+ employee.getId() + "  的总刻数变为： " + keInCount.add(totalAmount));
                employee.setKeInCount((employee.getKeInCount()==null?Global.BIG_DECIMAL_ZORE.add(totalAmount):employee.getKeInCount().add(totalAmount)));
                
//                System.err.println("--处理普通员工,id为："+employee.getId()+"的信息----    ,totalAmount: " + totalAmount);
                employeeMapper.updateById(employee);
            
            }
            //2、无订单记录，该员工无所在服务站，但有管理服务站，则为管理人员
            else if(employee.getStation() == null && stations.size()>0){
                
                //遍历该员工所管理的服务站
                for(Station station:stations){
                        //根据当前服务站id，获取该站内所有员工的订单服务信息
                        OrderInfoHistoryQuery queryByStationId = new OrderInfoHistoryQuery();
                        queryByStationId.setStationId(station.getId());
                        List<OrderInfoHistory> orderInfoHisByStationIdList = orderMapper.getOrderInfoHisByStationId(queryByStationId);
                        
                        /**
                         * 计算服务站内 每个员工 总刻数 * 平均星数
                         */
                        BigDecimal resultByEmployee = Global.BIG_DECIMAL_ZORE;
                        
                        /**
                         * 服务站总刻数(即是该管理岗位的总刻数)
                         */
                        BigDecimal totalAmountResultByStation = Global.BIG_DECIMAL_ZORE;
                        
                        for (OrderInfoHistory orderInfoHisByStationId : orderInfoHisByStationIdList) {
                            //获取每个员工的服务总刻数
                            BigDecimal totalAmountByEmployee = orderInfoHisByStationId.getTotalAmount()!=null?orderInfoHisByStationId.getTotalAmount():Global.BIG_DECIMAL_ZORE;
                            //获取每个员工的服务评价星数
                            BigDecimal avgStarByEmployee = new BigDecimal(orderInfoHisByStationId.getAvgStar()!=null?orderInfoHisByStationId.getAvgStar():Global.DEFAULT_EVALUATION_THREE_STAR);
                            //乘积累加
                            resultByEmployee = resultByEmployee.add(totalAmountByEmployee.multiply(avgStarByEmployee));
                            //服务站收入总数累加
                            totalAmountResultByStation = totalAmountResultByStation.add(totalAmountByEmployee);
                        }
                        
                        //若当前服务站总收入为0，则该管理人员无绩效工资
                        if(totalAmountResultByStation.intValue() == 0){
//                          System.err.println("服务站收入为0，该管理人员无绩效工资。。。。");
                            performanceSalary = performanceSalary.add(Global.BIG_DECIMAL_ZORE);
                        }
                        else{
                            /**
                             * 获取该管理人员的平均星数
                             */
                            BigDecimal avgStarByManagerResult = resultByEmployee.divide(totalAmountResultByStation,2, BigDecimal.ROUND_UP);
                            Double avgStarByManager = avgStarByManagerResult.doubleValue();
                            
                            /**
                             * 管理人员的绩效工资（累加）
                             */
                            performanceSalary = performanceSalary.add(getPerformanceSalaryByAvgStars(avgStarByManager,totalAmountResultByStation,performanceRatio));
                            
                        }
                    }
                
                    System.err.println("****管理人员的绩效工资***** ：" + performanceSalary);
                    
                    /**
                     * *************设置管理员人的绩效工资************
                     */
                    salary.setPerformanceSalary(performanceSalary);
                      
                }
            //3、无订单记录，也无服务站相关信息，则该员工绩效为0
            else{
                salary.setPerformanceSalary(Global.BIG_DECIMAL_ZORE);
            }
    }
    
    
}
