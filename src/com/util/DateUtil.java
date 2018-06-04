package com.util;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 *	时间工具类，主要正对时间的操作，时间的比较，格式化等
 *
 * @创建人 			王云川
 * @创建日期 		2017-03-22
 * @修改人 			王云川
 * @修改日期 		2017-03-22
 * @版本号 			1.0.0
 * @版权所有 		XX科技
*/
public class DateUtil {
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDHHMISS = "yyyyMMddHHmmss";


	/**
	 * 两个日期相减  简洁版
	 * @param startTime
	 * @param endTime
	 * @param format
	 */
	public static String dateDiff(Date date1, Date date2) {
		// 按照传入的格式生成一个simpledateformate对象
		if(date1 == null || date2 == null) return null;
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		// 获得两个时间的毫秒时间差异
		diff = date1.getTime() - date2.getTime();
		long day = diff / nd;// 计算差多少天
		long hour = diff % nd / nh;// 计算差多少小时
		long min = diff % nd % nh / nm;// 计算差多少分钟
		long sec = diff % nd % nh % nm / ns;// 计算差多少秒
		// 输出结果
		String rel = "";
		if(day != 0) rel+=day+"天";
		if(hour != 0) rel+=hour+"小时"; if(day != 0) return rel;
		if(min != 0) rel+=min+"分钟"; if(hour != 0) return rel;
		if(sec != 0) rel+=sec+"秒";if(min != 0) return rel;
		return rel;
	}
	/**
	 * 计算昨天
	 *
	 */
	public static List<Date> calcYesterday() {
		Date begin=null;
		Date end=null;
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(GregorianCalendar.DATE, -1);
		begin = calendar.getTime();
		end= calendar.getTime();
		List<Date> list=new ArrayList<Date>();
		list.add(begin);
		list.add(end);
		return list;
//		System.out.println(" 昨天---------------------- ");
//		System.out.println(" begin: " + DateUtil.format(begin,"yyyy-MM-dd"));
//		System.out.println(" end: " + DateUtil.format(end,"yyyy-MM-dd"));
//		System.out.println(" ---------------------- ");
	}
	/**
	 * 计算本周
	 */
	public static List<Date> calcThisWeek() {
		Date begin=null;
		Date end=null;
		GregorianCalendar calendar=new  GregorianCalendar();
		int minus = calendar.get(GregorianCalendar.DAY_OF_WEEK)-2;
		calendar.add(GregorianCalendar.DATE, -minus);
		begin = calendar.getTime();
		end=new Date();
		List<Date> list=new ArrayList<Date>();
		list.add(begin);
		list.add(end);
		return list;
//		System.out.println(" 本周---------------------- ");
//		System.out.println(" begin: " + DateUtil.format(begin,"yyyy-MM-dd"));
//		System.out.println(" end: " + DateUtil.format(end,"yyyy-MM-dd"));
//		System.out.println(" ---------------------- ");
	}
	
	/**
	 * 根据日期获取日期周的开始和结束日期
	 */
	public static List<Date> calcThisWeek(Date date) {
		List<Date> list=new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int d = 0;
		if(cal.get(Calendar.DAY_OF_WEEK)==1){
			d = -6;
		}else{
			d = 2-cal.get(Calendar.DAY_OF_WEEK);
		}
		cal.add(Calendar.DAY_OF_WEEK, d);
		//所在周开始日期
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
		list.add(cal.getTime());
		cal.add(Calendar.DAY_OF_WEEK, 6);
		//所在周结束日期
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
		list.add(cal.getTime());
		return list;
	}
	
	
	/**
	 * 计算上周
	 */
	public static List<Date> calcLastWeek() {
		Date begin=null;
		Date end=null;
		GregorianCalendar calendar=new  GregorianCalendar();
		int minus = calendar.get(GregorianCalendar.DAY_OF_WEEK);
		calendar.add(GregorianCalendar.DATE, -minus);
		calendar.add(GregorianCalendar.DATE, 1);
		end = calendar.getTime();
		begin = new Date(calendar.getTimeInMillis()-1000*60*60*24*6);
		List<Date> list=new ArrayList<Date>();
		list.add(begin);
		list.add(end);
		return list;
//		System.out.println(" 上周---------------------- ");
//		System.out.println(" begin: " + DateUtil.format(begin,"yyyy-MM-dd"));
//		System.out.println(" end: " + DateUtil.format(end,"yyyy-MM-dd"));
//		System.out.println(" ---------------------- ");
	}
	/**计算本月
	 *
	 */
	public static List<Date> calcThisMonth() {
		Date begin=null;
		Date end=null;
		GregorianCalendar calendar=new  GregorianCalendar();
		int dayOfMonth = calendar.get(GregorianCalendar.DATE);
		calendar.add(GregorianCalendar.DATE, -dayOfMonth + 1);
		begin = calendar.getTime();
		end=new Date();
		List<Date> list=new ArrayList<Date>();
		list.add(begin);
		list.add(end);
		return list;
//		System.out.println(" 本月---------------------- ");
//		System.out.println(" begin: " + DateUtil.format(begin,"yyyy-MM-dd"));
//		System.out.println(" end: " + DateUtil.format(end,"yyyy-MM-dd"));
//		System.out.println(" ---------------------- ");
	}
	
