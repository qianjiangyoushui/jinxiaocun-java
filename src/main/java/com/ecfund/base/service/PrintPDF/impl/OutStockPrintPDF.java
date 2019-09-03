package com.ecfund.base.service.PrintPDF.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiFileUploadSingleRequest;
import com.dingtalk.api.response.OapiFileUploadSingleResponse;
import com.ecfund.base.model.sale.Saleorder;
import com.ecfund.base.model.storage.Outstockorder;
import com.ecfund.base.service.PrintPDF.PrintPDF;
import com.ecfund.base.util.dingtalk.AccessTokenUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.taobao.api.FileItem;
import com.taobao.api.internal.util.WebUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * jiaxd-create
 */

@Service
public class OutStockPrintPDF implements PrintPDF{

    public String createPdf(String fileName, String agentId, String guid) {
        try {
            Document document = new Document();
            Rectangle pageSize = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            pageSize.rotate();
            document.setPageSize(pageSize);
            ByteArrayOutputStream ba = new ByteArrayOutputStream();
            File file = new File(fileName);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            writer.setViewerPreferences(PdfWriter.PageModeUseThumbs);
            document.open();
            float[] widths = {200, 200, 300, 500, 300, 300, 300, 300, 300, 300, 300, 300, 400, 300, 500, 200, 500, 400, 300};
            //表格处理   创建表格时必须指定表格的列数
            PdfPTable table = new PdfPTable(widths);
            table.setLockedWidth(true);
            table.setTotalWidth(800);//550
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            // 设置PDF表格标题
            pdfTitle(table);
            //制表单位等信息设置
            // pdf文档中加入table
            document.add(table);
            document.close();

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
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    private static void pdfTitle(PdfPTable table) throws Exception {
        String titleContent1 = "*****************业务个人支付明细表";
        // 表格的单元格
        PdfPCell cell = new PdfPCell();
        // 向单元格中插入数据
        // new Paragraph()是段落的处理，可以设置段落的对齐方式，缩进和间距。
        cell.setPhrase(new Paragraph(12.5f, "aaaa"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setMinimumHeight(30);//设置表格行高
        cell.setBorderWidth(0f);//去除表格的边框
        cell.setColspan(19);
        //cell.setRowspan(19);
        table.addCell(cell);
    }



}
