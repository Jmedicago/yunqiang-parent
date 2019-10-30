package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.pojo.BisTradeStock;
import org.springframework.web.multipart.MultipartFile;

public interface BisTradeStockService extends BaseService<BisTradeStock> {

    /**
     * 上传贸易型请购单
     *
     * @param type
     * @param uploadFile
     * @return
     */
    Ret uploadPrTrade(Integer type, MultipartFile uploadFile);

    /**
     * 上传贸易型采购单
     *
     * @param id
     * @param uploadFile
     * @return
     */
    Ret uploadPoTrade(Long id, MultipartFile uploadFile);

    /**
     * 贸易订单完成
     *
     * @param id
     * @return
     */
    Ret finishTrade(Long id);
}