	/**计算日期月的 当月开始日期和结束日期
	 *
	 */
	public static List<Date> calcThisMonth(Date d) {
		Date begin=null;
		Date end=null;
		GregorianCalendar calendar=new  GregorianCalendar();
		calendar.setTime(d);
		int dayOfMonth = calendar.get(GregorianCalendar.DATE);
		calendar.add(GregorianCalendar.DATE, -dayOfMonth + 1);
		begin = calendar.getTime();
//		end=new Date();
		calendar.add(Calendar.MONTH, 1);  
		calendar.set(Calendar.DAY_OF_MONTH, 0);  
		end=calendar.getTime();
		List<Date> list=new ArrayList<Date>();
		list.add(begin);
		list.add(end);
		
//		System.out.println(" 本月---------------------- ");
		System.out.println(" begin: " + DateUtil.format(begin,"yyyy-MM-dd"));
		System.out.println(" end: " + DateUtil.format(end,"yyyy-MM-dd"));
//		System.out.println(" ---------------------- ");
		return list;
	}
	/**
	 * 计算上月
	 */
	public static List<Date> calcLastMonth() {
		Date begin=null;
		Date end=null;
		GregorianCalendar calendar=new  GregorianCalendar();
		calendar.set(calendar.get(GregorianCalendar.YEAR), calendar.get(GregorianCalendar.MONTH), 1);
		calendar.add(GregorianCalendar.DATE, -1);
		end = calendar.getTime();
		calendar.add(GregorianCalendar.MONTH, -1);
		begin = new Date(calendar.getTimeInMillis()+1000*60*60*24);
		List<Date> list=new ArrayList<Date>();
		list.add(begin);
		list.add(end);
		return list;
//		System.out.println(" 上月---------------------- ");
//		System.out.println(" begin: " + DateUtil.format(begin,"yyyy-MM-dd"));
//		System.out.println(" end: " + DateUtil.format(end,"yyyy-MM-dd"));
//		System.out.println(" ---------------------- ");
	}

	/**
	 * 格式化
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return format(date, YYYYMMDD);
	}

	/**
	 * 解析
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parseDate(String dateStr) {
		return parse(dateStr, YYYYMMDD);
	}

	public static String formatDateTime(Date date) {
		return format(date, YYYYMMDDHHMISS);
	}

	public static Date parseDateTime(String dateStr) {
		return parse(dateStr, YYYYMMDDHHMISS);
	}

	public static String format(Date date, String format) {
		if (date == null)
			return null;
		if (format == null)
			format = YYYYMMDD;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static Date parse(String dateStr, String format) {
		Date o = null;
		if ("".equals(dateStr))
			return o;
		if (format == null)
			format = YYYYMMDD;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			o = dateFormat.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * 获取当前日期调整后日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getAjustDate(int date) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, c.get(Calendar.DATE) + date);
		return c.getTime();
	}

	/**
	 * 获取当前日期调整后日期
	 *
	 * @param date
	 * @return
	 */
	public static Date getAjustMin(Date date,int m) {
		if (date == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + m);
		return c.getTime();
	}

	/**
	 * 获取特定日期调整后日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getAjustDate(Date date, int day) {
		if (date == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.get(Calendar.DATE) + day);
		return c.getTime();
	}

	public static java.sql.Date getSqlDate(Date date) {
		if (date == null)
			return null;
		return new java.sql.Date(date.getTime());
	}

	public static java.sql.Timestamp getSqlDatetime(Date date) {
		if (date == null)
			return null;
		return new java.sql.Timestamp(date.getTime());
	}

	/**
	 * 获取当天的开始时间
	 * 
	 * @return
	 */
	public static Date getStartTime() {
		return getStartTime(new Date());
	}

	/** 获取指定日期的开始时间 */
	public static Date getStartTime(Date date) {
		Calendar todayStart = Calendar.getInstance();
		todayStart.setTime(date);
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return new Date(todayStart.getTime().getTime());
	}

	/**
	 * 获取当天的结束时间
	 * 
	 * @return
	 */
	public static Date getEndTime() {
		return getEndTime(new Date());
	}

