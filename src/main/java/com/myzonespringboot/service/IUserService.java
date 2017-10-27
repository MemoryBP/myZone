package com.myzonespringboot.service;

import com.myzonespringboot.model.PageBean;
import com.myzonespringboot.model.User;

import java.util.List;

public interface IUserService extends IBaseService {
	User login(String username,String password);
	
	User register(User user);
	
	int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> findall();
    
    PageBean queryForPage(int pageSize, int currentPage);
}
