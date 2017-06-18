package com.sunit.global.util;


//public class DateUtil {
//	 
//	  
//	ThreadLocal<SimpleDateFormat> dateFormats = new ThreadLocal();
//	
//	 private  final static String  MONTH_FORMAT = new String ("yyyy-MM");
//		
//		private final static String  SIMPLE_FORMAT = new String ("yyyy-MM-dd");
//
//		private final static String  LONG_FORMAT = new String ("yyyy-MM-dd HH:mm:ss");
//
//		private final static String  LONG_FORMAT_MINUTES = new String ("yyyy-MM-dd HH:mm");
//
//		private final static String  CN_FORMAT = new String ("yyyy年MM月dd日");
//
//		private final static String  L8_DATE_FORMAT = new String ("yyyyMMdd");
//	
//	private final static Log log = LogFactory.getLog(DateUtil.class);
//
//	public static SimpleDateFormat getDateFormat(String format) {
//	
//		
//		
//		
//		
//		
//		
////		if (null == dateFormat) {
////			synchronized (DateUtil.class) {
////				if (null == formatPool.get(format)) {
////					dateFormat = new SimpleDateFormat(format);
////					formatPool.put(format, dateFormat);
////				}
////			}
////		}
//
//		return dateFormat;
//	}
//
//	public static Date parseDate(String format, String date) {
//		SimpleDateFormat fmt = getDateFormat(format);
//		Date dt = null;
//		try {
//			dt = fmt.parse(date);
//		} catch (ParseException e) {
//			log.error(DateUtil.class, e);
//		}
//
//		return dt;
//	}
//
//	public static Date parseDate(SimpleDateFormat format, String date) {
//		Date dt = null;
//
//		try {
//			dt = format.parse(date);
//		} catch (Exception e) {
//			log.error(DateUtil.class, e);
//		}
//
//		return dt;
//	}
//
//	public static Date parseDate(String string) {
//		Date date = null;
//
//		if (SunitStringUtil.isBlankOrNull(string))
//			return null; 
//
//		try {
//			if (string.length() > 10)
//				date = LONG_FORMAT.parse(string);
//			else if (string.length() > 7)
//				date = SIMPLE_FORMAT.parse(string);
//			else 
//				date = MONTH_FORMAT.parse(string);
//		} catch (ParseException e) {
//			log.error(DateUtil.class, e);
//		}
//
//		return date;
//	}
//
//	public static Date parseDate(Object object) {
//		if (object instanceof Date)
//			return (Date) object;
//
//		if (object instanceof Calendar)
//			return ((Calendar) object).getTime();
//
//		return parseDate(object.toString());
//	}
//
//	public static Calendar parseCalendar(Object format, String string) {
//		Calendar cal = null;
//		Date date = null;
//
//		if (format instanceof SimpleDateFormat)
//			date = parseDate((SimpleDateFormat) format, string);
//		else
//			date = parseDate(format.toString(), string);
//
//		if (null != date) {
//			cal = Calendar.getInstance();
//			cal.setTime(date);
//		}
//
//		return cal;
//	}
// 
//	public static Calendar parseCalendar(String string) {
//		Calendar cal = null;
//		Date date = null;
//
//		date = parseDate(string);
//
//		if (null != date) {
//			cal = Calendar.getInstance();
//			cal.setTime(date);
//		}
//
//		return cal;
//	}
//
//	public static Calendar parseCalendar(Object object) {
//		Calendar cal = Calendar.getInstance();
//
//		if (object instanceof Date) {
//			cal.setTime((Date) object);
//			return cal;
//		}
//
//		if (object instanceof Calendar)
//			return (Calendar) object;
//
//		return parseCalendar(object.toString());
//	}
//	
//	public static String getCurrentTime(){
//		return LONG_FORMAT.format(Calendar.getInstance().getTime());
//	} 
//	
//	  
//	public static String getCurrentDate(){
//		return SIMPLE_FORMAT.format(Calendar.getInstance().getTime());
//	}  
//	
//	public static String getL8CurrentDate(){
//		return L8_DATE_FORMAT.format(Calendar.getInstance().getTime());
//	}  
//	
//	
//	
//	/**
//	 * 
//	* @Title: getTime 
//	* @Description: 对字符串日期进行分钟加减操作，typeCode 为0表示加法。
//	* @param @param date
//	* @param @param minute
//	* @param @param sft
//	* @param @return     
//	* @return String  
//	 * @throws ParseException 
//	* @throws 
//	* @author sjz 
//	* 2013-9-11 下午2:15:07
//	 */
//	public static String getOperationTime(String date,int minute, int typeCode,SimpleDateFormat sft ) throws ParseException{
//		String descTime = "";
//		Date srcDate = sft.parse( date );
//		Calendar cTime = Calendar.getInstance();
//		cTime.setTime( srcDate );
//		if(typeCode==0){
//			cTime.add( Calendar.MINUTE, minute);
//		}else{
//			cTime.add( Calendar.MINUTE, -minute);
//		}
//			
//		Date descDate = cTime.getTime();
//		descTime = sft.format( descDate );
//		
//		return descTime;
//	}
//	
//    /** 
//    * @Title: addDateOneDay 
//    * @Description: (根据时间计算月份加1，返回字符串结果) 
//    * @param @param date
//    * @param @return     
//    * @return String    
//    * @author：liangrujian
//    * @date : Mar 2, 2016 5:34:16 PM
//    */ 
//    public static  String addDateOneDay(String date,int day) {  
//        
//        DateFormat df = new SimpleDateFormat("yyyy-MM");  
//        Date d2 =null;
//        try {  
//            Date d1 = df.parse(date);  
//            Calendar  g = Calendar.getInstance();  
//            g.setTime(d1);  
//            g.add(Calendar.MONTH,day);             
//            d2= g.getTime();  
//        } catch (ParseException e) {              
//            e.printStackTrace();  
//        }  
//        return df.format(d2);
//    }
//    /** 
//    * @Title: subtractionDateMonth 
//    * @Description: (返回两个日期相减后的月份数) 
//    * @param @param startTime
//    * @param @param endTime
//    * @param @return     
//    * @return int    
//    * @author：liangrujian
//    * @date : Mar 2, 2016 6:43:49 PM
//    */ 
//    public static  int subtractionDateMonth(String startTime,String endTime) {  
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//    	int m=0;
// 		try {
// 			m=(sdf.parse(endTime).getYear()-sdf.parse(startTime).getYear())*12+(sdf.parse(endTime).getMonth()-sdf.parse(startTime).getMonth());
// 		} catch (ParseException e) {
// 			// TODO Auto-generated catch block
// 			e.printStackTrace();
// 		}
//    	return m;
//    }
//
//	public static SimpleDateFormat getMONTH_FORMAT() {
//		return MONTH_FORMAT;
//	}
//
//	public static SimpleDateFormat getSIMPLE_FORMAT() {
//		return SIMPLE_FORMAT;
//	}
//
//	public static SimpleDateFormat getLONG_FORMAT() {
//		return LONG_FORMAT;
//	}
//
//	public static SimpleDateFormat getLONG_FORMAT_MINUTES() {
//		return LONG_FORMAT_MINUTES;
//	}
//
//	public static SimpleDateFormat getCN_FORMAT() {
//		return CN_FORMAT;
//	}
//
//	public static SimpleDateFormat getL8_DATE_FORMAT() {
//		return L8_DATE_FORMAT;
//	}
//    
//    public static void main(String[] args) {
//    	
//    	
//	}
//    
//}