	/** 获取指定日期的结束时间 */
	public static Date getEndTime(Date date) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return new Date(todayEnd.getTime().getTime());
	}

	/**
	 * 
	 * 获取日期间的所有天
	 * 
	 */
	public static List<Date> getDates(Date start, Date end, List<Date> dateList) {
		long day = 60 * 60 * 24 * 1000;
		if ((end.getTime() - start.getTime()) < day) {
			dateList.add(start);
			return dateList;
		} else {
			dateList.add(start);
			DateUtil.getDates(new Date(start.getTime() + day), end, dateList);
		}
		return dateList;
	}

	/**
	 * 
	 * 获取日期间的所有月份
	 * 
	 */
	public static List<String> getMonth(String start, String end) {
		String splitSign = "-";
		String regex = "\\d{4}" + splitSign + "(([0][1-9])|([1][012]))"; // 判断YYYY-MM时间格式的正则表达式
		if (!start.matches(regex) || !end.matches(regex))
			return new ArrayList<String>();

		List<String> list = new ArrayList<String>();
		if (start.compareTo(end) > 0) {
			// start大于end日期时，互换
			String temp = start;
			start = end;
			end = temp;
		}

		String temp = start; // 从最小月份开始
		while (temp.compareTo(start) >= 0 && temp.compareTo(end) < 0) {
			list.add(temp); // 首先加上最小月份,接着计算下一个月份
			String[] arr = temp.split(splitSign);
			int year = Integer.valueOf(arr[0]);
			int month = Integer.valueOf(arr[1]) + 1;
			if (month > 12) {
				month = 1;
				year++;
			}
			if (month < 10) {// 补0操作
				temp = year + splitSign + "0" + month;
			} else {
				temp = year + splitSign + month;
			}
		}
		list.add(end);
		return list;
	}

	public static List<String> getYear(String startStr, String endStr) {
		List<String> list = new ArrayList<String>();
		int start = Integer.parseInt(startStr);
		int end = Integer.parseInt(endStr);
		for (int i = (start); i <= end; i++) {
			list.add(i + "");
		}
		return list;
	}

	/**
	 * 根据日期获取本月所有日期
	 */
	public static List<Date> getAllTheDateOftheMonth(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		int month = cal.get(Calendar.MONTH);
		while (cal.get(Calendar.MONTH) == month) {
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}

	/**
	 * 判断日期是否相等
	 * 
	 * @return
	 */
	public static boolean cheackDate(Date param1, Date param2, String format) {
		return (format(param1, format)).equals(format(param2, format));
	}

	// 判断第一个日期是否小于第二个日期
	public static boolean compareDate(Date param1, Date param2, String format) {
		if (DateUtil.parse(DateUtil.format(param1, format), format).getTime() <= DateUtil.parse(DateUtil.format(param2, format), format).getTime())
			return true;
		else
			return false;
	}
	
	/**
	 * 获取当前日期的最近的几个月
	 * @param num	具体要获取几个月
	 * @return
	 */
	public static List<Map<String,String>> getMonthByIndex(int num){
		 List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		 
		 Calendar calendar = Calendar.getInstance();
		 Map<String,String> m=null;
		 calendar.add(Calendar.MONTH, -num);
		 for(int i=num;i>=1;i--){
             calendar.add(Calendar.MONTH, 1);
             m=new HashMap<String, String>();
             m.put("key", DateUtil.format(calendar.getTime(),"yyyy-MM"));
    		 m.put("name", DateUtil.format(calendar.getTime(),"yy-MM"));
    		 list.add(m);
		 }
//		 m=new HashMap<String, String>();
//		 m.put("key", DateUtil.format(calendar.getTime(),"yyyy-MM"));
//		 m.put("name", DateUtil.format(calendar.getTime(),"yy-MM"));
//		 list.add(m);
		 return list;
	}
	
	/**
	 * 获取当前日期的最近的几年
	 * @param num	具体要获取几年
	 * @return
	 */
	public static List<Map<String,String>> getYearByIndex(int num){
		 List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		 
		 Calendar calendar = Calendar.getInstance();
		 Map<String,String> m=null;
         calendar.add(Calendar.YEAR, -num);

		 for(int i=num;i>=1;i--){
             calendar.add(Calendar.YEAR,1);
             m=new HashMap<String, String>();
             m.put("key", DateUtil.format(calendar.getTime(),"yyyy"));
    		 m.put("name", DateUtil.format(calendar.getTime(),"yyyy年"));
    		 list.add(m);
		 }
		 
//		 m=new HashMap<String, String>();
//		 m.put("key", DateUtil.format(calendar.getTime(),"yyyy"));
//		 m.put("name", DateUtil.format(calendar.getTime(),"yyyy年"));
//		 list.add(m);
		 return list;
	}
	
	
	public static void main(String[] args){
//		List<Map<String,String>> list=DateUtil.getYearByIndex(4);
//		for(Map<String,String> m:list){
//			System.out.println(m.get("key"));
//		}
//		List<Date> list=calcThisWeek(DateUtil.parse("2017-03-01", "yyyy-MM-dd"));
//		for(Date d:list){
//			System.out.println("日期==============>>>："+DateUtil.format(d,"yyyy-MM-dd"));
//
//		}
//		calcThisMonth(DateUtil.parse("2017-03-01", "yyyy-MM-dd"));
		
		System.out.println("==>>>>>"+("".split(",").length));
		
		
		double a = 12.34566776;  
		BigDecimal deSource = new BigDecimal(a);  
		double smtScale = deSource.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue(); 
		System.out.println("============"+smtScale);
		
	}

}
