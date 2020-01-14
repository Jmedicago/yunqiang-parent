package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.FinQInventory;

public interface FinQInventoryService extends BaseService<FinQInventory> {

    void saveQInventory(FinQInventory qInventory);
}
