package com.myzonespringboot.action;

import com.myzonespringboot.model.PageBean;
import com.myzonespringboot.model.User;
import com.myzonespringboot.service.IUserService;
import com.myzonespringboot.util.EncoderByMd5;
import com.myzonespringboot.util.IPAddressUtils;
import com.myzonespringboot.util.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("userController")
@RequestMapping("/index")
public class UserController {

    @Resource(name = "userService")
    private IUserService userService;

    @Resource
    private HttpSession session;

    @RequestMapping(value = "/view")
    public String getView() {
        return "/login";
    }


    @ResponseBody
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public Map<String, Object> getUserName() {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                result = Message.success(user);
            } else {
                result = Message.failure();
            }
        } catch (Exception e) {
            return Message.failure();
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(String username, String password, HttpServletRequest request) {
        System.out.println("请求设备:" + request.getHeader("User-Agent"));
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            User user = userService.login(username, (EncoderByMd5.getMd5(password)));//md5加密
            if (user != null) {
                session.setAttribute("user", user);
                result = Message.success(user);
            } else {
                result = Message.failure();
            }
        } catch (Exception e) {
            return Message.failure();
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    public Map<String, Object> login(Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            User user = userService.selectByPrimaryKey(id);
            if (user != null) {
                result = Message.success(user);
            } else {
                result = Message.failure();
            }
        } catch (Exception e) {
            return Message.failure();
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if(userService.selectByUserName(user)==1)
                return Message.info(2,"用户名已存在!");
            user.setCreateDate(new Date()); // new Date()为获取当前系统时间
            user.setUpdateDate(new Date()); // new Date()为获取当前系统时间
            user.setPassword(EncoderByMd5.getMd5(user.getPassword()));
            User user2 = userService.register(user);
            if (user2 != null) {
                result = Message.success();
            } else {
                result = Message.failure();
            }
        } catch (Exception e) {
            return Message.failure();
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/findall", method = RequestMethod.POST)
    public Map<String, Object> findall() {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            List<User> users = userService.findall();
            if (users != null && users.size() > 0) {
                result = Message.success(users);
            } else {
                result = Message.failure();
            }
        } catch (Exception e) {
            return Message.failure();
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/de/{id}", method = RequestMethod.GET)
    public Map<String, Object> de(@PathVariable("id") Long id) {
        try {
            int reval = userService.deleteByPrimaryKey(id);
            if (reval == 0) {
                return Message.success(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Message.failure();
        }

        return Message.failure();
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map<String, Object> update(@RequestBody User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (user.getId() != null) {
                userService.updateByPrimaryKeySelective(user);
                result = Message.success();
            } else {
                result = Message.failure();
            }
        } catch (Exception e) {
            return  Message.failure();
        }

        System.out.println(result);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/clearAll", method = RequestMethod.GET)
    public Map<String, Object> clear() {
        session.invalidate();
        return Message.success();
    }

    @ResponseBody
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Map<String, Object> pageBean(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            PageBean pageBean = userService.queryForPage(pageSize, page);
            System.out.println("总记录数:" + pageBean.getAllRow() + "显示条数:" + pageBean.getPageSize() + "当前页:" + pageBean.getCurrentPage() + "总页数:" + pageBean.getTotalPage());
            if (pageBean != null) {
                result.put("code", 0);
                result.put("msg", "获取成功!");
                result.put("data", pageBean.getList());
                result.put("currentPage", pageBean.getCurrentPage());
                result.put("totalPage", pageBean.getTotalPage());
            } else {
                result = Message.failure();
            }
        } catch (Exception e) {
            return Message.failure();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public Map<String, Object> getLocation(@RequestParam("ip") String ip) {
        return Message.success(IPAddressUtils.getAddress(ip));
    }

    @ResponseBody
    @RequestMapping(value = "/prize", method = RequestMethod.GET)
    public Map<String, Object> prize() {
        int prizeCount = 0;
        int choice = (int) (Math.random() * 7);
        if (prizeCount == 0 && choice == 6)
            choice = 0;

        return Message.success(choice);
    }
}
