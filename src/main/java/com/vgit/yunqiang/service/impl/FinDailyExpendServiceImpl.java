package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.DailyExpendQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.mapper.FinDailyExpendMapper;
import com.vgit.yunqiang.pojo.FinDailyExpend;
import com.vgit.yunqiang.pojo.FinDyDaily;
import com.vgit.yunqiang.service.FinDailyExpendService;
import com.vgit.yunqiang.service.FinDyDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinDailyExpendServiceImpl extends BaseServiceImpl<FinDailyExpend> implements FinDailyExpendService {

    @Autowired
    private FinDailyExpendMapper mapper;

    @Autowired
    private FinDyDailyService finDyDailyService;

    @Override
    protected BaseMapper<FinDailyExpend> getMapper() {
        return this.mapper;
    }

    @Override
    public void saveOrUpdateStock(FinDailyExpend dailyExpend) {
        double amount = dailyExpend.getAmount() != null ? dailyExpend.getAmount() * 100 : 0;
        if (dailyExpend.getId() == null) {
            dailyExpend.setAmount(amount);
            this.mapper.savePart(dailyExpend);
        } else {
            dailyExpend.setAmount(amount);
            this.mapper.update(dailyExpend);
        }

        // 更新店员日报
        FinDyDaily dyDaily = this.finDyDailyService.getByCode(dailyExpend.getDailyCode());
        if (dyDaily != null) {
            DailyExpendQuery query = new DailyExpendQuery();
            query.setDailyCode(dailyExpend.getDailyCode());
            double expendTotal = 0;
            double sales = 0;

            List<FinDailyExpend> dailyExpends = this.mapper.query(query);
            for (FinDailyExpend expend :dailyExpends) {
                expendTotal += expend.getAmount();
            }

            sales = dyDaily.getIncome() + expendTotal;

            dyDaily.setExpendSubTotal(expendTotal);
            dyDaily.setSales(sales);
            this.finDyDailyService.updatePart(dyDaily);
        }

    }

    @Override
    public Ret deleteById(Long id) {
        FinDailyExpend dailyExpend = this.mapper.get(id);
        // 更新店员日报
        FinDyDaily dyDaily = this.finDyDailyService.getByCode(dailyExpend.getDailyCode());
        if (dyDaily != null) {
            DailyExpendQuery query = new DailyExpendQuery();
            query.setDailyCode(dyDaily.getCode());
            double expendTotal = 0;

            List<FinDailyExpend> dailyExpends = this.mapper.query(query);
            for (FinDailyExpend expend :dailyExpends) {
                if (expend.getId() != id) {
                    expendTotal += expend.getAmount();
                }
            }

            System.out.println("支出总计：" + expendTotal);
            System.out.println("交付现金：" + dyDaily.getIncome());

            double sales = dyDaily.getIncome() + expendTotal;
            System.out.println("销售额：" + sales);

            dyDaily.setExpendSubTotal(expendTotal);
            dyDaily.setSales(sales);
            this.finDyDailyService.updatePart(dyDaily);
        }
        // 删除
        this.mapper.delete(id);
        return Ret.me();
    }

}
