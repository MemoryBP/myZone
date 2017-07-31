package com.myzonespringboot.action;

import com.myzonespringboot.model.PageBean;
import com.myzonespringboot.model.User;
import com.myzonespringboot.service.IUserService;
import com.myzonespringboot.util.EncoderByMd5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/index")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Resource
	private HttpSession session;

	@RequestMapping(value = "/view")
	public String getView() {
		return "login";
	}


	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public Map<String, Object> getUserName() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			User user=(User)session.getAttribute("user");
			if (user != null) {
				result.put("code", 0);
				result.put("msg", "获取成功!");
				result.put("data", user);
			} else {
				result.put("code", 1);
				result.put("msg", "获取失败!");
				result.put("data", null);
			}
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", "获取失败!");
			result.put("data", null);
			return result;
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(String username, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			User user = userService.login(username, (EncoderByMd5.getMd5(password)));//md5加密
			if (user != null) {
				session.setAttribute("user", user);
				result.put("code", 0);
				result.put("msg", "登陆成功!");
			} else {
				result.put("code", 1);
				result.put("msg", "登陆失败!");
			}
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", "登陆失败!");
			return result;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/userInfo", method = RequestMethod.POST)
	public Map<String, Object> login(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			User user = userService.selectByPrimaryKey(id);
			if (user != null) {
				result.put("code", 0);
				result.put("msg", "获取成功!");
				result.put("data", user);
			} else {
				result.put("code", 1);
				result.put("msg", "获取失败!");
				result.put("data", null);
			}
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", "获取失败!");
			result.put("data", null);
			return result;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Map<String, Object> register(@RequestBody User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			user.setCreateDate(new Date()); // new Date()为获取当前系统时间
			user.setUpdateDate(new Date()); // new Date()为获取当前系统时间
			user.setPassword(EncoderByMd5.getMd5(user.getPassword()));
			User user2 = userService.register(user);
			if (user2 != null) {
				result.put("code", 0);
				result.put("msg", "注册成功!");
			} else {
				result.put("code", 1);
				result.put("msg", "注册失败!");
			}
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", "注册失败!");
			return result;
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/findall", method = RequestMethod.POST)
	public Map<String, Object> findall() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<User> users = userService.findall();
			if (users!=null && users.size()>0) {
				result.put("code", 0);
				result.put("msg", "获取成功!");
				result.put("data", users);
			}else {
				result.put("code", 1);
				result.put("msg", "获取失败!");
				result.put("data", null);
			}
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", "获取失败!");
			result.put("data", null);
			return result;
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/de/{id}", method=RequestMethod.GET)
	public Map<String, Object> de(@PathVariable("id") Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int reval=userService.deleteByPrimaryKey(id);
			if (reval!=0) {
				result.put("code", 0);
				result.put("msg", "删除成功!");
				result.put("data", id);
			}else {
				result.put("code", 1);
				result.put("msg", "删除失败!");
				result.put("data", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 1);
			result.put("msg", "删除失败!");
			result.put("data", null);
			return result;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	public Map<String, Object> update(@RequestBody User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			System.out.println(user.getId());
			if (user.getId()!=null) {
				userService.updateByPrimaryKeySelective(user);
				result.put("code", 0);
				result.put("msg", "更新成功!");
			}else {
				result.put("code", 1);
				result.put("msg", "更新失败!");
			}
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", "更新失败!");
			return result;
		}
		
		System.out.println(result);
		return result;
	}
	
	@RequestMapping(value = "/clearAll", method=RequestMethod.GET)
	public Map<String, Object> clear() {
		Map<String, Object> result = new HashMap<String, Object>();
		session.invalidate();
		result.put("code", 0);
		return result;
	}
	
	@RequestMapping(value = "/page", method=RequestMethod.GET)
	public Map<String, Object> pageBean(@RequestParam("pageSize") int pageSize,@RequestParam("page") int page) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			PageBean pageBean = userService.queryForPage(pageSize, page);
			System.out.println("总记录数:"+pageBean.getAllRow()+"显示条数:"+pageBean.getPageSize()+"当前页:"+pageBean.getCurrentPage()+"总页数:"+pageBean.getTotalPage());
			if (pageBean!=null) {
				result.put("code", 0);
				result.put("msg", "获取成功!");
				result.put("data", pageBean.getList());
				result.put("currentPage", pageBean.getCurrentPage());
				result.put("totalPage", pageBean.getTotalPage());
			}else {
				result.put("code", 1);
				result.put("msg", "获取失败!");
				result.put("data", null);
			}
		} catch (Exception e) {
			result.put("code", 1);
			result.put("msg", "获取失败!");
			result.put("data", null);
			return result;
		}
		return result;
	}
}
