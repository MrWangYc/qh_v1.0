package com.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类 2013-10-21
 */
public class StringUtil {

	
	
	/**
	 * 	将字符串转换成整形
	 * @param val
	 * @return
	 */
	public static Integer parseInt(String val) {
		if(StringUtil.isInteger(val))
			return Integer.parseInt(val);
		else return 0;
	}
	/**
	 * 	将字符串转换成浮点型
	 * @param val
	 * @return
	 */
	public static Float parseFloat(String val) {
		if(StringUtil.isFloat(val))
			return Float.parseFloat(val);
		else return 0f;
	}
	/**
	 * 保留字符的几位小数
	 * 
	 * @param val
	 *            值
	 * @param length
	 *            保留位数
	 * @return
	 */
	public static String retainDecimals(String val, int length) {
		double x = 0;
		if (isFloat(val) || isInteger(val)) {
			x = Double.parseDouble(val);
		} else {
			return "0";
		}
		String format = "0.";
		for (int i = 0; i < length; i++) {
			format += "0";
		}
		DecimalFormat df = new DecimalFormat(format);
		return df.format(x);
	}

	/**
	 * 判断字符串为空，或者是空字符串
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNull(String val) {
		if (val == null || "".equals(val.trim()) || "null".equals(val.trim()))
			return true;
		else
			return false;
	}
	
	public static boolean isNull(Boolean val) {
		if (val == null)
			return true;
		else
			return false;
	}
	
	public static boolean isNull(Integer val) {
		if (val == null)
			return true;
		else
			return false;
	}
	
	public static boolean isNull(Float val) {
		if (val == null)
			return true;
		else
			return false;
	}
	public static boolean isNull(Double val) {
		if (val == null)
			return true;
		else
			return false;
	}
	
	public static boolean isNull(Date val) {
		if (val == null)
			return true;
		else
			return false;
	}

	// 保留2位小数
	public static double getDouble(double a, int w) {
		BigDecimal bg = new BigDecimal(a);
		double f1 = bg.setScale(w, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	//首字母转小写
	 public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    //首字母转大写
    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

	/**
	 * 判断一个字符是否是整形
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		
	    Pattern pattern1 = Pattern.compile("^((-?\\d+.?\\d*)[Ee]{1}(-?\\d+))$");
		if (str == null || "".equals(str.trim()))
			return false;
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches() && !pattern1.matcher(str).matches()) {
			return false;
		}
		return true;
	}

	//处理数组字符
	public static String[] arrContrast(String[] arr1, String[] arr2){
		List<String> list = new LinkedList<String>();
		for (String str : arr1) { //处理第一个数组,list里面的值为1,2,3,4
			if (!list.contains(str)) {
				list.add(str);
				}
			}
			for (String str : arr2) { //如果第二个数组存在和第一个数组相同的值，就删除
				if(list.contains(str)){
					list.remove(str);
				}
			}
			String[] result = {}; //创建空数组
		return list.toArray(result); //List to Array
	}
	
	/**
	 * 判断一个字符串是否是浮点型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isFloat(String str) {
		if(StringUtils.isEmpty(str)){
			return false;
		}
		if (isInteger(str)) {
			return true;
		}
		boolean result = false;
		result = str.matches("[\\d]+\\.[\\d]+");
		return result;
	}

	/**
	 * 验证字符串是否是手机号码
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhoneNum(String phone) {
		if (phone == null)
			return false;
		String pattern = "^(13[0-9]|15[01]|153|15[6-9]|180|18[23]|18[5-9])\\d{8}$";
		return phone.matches(pattern);
	}

	/**
	 * 将字符串中非整数的数字替换为空
	 * 
	 * @return
	 */
	public static String replaceNotInteger(String str) {
		if (str == null || "".equals(str.trim()))
			return null;
		return str.replaceAll("([^0-9]*)", "");
	}

	/**
	 * 判断两个字符串是否相等
	 * 
	 * @param str1
	 *            字符串1，如果为空，则赋值空字符串
	 * @param str2
	 *            字符串2，如果为空，则赋值空字符串
	 * @return
	 */
	public static boolean equal(String str1, String str2) {
		if (str1 == null)
			str1 = "";
		if (str2 == null)
			str2 = "";
		return str1.equals(str2);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
//		String regex="[^(a-zA-Z0-9\\u4e00-\\u9fa5\\u3002\\uff1b\\uff0c\\uff1a\\u201c\\u201d\\uff08\\uff09\\u3001\\uff1f\\u300a\\u300b)]";
//		
//		String str="12312桑德菲杰拉双,,方家里发链接<《￥－ｘｂ，Z,Tz";
//		System.out.println(str.replaceAll(regex, ""));
		
//		String s="position: fixed; z-index: 10000; width: 150px; top: 350px; left: 500px;>>>>>>>>>>>>>>>>>>";
//		Matcher m1=Pattern.compile("width: (.*?);").matcher(s);
//		while(m1.find())
//			System.out.println(">>>>>>>>>>>>>>>>>>>"+m1.group(1));
		
		
		
//		System.out.println(ZjUtils.EncryptU.aesEncrypt("sajflajsfjasdfasdfasdf","$%0yy5yy5*!MM5#2"));
		
//		System.out.println(ZjUtils.EncryptU.aesDecrypt(ZjUtils.EncryptU.aesEncrypt("sajflajsfjasdfasdfasdf","$%0yy5yy5*!MM5#2"), "$%0yy5yy5*!MM5#2"));

	}

