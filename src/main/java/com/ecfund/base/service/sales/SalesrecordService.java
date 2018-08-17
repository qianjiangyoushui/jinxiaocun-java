package com.ecfund.base.service.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.service.BaseService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.model.sales.Salesrecord;
import com.ecfund.base.dao.sales.SalesrecordDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-07 14:16
 * @filename SalesrecordService.java
 * 
 */

@Service
public class SalesrecordService extends BaseService<Salesrecord> {

	@Autowired
	private SalesrecordDAO salesrecordDAO;

	@Autowired
	public void setBaseDAO(SalesrecordDAO salesrecordDAO) {
		super.setBaseDAO(salesrecordDAO);
	}
	
	public Page findlist(Salesrecord sales, Page page) throws Exception {
		Page pagelist=new Page();
		pagelist.setRows(salesrecordDAO.findlist(sales, page.getBegin(), page.getPageSize()));
		pagelist.setPageNo(page.getPageNo());
		pagelist.setCountItem(this.findCount(sales));
		return pagelist;
		
	}

}