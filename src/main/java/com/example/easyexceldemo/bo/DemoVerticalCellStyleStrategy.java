package com.example.easyexceldemo.bo;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.AbstractVerticalCellStyleStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

public class DemoVerticalCellStyleStrategy extends AbstractVerticalCellStyleStrategy {
    @Override
    protected WriteCellStyle headCellStyle(Head head) {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //字体
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 11);
        headWriteFont.setBold(true);
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
        return headWriteCellStyle;
    }

    @Override
    protected WriteCellStyle contentCellStyle(Head head) {
        Integer columnIndex = head.getColumnIndex();
        if(3 == columnIndex){
            WriteCellStyle bodyWriteCellStyle = new WriteCellStyle();
            //设置数据格式索引
            bodyWriteCellStyle.setDataFormat((short)49);
            return bodyWriteCellStyle;
        }else {
            return new WriteCellStyle();
        }
    }
}
