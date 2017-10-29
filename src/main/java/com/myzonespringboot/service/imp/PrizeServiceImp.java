package com.myzonespringboot.service.imp;

import com.myzonespringboot.dao.IBaseDao;
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
public class PrizeServiceImp extends BaseServiceImpl<Prize> implements IPrizeService {

   @Resource(name = "prizeDaoImp")
   public void setBaseDao(IBaseDao<Prize> baseDao){
       super.setBaseDao(baseDao);
   }
}
