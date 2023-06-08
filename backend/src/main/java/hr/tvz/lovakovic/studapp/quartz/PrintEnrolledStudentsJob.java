package hr.tvz.lovakovic.studapp.quartz;

import hr.tvz.lovakovic.studapp.student.StudentService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

public class PrintEnrolledStudentsJob implements Job {


    // A static field for holding a reference to the application context
    private static ApplicationContext applicationContext;

    // A setter method for the application context
    public static void setApplicationContext(ApplicationContext applicationContext) {
        PrintEnrolledStudentsJob.applicationContext = applicationContext;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        StudentService studentService = applicationContext.getBean(StudentService.class);

        System.out.println("Ovo su trenutno upisani studenti");
        System.out.println("---------------------------------");
        studentService.findAll().forEach(student ->
                System.out.println(student.getJmbag() + " - " + student.getFirstName() + " " + student.getLastName())
        );
        System.out.println("---------------------------------");
    }
}
