package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.query.MendianQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.pojo.Mendian;
import com.vgit.yunqiang.service.MendianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/mendian")
public class MendianController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MendianController.class);

    @Resource
    private MendianService mendianService;

    @RequestMapping("/treegrid")
    @ResponseBody
    public Ret treegrid() {
        /*List<MendianModel> results = this.mendianService.treegrid(mendianService.ROOT);
        LOGGER.info("[mendian]treegrid={}", results);*/
        return Ret.me().setCode(ICodes.SUCCESS);
    }

    @RequestMapping("/list")
    @ResponseBody
    public Ret list(MendianQuery mendianQuery) {
        Page<Mendian> page = this.mendianService.queryPage(mendianQuery);
        LOGGER.info("[mendian]list={}", page);
        return Ret.me().setData(page);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Ret add(Mendian mendian) {
        mendian.setCreateTime(System.currentTimeMillis());
        mendian.setState(1);
        this.mendianService.savePart(mendian);
        LOGGER.info("[mendian]add={}", mendian);
        return Ret.me().setCode(ICodes.SUCCESS);
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Ret modify(Mendian mendian) {
        mendian.setUpdateTime(System.currentTimeMillis());
        this.mendianService.updatePart(mendian);
        LOGGER.info("[mendian]modify={}", mendian);
        return Ret.me().setCode(ICodes.SUCCESS);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Ret delete(Long id) {
        this.mendianService.delete(id);
        LOGGER.info("[mendian]delete={}", id);
        return Ret.me().setCode(ICodes.SUCCESS);
    }

}
