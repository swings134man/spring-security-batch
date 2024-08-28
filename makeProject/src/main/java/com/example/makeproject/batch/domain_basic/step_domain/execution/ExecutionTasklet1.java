package com.example.makeproject.batch.domain_basic.step_domain.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/************
 * @info : Execution Context 커스텀 Tasklet 1
 * @name : ExecutionTasklet1
 * @date : 2023/01/05 6:31 PM
 * @author : SeokJun Kang(swings134@gmail.com)
 * @version : 1.0.0
 * @Description :
 *
 *  - ExecutionContext_4.java 파일의 커스텀 Tasklet
 ************/
@Component
@Slf4j
public class ExecutionTasklet1 implements Tasklet {

    // contribution 안에 Step Execution을 참조 해서 execution context객체를 활용하는 방법.
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("step1 was executed");

        // Step Execution에서 Execution Context 뽑아내기
        // Step Execution 내부의 Job Execution 꺼내서 Job Execution Context 도 꺼내보기.

        // Job Context
        ExecutionContext jobExeContext = contribution.getStepExecution().getJobExecution().getExecutionContext();

        // Step Context
        ExecutionContext stepExeContext = contribution.getStepExecution().getExecutionContext();

        // job Exe Context 에 job name 을 put으로 저장 -> jobExeContext
        String jobName = chunkContext.getStepContext().getStepExecution().getJobExecution().getJobInstance().getJobName();

        // step Exe Context 에 step name 을 put으로 저장 -> stepExeContext
        String stepName = chunkContext.getStepContext().getStepExecution().getStepName();

        // Context 내부의 잡네임, 값을 저장한 적이 없는 경우
        if(jobExeContext.get("jobName") == null) {
            jobExeContext.put("jobName", jobName);
        }

        //  Step
        if (stepExeContext.get("stepName") == null) {
            stepExeContext.put("stepName", stepName);
        }

        // --------- 출력 부분
        System.out.println("jobName : " + jobExeContext.get("jobName"));
        System.out.println("stepName : " + stepExeContext.get("stepName"));



        return RepeatStatus.FINISHED;
    }
}
