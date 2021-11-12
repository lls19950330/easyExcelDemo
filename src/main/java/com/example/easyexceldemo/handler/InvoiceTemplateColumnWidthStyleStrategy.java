package com.example.easyexceldemo.handler;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.style.column.AbstractHeadColumnWidthStyleStrategy;

/**
 * 自动列宽策略 <br>
 * @author lls
 * @version 1.0.0
 * @date 2021/11/10
 */

public class InvoiceTemplateColumnWidthStyleStrategy extends AbstractHeadColumnWidthStyleStrategy {

    private static InvoiceTemplateColumnWidthStyleStrategy columnWidthStyleStrategy;

    private InvoiceTemplateColumnWidthStyleStrategy(){}

    public static InvoiceTemplateColumnWidthStyleStrategy getInstance(){
            if(null == columnWidthStyleStrategy){
                columnWidthStyleStrategy = new InvoiceTemplateColumnWidthStyleStrategy();
            }
            return columnWidthStyleStrategy;
    }

    @Override
    protected Integer columnWidth(Head head, Integer columnIndex) {
        return Integer.valueOf(30);
    }
}
