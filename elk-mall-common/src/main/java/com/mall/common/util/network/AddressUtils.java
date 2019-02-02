package com.mall.common.util.network;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressUtils
{
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";//淘宝的开放api

    public static String getRealAddressByIP(String ip)
    {
        String address = "";
        try
        {
            address = HttpUtils.sendPost(IP_URL, "ip=" + ip);
            JSONObject json = JSONObject.parseObject(address);
            JSONObject object = json.getObject("data", JSONObject.class);
            String region = object.getString("region");
            String city = object.getString("city");
            address = region + " " + city;
        }
        catch (Exception e)
        {
            log.error("获取地理位置异常:", e);
        }
        return address;
    }
}