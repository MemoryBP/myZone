package com.myzonespringboot.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by 陈 on 2017/7/11.
 */
@Component
public class QuartzJob {
    /*@Scheduled(cron="0 56 16 ? * FRI")//周五的下午16:00触发 生成数据统计周报*/
    /*@Scheduled(fixedRate = 1000 * 60)*/
    @Scheduled(cron="0 0 17 ? * MON-FRI")
    public void weekly() {

        //获取统计数据值

        //配置短信内容
        /*String content="这是Go贷统计数据【Go贷】";//内容
        //配置短信接口参数
        String url ="http://www.ztsms.cn/sendNSms.do";
        String username ="dali";
        String password ="Dali201688";//密码
        String mobile ="13541403440";//号码
        String productid ="676766";//产品id
        String xh ="0";//没有
        String tkey =TimeUtil.getNowTime("yyyyMMddHHmmss");
        try{
            content= URLEncoder.encode(content,"utf-8");
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String param="url="+url+"&usernam="+username+"&password="+MD5Gen.getMD5(MD5Gen.getMD5(password)+tkey)+"&tkey="+tkey+"&mobile="+mobile+"&content="+content+"&productid="+productid+"&xh"+xh;

        *//*http://www.ztsms.cn/sendNSms.do?username=用户名&password=密码& tkey=20160324151338&mobile=手机号&content=此次登录验证码123456【助通科技】&productid=产品id&xh=*//*
        //启动短信接口
        String ret=HttpRequest.sendPost(url, param);//sendPost or sendGet  即get和post方式
        System.out.println("ret:"+ret);
        System.out.println(param);*/
        System.out.println("周报已发送!");
    }

    @Scheduled(cron="0 0 9 ? * MON-FRI")//周一至周五的上午9:00触发
    public void toWork() {
        /*String time = new SimpleDateFormat("MMM d，yyyy KK:mm:ss a", Locale.ENGLISH).format(System.currentTimeMillis());*/
        String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(System.currentTimeMillis());
        System.out.println("time:"+time);
        System.out.println("上班啦!!!!!!!!!!!");
    }

    @Scheduled(cron="0 0 12 ? * *")
    public void eatLunch() {
        String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(System.currentTimeMillis());
        System.out.println("time:"+time);
        System.out.println("吃午饭啦!!!!!!!!!!!");
    }

    @Scheduled(cron="0 0 13 ? * MON-FRI")
    public void workAfternoon() {
        String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(System.currentTimeMillis());
        System.out.println("time:"+time);
        System.out.println("中午上班啦!!!!!!!!!!!");
    }

    @Scheduled(cron="0 0 17 ? * MON-FRI")
    public void goHome() {
        String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(System.currentTimeMillis());
        System.out.println("time:"+time);
        System.out.println("下班啦!!!!!!!!!!!");
    }

    @Scheduled(cron="0 10 18 ? * MON-FRI")
    public void goneHome() {
        String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(System.currentTimeMillis());
        System.out.println("time:"+time);
        System.out.println("到家啦!!!!!!!!!!!");
    }

    @Scheduled(cron="0 0 19 ? * *")
    public void eatDinner() {
        String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(System.currentTimeMillis());
        System.out.println("time:"+time);
        System.out.println("吃晚饭啦!!!!!!!!!!!");
    }


}
