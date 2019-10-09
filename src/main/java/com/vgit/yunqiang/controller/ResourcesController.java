package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.utils.JsonUtils;
import com.vgit.yunqiang.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping("/" + ResourcesController.DOMAIN)
public class ResourcesController {

    public static final String DOMAIN = "resources";

    @Autowired
    private ResourcesService resourcesService;

    @RequestMapping(value = "/upload", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile) {
        Map result = this.resourcesService.upload(uploadFile);
        String json = JsonUtils.objectToJson(result);
        return json;
    }

}
