package com.mall.common.aspect;

import java.net.InetAddress;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.mall.common.mongodb.model.ExceptionLogVO;
import com.mall.common.mongodb.repo.ExceptionLogRepo;
import com.mall.common.util.exception.ExceptionUtils;
import com.mall.common.util.exception.RRException;
import com.mall.common.util.pattern.StringToolkit;


/**
 * 异常日志处理切面
 * 在线程级别保存应用编码、交易流水号、操作用户编码
 * @author King chen
 * @emai 396885563@qq.com
 * @date 2018年3月30日
 */
//@Aspect
//@Order(5)
//@Component
public class ExceptionAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExceptionLogRepo exceptionLogRepo;
    private static String ipAddress = "127.0.0.1";
    private static Configuration configs ;

    @AfterThrowing(pointcut="execution(* com.mall.common.base.service.*.*(..)) || execution(* com.mall.forum.service.*.*(..))", throwing = "e")
    public void afterThrowing(JoinPoint  point,Exception e) throws Throwable  {
        String serialNo =UUID.randomUUID().toString();
        String appcode=configs.getString("serverName");
        addExceptionLog(ExceptionUtils.makeStackTrace(e), point,appcode!=null?appcode.toString():null, serialNo);
        logger.error(String.format("异常流水号【%s】", serialNo)+String.format("服务【%s】", appcode)+String.format("方法【%s】异常！", point.getSignature()));
        throw new RRException(String.format("服务调用时【%s】发生未知异常，异常流水号【%s】，请联系管理员", appcode,serialNo),500,e);

    }

    private void addExceptionLog(String errMsg, JoinPoint joinPoint,String appcode, String serialNo) throws Throwable {
        try {
            ExceptionLogVO vo = new ExceptionLogVO();

            String apiName = joinPoint.getTarget().getClass().getName() + "#" + joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            try {
                String params = new Gson().toJson(args[0]);
                vo.setOutputData(StringToolkit.getObjectString(joinPoint.getTarget()));
                vo.setInputData(params);
            } catch (Exception e) {

            }
            String logCode = appcode + "-" + DateTimeUtils.currentTimeMillis();
            vo.setAppCode(appcode);
            vo.setSeriaNo(serialNo);
            vo.setApiName(apiName);
            vo.setCreateTime(new Date());
            vo.setIp(ipAddress);
            vo.setLogCode(logCode);
            vo.setExceptionMsg(errMsg);
//            exceptionLogRepo.insert(vo);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //本机IP
    static {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ipAddress = inetAddress.getHostAddress();
            configs = new PropertiesConfiguration("settings.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}