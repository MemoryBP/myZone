package com.myzonespringboot.service.imp;

import com.myzonespringboot.dao.IPrizeDao;
import com.myzonespringboot.service.IPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by cgq on 2017/10/26.
 */
@Service("prizeServiceImp")
public class PrizeServiceImp extends BaseServiceImpl implements IPrizeService {

    @Resource(name = "prizeDaoImp")
    private IPrizeDao prizeDao;

    public void delete(Object obj){
        prizeDao.delete(obj);
    }

}
