package com.myzonespringboot.dao.imp;

import com.myzonespringboot.dao.IPrizeDao;
import com.myzonespringboot.model.Prize;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;


/**
 * Created by cgq on 2017/10/27.
 */
@Repository("prizeDaoImp")
public class PrizeDaoImp extends BaseDaoImp<Prize> implements IPrizeDao {
    /*private Class<?> tclazz;
    private Class<?> idclazz;
    public PrizeDaoImp() {
        this.tclazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.idclazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }*/

}
