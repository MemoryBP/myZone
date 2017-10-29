package com.myzonespringboot.dao.imp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.myzonespringboot.dao.IBaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myzonespringboot.dao.IUserDao;
import com.myzonespringboot.model.User;

import javax.annotation.Resource;


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

		return (User) getOneObject("from User where username=? and password=?",new String[]{username,password});
	}

	@Override
	@Transactional(readOnly = false)
	public User register(User user) {

		try {
			/*User user1=(User) save(user);
			if(user1!=null)
				return user1;*/
			saveOrUpdate(user);
			return user;
		}catch (Exception e){
			e.printStackTrace();
		}

		/*try {
			Session session = sessionFactory.getCurrentSession();
			Serializable save = session.save(user);
			// 你只要判断不是null就行了
			if (save != null) {
				return user;
			}
		} catch (Exception e) {
			return null;
		}*/
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public int deleteByPrimaryKey(Long id) {
		try {
			bulkUpdate("delete from User where id=?",new Long[]{id});
		}catch (Exception e){
			return 1;
		}

		/*try {
			Session session = sessionFactory.getCurrentSession();
			int reval = session.createQuery("delete User u where u.id=?").setParameter(0, id).executeUpdate();// 返回更新条数
			if (reval > 0) {
				return reval;
			}
		} catch (Exception e) {
			return 0;
		}*/
		return 0;
	}

	@Override
	public int insert(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional(readOnly = true)
	public User selectByPrimaryKey(Long id) {
		User user=(User)getOneObject("from User where id=?",new Long[]{id});
		if(user!=null)
			return user;
		/*Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			List<User> users = session.createCriteria(User.class).add(Restrictions.eq("id", id)).list();
			if (users != null && users.size() > 0) {
				return users.get(0);
			}
		} catch (Exception e) {
			session.close();
			return null;
		}*/
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public int updateByPrimaryKeySelective(User record) {
		// 只是更新新的model中不为空的字段
		try {
			Session session = sessionFactory.getCurrentSession();
			User user = (User) session.load(User.class, record.getId());
			user.setEmail(record.getEmail() != null ? record.getEmail() : "");
			user.setIdCard(record.getIdCard() != null ? record.getIdCard() : "");
			user.setPhone(record.getPhone() != null ? record.getPhone() : "");
			user.setType(record.getType());
			user.setUsername(record.getUsername() != null ? record.getUsername() : "");
			user.setUpdateDate(new Date());
			session.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}

		return 0;
	}

	@Override
	public int updateByPrimaryKey(User record) {
		// 为空的字段在数据库中置为NULL
		return 0;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findall() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			List<User> users = session.createCriteria(User.class).list();
			if (users != null && users.size() > 0) {
				return users;
			}
		} catch (Exception e) {
			session.close();
			return null;
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public int  getCount(String hql) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return Integer.parseInt(q.list().get(0).toString());
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> queryForPage(String hql, int offset, int length) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}

}
