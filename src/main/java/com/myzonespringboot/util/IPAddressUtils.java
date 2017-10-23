package com.myzonespringboot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * IP地址工具类
 *
 * Created by cgq on 2017/9/13.
 */
public class IPAddressUtils {

    /**
     * 请求地址
     */
    private static final String IP_URL = "http://ip.taobao.com/service/getIpInfo2.php?ip=";
    private static final String BAIDU_URL="https://api.map.baidu.com/location/ip?ak=GoR8bUfkCI1HQdtLMeiuQFecDnWfdQiw&ip=";
    private IPAddressUtils() {

    }

    public static void main(String[] args) {
        getAddress("171.110.240.62");
    }

    /**
     * 获取响应结果
     * @param ip
     * @return
     * @throws IOException
     */
    public static JSONObject getAddressByIp(String ip) throws IOException {
        URL url = new URL(IP_URL + ip);
        /*URL url = new URL(BAIDU_URL + ip);*/
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        InputStream in = httpConn.getInputStream();
        String content = IOUtils.toString(httpConn.getInputStream(), "UTF-8");
        JSONObject js = JSON.parseObject(content);
        in.close();
        httpConn.disconnect();
        return "0".equals(js.getString("code")) ? js.getJSONObject("data") : null;
        /*return "0".equals(js.getString("status")) ? js.getJSONObject("content") : null;*/
    }

    /**
     * 获取IP物理地址
     * @param ip
     * @return
     */
    public static String getAddress(String ip) {
        String address = "";
        if("".equals(ip) || ip==null)
            return null;
        else if("127.0.0.1".equals(ip))
            return "本机";
        try {
            JSONObject js = getAddressByIp(ip);
            if (js != null) {
                if ("local".equals(js.get("isp_id")))
                    return "内网IP";
                StringBuffer buffer = new StringBuffer();
                //buffer.append(js.get("country"));//国家
                //buffer.append(js.get("area"));//地区
                buffer.append(js.get("region"));//省份
                buffer.append(js.get("city"));//市区
                buffer.append(js.get("county"));//地区
                //buffer.append(js.get("isp"));//ISP公司
                return buffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return address;
    }
}
