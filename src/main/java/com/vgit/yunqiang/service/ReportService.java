package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.query.ReportQuery;

import java.util.Hashtable;

/**
 * 报表服务
 */
public interface ReportService {

    /**
     * 生成报表
     *
     * @param query
     * @return
     */
    Hashtable<String, Object> report(ReportQuery query);
}
