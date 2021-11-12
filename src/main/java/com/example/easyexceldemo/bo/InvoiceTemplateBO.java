package com.example.easyexceldemo.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 发票导入功能excel模板BO <br>
 * @author lls
 * @version 1.0.0
 * @date 2021/11/11
 */
@Data
@ToString
public class InvoiceTemplateBO {
    @NotEmpty(message = "，发票代码未填写")
    @ExcelProperty(value = "*发票代码")
    private String invoiceCode;
    @NotEmpty(message = "，发票号码未填写")
    @ExcelProperty(value = "*发票号码")
    private String invoiceNo;
    @NotNull(message = "，开票日期未填写")
    @ExcelProperty(value = "*开票日期\n" + "2000-00-00")
    @DateTimeFormat("yyyy-MM-dd")
    private Date invoiceDate;
    @NotEmpty(message = "，校验码未填写")
    @ExcelProperty(value = "*校验码")
    private String checkCode;

    public static String getTemplateHead(){
        return "[*发票代码, *发票号码, *开票日期\n" +
                "2000-00-00, *校验码]";
    }
}
