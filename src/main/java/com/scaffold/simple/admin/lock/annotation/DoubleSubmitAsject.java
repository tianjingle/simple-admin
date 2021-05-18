//package com.scaffold.simple.admin.lock.annotation;
//
//import com.scaffold.simple.admin.lock.curator.ReSubmitLock;
//import com.scaffold.simple.admin.utils.SessionUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//import java.text.MessageFormat;
//import java.util.Objects;
//
//
///**
// * @Author: tianjl
// * @Date: 2020/7/27 10:27
// * @Eamil: 2695062879@qq.com
// */
//@Aspect
//@Configuration
//public class DoubleSubmitAsject {
//
//    private Logger logger = LoggerFactory.getLogger(DoubleSubmitAsject.class);
//    /**
//     * 进行一些操作
//     */
//    @Autowired
//    private ReSubmitLock reSubmitLock;
//
//
//    @Value("${double.submit.token:ztoken}")
//    private String tokenName;
//
//    /**
//     * /**
//     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
//     */
//    @Pointcut("@annotation(com.scaffold.simple.admin.lock.annotation.DoubleSubmitAnnotation)")
//    public void doubleSubmit() {
//    }
//
//    /**
//     * 执行之前进行
//     *
//     * @param joinPoint 切点
//     */
//    @Before(value = "doubleSubmit()")
//    public void doBefore(JoinPoint joinPoint) throws Exception {
//        boolean isRestController = joinPoint.getTarget().getClass().isAnnotationPresent(RestController.class);
//        boolean isController = joinPoint.getTarget().getClass().isAnnotationPresent(Controller.class);
//        if (isRestController | isController) {
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            assert attributes != null;
//            String token = attributes.getRequest().getHeader(tokenName);
//            String userId = SessionUtils.getUserId();
//            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//            Method method = methodSignature.getMethod();
//            DoubleSubmitAnnotation doubleSubmitAnnotation = method.getAnnotation(DoubleSubmitAnnotation.class);
//            if (!StringUtils.isEmpty(token) && !StringUtils.isEmpty(userId)) {
//
//                if (!Objects.isNull(doubleSubmitAnnotation)) {
//                    if (doubleSubmitAnnotation.check()) {
//                        if (!reSubmitLock.check(userId, token)) {
//                            logger.warn(MessageFormat.format("重复提交的表单：{0}：{1}", userId, token));
//                            throw new Exception("重复的表单，请重新提交");
//                        }
//                    }
//                }
//            } else {
//                logger.warn(MessageFormat.format("无效的放重复检验：{0}：{1}", userId, token));
//            }
//            if (doubleSubmitAnnotation.generate()) {
//                SessionUtils.setSubmitToken(reSubmitLock.generateToken(userId));
//            }
//        }
//    }
//
//    /**
//     * 返回打印日志
//     *
//     * @param joinPoint 切点
//     * @param keys      返回的数据
//     */
//    @AfterReturning(value = "doubleSubmit()", returning = "keys")
//    public void saveOperLog(JoinPoint joinPoint, Object keys) {
//    }
//}
