package com.example.makeproject.batch.domain_detail.scope;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/************
 * @info : batch - job Listener for Scope_config_1.class
 * @name : JobListenerScope
 * @date : 2023/03/02 6:13 PM
 * @author : SeokJun Kang(swings134@gmail.com)
 * @version : 1.0.0
 * @Description :
 ************/
public class JobListenerScope implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        // 실제 takslet이 실행되기전에 해당 코드가 먼저 실행 됨으로써
        // Execution context에 name 값이 저장된다.
        jobExecution.getExecutionContext().putString("name", "testUset1");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
