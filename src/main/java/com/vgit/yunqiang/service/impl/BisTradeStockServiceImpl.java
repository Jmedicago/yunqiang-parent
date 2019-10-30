package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.GlobalSettingNames;
import com.vgit.yunqiang.common.consts.bis.TradeStockStateConsts;
import com.vgit.yunqiang.common.consts.msg.SysFileUploadMsgConsts;
import com.vgit.yunqiang.common.utils.FtpUtils;
import com.vgit.yunqiang.common.utils.GlobalSetting;
import com.vgit.yunqiang.common.utils.IDUtils;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.service.BisProductService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.BisTradeStockMapper;
import com.vgit.yunqiang.pojo.BisTradeStock;
import com.vgit.yunqiang.service.BisTradeStockService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BisTradeStockServiceImpl extends BaseServiceImpl<BisTradeStock> implements BisTradeStockService {

    @Autowired
    private BisTradeStockMapper mapper;

    @Autowired
    private BisProductService bisProductService;

    @Override
    protected BaseMapper<BisTradeStock> getMapper() {
        // TODO Auto-generated method stub
        return this.mapper;
    }

    @Override
    public Ret uploadPrTrade(MultipartFile uploadFile) {
        try {
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
            if (!result) { // 上传失败
                return Ret.me().setSuccess(false).setCode(SysFileUploadMsgConsts.FILE_UPLOAD_ERROR);
            }

            BisTradeStock tradeStock = new BisTradeStock();
            tradeStock.setBeforeResource(GlobalSetting.get(GlobalSettingNames.IMAGE_BASE_URL) + "/" + newName);
            tradeStock.setFileName(oldName);
            tradeStock.setCreateTime(System.currentTimeMillis());
            tradeStock.setStatus((int) TradeStockStateConsts.WAIT_SHIP_AUDITING);
            this.mapper.save(tradeStock);

            return Ret.me().setSuccess(true).setCode(SysFileUploadMsgConsts.FILE_UPLOAD_SUCCESS);
        } catch (Exception e) {
            return Ret.me().setSuccess(false).setCode(SysFileUploadMsgConsts.FILE_UPLOAD_EXCEPTION);
        }
    }

    @Override
    public Ret uploadPoTrade(Long id, MultipartFile uploadFile) {
        try {
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
            if (!result) { // 上传失败
                return Ret.me().setSuccess(false).setCode(SysFileUploadMsgConsts.FILE_UPLOAD_ERROR);
            }

            BisTradeStock tradeStock = new BisTradeStock();
            tradeStock.setId(id);
            tradeStock.setAfterResource(GlobalSetting.get(GlobalSettingNames.IMAGE_BASE_URL) + "/" + newName);
            tradeStock.setConfirmTime(System.currentTimeMillis());
            this.mapper.updatePart(tradeStock);

            return Ret.me().setSuccess(true).setCode(SysFileUploadMsgConsts.FILE_UPLOAD_SUCCESS);
        } catch (Exception e) {
            return Ret.me().setSuccess(false).setCode(SysFileUploadMsgConsts.FILE_UPLOAD_EXCEPTION);
        }
    }

    @Override
    public Ret finishTrade(Long id) {
        BisTradeStock bisTradeStock = this.mapper.get(id);
        bisTradeStock.setStatus((int) TradeStockStateConsts.SHIP_FINISH_TAKE);
        this.mapper.updatePart(bisTradeStock);
        if (bisTradeStock.getType() == 0) {// 导入商品
            return this.bisProductService.batch(bisTradeStock.getAfterResource());
        } else {
            return Ret.me();
        }
    }

}
