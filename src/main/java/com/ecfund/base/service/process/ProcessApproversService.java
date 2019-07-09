package com.ecfund.base.service.process;

import com.ecfund.base.dao.process.ProcessApproversDAO;
import com.ecfund.base.model.process.ProcessApprovers;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessApproversService extends BaseService<ProcessApprovers> {

    @Autowired
    private ProcessApproversDAO processApproversDAO;

    @Autowired
    public void setBaseDAO(ProcessApproversDAO processApproversDAO) {
        super.setBaseDAO(processApproversDAO);
    }

}