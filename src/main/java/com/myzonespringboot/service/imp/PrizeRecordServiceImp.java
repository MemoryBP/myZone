package com.myzonespringboot.service.imp;

import com.myzonespringboot.dao.IBaseDao;
import com.myzonespringboot.model.Prize;
import com.myzonespringboot.model.Prize_record;
import com.myzonespringboot.model.User;
import com.myzonespringboot.service.PrizeRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by cgq on 2017/10/30.
 */
@Service("prizeRecordServiceImp")
public class PrizeRecordServiceImp extends BaseServiceImpl<Prize_record> implements PrizeRecordService {

    @Resource(name = "prizeRecordDaoImp")
    public void setBaseDao(IBaseDao<Prize_record> baseDao){
        super.setBaseDao(baseDao);
    }

    @Override
    public void saveRecord(User user, Prize prize){
        Prize_record prize_record=new Prize_record();
        prize_record.setCreateDate(new Date());
        prize_record.setCustomer(user);
        prize_record.setEndDate(prize.getEndDate());
        prize_record.setPrize(prize);
        save(prize_record);
    }

    @Override
    public List<Prize_record> selectRecord(User user) {
        return getManyObjects("from Prize_record p where p.customer.id=?",new Long[]{user.getId()});
    }


}
