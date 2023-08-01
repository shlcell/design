package com.study.design.auditlog;

import com.study.design.auditlog.pojo.AuditLog;
import org.springframework.stereotype.Component;

@Component
public class LoginLogProcessor extends AbstractAuditLogProcessor{
    @Override
    protected AuditLog buildDetails(AuditLog auditLog) {
        return auditLog;
    }
}
