package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.TimeUtils;
import com.vgit.yunqiang.mapper.FinExpendItemMapper;
import com.vgit.yunqiang.mapper.FinStockDailyExpendItemMapper;
import com.vgit.yunqiang.pojo.FinExpendItem;
import com.vgit.yunqiang.pojo.FinStockDailyExpendItem;
import com.vgit.yunqiang.service.FinStockDailyExpendItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinStockDailyExpendItemServiceImpl extends BaseServiceImpl<FinStockDailyExpendItem> implements FinStockDailyExpendItemService {

    @Autowired
    private FinStockDailyExpendItemMapper mapper;

    @Autowired
    private FinExpendItemMapper finExpendItemMapper;

    @Override
    protected BaseMapper<FinStockDailyExpendItem> getMapper() {
        return this.mapper;
    }

    @Override
    public FinStockDailyExpendItem saveOrUpdateStockDailyExpendItem(FinStockDailyExpendItem stockDailyExpendItem) {
        if (stockDailyExpendItem.getId() == null) {
            stockDailyExpendItem.setCreateTime(System.currentTimeMillis());
            this.mapper.savePart(stockDailyExpendItem);
        } else {
            stockDailyExpendItem.setUpdateTime(System.currentTimeMillis());
            this.mapper.updatePart(stockDailyExpendItem);
        }
        return stockDailyExpendItem;
    }

    @Override
    public Page<FinStockDailyExpendItem> getExpendItemsByStockId(Long stockId, Long dateTime) {
        Page<FinStockDailyExpendItem> page = new Page<FinStockDailyExpendItem>();

        Timestamp startTime = TimeUtils.getDayStartTime(new Date(dateTime));
        Timestamp endTime = TimeUtils.getDayEndTime(new Date(dateTime));
        List<FinStockDailyExpendItem> rows = this.mapper.getExpendItemsByStockId(stockId, startTime.getTime(), endTime.getTime());
        page.setRows(rows);

        double expendTotal = 0;
        for (FinStockDailyExpendItem row : rows) {
            FinExpendItem  finExpendItem = this.finExpendItemMapper.get(row.getExpendItemId());
            if (finExpendItem != null && !finExpendItem.getCategory().equals("C")) {
                expendTotal += row.getAmount();
            }
        }
        Map<String, Object> footer = new HashMap<String, Object>();
        footer.put("expendTotal", expendTotal);
        page.setFooter(footer);

        return page;
    }

}
