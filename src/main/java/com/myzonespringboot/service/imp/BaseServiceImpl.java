package com.myzonespringboot.service.imp;

import com.myzonespringboot.dao.IBaseDao;
import com.myzonespringboot.service.IBaseService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cgq on 2017/10/26.
 */
public class BaseServiceImpl implements IBaseService {

    @Resource(name = "baseDaoImp")
    private IBaseDao baseDao;

    @Override
    public List getManyObjects(String hql) {
        return baseDao.getManyObjects(hql);
    }

    @Override
    public List getManyObjects(String hql, Object[] args) {
        return baseDao.getManyObjects(hql,args);
    }

    @Override
    public Object getOneObject(String hql, Object[] args) {
        return baseDao.getOneObject(hql,args);
    }

    @Override
    public Object save(Object obj) {
        return baseDao.save(obj);
    }

    @Override
    public List saveAll(List objs) {
        return baseDao.saveAll(objs);
    }

    @Override
    public void update(Object obj) {
        baseDao.update(obj);
    }

    @Override
    public void merge(Object obj) {
        baseDao.merge(obj);
    }

    @Override
    public void saveOrUpdate(Object obj) {
        baseDao.saveOrUpdate(obj);
    }

    @Override
    public void delete(Object obj) {
        baseDao.delete(obj);
    }

    @Override
    public void saveManyObjects(List list) {
        baseDao.saveManyObjects(list);
    }

    @Override
    public int bulkUpdate(String hql) {
        return baseDao.bulkUpdate(hql);
    }

    @Override
    public int bulkUpdate(String hql, Object[] params) {
        return baseDao.bulkUpdate(hql,params);
    }

    @Override
    public void executeNativeSqlUpdate(String sql) {
        baseDao.executeNativeSqlUpdate(sql);
    }

    @Override
    public void executeNativeSqlUpdate(String sql, Object[] params) {
        baseDao.executeNativeSqlUpdate(sql,params);
    }

    @Override
    public List executeNativeSqlQuery(String sql, Object[] params) {
        return baseDao.executeNativeSqlQuery(sql,params);
    }

    @Override
    public List findByPage(String sql, int firstRow, int maxRow) {
        return baseDao.findByPage(sql,firstRow,maxRow);
    }

    @Override
    public int findTotal(String sql) {
        return baseDao.findTotal(sql);
    }

    @Override
    public List executeNativeSqlQueryForClass(String sql, Object[] params, Class clazz) {
        return baseDao.executeNativeSqlQueryForClass(sql,params,clazz);
    }

    @Override
    public List findMapBySql(String sql) {
        return baseDao.findMapBySql(sql);
    }
}
