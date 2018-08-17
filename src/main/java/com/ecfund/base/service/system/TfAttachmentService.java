package com.ecfund.base.service.system;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.dao.system.TfAttachmentDAO;
import com.ecfund.base.model.system.TfAttachment;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.util.common.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2012-10-23 18:20
 * @filename TfAttachmentService.java
 * @author Hmilyld
 * 
 */

@Service
public class TfAttachmentService extends BaseService<TfAttachment> {

	@Autowired
	private TfAttachmentDAO tfAttachmentDAO;

	@Autowired
	public void setBaseDAO(TfAttachmentDAO tfAttachmentDAO) {
		super.setBaseDAO(tfAttachmentDAO);
	}

	public String insert(String json) throws Exception {
		JSONArray array = JSONArray.fromObject(json);
		String[] guidArray = new String[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject obj = (JSONObject) array.get(i);
			TfAttachment entity = (TfAttachment) JSONObject.toBean(obj,
					TfAttachment.class);
			guidArray[i] = tfAttachmentDAO.insert(entity);
		}
		return StringUtils.convertStrArray(guidArray, ",");
	}

	/**
	 * 删除附件内容，同时删除服务器上已存文件
	 * 
	 * @param guid
	 * @return
	 * @throws Exception
	 */
	public void delete(String[] ids, String realPath) throws Exception {
		// 首先查询到所有的信息内容
		TfAttachment entity = new TfAttachment();
		entity.setIds(ids);
		List<TfAttachment> list = tfAttachmentDAO.find(entity);
		// 删除所有的文件内容
		for (TfAttachment att : list) {
			String filePath = realPath + att.getUrl();
			File file = new File(filePath);
			file.deleteOnExit();
		}
		tfAttachmentDAO.mulDel(ids);
		return;
	}

}