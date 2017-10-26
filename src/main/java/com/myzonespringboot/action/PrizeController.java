package com.myzonespringboot.action;

import com.myzonespringboot.model.User;
import com.myzonespringboot.util.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller("prizeManageController")
@RequestMapping("/prizeManage")
public class PrizeController {
    @Resource
    private HttpSession session;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> prize() {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Message.failure("请先登录!");
        }
        return Message.success(startPrize());
    }

    /**
     * 开始抽奖
     * @return
     */
    private Map<String,Object> startPrize(){
        Map<String,Object> prizeMsg=new HashMap<String, Object>();
        int num = (int) (Math.random() * 10000 + 1);//产生1~10001随机数 [1,10001)
        String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        if (num == 1) {
            prizeMsg.put("msg","恭喜获得一等奖!");
            prizeMsg.put("choice",1);
            prizeMsg.put("content","恭喜获得一等奖! " + date);
        } else if (num > 1 && num <= 10) {
            prizeMsg.put("msg","恭喜获得二等奖!");
            prizeMsg.put("choice",2);
            prizeMsg.put("content","恭喜获得二等奖! " + date);
        } else if (num > 10 && num <= 110) {
            prizeMsg.put("msg","恭喜获得三等奖!");
            prizeMsg.put("choice",3);
            prizeMsg.put("content","恭喜获得三等奖! " + date);
        } else if (num > 110 && num <= 310) {
            prizeMsg.put("msg","恭喜获得四等奖!");
            prizeMsg.put("choice",4);
            prizeMsg.put("content","恭喜获得四等奖! " + date);
        } else if (num > 310 && num <= 610) {
            prizeMsg.put("msg","恭喜获得五等奖!");
            prizeMsg.put("choice",5);
            prizeMsg.put("content","恭喜获得五等奖! " + date);
        } else if (num > 610 && num <= 1110) {
            prizeMsg.put("msg","恭喜获得六等奖!");
            prizeMsg.put("choice",6);
            prizeMsg.put("content","恭喜获得六等奖! " + date);
        } else {
            prizeMsg.put("msg","未中奖!");
            prizeMsg.put("choice",0);
            prizeMsg.put("content","未中奖! " + date);
        }
        return prizeMsg;
    }

    /*public static void main(String[] args) {
        int num = (int) (Math.random() * 10000 + 1);//产生1~10001随机数 [1,10001)
        if (num == 1) {
            System.out.println(num+"恭喜获得一等奖!");
        } else if (num > 1 && num <= 10) {
            System.out.println(num+"恭喜获得二等奖!");
        } else if (num > 10 && num <= 110) {
            System.out.println(num+"恭喜获得三等奖!");
        } else if (num > 110 && num <= 310) {
            System.out.println(num+"恭喜获得四等奖!");
        } else if (num > 310 && num <= 610) {
            System.out.println(num+"恭喜获得五等奖!");
        } else if (num > 610 && num <= 1110) {
            System.out.println(num+"恭喜获得六等奖!");
        } else {
            System.out.println(num+"未中奖!");
        }
    }*/
}
