package com.sunit.global.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.log4j.Logger;

import com.sunit.global.util.pinyinhelper.Pinyin;
public class ChineseConvertPinyinUtils {

	static Logger logger = Logger.getLogger(ChineseConvertPinyinUtils.class);
	 /**  
	* @Title: toPinyin 
	* @Description: (将字符串转为首字母缩写,中文转拼音全拼) 
	* @param @param s 要转换的字符串
	* @param @return
	* @param @throws BadHanyuPinyinOutputFormatCombination     
	* @return String  返回转换后的字符串
	* @author：liangrujian
	* @date : Nov 28, 2015 3:32:37 PM
	*/ 
	public static String toPinyin(String s)  {
		 String qpStr ="";
		 String sxStr ="";
		 if(!SunitStringUtil.isBlankOrNull(s)){
	        char[] allChars = s.toCharArray(); 
	        final int allCharsLength = allChars.length;
	        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
	        format.setVCharType(HanyuPinyinVCharType.WITH_V);
	        
	        int chineseCount = 0;
	        for (int i = 0; i < allCharsLength; i++) {
	            char targetChar = allChars[i];
	            String[] pinyins=null;
				try {
					pinyins = PinyinHelper.toHanyuPinyinStringArray(targetChar, format);
				     if (pinyins != null && pinyins.length > 0) {
			                chineseCount++;
			                qpStr +=Pinyin.toPinyin(targetChar);//全拼
			                //截取首字母
			                sxStr +=Pinyin.toPinyin(targetChar).substring(0,1);//截取首字母
			                
			            }else{
			            	qpStr +=targetChar;
			            	sxStr +=targetChar;
			            }
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	       
	        }
	        //CHECKSTYLE:OFF 
	        int expectedChineseCount = 20378;
		 }
		 return sxStr+","+qpStr;
	    }
}
