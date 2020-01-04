package com.vgit.yunqiang.service.test;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.mapper.FinDayMapper;
import com.vgit.yunqiang.pojo.*;
import com.vgit.yunqiang.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Hashtable;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class RoleServiceTest {

    @Resource
    private SysRoleService sysRoleService;

    @Autowired
    private FinDyDailyService finDyDailyService;

    @Autowired
    private FinDzDailyService finDzDailyService;

    @Autowired
    private FinArrearsService finArrearsService;

    @Autowired
    private FinDSalesService finDSalesService;

    @Autowired
    private FinMSalesService finMSalesService;

    @Autowired
    private FinDayMapper finDayMapper;

    @Autowired
    private FinQExpendsService finQExpendsService;

    @Test
    public void testFindRoles() throws Exception {
        //System.out.println(this.roleService.findRoles(1L, 2L));

        Long[] ids = new Long[]{1013L};
        this.sysRoleService.correlationPermissions(1003L, ids);
    }

    @Test
    public void testDyDailyList() {
        List<FinDyDaily> dyDailyList = this.finDyDailyService.queryDailyList(1004L);
        for (FinDyDaily finDyDaily : dyDailyList) {
            System.out.println(finDyDaily.toString());
        }
    }

    @Test
    public void testDzDailyList() {
        List<FinDzDaily> dzDailyList = this.finDzDailyService.queryDailyList(1001L);
        System.out.println(dzDailyList);
    }

    @Test
    public void testArrearsList() {
        //List<BisStock> arrearsList = this.finArrearsService.queryArrearsList("2019");
        ReportQuery query = new ReportQuery();
        query.setYear("2019");
        Hashtable<String, Object> arrearsList = this.finArrearsService.queryArrearsReport(query);
        System.out.println(arrearsList);
    }

    @Test
    public void testAddDays() {
        for (int i = 1; i <= 31; i ++) {
            FinDay day = new FinDay();
            day.setName(i + "日");
            this.finDayMapper.save(day);
        }
    }

    @Test
    public void testGetSalesList() {
        /*List<BisStock> salesList = this.finDSalesService.getDSalesList(1, "2019", "1");
        System.out.println(salesList);*/
        /*List<BisStock> salesList = this.finMSalesService.getMSalesList(1005L, "2019");*/
        List<BisStock> quarterlies = this.finQExpendsService.getExpendsList("2019", "Q1", 1001L);
        System.out.println(quarterlies);
    }

}
