package com.myzonespringboot.service;


import java.util.List;

/**
 * Created by cgq on 2017/10/26.
 */
public interface IBaseService {
    /**
     *
     * 功能描述: 获取多个结果
     *
     * @param hql
     * @return
     */
    List getManyObjects(String hql);
    /**
     *
     * 功能描述: 预编译,带参数,得到多条记录
     *
     * @param hql
     * @param args
     * @return
     */
    List getManyObjects(String hql, Object[] args);

    /**
     *
     * 功能描述: 预编译,带参数，得到一条记录
     *
     * @param hql
     * @param args
     * @return
     */
    Object getOneObject(String hql, Object[] args);

    /**
     *
     * 功能描述: 保存一条记录
     *
     * @param obj
     * @return
     */
    Object save(Object obj);

    /**
     *
     * 功能描述: 保存多条记录
     *
     * @param objs
     * @return
     */
    List saveAll(List objs);

    /**
     *
     * 功能描述: 更新一条实体
     *
     * @param obj
     * @return
     */
    void update(Object obj);

    /**
     *
     * 功能描述: 更新/保存一条实体
     *
     * @param obj
     * @return
     */
    void merge(Object obj);

    /**
     *
     * 功能描述: 更新/保存一条实体
     *
     * @param obj
     * @return
     */
    void saveOrUpdate(Object obj);

    /**
     *
     * 功能描述: 删除实体
     *
     * @param obj
     */
    void delete(Object obj);

    /**
     *
     * 功能描述:保存集合对象
     *
     * @param list
     */
    void saveManyObjects(List list);

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
    List executeNativeSqlQuery(final String sql, final Object[] params);

    /**
     *
     * 功能描述: 分页查询
     *
     * @param sql
     * @param firstRow
     * @param maxRow
     * @return
     */
    List findByPage(final String sql, final int firstRow, final int maxRow);

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
    List executeNativeSqlQueryForClass(final String sql, final Object[] params, final Class clazz);

    /**
     *
     * 功能描述:执行原生sql 查询 返回List<map>的 结构
     *
     * @param sql
     * @return
     */
    List findMapBySql(final String sql);

}
