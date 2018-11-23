package ${actionPackage};

import org.springframework.beans.factory.annotation.Autowired;

import com.ecfund.base.action.BaseAction;
import com.ecfund.base.common.ResultNameBean;
import ${entityPackage}.${tablename};
import ${servicePackage}.${tablename}Service;
import com.ecfund.base.util.json.JSONUtils;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date ${.now?string('yyyy-MM-dd HH:mm')}
 * @filename ${tablename}Action.java
 * 
 */

public class ${tablename}Action extends BaseAction implements
		ModelDriven<${tablename}> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ${tablename}Service ${tableLower}Service;

	private ${tablename} entity = new ${tablename}();

	/**
	 * 默认首页
	 * 
	 * @return
	 */
	public String index() {
		return ResultNameBean.FTLPAGE;
	}

	/**
	 * 获取列表json数据
	 */
	public void list() {
		try {
			setPager(${tableLower}Service.find(entity, getStart(), getRows()));
			String json = JSONUtils.fromObject(getPager());
			sendMsg(json);
		} catch (Exception ex) {
			sendMsg(JSONUtils.fromString("info", "数据获取失败!"));
		}
	}

	/**
	 * 修改数据
	 * 
	 * @return
	 */
	public void update() {
		try {
			${tableLower}Service.update(${tablename}.class, json);
			sendMsg(JSONUtils.fromString("info", "数据维护成功!"));
		} catch (Exception ex) {
			sendMsg(JSONUtils.fromString("info", "数据维护失败!" + ex.getMessage()));
		}
	}

	/**
	 * 删除数据
	 */
	public void del() {
		try {
			String status = ${tableLower}Service.delete(entity);
			sendMsg(JSONUtils.fromString("info", status));
		} catch (Exception ex) {
			sendMsg(JSONUtils.fromString("info", "数据操作失败!" + ex.getMessage()));
		}
	}

	@Override
	public ${tablename} getModel() {
		return entity;
	}

	public ${tablename} getEntity() {
		return entity;
	}

	public void setEntity(${tablename} entity) {
		this.entity = entity;
	}

}