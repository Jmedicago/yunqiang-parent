package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.GlobalSettingNames;

import com.vgit.yunqiang.common.utils.FtpUtils;
import com.vgit.yunqiang.common.utils.GlobalSetting;
import com.vgit.yunqiang.common.utils.IDUtils;
import com.vgit.yunqiang.service.ResourcesService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class ResourcesServiceImpl implements ResourcesService {

    @Override
    public Map<String, Object> upload(MultipartFile uploadFile) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            //生成一个新的文件名
            //取原始文件名
            String oldName = uploadFile.getOriginalFilename();
            //生成新文件名
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            //图片上传
            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            boolean result = FtpUtils.uploadFile(
                    GlobalSetting.get(GlobalSettingNames.FTP_ADDRESS),
                    Integer.parseInt(GlobalSetting.get(GlobalSettingNames.FTP_PORT)),
                    GlobalSetting.get(GlobalSettingNames.FTP_USERNAME),
                    GlobalSetting.get(GlobalSettingNames.FTP_PASSWORD),
                    GlobalSetting.get(GlobalSettingNames.FTP_BASE_PATH),
                    imagePath, newName, uploadFile.getInputStream());
            //返回结果
            if (!result) {
                resultMap.put("error", 1);
                resultMap.put("message", "文件上传失败");
                return resultMap;
            }
            resultMap.put("error", 0);
            resultMap.put("url", GlobalSetting.get(GlobalSettingNames.IMAGE_BASE_URL) + "/" + newName);
            return resultMap;

        } catch (Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传发生异常");
            return resultMap;
        }
    }

    @Override
    public Map<String, Object> upload(String suffix, byte[] data) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            //生成新文件名
            String fileName = IDUtils.genImageName();
            fileName = fileName + "." + suffix;
            //图片上传
            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            boolean result = FtpUtils.uploadFile(
                    GlobalSetting.get(GlobalSettingNames.FTP_ADDRESS),
                    Integer.parseInt(GlobalSetting.get(GlobalSettingNames.FTP_PORT)),
                    GlobalSetting.get(GlobalSettingNames.FTP_USERNAME),
                    GlobalSetting.get(GlobalSettingNames.FTP_PASSWORD),
                    GlobalSetting.get(GlobalSettingNames.FTP_BASE_PATH),
                    imagePath, fileName, new ByteArrayInputStream(data));

            //返回结果
            if (!result) {
                resultMap.put("error", 1);
                resultMap.put("message", "文件上传失败");
                return resultMap;
            }
            resultMap.put("error", 0);
            resultMap.put("url", GlobalSetting.get(GlobalSettingNames.IMAGE_BASE_URL) + "/" + fileName);
            return resultMap;
        } catch (Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传发生异常");
            return resultMap;
        }
    }

}
