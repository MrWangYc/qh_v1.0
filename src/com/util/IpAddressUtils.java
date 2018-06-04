package com.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import sun.net.www.http.HttpClient;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *	基于新浪网接口获取IP的物理地址类
 *
 * @创建人 			王云川
 * @创建日期 		2017-09-25
 * @修改人 			王云川
 * @修改日期 		2017-09-25
 * @版本号 			1.0.0
 * @版权所有 		XX科技
 */
public class IpAddressUtils {


    public static void main(String[] a) {
      System.out.println("===============>>>"+getIpInfoBySina("39.130.98.135").toString());
        System.out.println("===============>>>"+getAllName("39.130.98.135"));
        System.out.println("===============>>>"+getNameByKey("39.130.98.135","city"));
    }

    //（这是新浪的接口地址）
    public static final String  PROP_IPSEARCHURL="http://ip.taobao.com/service/getIpInfo.php";

    /**
     * 根据IP获取地址
     * @param ip
     * @return
     */
    public static JSONObject getIpInfoBySina(String ip){
        if(StringUtils.isBlank(ip)){
            return null;
        }
        Map<String,String> params=new HashMap<String, String>();
        params.put("ip",ip);
        String remoteIpInfo=HttpUtil.get(PROP_IPSEARCHURL,params);
        JSONObject result=JSONObject.parseObject(unicodeToString(remoteIpInfo));
        return result;
    }

    /**
     * 根据IP获取地址全名
     * @param ip
     * @return
     */
    public static String  getAllName(String ip){
        JSONObject o=getIpInfoBySina(ip);
        String str="";
        if(o.containsKey("code") && o.getInteger("code") == 0) {
            JSONObject data=o.getJSONObject("data");
            str = data.getString("country") + "|" + data.getString("area") + "|" + data.getString("region") + "|" + data.getString("city") + "|" + data.getString("isp") + "";
        }
        return str;
    }

    /**
     * 根据IP地址或类型获取名称
     * @param ip    IP地址
     * @param key   城市，省份，ips等
     * @return
     */
    public static String  getNameByKey(String ip,String key){
        JSONObject o=getIpInfoBySina(ip);
        String str="";
        if(o.containsKey("code") && o.getInteger("code") == 0 && o.getJSONObject("data").containsKey(key))
           str=o.getJSONObject("data").getString(key);
        return str;
    }
    /**
     * unicode转中文
     * @param str
     * @return
     * @author yutao
     * @date 2017年1月24日上午10:33:25
     */
    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch+"" );
        }
        return str;
    }
}
