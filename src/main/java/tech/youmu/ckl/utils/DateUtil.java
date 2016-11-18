/**
 * @Title: DateUtils.java
 * @Package tech.youmu.ckl.utils
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 下午5:00:21
 * @version V1.0
 */

package tech.youmu.ckl.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	public static String getDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return simpleDateFormat.format(date);

	}

	/**
	 * 
	 * getCurrentDate:判断当前是本月的第几天. <br/> 
	 * 适用条件 : 自动任务执行.<br/> 
	 * 
	 * @author caodejun[caodejun@imimz.com]
	 * @return 
	 * @since JDK 1.7
	 */
	public static int getCurrentDate() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 拿到当前月的最大天数
	 *  <p>Author:yjh;</p>
	 *  <p>Date:2016年8月26日下午3:13:50;</p>
	 *	<p>Description: TODO;</p>
	 *  @return
	 */
	public static String getMaxDayOfMonth(){
	    Calendar calendar = Calendar.getInstance(); 
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        //设置格式（只拿日期）
        DateFormat format = new SimpleDateFormat("dd");
        return format.format(calendar.getTime());
	}
	
	/**
	 * 
	 *  <p>Author:yjh;</p>
	 *  <p>Date:2016年9月8日下午4:21:42;</p>
	 *	<p>Description: 根据今日获得上个月的年和月;</p>
	 *  @return
	 */
	public static String getLastMonthOfToday(){
	    Calendar c = Calendar.getInstance();
	    //获取上一个月
        c.add(Calendar.MONTH, -1);
        String dateStr = new SimpleDateFormat("yyyy-MM").format(c.getTime());
        return dateStr;
	}
	
	/**
	 * 
	 *  <p>Author:yjh;</p>
	 *  <p>Date:2016年10月13日下午2:57:02;</p>
	 *	<p>Description: 根据今日获得上一年的年份;</p>
	 *  @return
	 */
	public static String getLastYearOfToday(){
        Calendar c = Calendar.getInstance();
        //获取上一年
        c.add(Calendar.YEAR, -1);
        String dateStr = new SimpleDateFormat("yyyy").format(c.getTime());
        return dateStr;
    }
	
	
	public static String getYearOfToday(){
        Calendar c = Calendar.getInstance();
        String dateStr = new SimpleDateFormat("yyyy").format(c.getTime());
        return dateStr;
    }

	public static List<String> getDatas(String startTime, String endTime) {
		List<String> list = new ArrayList<String>();
		try {
			Calendar startCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = df.parse(startTime);
			startCalendar.setTime(startDate);
			Date endDate = df.parse(endTime);
			endCalendar.setTime(endDate);
			while (true) {
				if (startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis()) {
					list.add(df.format(endCalendar.getTime()));
					endCalendar.add(Calendar.DAY_OF_MONTH, -1);
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String getDate(int count,int type) {
		Calendar calendar = Calendar.getInstance();
        calendar.add(type, count);    
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(calendar.getTime());
	}
	
	public static long getTime(String date,int count,int type) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(stringToDate(date));
        calendar.add(type, count);    
		return calendar.getTimeInMillis();
	}
	
	public static long getTimestamp(){
	    return System.currentTimeMillis()/1000;
	}
	
	
	public static String dateToString(Date date) {
	    if(date ==null){
	        return null;
	    }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);

    }
	
	public static Date stringToDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

    }
	
	
	public static String getDifferTime(long startTime, long endTime) {
		//按照传入的格式生成一个simpledateformate对象
		long nh = 1000*60*60;//一小时的毫秒数
		long nm = 1000*60;//一分钟的毫秒数
		long ns = 1000;//一秒钟的毫秒数long diff;try {
		//获得两个时间的毫秒时间差异
		long diff =endTime-startTime;
		long hour = diff/nh;//计算差多少小时
		long min = diff%nh/nm;//计算差多少分钟
		long sec = diff%nh%nm/ns;//计算差多少秒//输出结果
		StringBuffer buffer= new StringBuffer();
		buffer.append(hour).append(":").append(min).append(":").append(sec);
		return buffer.toString();
	}
	
	public static Long getDifferTime(String startTime,String endTime){
	    long start = stringToDate(startTime).getTime();
	    long end = stringToDate(endTime).getTime();
        return end-start;
	}


}
