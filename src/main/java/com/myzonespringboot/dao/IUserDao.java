package com.myzonespringboot.dao;

import com.myzonespringboot.model.User;

import java.util.List;

public interface IUserDao extends IBaseDao<User> {
	User login(String username,String password);

	int selectByUserName(User user);

	User register(User user);
	
	int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> findall();
    //总记录条数
    int getCount(String hql);
    /**
     * 分页查询
     * @param hql
     * @param offset 当前页开始记录
     * @param length 每页记录数
     * @return
     */
    List<User> queryForPage(String hql,int offset,int length);
}
