//package com.example.makeproject.batch.practice.scope_test;
//
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
///************
// * @info : Test Batch - Scope Runner Class
// * @name : ScopeJobRunner
// * @date : 2023/03/02 7:13 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// ************/
//@Component
//public class ScopeJobRunner {
//
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private ScopeJobConfig jobConfig;
//
//    public void runjob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("startKey", "LucasKang")
//                .toJobParameters();
//
//        jobLauncher.run(jobConfig.scopeJob(), jobParameters);
//    }
//}
