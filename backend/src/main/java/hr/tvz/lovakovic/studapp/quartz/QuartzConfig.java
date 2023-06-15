package hr.tvz.lovakovic.studapp.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    JobDetail printEnrolledStudentsJobDetail() {
        PrintEnrolledStudentsJob.setApplicationContext(applicationContext);
        return JobBuilder.newJob(PrintEnrolledStudentsJob.class)
                .withIdentity("PrintEnrolledStudentsJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger printEnrolledStudentsTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(printEnrolledStudentsJobDetail())
                .withIdentity("PrintEnrolledStudentsTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