	public static void writeLog(String str) {
		try {
			String path = "D:\\gzip.txt";
			File file = new File(path);
			if (!file.exists())
				file.createNewFile();
			FileOutputStream out = new FileOutputStream(file, false); // 如果追加方式用true
			StringBuffer sb = new StringBuffer();
			sb.append(str + "\n");
			out.write(sb.toString().getBytes("utf-8"));// 注意需要转换对应的字符集
			out.close();
		} catch (IOException ex) {
			System.out.println(ex.getStackTrace());
		}
	}

	public static String readLog() {
		StringBuffer sb = new StringBuffer();
		String tempstr = null;
		try {
			String path = "D:\\gzip.txt";
			File file = new File(path);
			if (!file.exists())
				throw new FileNotFoundException();
			// BufferedReader br=new BufferedReader(new FileReader(file));
			// while((tempstr=br.readLine())!=null)
			// sb.append(tempstr);
			// 另一种读取方式
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			while ((tempstr = br.readLine()) != null)
				sb.append(tempstr);
		} catch (IOException ex) {
			System.out.println(ex.getStackTrace());
		}
		return sb.toString();
	}

	/**
	 * 是否包含
	 * @return
	 */
	public static boolean isContainChinese() {
		String str = "procEssbuilde";
		str = str.toLowerCase();
		String pattern = "^*(processbuilder)*$";
		return str.matches(pattern);
	}
	
	
	/**
	 * 解析出url请求的路径，包括页面
	 * 
	 * @param strURL
	 *            url地址
	 * @return url路径
	 */
	public static String UrlPage(String strURL) {
		String strPage = null;
		String[] arrSplit = null;

		strURL = strURL.trim();

		arrSplit = strURL.split("[?]");
		if (strURL.length() > 0) {
			if (arrSplit.length > 1) {
				if (arrSplit[0] != null) {
					strPage = arrSplit[0];
				}
			}
		}

		return strPage;
	}

	/**
	 * 去掉url中的路径，留下请求参数部分
	 * 
	 * @param strURL
	 *            url地址
	 * @return url请求参数部分
	 */
	private static String TruncateUrlPage(String strURL) {
		String strAllParam = null;
		String[] arrSplit = null;

		strURL = strURL.trim();

		if(strURL.length() > 1 && strURL.charAt(0) != '?')
			strURL="?"+strURL;
		arrSplit = strURL.split("[?]");
		if (strURL.length() > 1) {
			if (arrSplit.length > 1) {
				if (arrSplit[1] != null) {
					strAllParam = arrSplit[1];
				}
			}
		}

		return strAllParam;
	}

	/**
	 * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
	 * 
	 * @param URL
	 *            url地址
	 * @return url请求参数部分
	 */
	public static Map<String,String[]> URLRequest(String URL) {
		Map<String, String[]> mapRequest = new HashMap<String, String[]>();

		String[] arrSplit = null;

		String strUrlParam = TruncateUrlPage(URL);
		if (strUrlParam == null) {
			return mapRequest;
		}
		// 每个键值为一组 www.2cto.com
		arrSplit = strUrlParam.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");

			String key="";
			String val="";
			
			// 解析出键值
			if (arrSplitEqual.length > 1) {
				key=arrSplitEqual[0];
				val=arrSplitEqual[1];
				// 正确解析
//				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
			} else {
				key=arrSplitEqual[0];
//				if (arrSplitEqual[0] != "") {
//					
//					// 只有参数没有值，不加入
//					mapRequest.put(arrSplitEqual[0], "");
//				}
			}
			
			/*if(!StringUtil.isNull(key)){
				try {
					if(!mapRequest.containsKey(URLDecoder.decode(key,"utf-8"))){
						if (key.equals("fileBase64"))
							mapRequest.put(URLDecoder.decode(key, "utf-8"), new String[]{"hex:" + ZjUtils.EncryptU.bytesToHexString(val.getBytes())});
						else
							mapRequest.put(URLDecoder.decode(key, "utf-8"), new String[]{URLDecoder.decode(val, "utf-8")});
					}else{
						String[] ary=mapRequest.get(URLDecoder.decode(key,"utf-8"));
						ary=Arrays.copyOf(ary, ary.length+1);
						ary[ary.length-1]=URLDecoder.decode(val,"utf-8");
						mapRequest.put(URLDecoder.decode(key,"utf-8"),ary);
					}
//					System.out.println(key+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+val);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
		}
		return mapRequest;
	}
}
