package com.mall.common.schedule;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/9/12 14:24
 */
public class MyCronJob extends QuartzJobBean {


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("任务执行了" + new Date());

    }
}

