package com.myzonespringboot.service.imp;

import com.myzonespringboot.dao.IBaseDao;
import com.myzonespringboot.dao.IUserDao;
import com.myzonespringboot.dao.imp.BaseDaoImp;
import com.myzonespringboot.model.PageBean;
import com.myzonespringboot.model.User;
import com.myzonespringboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@SuppressWarnings("unchecked")
@Service("userService")
public class UserServiceImp extends BaseServiceImpl<User> implements IUserService {
	@Resource(name = "userDao")
	private IUserDao userDao;

	@Resource(name = "userDao")
	public void setBaseDao(IBaseDao<User> baseDao){
		super.setBaseDao(baseDao);
	}

	@Override
	@Transactional(readOnly = true)
	public User login(String username, String password) {
		return userDao.login(username, password);
	}

	@Override
	@Transactional(readOnly = true)
	public int selectByUserName(User user) {
		return userDao.selectByUserName(user);
	}

	@Override
	@Transactional
	public User register(User user) {
		return userDao.register(user);
	}

	@Override
	@Transactional
	public int deleteByPrimaryKey(Long id) {
		return userDao.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int insert(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional(readOnly = true)
	public User selectByPrimaryKey(Long id) {
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int updateByPrimaryKeySelective(User record) {
		return userDao.updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional
	public int updateByPrimaryKey(User record) {
		return userDao.updateByPrimaryKey(record);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findall() {
		return userDao.findall();
	}

	@Override
	@Transactional(readOnly = true)
	public PageBean queryForPage(int pageSize, int page) {
		String hql = "select count(*) from User";
		int count = userDao.getCount(hql);// 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
		int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		int length = pageSize; // 每页记录数
		int currentPage = PageBean.countCurrentPage(page);
		List<User> list = userDao.queryForPage("from User", offset, length); // 该分页的记录

		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

}
