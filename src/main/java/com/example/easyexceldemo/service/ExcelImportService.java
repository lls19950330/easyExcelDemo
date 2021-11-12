package com.example.easyexceldemo.service;

import com.example.easyexceldemo.bo.InvoiceTemplateImportRspBO;

import java.io.InputStream;

public interface ExcelImportService {

    InvoiceTemplateImportRspBO invoiceTemplateImport(InputStream inputStream);

}
