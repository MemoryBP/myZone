package com.myzonespringboot.service;

import com.myzonespringboot.model.Prize;
import com.myzonespringboot.model.Prize_record;
import com.myzonespringboot.model.User;

import java.util.List;

/**
 * Created by cgq on 2017/10/30.
 */
public interface PrizeRecordService extends IBaseService<Prize_record> {
    /**
     * 保存中奖记录
     * @param user
     * @param prize
     */
    void saveRecord(User user, Prize prize);

    /**
     * 查询中奖记录
     * @param user
     * @return
     */
    List<Prize_record> selectRecord(User user);
}
