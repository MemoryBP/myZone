package com.myzonespringboot.dao.imp;

import com.myzonespringboot.dao.IBaseDao;
import org.hibernate.*;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgq on 2017/10/26.
 */
@Repository("baseDaoImp")
public class BaseDaoImp extends HibernateDaoSupport implements IBaseDao  {

    @Resource
    private SessionFactory sessionFactory;

    @PostConstruct
    public void initSessionFactory() {
        super.setSessionFactory(sessionFactory);
    }

    /**
     *
     * 功能描述: 获取多个结果
     *
     * @param hql
     * @return
     */
    @Override
    public List getManyObjects(String hql) {
        return getHibernateTemplate().find(hql);
    }

    /**
     *
     * 功能描述: 预编译,带参数,得到多条记录
     *
     * @param hql
     * @param args
     * @return
     */
    @Override
    public List getManyObjects(String hql, Object[] args) {
        return getHibernateTemplate().find(hql, args);
    }

    /**
     *
     * 功能描述: 预编译,带参数，得到一条记录
     *
     * @param hql
     * @param args
     * @return
     */
    @Override
    public Object getOneObject(String hql, Object[] args) {
        List list = getHibernateTemplate().find(hql, args);
        if (list == null || list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    /**
     *
     * 功能描述: 保存一条记录
     *
     * @param obj
     * @return
     */
    @Override
    public Object save(Object obj) {
        return getHibernateTemplate().save(obj);
    }

    /**
     *
     * 功能描述: 保存多条记录
     *
     * @param objs
     * @return
     */
    @Override
    public List saveAll(List objs) {
        if (objs == null || objs.size() == 0) {
            return null;
        }
        List list = new ArrayList();
        for (Object ob : objs) {
            list.add(save(ob));
        }
        return list;
    }

    /**
     *
     * 功能描述: 更新一条实体
     *
     * @param obj
     * @return
     */
    @Override
    public void update(Object obj) {
        getHibernateTemplate().update(obj);
    }

    /**
     *
     * 功能描述: 更新/保存一条实体
     *
     * @param obj
     * @return
     */
    @Override
    public void merge(Object obj) {
        getHibernateTemplate().merge(obj);
    }

    /**
     *
     * 功能描述: 更新/保存一条实体
     *
     * @param obj
     * @return
     */
    @Override
    public void saveOrUpdate(Object obj) {
        getHibernateTemplate().saveOrUpdate(obj);
    }

    /**
     *
     * 功能描述: 删除实体
     *
     * @param obj
     */
    @Override
    public void delete(Object obj) {
        getHibernateTemplate().delete(obj);
    }

    /**
     *
     * 功能描述:保存集合对象
     *
     * @param list
     */
    @Override
    public void saveManyObjects(List list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            getHibernateTemplate().save(list.get(i));
        }
    }

    /**
     * 功能描述: 执行HQL
     *
     * @param hql
     * @return
     */
    @Override
    public int bulkUpdate(String hql) {
        return getHibernateTemplate().bulkUpdate(hql);
    }

    /**
     *
     * 功能描述: 执行HQL,带参数
     *
     * @param hql
     * @param params
     * @return
     */
    @Override
    public int bulkUpdate(String hql, final Object[] params) {
        return getHibernateTemplate().bulkUpdate(hql, params);
    }

    /**
     *
     * 功能描述: 执行原生增删改SQL
     *
     * @param sql
     */
    @Override
    public void executeNativeSqlUpdate(final String sql) {
        this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) {
                session.createSQLQuery(sql).executeUpdate();
                return null;
            }
        });
    }

    /**
     *
     * 功能描述: 执行原生增删改SQL 带参数
     *
     * @param sql
     * @param params
     */
    @Override
    public void executeNativeSqlUpdate(final String sql, final Object[] params) {
        this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) {
                Query query = session.createSQLQuery(sql);
                if (params != null && params.length > 0) {
                    for (int i = 0; i < params.length; i++) {
                        query.setParameter(i, params[i]);
                    }
                }
                query.executeUpdate();
                return null;
            }
        });
    }

    /**
     *
     * 功能描述: 执行原生查询SQL,带参数
     *
     * @param sql
     * @return
     */
    @Override
    public List executeNativeSqlQuery(final String sql, final Object[] params) {
        List list = (List) this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) {
                Query query = session.createSQLQuery(sql);
                if (params != null && params.length > 0) {
                    for (int i = 0; i < params.length; i++) {
                        query.setParameter(i, params[i]);
                    }
                }
                return query.list();
            }
        });
        return list;
    }

    @Override
    public List findByPage(String sql, int firstRow, int maxRow) {
        return null;
    }

    /**
     *
     * 功能描述: 分页查询
     *
     * @param sql
     * @param firstRow
     * @param maxRow
     * @return
     */
    /*public List findByPage(final String sql, final int firstRow, final int maxRow) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {

            public Object doInHibernate(Session session) {
                Query q = session.createQuery(sql);
                q.setFirstResult(firstRow);
                q.setMaxResults(maxRow);
                return q.list();
            }

        });
    }*/

    /**
     *
     * 功能描述: 获取总页数
     *
     * @param sql
     * @return
     */
    public int findTotal(final String sql) {
        Long total = (Long) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) {
                String countHQL = "select count(1) " + sql;
                Query q = session.createQuery(countHQL);
                Long total = (Long) q.uniqueResult();
                return total;
            }

        });
        if (total != null) {
            return total.intValue();
        } else {
            return 0;
        }
    }

    /**
     * 根据指定的原生SQL和参数 查询 返回对应的java实体
     * @param sql 原生SQL查询语句
     * @param params SQL参数数组
     * @param clazz 实体类
     * @return List
     */
    public List executeNativeSqlQueryForClass(final String sql, final Object[] params, final Class clazz) {
        List list = (List) this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery(sql).addEntity(clazz);
                if (params != null && params.length > 0) {
                    for (int i = 0; i < params.length; i++) {
                        query.setParameter(i, params[i]);
                    }
                }
                return query.list();
            }
        });
        return list;
    }

    @Override
    public List findMapBySql(String sql) {
        return null;
    }

    /**
     *
     * 功能描述:执行原生sql 查询 返回List<map>的 结构
     *
     * @param sql
     * @return
     */
    /*public List findMapBySql(final String sql) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {

            public Object doInHibernate(Session session) {
                SQLQuery query = session.createSQLQuery(sql);
                query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
                return query.list();
            }

        });
    }*/
}
