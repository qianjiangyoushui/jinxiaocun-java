package com.ecfund.base.service.purchase;

import com.ecfund.base.dao.purchase.ProductsDAO;
import com.ecfund.base.model.purchase.Products;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService extends BaseService<Products> {

    //@Autowired
    //private ProductsDAO productsDAO;

    @Autowired
    public void setBaseDAO(ProductsDAO productsDAO) {
        super.setBaseDAO(productsDAO);
    }

}