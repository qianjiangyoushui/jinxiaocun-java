package com.ecfund.base.service.PrintPDF.impl;

/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * jiaxd-create
 */

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiFileUploadSingleRequest;
import com.dingtalk.api.response.OapiFileUploadSingleResponse;
import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.dao.sale.SaleorderDAO;
import com.ecfund.base.model.sale.Saleorderdetail;
import com.ecfund.base.service.PrintPDF.PrintPDF;
import com.ecfund.base.model.sale.Saleorder;
import com.ecfund.base.model.storage.Outstockorder;
import com.ecfund.base.util.dingtalk.AccessTokenUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.taobao.api.FileItem;
import com.taobao.api.internal.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleOrderPrintPDF implements PrintPDF {

    @Autowired
    private SaleorderDAO saleorderDAO;

    @Override
    public String createPdf(String fileName, String agentId, String guid) {
        String mediaId="";
        try {
            String os = System.getProperties().getProperty("os.name");
            String prefixFont = "";
            if (os.startsWith("win") || os.startsWith("Win")) {
                prefixFont = "C:\\Windows\\Fonts" + File.separator;
            } else {
                prefixFont = "/usr/share/fonts/chinese" + File.separator;
            }
            Saleorder saleorder = new Saleorder();
            saleorder.setGuid(guid);
            saleorder = saleorderDAO.view("findDetail",saleorder);
            Document document = new Document();
            BaseFont bf = null;
            Font fontChinese = null;

            BaseFont baseFont = BaseFont.createFont(prefixFont + "simfang.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            fontChinese = new Font(baseFont);// 中文字体
            fontChinese.setSize(16);//字体大小
            Rectangle pageSize = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            pageSize.rotate();
            document.setPageSize(pageSize);
            ByteArrayOutputStream ba = new ByteArrayOutputStream();
            File file = new File(fileName);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            writer.setViewerPreferences(PdfWriter.PageModeUseThumbs);
            document.open();
            document.add(new Paragraph("销售开单",fontChinese));
            //表格处理   创建表格时必须指定表格的列数
            PdfPTable table = new PdfPTable(new float[] {150,400});
            table.setWidthPercentage(90);
            table.setLockedWidth(true);
            table.setTotalWidth(550);//550
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            table.getDefaultCell().setBorder(1);
            // 设置PDF表格标题
            //drawTable(fontChinese,table,guid);
            try {
                PdfPCell cell1 = new PdfPCell();
                cell1.setColspan(1);
                cell1.setPhrase(new Paragraph("订货单号", fontChinese));
                cell1.setFixedHeight(24f);
                PdfPCell cell2 = new PdfPCell();
                cell2.setColspan(1);
                cell2.setFixedHeight(24f);
                cell2.setPhrase(new Paragraph(saleorder.getCode(), fontChinese));
                PdfPCell cell3 = new PdfPCell();
                cell3.setFixedHeight(24f);
                cell3.setPhrase(new Paragraph("客户名称", fontChinese));
                PdfPCell cell4 = new PdfPCell();
                cell4.setColspan(1);
                cell4.setFixedHeight(24f);
                cell4.setPhrase(new Paragraph(saleorder.getCustomername(), fontChinese));
                PdfPCell cell5 = new PdfPCell();
                cell5.setPhrase(new Paragraph("结算方式：", fontChinese));
                PdfPCell cell6 = new PdfPCell();
                cell6.setPhrase(new Paragraph(saleorder.getPaytypename(), fontChinese));
                cell6.setColspan(1);

                PdfPCell cell7 = new PdfPCell();
                cell7.setColspan(1);
                cell7.setPhrase(new Paragraph("发票类型", fontChinese));
                cell7.setFixedHeight(24f);
                PdfPCell cell8 = new PdfPCell();
                cell8.setColspan(1);
                cell8.setFixedHeight(24f);
                cell8.setPhrase(new Paragraph(saleorder.getTicketname(), fontChinese));

                PdfPCell cell9 = new PdfPCell();
                cell9.setColspan(1);
                cell9.setPhrase(new Paragraph("结算账号", fontChinese));
                cell9.setFixedHeight(24f);
                PdfPCell cell10 = new PdfPCell();
                cell10.setColspan(1);
                cell10.setFixedHeight(24f);
                cell10.setPhrase(new Paragraph(saleorder.getPayacount(), fontChinese));

                PdfPCell cell11 = new PdfPCell();
                cell11.setColspan(1);
                cell11.setPhrase(new Paragraph("送货地点", fontChinese));
                cell11.setFixedHeight(24f);
                PdfPCell cell12 = new PdfPCell();
                cell12.setColspan(1);
                cell12.setFixedHeight(24f);
                cell12.setPhrase(new Paragraph(saleorder.getAddress(), fontChinese));

                PdfPCell cell13 = new PdfPCell();
                cell13.setColspan(1);
                cell13.setPhrase(new Paragraph("联系电话", fontChinese));
                cell13.setFixedHeight(24f);
                PdfPCell cell14 = new PdfPCell();
                cell14.setColspan(1);
                cell14.setFixedHeight(24f);
                cell14.setPhrase(new Paragraph(saleorder.getContactphone(), fontChinese));

                PdfPCell cell15 = new PdfPCell();
                cell15.setColspan(1);
                cell15.setPhrase(new Paragraph("总金额", fontChinese));
                cell15.setFixedHeight(24f);
                PdfPCell cell16 = new PdfPCell();
                cell16.setColspan(1);
                cell16.setFixedHeight(24f);
                cell16.setPhrase(new Paragraph(saleorder.getSummoney().toString(), fontChinese));

                PdfPCell cell17 = new PdfPCell();
                cell17.setColspan(1);
                cell17.setPhrase(new Paragraph("关联订货单号", fontChinese));
                cell17.setFixedHeight(24f);
                PdfPCell cell18 = new PdfPCell();
                cell18.setColspan(1);
                cell18.setFixedHeight(24f);
                cell18.setPhrase(new Paragraph(saleorder.getApplycode(), fontChinese));


                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
                table.addCell(cell7);
                table.addCell(cell8);
                table.addCell(cell9);
                table.addCell(cell10);
                table.addCell(cell11);
                table.addCell(cell12);
                table.addCell(cell13);
                table.addCell(cell14);
                table.addCell(cell15);
                table.addCell(cell16);
                table.addCell(cell17);
                table.addCell(cell18);


            } catch (Exception e) {
                e.printStackTrace();
            }
            //制表单位等信息设置
            // pdf文档中加入table
            document.add(table);
            document.add(new Paragraph("货品明细",fontChinese));
            document.add(createDetailTable(fontChinese,saleorder.getDetailList()));
            document.add(new Paragraph("客户确认签字：",fontChinese));
            document.add(new Paragraph("库管签字：",fontChinese));
            document.close();
            writer.flush();

            OapiFileUploadSingleRequest request = new OapiFileUploadSingleRequest();
            request.setFileSize(1000L);
            request.setAgentId(agentId);
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/file/upload/single?" + WebUtils.buildQuery(request.getTextParams(), "utf-8"));
            // 必须重新new一个请求
            request = new OapiFileUploadSingleRequest();
            request.setFile(new FileItem(file));
            OapiFileUploadSingleResponse response = client.execute(request, AccessTokenUtil.getToken());


            String s = response.getBody();
            JSONObject json = JSONObject.parseObject(s);
            mediaId = json.getString("media_id");
        }catch (Exception e){
            e.printStackTrace();
        }
        return mediaId;
    }

    private  PdfPTable createDetailTable( Font fontChinese,List<Saleorderdetail> list){
        PdfPTable table = new PdfPTable(new float[] {150,80, 80,80, 80,80});
        table.setWidthPercentage(90);
        table.setLockedWidth(true);
        table.setTotalWidth(550);//550
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        table.getDefaultCell().setBorder(1);
        // 设置PDF表格标题
        //drawTable(fontChinese,table,guid);
        Saleorder saleorder = new Saleorder();
        try {
            PdfPCell cell1 = new PdfPCell();
            cell1.setColspan(1);
            cell1.setPhrase(new Paragraph("货品名称", fontChinese));
            cell1.setFixedHeight(36f);

            PdfPCell cell2 = new PdfPCell();
            cell2.setColspan(1);
            cell2.setPhrase(new Paragraph("规格", fontChinese));
            cell2.setFixedHeight(24f);

            PdfPCell cell3 = new PdfPCell();
            cell3.setColspan(1);
            cell3.setPhrase(new Paragraph("单位", fontChinese));
            cell3.setFixedHeight(24f);

            PdfPCell cell4 = new PdfPCell();
            cell4.setColspan(1);
            cell4.setPhrase(new Paragraph("单价", fontChinese));
            cell4.setFixedHeight(24f);

            PdfPCell cell5= new PdfPCell();
            cell5.setColspan(1);
            cell5.setPhrase(new Paragraph("数量", fontChinese));
            cell5.setFixedHeight(24f);

            PdfPCell cell6 = new PdfPCell();
            cell6.setColspan(1);
            cell6.setPhrase(new Paragraph("金额", fontChinese));
            cell6.setFixedHeight(24f);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            for(int i=0;i<list.size();i++){
                Saleorderdetail saleorderdetail = list.get(i);
                table.addCell(new PdfPCell(new Paragraph(saleorderdetail.getProductname(),fontChinese)));
                table.addCell(new PdfPCell(new Paragraph(saleorderdetail.getNormal(),fontChinese)));
                table.addCell(new PdfPCell(new Paragraph(saleorderdetail.getUnit(),fontChinese)));
                table.addCell(new PdfPCell(new Paragraph(saleorderdetail.getPrice().toString(),fontChinese)));
                table.addCell(new PdfPCell(new Paragraph(saleorderdetail.getAmount().toString(),fontChinese)));
                table.addCell(new PdfPCell(new Paragraph(saleorderdetail.getSummoney().toString(),fontChinese)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }





}
