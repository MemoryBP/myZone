package com.myzonespringboot.dao;
import java.io.Serializable;
import java.util.List;

/**
 * Created by cgq on 2017/10/26.
 */
public interface IBaseDao<T,PK extends Serializable> {
    /**
     *
     * 功能描述: 获取多个结果
     *
     * @return
     */
    List<T> getManyObjects();
    /**
     *
     * 功能描述: 预编译,带参数,得到多条记录
     *
     * @param hql
     * @param args
     * @return
     */
    List<T> getManyObjects(String hql, Object[] args);

    /**
     *
     * 功能描述: 预编译,带参数，得到一条记录
     *
     * @param hql
     * @param args
     * @return
     */
    T getOneObject(String hql, Object[] args);

    /**
     *
     * 功能描述: 保存一条记录
     *
     * @return
     */
    T save(T t);

    /**
     *
     * 功能描述: 保存多条记录
     *
     * @param objs
     * @return
     */
    List<T> saveAll(List<T> objs);

    /**
     *
     * 功能描述: 更新一条实体
     *
     * @return
     */
    void update(T t);

    /**
     *
     * 功能描述: 更新/保存一条实体
     *
     * @return
     */
    void merge(T t);

    /**
     *
     * 功能描述: 更新/保存一条实体
     *
     * @return
     */
    void saveOrUpdate(T t);

    /**
     *
     * 功能描述: 删除实体
     *
     */
    void delete(T t);

    /**
     *
     * 功能描述:保存集合对象
     *
     * @param list
     */
    void saveManyObjects(List<T> list);

    /**
     * 功能描述: 执行HQL
     *
     * @param hql
     * @return
     */
    int bulkUpdate(String hql);

    /**
     *
     * 功能描述: 执行HQL,带参数
     *
     * @param hql
     * @param params
     * @return
     */
    int bulkUpdate(String hql, final Object[] params);

    /**
     *
     * 功能描述: 执行原生增删改SQL
     *
     * @param sql
     */
    void executeNativeSqlUpdate(final String sql);

    /**
     *
     * 功能描述: 执行原生增删改SQL 带参数
     *
     * @param sql
     * @param params
     */
    void executeNativeSqlUpdate(final String sql, final Object[] params);

    /**
     *
     * 功能描述: 执行原生查询SQL,带参数
     *
     * @param sql
     * @return
     */
    List<T> executeNativeSqlQuery(final String sql, final Object[] params);

    /**
     *
     * 功能描述: 分页查询
     *
     * @param sql
     * @param firstRow
     * @param maxRow
     * @return
     */
    List<T> findByPage(final String sql, final int firstRow, final int maxRow);

    /**
     *
     * 功能描述: 获取总页数
     *
     * @param sql
     * @return
     */
    int findTotal(final String sql);

    /**
     * 根据指定的原生SQL和参数 查询 返回对应的java实体
     * @param sql 原生SQL查询语句
     * @param params SQL参数数组
     * @param clazz 实体类
     * @return List
     */
    List<T> executeNativeSqlQueryForClass(final String sql, final Object[] params, final Class clazz);

    /**
     *
     * 功能描述:执行原生sql 查询 返回List<map>的 结构
     *
     * @param sql
     * @return
     */
    List<T> findMapBySql(final String sql);
}
