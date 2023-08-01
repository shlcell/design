package com.study.design.auditlog;

import com.study.design.auditlog.pojo.AuditLog;
import org.springframework.stereotype.Component;

@Component
public class OrderLogProcessor extends AbstractAuditLogProcessor{
    @Override
    protected AuditLog buildDetails(AuditLog auditLog) {
        String orderId = auditLog.getOrderId();
        String productDetails = "通过 orderId 获取";
        auditLog.setDetails(productDetails);
        System.out.println(auditLog);
        return auditLog;
    }
}
