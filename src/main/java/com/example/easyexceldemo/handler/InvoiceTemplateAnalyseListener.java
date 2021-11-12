package com.example.easyexceldemo.handler;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.easyexceldemo.bo.InvoiceTemplateBO;
import com.example.easyexceldemo.bo.InvoiceTemplateImportRspBO;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.*;

@Slf4j
public class InvoiceTemplateAnalyseListener  extends AnalysisEventListener<InvoiceTemplateBO> {

    private  List<InvoiceTemplateBO> invoiceList = new ArrayList<>();

    private  StringBuilder validMsg = new StringBuilder();

    private StringBuilder invalidInvoiceMsg = new StringBuilder();

    private static String templateHead = InvoiceTemplateBO.getTemplateHead();

    private static String TEMPLATE_ERROR = "导入模板有误";

    public InvoiceTemplateImportRspBO getImportResult(){
        return new InvoiceTemplateImportRspBO(invoiceList,invalidInvoiceMsg.toString());
    }

    //校验模板表头
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        if(4 != headMap.size()){
            throw new RuntimeException(TEMPLATE_ERROR);
        }
        String importHead = headMap.values().toString();
        log.info("templateHead:{}",importHead);
        if(!templateHead.equals(importHead)){
            throw new RuntimeException(TEMPLATE_ERROR);
        }
    }

    @Override
    public void invoke(InvoiceTemplateBO data, AnalysisContext context) {
        log.info("读取到行数据："+data);
        //校验参数。 通过:data放入invoiceList ；不通过:异常信息放入validMsg
        validInvoiceParam(data,context);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //validMsg不为空抛出异常；为空 整理参数，查询发票是否有效
        if(0 == validMsg.length()){
            log.info("校验验证通过集合："+invoiceList);
            verifyInvoiceValidity(invoiceList);
        }else {
            invoiceList.clear();
            throw new RuntimeException(validMsg.toString());
        }
    }

    //校验参数。 通过:data放入invoiceList ；不通过:异常信息放入validMsg
    private void validInvoiceParam(InvoiceTemplateBO target,AnalysisContext context){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<InvoiceTemplateBO>> constraintViolations = validator.validate(target, Default.class);
        Iterator<ConstraintViolation<InvoiceTemplateBO>> iterator = constraintViolations.iterator();
        StringBuilder constraintViolationMsg = new StringBuilder();
        while (iterator.hasNext()){
            ConstraintViolation<InvoiceTemplateBO> constraintViolationItem = iterator.next();
            constraintViolationMsg.append(constraintViolationItem.getMessage());
        }
        if(0 == constraintViolationMsg.length()){
            invoiceList.add(target);
        }else {
            validMsg.append("发票第").append(context.getCurrentRowNum()+1).append("行").append(constraintViolationMsg).append("。");
        }
    }

    //校验发票有效性。有效：放入invoiceList。无效：组合 invalidInvoiceMsg
    private void verifyInvoiceValidity(List<InvoiceTemplateBO> invoiceList){
        //todo Constructor注入校验service，调用方法，处理返回值
        if(true){
            //验证通过，
        }else {
            //验证不通过，invoiceList remove无效记录。invalidInvoiceMsg添加无效信息
        }
    }

}
