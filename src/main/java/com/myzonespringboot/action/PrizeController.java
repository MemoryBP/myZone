package com.myzonespringboot.action;

import com.myzonespringboot.model.User;
import com.myzonespringboot.util.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller("prizeController")
@RequestMapping("/prize")
public class PrizeController {
    @Resource
    private HttpSession session;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> prize(){
        User user = (User) session.getAttribute("user");
        if(user==null){
            return Message.failure("请先登录!");
        }
        int prizeCount = 0;
        int choice = (int) (Math.random() * 7);
        if (prizeCount == 0 && choice == 6)
            choice = 0;

        return Message.success(choice);
    }
}
