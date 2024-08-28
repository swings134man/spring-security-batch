package com.example.makeproject.batch.practice.scope_test;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/************
 * @info : Test Batch - Job Listener Class
 * @name : CustomScopeJobListener
 * @date : 2023/03/02 7:03 PM
 * @author : SeokJun Kang(swings134@gmail.com)
 * @version : 1.0.0
 * @Description :
 ************/
public class CustomScopeJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        // JOB 실행전 - JobExecutionContext Set
        jobExecution.getExecutionContext().putString("jobEC", "test job 1");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
