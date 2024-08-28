package com.example.makeproject.batch.domain_basic.step_domain.execution;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/************
 * @info : Execution Context 커스텀 Tasklet 4
 * @name : ExecutionTasklet4
 * @date : 2023/01/05 6:31 PM
 * @author : SeokJun Kang(swings134@gmail.com)
 * @version : 1.0.0
 * @Description :
 * - ExecutionContext_4.java 파일의 커스텀 Tasklet
 ************/
@Component
public class ExecutionTasklet4 implements Tasklet {

    // contribution 안에 Step Execution을 참조 해서 execution context객체를 활용하는 방법.
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("step4 was executed");

        System.out.println("name : "+ chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("name"));

        return RepeatStatus.FINISHED;
    }
}
