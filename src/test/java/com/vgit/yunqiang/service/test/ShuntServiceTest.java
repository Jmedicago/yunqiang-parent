package com.vgit.yunqiang.service.test;

import com.vgit.yunqiang.pojo.BisStockShunt;
import com.vgit.yunqiang.service.BisStockService;
import com.vgit.yunqiang.service.BisStockShuntService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class ShuntServiceTest {

    @Autowired
    private BisStockShuntService bisStockShuntService;

    @Autowired
    private BisStockService bisStockService;

    // 入库
    @Test
    public void testCheckIn() {
        BisStockShunt stockShunt = new BisStockShunt();
        stockShunt.setAmount(100);
        stockShunt.setSkuId(162L);
        //bisStockShuntService.checkIn(stockShunt);
    }

    // 出库
    @Test
    public void testCheckOut() {
        bisStockShuntService.checkOut(11L);
    }

    /*// 分流
    @Test
    public void testShunt() {
        BisStockShunt stockShunt = new BisStockShunt();
        stockShunt.setStockId(Long.valueOf(BisStockShuntService.NORTH_STOCK));
        stockShunt.setAmount(20);
        stockShunt.setSkuId(145L);
        bisStockShuntService.shunt(stockShunt);
    }*/

    @Test
    public void testGetShipLocation() {
        String stockId = this.bisStockService.getShipmentLocation(1043L);
        System.out.println("****************************************");
        System.out.println("*             " + stockId + "          *");
        System.out.println("****************************************");
    }
}
