package com.myzonespringboot.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cgq on 2017/10/26.
 */
public interface IBaseDao<T> {

    /**
     * 添加一个对象
     * @param entity
     */
    void save(T entity);

    /**
     * 更新一个对象，所有属性
     * @param entity
     */
    void update(T entity);

    /**
     * 更新一个对象，部分属性
     * @param id
     * @param names
     * @param values
     */
    void partUpdate(Long id, String[] names, Object[] values);

    /**
     * 删除一个对象
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id查找一个对象
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 根据HQL返回对象List
     * @param hql
     * @param params
     * @return
     */
    List<T> findByHQL(String hql, Object... params);

    /**
     * 分页查询
     * @param hql
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<T> queryPage(String hql, int pageNo, int pageSize);
}
