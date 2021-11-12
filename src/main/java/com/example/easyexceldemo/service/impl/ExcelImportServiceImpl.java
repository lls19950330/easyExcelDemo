package com.example.easyexceldemo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.example.easyexceldemo.bo.InvoiceTemplateBO;
import com.example.easyexceldemo.bo.InvoiceTemplateImportRspBO;
import com.example.easyexceldemo.handler.InvoiceTemplateAnalyseListener;
import com.example.easyexceldemo.service.ExcelImportService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ExcelImportServiceImpl implements ExcelImportService {

    @Override
    public InvoiceTemplateImportRspBO invoiceTemplateImport(InputStream inputStream) {
        InvoiceTemplateAnalyseListener invoiceTemplateAnalyseListener = new InvoiceTemplateAnalyseListener();
        EasyExcel.read(inputStream, InvoiceTemplateBO.class,invoiceTemplateAnalyseListener)
                .sheet()
                .doRead();
        return invoiceTemplateAnalyseListener.getImportResult();
    }

}
