package com.example.makeproject.batch.domain_detail.job.execution_decider;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/************
 * @info : Batch - Decider 구현을 위한, JobExecutionDecider 구현체.
 * @name : CustomDecider
 * @date : 2023/03/01 6:19 PM
 * @author : SeokJun Kang(swings134@gmail.com)
 * @version : 1.0.0
 * @Description :   JobExecutionDeciderConfig.class -> decider() 에서 return됨.
 *
 *  - count가 짝수이면 -> EVEN, 홀수이면 -> ODD Return
 ************/
public class CustomDecider implements JobExecutionDecider {

    private int count = 1; // if 0=ODD, 1=EVEN


    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {

        // Logic
        count++;

        if(count%2 == 0){ //count를 2로 나누었을떄 나머지가 0 이면 -> 짝수이면
            return new FlowExecutionStatus("EVEN");
        }else {
            return new FlowExecutionStatus("ODD");
        }

    }
}
