package com.vgit.yunqiang.service.test;

import com.vgit.yunqiang.pojo.FinStockDaily;
import com.vgit.yunqiang.pojo.FinStockDailyExpendItem;
import com.vgit.yunqiang.service.BisProductService;
import com.vgit.yunqiang.service.FinStockDailyExpendItemService;
import com.vgit.yunqiang.service.FinStockDailyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vgit.yunqiang.pojo.BisProductType;
import com.vgit.yunqiang.service.BisProductTypeService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class ProductTypeServiceTest {

    @Autowired
    private BisProductTypeService bisProductTypeService;

    @Autowired
    private BisProductService bisProductService;

    @Autowired
    private FinStockDailyExpendItemService finStockDailyExpendItemService;

    @Autowired
    private FinStockDailyService finStockDailyService;


    @Test
    public void testGetProductType() throws Exception {
        BisProductType pt = this.bisProductTypeService.get(1001);
        System.out.println(pt.getName());
    }

    @Test
    public void testDeleteProductType() throws Exception {
        //this.bisProductTypeService.deleteById(11L);
        //this.bisProductService.test();
        StringBuilder digest = new StringBuilder(); // 订单摘要

        String skuProperties = "5:颜色:8:黑色_6:尺码:10:38";
        String[] propArr = skuProperties.split("_");
        for (String props : propArr) {
            String[] propValueArr = props.split(":");
            digest.append("-").append(propValueArr[3]);
        }
        digest.append("×").append(3).append(",");
        System.out.println("____________________ SYSTEM OUT _____________________");
        System.out.println(digest.toString());
        System.out.println("_____________________________________________________");
    }

    @Test
    public void testQueryReport() {
        List<FinStockDaily> itemList = this.finStockDailyService.queryDaily(1004L);
        for (FinStockDaily stockDaily : itemList) {
            System.out.println(stockDaily);
        }
    }

}
