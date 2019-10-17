package com.vgit.yunqiang.service.test;

import com.vgit.yunqiang.service.BisProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vgit.yunqiang.pojo.BisProductType;
import com.vgit.yunqiang.service.BisProductTypeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class ProductTypeServiceTest {
	
	@Autowired
	private BisProductTypeService bisProductTypeService;

	@Autowired
	private BisProductService bisProductService;

	
	@Test
	public void testGetProductType() throws Exception {
		BisProductType pt = this.bisProductTypeService.get(1001);
		System.out.println(pt.getName());
	}

	@Test
	public void testDeleteProductType() throws Exception {
		//this.bisProductTypeService.deleteById(11L);
		//this.bisProductService.test();
	}
	
}
