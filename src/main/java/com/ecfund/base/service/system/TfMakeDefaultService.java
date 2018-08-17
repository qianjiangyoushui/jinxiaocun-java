package com.ecfund.base.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.dao.system.TfMakeDefaultDAO;
import com.ecfund.base.model.system.TfMakeDefault;
import com.ecfund.base.service.BaseService;

@Service
public class TfMakeDefaultService extends BaseService<TfMakeDefault> {

	@Autowired
	public void setBaseDAO(TfMakeDefaultDAO tfMakeDefaultDAO) {
		super.setBaseDAO(tfMakeDefaultDAO);
	}

}
