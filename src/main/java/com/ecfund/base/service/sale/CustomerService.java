package com.ecfund.base.service.sale;

import com.ecfund.base.dao.sale.CustomerDAO;
import com.ecfund.base.model.sale.Customer;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends BaseService<Customer> {

    //@Autowired
    //private CustomerDAO customerDAO;

    @Autowired
    public void setBaseDAO(CustomerDAO customerDAO) {
        super.setBaseDAO(customerDAO);
    }

}