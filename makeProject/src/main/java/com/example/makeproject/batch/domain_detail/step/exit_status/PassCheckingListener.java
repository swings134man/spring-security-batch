package com.example.makeproject.batch.domain_detail.step.exit_status;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/************
 * @info : Custom Exit Status - 구현하기 위한 Listener
 * @name : PassCheckingListener
 * @date : 2023/01/26 11:36 PM
 * @author : SeokJun Kang(swings134@gmail.com)
 * @version : 1.0.0
 * @Description : 
 ************/
public class PassCheckingListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {


        String exitCode = stepExecution.getExitStatus().getExitCode();// Exit Code

        // FAILED 가 아니면? -> step2() 의 종료코드가 FAILED 가 아닐 경우.
        if(!exitCode.equals(ExitStatus.FAILED.getExitCode())) {
            return new ExitStatus("PASS");
        }


        return null;
    }
}
