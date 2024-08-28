//package com.example.makeproject.batch.domain_detail.flow;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///************
// * @info : Flow Job의 Transition API 들을 활용하는 클래스
// * @name : Flow_Transition_3
// * @date : 2023/01/19 5:39 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - 해당 클래스는 Transition API (on, to, from, end, stop, stopAndRestart 메서드들을 테스트 하는 클래스이다.)
// *
// * - 해당 클래스의 흐름 :
// * --> 1. step1 실행 -> 결과가 FAILED 이면 -> step2() 실행 -> step2()가 FAILED면 Job 중단(stop())
// * --> 2. step1이 FAILED가 아닌 모든 경우 일때 -> Step3() 성공 시 -> step4() -> step4()의 상태가 FAILED이면 Job() 종료 (end())
// *
// * - 흐름 1,2번을 판가름 하는 요소는 2가지
// * 1. step1의 ExitCode = FAILED이냐 아니냐
// * 2. step4의 ExitCode = FAILED이면 = COMPLETED JOB 종료 --> 아니면 BATCH STATUS = FAILED
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class Flow_Transition_3 {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("flow_Transition")
//                .start(step1())
//                    .on("FAILED")
//                    .to(step2())
//                    .on("FAILED")
//                    .stop()
//                .from(step1())
//                    .on("*")
//                    .to(step3())
//                    .next(step4())
//                    .on("FAILED")
//                    .end()
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step1 has executed");
//
//                    // 종료 상태 코드 -> step2()로 가게됨.
//                    contribution.setExitStatus(new ExitStatus("FAILED"));
//
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step2 has executed");
//
//                    // 종료 상태
//                    contribution.setExitStatus(new ExitStatus("FAILED"));
//
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step3() {
//        return  stepBuilderFactory.get("step3")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("step3 has executed");
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step4() {
//        return stepBuilderFactory.get("step4")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step4 has executed");
//
//                    // 종료 코드 -> FAILED 이면 해당 Batch Job  STATUS = COMPLETED
//                    contribution.setExitStatus(new ExitStatus("FAILED"));
//
//
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//}
