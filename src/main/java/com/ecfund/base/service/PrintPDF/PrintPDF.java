package com.ecfund.base.service.PrintPDF;

/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * jiaxd-create
 */

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.sale.Saleorder;
import com.ecfund.base.model.storage.Outstockorder;

public interface PrintPDF {
    public String createPdf(String fileName,String agentId,String guid);
}
