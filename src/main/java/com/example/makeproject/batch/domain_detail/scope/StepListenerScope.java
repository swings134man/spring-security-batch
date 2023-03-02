package com.example.makeproject.batch.domain_detail.scope;


import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/************
 * @info : batch - step listener for Scope_config_1
 * @name : StepListenerScope
 * @date : 2023/03/02 6:16 PM
 * @author : SeokJun Kang(swings134@gmail.com)
 * @version : 1.0.0
 * @Description :
 ************/
public class StepListenerScope implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        // 해당 Step이 실행되기전에 name2=testUser2 라는 값을, stepExecutionContext에 저장한다.
        stepExecution.getExecutionContext().putString("name2", "testUser2");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
