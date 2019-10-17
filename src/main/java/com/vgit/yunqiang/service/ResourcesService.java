package com.vgit.yunqiang.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ResourcesService {

    /**
     * 文件上传
     *
     * @param uploadFile
     * @return
     */
    Map<String, Object> upload(MultipartFile uploadFile);

    /**
     * 文件上传
     *
     * @param suffix
     * @param data
     * @return
     */
    Map<String, Object> upload(String suffix, byte[] data);

}
