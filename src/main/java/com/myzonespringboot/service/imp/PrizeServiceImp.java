package com.myzonespringboot.service.imp;

import com.myzonespringboot.dao.IPrizeDao;
import com.myzonespringboot.model.Prize;
import com.myzonespringboot.service.IPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;

/**
 * Created by cgq on 2017/10/26.
 */
@Service("prizeServiceImp")
public class PrizeServiceImp extends BaseServiceImpl<Prize,Long> implements IPrizeService {
   /* @Autowired
    public void setTclazz(Class<Prize> tclazz) {
        super.setTclazz(tclazz);
    }
    @Resource(name = "prizeDaoImp")
    public void setIdclazz(Class<Prize> idclazz) {
        super.setTclazz(idclazz);
    }*/
}
