package com.myzonespringboot.service.imp;

import com.myzonespringboot.dao.IBaseDao;
import com.myzonespringboot.service.IBaseService;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by cgq on 2017/10/26.
 */
public class BaseServiceImpl<T,PK extends Serializable> implements IBaseService<T,PK> {

    @Resource(name = "baseDaoImp")
    private IBaseDao<T,PK> baseDao;

    private Class<?> tclazz;
    private Class<?> idclazz;
    public BaseServiceImpl() {
        this.tclazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.idclazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public void setTclazz(Class<?> tclazz) {
        this.tclazz = tclazz;
    }

    public void setIdclazz(Class<?> idclazz) {
        this.idclazz = idclazz;
    }

    @Override
    public List<T> getManyObjects() {
        return baseDao.getManyObjects();
    }

    @Override
    public List<T> getManyObjects(String hql, Object[] args) {
        return baseDao.getManyObjects(hql, args);
    }

    @Override
    public T getOneObject(String hql, Object[] args) {
        return baseDao.getOneObject(hql, args);
    }

    @Override
    public T save(T t) {
        return baseDao.save(t);
    }

    @Override
    public List<T> saveAll(List<T> objs) {
        return baseDao.saveAll(objs);
    }

    @Override
    public void update(T t) {
        baseDao.update(t);
    }

    @Override
    public void merge(T t) {
        baseDao.merge(t);
    }

    @Override
    public void saveOrUpdate(T t) {
        baseDao.saveOrUpdate(t);
    }

    @Override
    public void delete(T t) {
        baseDao.delete(t);
    }

    @Override
    public void saveManyObjects(List<T> list) {
        baseDao.saveManyObjects(list);
    }

    @Override
    public int bulkUpdate(String hql) {
        return baseDao.bulkUpdate(hql);
    }

    @Override
    public int bulkUpdate(String hql, Object[] params) {
        return baseDao.bulkUpdate(hql, params);
    }

    @Override
    public void executeNativeSqlUpdate(String sql) {
        baseDao.executeNativeSqlUpdate(sql);
    }

    @Override
    public void executeNativeSqlUpdate(String sql, Object[] params) {
        baseDao.executeNativeSqlUpdate(sql, params);
    }

    @Override
    public List<T> executeNativeSqlQuery(String sql, Object[] params) {
        return baseDao.executeNativeSqlQuery(sql, params);
    }

    @Override
    public List<T> findByPage(String sql, int firstRow, int maxRow) {
        return baseDao.findByPage(sql, firstRow, maxRow);
    }

    @Override
    public int findTotal(String sql) {
        return baseDao.findTotal(sql);
    }

    @Override
    public List<T> executeNativeSqlQueryForClass(String sql, Object[] params, Class clazz) {
        return baseDao.executeNativeSqlQueryForClass(sql, params, clazz);
    }

    @Override
    public List<T> findMapBySql(String sql) {
        return baseDao.findMapBySql(sql);
    }


}
