package com.example.makeproject.batch.practice.scope_test;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;


/************
 * @info : Test batch - Step Listener Class
 * @name : CustomScopeStepListener
 * @date : 2023/03/02 7:11 PM
 * @author : SeokJun Kang(swings134@gmail.com)
 * @version : 1.0.0
 * @Description :
 ************/
public class CustomScopeStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        // step 실행전.
        stepExecution.getExecutionContext().putString("stepEC", "test step 2");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
