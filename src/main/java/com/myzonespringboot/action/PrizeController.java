package com.myzonespringboot.action;

import com.myzonespringboot.model.Prize;
import com.myzonespringboot.model.User;
import com.myzonespringboot.service.IPrizeService;
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
import java.util.List;
import java.util.Map;

@Controller("prizeManageController")
@RequestMapping("/prizeManage")
public class PrizeController {
    @Resource
    private HttpSession session;

    @Resource(name = "prizeServiceImp")
    private IPrizeService prizeService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> prize() {
        User user = (User) session.getAttribute("user");
        List<Prize> prizeList=prizeService.getManyObjects("from Prize where name='一等奖'");
        for (Prize prize:prizeList){
            System.out.println(prize.getName());
        }
        return user == null ? Message.failure("请先登录!") : Message.success(startPrize());
    }

    public void testPriz(){
        Prize prize=new Prize();
        prize.setCode(8);
        prize.setName("测试");
        prize.setEnable(true);
        prize.setCreateDate(new Date());
        prize.setMemo("测试");
        prizeService.save(prize);
    }

    /**
     * 开始抽奖
     *
     * @return
     */
    private Map<String, Object> startPrize() {
        Map<String, Object> prizeMsg = new HashMap<String, Object>();
        int num = (int) (Math.random() * 10000 + 1);//产生1~10001随机数 [1,10001)
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        if (num == 1) {
            prizeMsg.put("msg", "恭喜获得一等奖!");
            prizeMsg.put("choice", 1);
            prizeMsg.put("content", "恭喜获得一等奖! " + date);
        } else if (num > 1 && num <= 10) {
            prizeMsg.put("msg", "恭喜获得二等奖!");
            prizeMsg.put("choice", 2);
            prizeMsg.put("content", "恭喜获得二等奖! " + date);
        } else if (num > 10 && num <= 110) {
            prizeMsg.put("msg", "恭喜获得三等奖!");
            prizeMsg.put("choice", 3);
            prizeMsg.put("content", "恭喜获得三等奖! " + date);
        } else if (num > 110 && num <= 310) {
            prizeMsg.put("msg", "恭喜获得四等奖!");
            prizeMsg.put("choice", 4);
            prizeMsg.put("content", "恭喜获得四等奖! " + date);
        } else if (num > 310 && num <= 610) {
            prizeMsg.put("msg", "恭喜获得五等奖!");
            prizeMsg.put("choice", 5);
            prizeMsg.put("content", "恭喜获得五等奖! " + date);
        } else if (num > 610 && num <= 1110) {
            prizeMsg.put("msg", "恭喜获得六等奖!");
            prizeMsg.put("choice", 6);
            prizeMsg.put("content", "恭喜获得六等奖! " + date);
        } else {
            prizeMsg.put("msg", "未中奖!");
            prizeMsg.put("choice", 0);
            prizeMsg.put("content", "未中奖! " + date);
        }
        return prizeMsg;
    }

    public static void main(String[] args) {
        //数据库操作demo
        //读取已启用的奖品模板以模板及总量
        List<Prize> prizeList=null;//"select * from prize where enable=1";//获取list
        Integer size=prizeList.size();//获取list总量
        Integer remains=0;//剩余奖品总量
        for (Prize prize:prizeList){
            remains+=prize.getRemain();
        }




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
    }
}
