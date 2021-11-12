package com.example.easyexceldemo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.example.easyexceldemo.bo.InvoiceTemplateBO;
import com.example.easyexceldemo.bo.InvoiceTemplateImportRspBO;
import com.example.easyexceldemo.handler.InvoiceTemplateColumnWidthStyleStrategy;
import com.example.easyexceldemo.service.ExcelImportService;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

@RestController
public class ExcelController {

    @Autowired
    private ExcelImportService excelImportService;

    // http://localhost:8989/invoiceTemplateDownload
    @GetMapping("/invoiceTemplateDownload")
    public void templateDownload(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("发票导入模板-普票s", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write(response.getOutputStream(), InvoiceTemplateBO.class);
        excelWriterBuilder.registerWriteHandler(templateWriteHandler);
        excelWriterBuilder.registerWriteHandler(InvoiceTemplateColumnWidthStyleStrategy.getInstance());
        excelWriterBuilder.sheet().doWrite(new ArrayList());
    }

    @PostMapping("/invoiceTemplateImport")
    public InvoiceTemplateImportRspBO invoiceTemplateImport(@RequestParam("file") MultipartFile file) throws IOException{
        return excelImportService.invoiceTemplateImport(file.getInputStream());
    }

    private static WriteHandler templateWriteHandler;
    static {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //字体
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 11);
        headWriteFont.setBold(false);
        headWriteCellStyle.setWriteFont(headWriteFont);
        //边框
        headWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        headWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        headWriteCellStyle.setBorderRight(BorderStyle.THIN);
        //前景色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        //是否换行
        headWriteCellStyle.setWrapped(true);
        headWriteCellStyle.setLocked(true);
        templateWriteHandler = new HorizontalCellStyleStrategy(headWriteCellStyle,new WriteCellStyle());
    }
}
