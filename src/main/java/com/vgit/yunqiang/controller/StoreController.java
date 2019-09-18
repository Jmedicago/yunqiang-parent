package com.vgit.yunqiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StoreController {

    @RequestMapping("/stock-in")
    public String storeIn() {
        return "stock/stock-in";
    }

    @RequestMapping("/stock-out")
    public String storeOut() {
        return "stock/stock-out";
    }

}
