package org.example.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.entity.audit.Audit;
import org.example.model.repository.AuditRepository;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.Method;

public class AuditAspect {

    @Pointcut("within(@org.example.aop.annotations.Audit *) && execution(* *(..))")
    public void annotatedByAudit() {

    }

    @After("annotatedByAudit()")
    public Audit audit(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String value = method.getAnnotation(org.example.aop.annotations.Audit.class).value();

        Audit audit = new Audit(value);
        AuditRepository.addAudit(audit);
        return audit;
    }
}
