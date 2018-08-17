package com.ecfund.base.service.workorder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ecfund.base.common.UUIDCreate;
import com.ecfund.base.dao.workorder.WorkorderDAO;
import com.ecfund.base.model.eliteG1.Greenhouses;
import com.ecfund.base.model.tasks.Tasks;
import com.ecfund.base.model.workorder.Workorder;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.tasks.TasksService;
import com.ecfund.base.util.common.Page;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-24 09:26
 * @filename WorkorderService.java
 * 
 */

@Service
public class WorkorderService extends BaseService<Workorder> {

	@Autowired
	private WorkorderDAO workorderDAO;

	@Autowired
	public void setBaseDAO(WorkorderDAO workorderDAO) {
		super.setBaseDAO(workorderDAO);
	}
	
	@Autowired
	private TasksService tasksService;
	
	@Transactional
	public void save(Workorder workorder,String canyuid){
		
		try {
			String workid = this.insert(workorder);
			
			String[] canyuids = canyuid.split(",");
			
			for (int i = 0; i < canyuids.length; i++) {
				Tasks task = new Tasks();
				task.setGuid(UUIDCreate.get());
				task.setUserid(canyuids[i]);
				task.setStatus("1");
				task.setWorkorderid(workid);
				this.tasksService.insert(task);
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
	}
	
	
	public Page findpagelist(Workorder workorder,int start,int pagesize) throws Exception{
		Page page=new Page();
		page.setRows(workorderDAO.findpagelist(workorder, start, pagesize));
		page.setCountItem(this.findCount(workorder));
		return page;
	}
	
	
	public Page findList(Workorder workorder,int start,int pagesize) throws Exception{
		Page page=new Page();
		page.setRows(workorderDAO.findList(workorder, start, pagesize));
		page.setCountItem(this.findCount(workorder));
		return page;
	}
	
	

}