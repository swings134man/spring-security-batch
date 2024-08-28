package com.example.makeproject.batch.domain_basic.step_domain.execution;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/************
 * @info : Execution Context 커스텀 Tasklet 2
 * @name : ExecutionTasklet2
 * @date : 2023/01/05 6:31 PM
 * @author : SeokJun Kang(swings134@gmail.com)
 * @version : 1.0.0
 * @Description :
 * - ExecutionContext_4.java 파일의 커스텀 Tasklet
 ************/
@Component
public class ExecutionTasklet2 implements Tasklet {

    // step1 종료 후 실행
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println(" ------------- step2 was executed ------------- ");


        ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
        ExecutionContext stepExecutionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();

        System.out.println("jobName : " + jobExecutionContext.get("jobName")); // 공유 가능! 값 나옴
        System.out.println("stepName : " + stepExecutionContext.get("stepName")); // 공유 불가! - 값 안나옴.

        String stepName = chunkContext.getStepContext().getStepExecution().getStepName();

        // step1의 stepName 공유불가로 -> null 이면 -> 2의 스텝네임을 풋.
        // step2 의 Excution Context에 해당 step name 이 저장됨.
        if(stepExecutionContext.get("stepName") == null) {
            stepExecutionContext.put("stepName", stepName);
        }


        return RepeatStatus.FINISHED;
    }
}
