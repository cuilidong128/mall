package com.mall.config;

import com.mall.common.schedule.MyCronJob;
import com.mall.common.schedule.MyJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;


/**
 * 定时任务配置

 */
// @Configuration
public class QuartzConfiguration {
    @Bean
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(MyJob.class).withIdentity("myJob").storeDurably().build();
    }

    @Bean
    public Trigger myJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(15).repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(myJobDetail())
                .withIdentity("myJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public JobDetail myCronJobDetail() {
        return JobBuilder.newJob(MyCronJob.class).withIdentity("myCronJob").storeDurably().build();
    }

    @Bean
    public Trigger CronJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");

        return TriggerBuilder.newTrigger()
                .forJob(myCronJobDetail())
                .withIdentity("myCronJobTrigger")
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}
