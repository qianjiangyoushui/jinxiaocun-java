package ${servicePackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.service.BaseService;

import ${entityPackage}.${tablename};
import ${daoPackage}.${tablename}DAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date ${.now?string('yyyy-MM-dd HH:mm')}
 * @filename ${tablename}Service.java
 * 
 */

@Service
public class ${tablename}Service extends BaseService<${tablename}> {

	//@Autowired
	//private ${tablename}DAO ${tableLower}DAO;

	@Autowired
	public void setBaseDAO(${tablename}DAO ${tableLower}DAO) {
		super.setBaseDAO(${tableLower}DAO);
	}

}