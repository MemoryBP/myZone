package com.myzonespringboot.service;


import com.myzonespringboot.model.Prize;

import java.util.List;

/**
 * Created by cgq on 2017/10/26.
 */
public interface IPrizeService extends IBaseService<Prize>{

    /**
     * 获取已启用的奖品
     * @return
     */
    List<Prize> getEnabledPrize();
}
