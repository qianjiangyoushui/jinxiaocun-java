package com.ecfund.base.service.purchase;

import com.ecfund.base.dao.purchase.ProductCategoryDAO;
import com.ecfund.base.model.purchase.ProductCategory;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService extends BaseService<ProductCategory> {

    //@Autowired
    //private ProductCategoryDAO productCategoryDAO;

    @Autowired
    public void setBaseDAO(ProductCategoryDAO productCategoryDAO) {
        super.setBaseDAO(productCategoryDAO);
    }

}