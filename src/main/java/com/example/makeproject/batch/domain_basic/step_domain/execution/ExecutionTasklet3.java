package com.example.makeproject.batch.domain_basic.step_domain.execution;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/************
 * @info : Execution Context 커스텀 Tasklet 3
 * @name : ExecutionTasklet3
 * @date : 2023/01/05 6:31 PM
 * @author : SeokJun Kang(swings134@gmail.com)
 * @version : 1.0.0
 * @Description :
 * - ExecutionContext_4.java 파일의 커스텀 Tasklet
 *
 ************/
@Component
public class ExecutionTasklet3 implements Tasklet {

    // Exception 을 던져서 job을 재실행 할 수 있는 상황으로 만듬. -> step4 로 넘어가지 않고 job = failed
    // 실패한 지점에 데이터를 가져올 수 있는지?
    // 만약 Exception 을 안던지고 재실행 할시 -> 실패한 데이터를 다시 활용 할 수 있는지 ? -> 1번 시도는 실패했지만 데이터는 저장되어있고 -> 그 데이터를 활용 가능한지?
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        System.out.println(" ------------- step3 was executed ------------- ");

        // Step 간 공유를 위한 -> 임의의 데이터(null) -> null 이면 저장. ()
        Object name = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("name");
        if(name == null) {
           chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("name", "user1");

            // 예외가 터지는 타이밍에 -> JOb_Excution은 Fail, Step_Execution 에는 step3만 Fail, step4 는 없음.
            //
//            throw new RuntimeException("step3 was failed");
        }


        return RepeatStatus.FINISHED;
    }
}
