//package com.example.makeproject.batch.first_job;
//
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///************
// * @info : Job Paramter Config class Runner
// * @name : JobParameterRunner
// * @date : 2022/12/29 7:11 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// *  * this is for JobParamter.class Runner
// *
// * - yml batch.job.enable = false
// * - DATE 값을 안주면 DATE_VAL 값은 기본값이 들어간다.
// *
// ************/
//@Component
//public class JobParameterRunner implements ApplicationRunner {
//
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private Job job;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("name", "user1")
//                .addLong("seq", 2L)
//                .addDate("date", new Date())
//                .addDouble("age", 16.5)
//                .toJobParameters();
//
//        jobLauncher.run(job, jobParameters);
//    }
//
//
//}
