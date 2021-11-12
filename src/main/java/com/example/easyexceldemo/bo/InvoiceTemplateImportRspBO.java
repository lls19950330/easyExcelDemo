package com.example.easyexceldemo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 发票导入返回值 <br>
 * @author lls
 * @version 1.0.0
 * @date 2021/11/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceTemplateImportRspBO {
    //验证有效的发票信息
    private List<InvoiceTemplateBO> invoiceList;
    //验证无效的发票信息 提示
    private String invalidMsg;

}
