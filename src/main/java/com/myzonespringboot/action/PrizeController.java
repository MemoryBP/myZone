package com.myzonespringboot.action;

import com.myzonespringboot.model.Prize;
import com.myzonespringboot.model.Prize_record;
import com.myzonespringboot.model.User;
import com.myzonespringboot.service.IPrizeService;
import com.myzonespringboot.service.PrizeRecordService;
import com.myzonespringboot.util.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller("prizeManageController")
@RequestMapping("/prizeManage")
public class PrizeController {
    @Resource
    private HttpSession session;

    @Resource(name = "prizeServiceImp")
    private IPrizeService prizeService;

    @Resource(name = "prizeRecordServiceImp")
    private PrizeRecordService prizeRecordService;


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> prize() {
        User user = (User) session.getAttribute("user");
        List<Prize> prizeList = prizeService.getEnabledPrize();
        return user == null ? Message.failure("请先登录!") : Message.success(startPrize(user, getPercentArea(prizeList), prizeList));
    }

    /**
     * 获取中奖记录
     *
     * @return
     */
    @RequestMapping(value = "/prize_record", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> prize_record() {
        User user = (User) session.getAttribute("user");
        List<Prize_record> prizeRecordList = prizeRecordService.selectRecord(user);
        List<Map<String, Object>> viewMapList = new ArrayList<Map<String, Object>>();
        for (Prize_record prize_record : prizeRecordList) {
            viewMapList.add(convertViewMap(prize_record));
        }
        return user == null ? Message.failure("请先登录!") : Message.success(viewMapList);
    }

    private Map<String, Object> convertViewMap(Prize_record prize_record) {
        Map<String, Object> viewMap = new HashMap<String, Object>();
        viewMap.put("username", prize_record.getCustomer().getUsername());
        viewMap.put("prizeName", prize_record.getPrize().getName());
        viewMap.put("memo", prize_record.getPrize().getMemo());
        viewMap.put("createDate", prize_record.getCreateDate().toString());
        return viewMap;
    }

    /**
     * 计算概率区间
     *
     * @return
     */
    private List<Integer> getPercentArea(List<Prize> prizeList) {
        List<Integer> areaList = new ArrayList<Integer>();
        Integer intiArea = 0;
        areaList.add(intiArea);
        for (Prize prize : prizeList) {
            if (prize.isEnable()) {
                intiArea += prize.getRemain();
                /*intiArea += prize.getPercent().multiply(new BigDecimal("10000")).intValue();*/
                areaList.add(intiArea);
            }
        }
        return areaList;
    }

    /**
     * 开始抽奖
     *
     * @param user      当前用户
     * @param areaList  概率区间
     * @param prizeList 奖品
     * @return
     */
    private Map<String, Object> startPrize(User user, List<Integer> areaList, List<Prize> prizeList) {
        Map<String, Object> prizeMsg = new HashMap<String, Object>();
        int num = (int) (Math.random() * 10000 + 1);//产生1~10001随机数 [1,10001)
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        for (int i = 0; i < areaList.size() - 1; i++) {
            if (num > areaList.get(i) && num <= areaList.get(i + 1)) {
                prizeMsg.put("msg", "恭喜获得" + prizeList.get(i).getName());
                prizeMsg.put("choice", prizeList.get(i).getCode().toString());
                prizeMsg.put("content", "恭喜获得" + prizeList.get(i).getName() + "!" + prizeList.get(i).getMemo() + " " + date);
                prizeRecordService.saveRecord(user, prizeList.get(i));
                Prize prize=prizeList.get(i);
                prize.setRemain(prize.getRemain()-1);
                prize.setUpdateDate(new Date());
                prizeService.update(prize);
                return prizeMsg;
            }
        }

        if (prizeMsg.size() == 0) {
            prizeMsg.put("msg", "未中奖!");
            prizeMsg.put("choice", 0);
            prizeMsg.put("content", "未中奖!" + " " + date);
        }
        return prizeMsg;
    }
}
