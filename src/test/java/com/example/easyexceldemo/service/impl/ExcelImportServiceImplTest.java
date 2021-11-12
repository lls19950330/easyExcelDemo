package com.example.easyexceldemo.service.impl;

import com.example.easyexceldemo.bo.InvoiceTemplateImportRspBO;
import com.example.easyexceldemo.service.ExcelImportService;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class ExcelImportServiceImplTest {

    @Autowired
    private ExcelImportService excelImportService;

    @Test
    void invoiceTemplateDownload() throws Exception {
        File file = new File("D:\\temp\\发票导入模板-普票.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        InvoiceTemplateImportRspBO invoiceTemplateImportRspBO = excelImportService.invoiceTemplateImport(fileInputStream);
        log.info("导入结果：{}", JSONObject.toJSONString(invoiceTemplateImportRspBO));
    }

}