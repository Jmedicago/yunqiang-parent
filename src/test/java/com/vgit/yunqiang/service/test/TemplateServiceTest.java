package com.vgit.yunqiang.service.test;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class TemplateServiceTest {

    private String template;

    private Object data;


    public void freeMark(Object data, String template) {

    }

    /**
     * catalogue
     * <p>
     * -home
     * --banner
     * --news
     * --product
     * --cases
     * -product
     * --banner
     * --list
     */
    public void get() {

    }

    // 一对一
    class SingleData {

    }

    // 一对多
    class ListData {
        List<SingleData> listData;
    }

    // 多对多
    class ComplexData {

    }

    // <a href="" class="" target="_blank"></a>
    class BaseData {

        private Long id;

        private String text;

        private String src;

        private String alt;
    }

}
