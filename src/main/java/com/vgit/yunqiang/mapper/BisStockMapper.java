package com.vgit.yunqiang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisStock;

/**
 * 业务 - 库存DAO
 * 
 * @author Admin
 *
 */
public interface BisStockMapper extends BaseMapper<BisStock> {

	/**
	 * 查询仓库树形列表
	 * 
	 * @param parentId
	 * @return
	 */
	List<BisStock> queryTree(@Param("parentId") Long parentId);

}
