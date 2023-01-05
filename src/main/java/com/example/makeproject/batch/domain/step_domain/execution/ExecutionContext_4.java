//package com.example.makeproject.batch.step_domain.execution;
//
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///************
// * @info : Execution Context 관련 클래스
// * @name : ExecutionContext_4
// * @date : 2023/01/05 6:23 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - Tasklet에 인자로 주어지는 StepConfigu, 청크컨텍스트 의 인자값을 뽑아서 StepExecution 을 가져오고
// * - Step Execution에서 -> Job Execution 데이터 참조하는 방법
// * - Job - Execution Context 뽑아오기
// * - Step 간 데이터 공유 방법.
// * - DB 에 저장되어있는 map 확인.
// *
// * - 실행 순서 - > Task3 에서 예외 -> DB 확인 -> 예외 X -> 재실행
// ************/
//
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class ExecutionContext_4 {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    // Custom Tasklet
//    private final ExecutionTasklet1 tasklet1;
//    private final ExecutionTasklet2 tasklet2;
//    private final ExecutionTasklet3 tasklet3;
//    private final ExecutionTasklet4 tasklet4;
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("executionContextJob")
//                .start(step1())
//                .next(step2())
//                .next(step3())
//                .next(step4())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet(tasklet1)
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet(tasklet2)
//                .build();
//    }
//
//    @Bean
//    public Step step3() {
//        return stepBuilderFactory.get("step3")
//                .tasklet(tasklet3)
//                .build();
//    }
//
//    @Bean
//    public Step step4() {
//        return stepBuilderFactory.get("step4")
//                .tasklet(tasklet4)
//                .build();
//    }
//
//
//
//}
