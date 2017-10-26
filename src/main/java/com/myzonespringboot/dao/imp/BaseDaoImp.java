package com.myzonespringboot.dao.imp;

import com.myzonespringboot.dao.IBaseDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cgq on 2017/10/26.
 */
@Repository("baseDaoImp")
public class BaseDaoImp<T> implements IBaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> clazz; //T的具体类

    /**
     * 通过构造方法指定DAO的具体实现类
     */
    @SuppressWarnings("unchecked")
    public BaseDaoImp() {
        /*ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[0];*/
        //System.out.println("DAO的真实实现类是："+this.clazz.getName());
    }

    @Override
    public void save(T entity) {

    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void partUpdate(Long id, String[] names, Object[] values) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public T findById(Long id) {
        return null;
    }

    @Override
    public List<T> findByHQL(String hql, Object... params) {
        return null;
    }

    @Override
    public List<T> queryPage(String hql, int pageNo, int pageSize) {
        return null;
    }
}
