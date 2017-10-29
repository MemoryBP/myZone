package com.myzonespringboot.dao.imp;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myzonespringboot.dao.IUserDao;
import com.myzonespringboot.model.User;


@Repository("userDao")
public class UserDao extends BaseDaoImp<User> implements IUserDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public User login(String username, String password) {
        /*
         * String hql = "from User u where u.username = '" + username +
		 * "' and u.password = '" + password + "'";
		 */
        /*try {
			*//*
			 * getCurrentSession()创建的Session在commit或rollback后会自动关闭，采用OpenSession
			 * ()必须手动关闭。
			 * autoCloseSessionEnabled，flushBeforeCompletionEnabled都为true，
			 * 并且session会同sessionFactory组成一个map以sessionFactory为主键绑定到当前线程。
			 * openSession()反之
			 *//*

			Session session = sessionFactory.getCurrentSession();
			List<?> users = session.createCriteria(User.class).add(Restrictions.eq("username", username))
					.add(Restrictions.eq("password", password)).list();
			System.out.println("账户:"+username+",密码:"+password);
			if (users != null && users.size() > 0) {
				return (User) users.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}*/

        return getOneObject("from User where username=? and password=?", new String[]{username, password});
    }

    @Override
    public int selectByUserName(User user) {
        return (getOneObject("from User where username=?", new String[]{user.getUsername()}) == null ? 0 : 1);
    }

    @Override
    public User register(User user) {
        try {

            saveOrUpdate(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(Long id) {
        try {
            bulkUpdate("delete from User where id=?", new Long[]{id});
        } catch (Exception e) {
            return 1;
        }
        return 0;
    }

    @Override
    public int insert(User record) {
        // TODO Auto-generated method stub
        return save(record) == null ? 1 : 0;
    }

    @Override
    public int insertSelective(User record) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        User user = (User) getOneObject("from User where id=?", new Long[]{id});
        if (user != null)
            return user;
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        // 只是更新新的model中不为空的字段
        try {
            update(record);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }

        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        // 为空的字段在数据库中置为NULL
        try {
            update(record);
        } catch (Exception e) {
            e.getStackTrace();
            return 1;
        }
        return 0;
    }

    @Override
    public List<User> findall() {
        try {
            return getManyObjects();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getCount(String hql) {
        return getManyObjects().size();
    }

    @Override
    public List<User> queryForPage(String hql, int offset, int length) {
        return findByPage(offset, length);
    }

}
