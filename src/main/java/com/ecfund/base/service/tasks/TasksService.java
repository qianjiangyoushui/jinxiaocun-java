package com.ecfund.base.service.tasks;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.dao.tasks.TasksDAO;
import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.tasks.Tasks;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.model.workorder.Workorder;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.publics.LogsService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.service.workorder.WorkorderService;
import com.ecfund.base.util.common.Page;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-24 10:10
 * @filename TasksService.java
 * 
 */

@Service
public class TasksService extends BaseService<Tasks> {

	@Autowired
	private TasksDAO tasksDAO;

	@Autowired
	public void setBaseDAO(TasksDAO tasksDAO) {
		super.setBaseDAO(tasksDAO);
	}
	
	@Autowired
	private UpimageService upimageService;

	@Autowired
	private LogsService logService;
	
	@Autowired
	private WorkorderService workorderService;
	
	public Page findList(Tasks task,int start,int pagesize) throws Exception{
		Page page=new Page();
		page.setRows(tasksDAO.findList(task, start, pagesize));
		page.setCountItem(this.findCount(task));
		return page;
	}
	
	public Tasks getTasks(Tasks task){
		return this.tasksDAO.getTasks(task);
	}
	
	public void receive(Tasks tasks){
		try {
			this.update(tasks);
			Tasks t = new Tasks();
			t.setWorkorderid(tasks.getWorkorderid());
			List<Tasks> list = this.find(t);
			boolean flag = true;
			for(Tasks ta : list){
				if("1".equals(ta.getStatus())){
					flag = false;
					break;
				}
			}
			if(flag){
				Workorder workorder = new Workorder();
				workorder.setGuid(tasks.getWorkorderid());
				workorder.setStatus("2");
				this.workorderService.update(workorder);
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
	}
	
	public void edit(Tasks tasks,Users user,JSONArray imageids){
		try {
			tasks.setStatus("3");
			this.update(tasks);
			
			for(int i=0;i<imageids.size();i++){
				Upimage image = new Upimage();
				image.setGuid(imageids.get(i).toString());
				image.setRelatedid(tasks.getGuid());
				upimageService.update(image);
			}
			
			Logs log=new Logs();
			log.setDescription("任务完成");
			log.setOperatedate(new Date(System.currentTimeMillis()));
			log.setOperatorid(user.getGuid());
			log.setRelatedid(tasks.getGuid());
			
			logService.insert(log);
			
			Tasks t = new Tasks();
			t.setWorkorderid(tasks.getWorkorderid());
			List<Tasks> list = this.find(t);
			boolean flag = true;
			for(Tasks ta : list){
				if("1".equals(ta.getStatus()) || "2".equals(ta.getStatus())){
					flag = false;
					break;
				}
			}
			if(flag){
				Workorder workorder = new Workorder();
				workorder.setGuid(tasks.getWorkorderid());
				workorder.setStatus("3");
				this.workorderService.update(workorder);
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
		
		
		
		
	}

}