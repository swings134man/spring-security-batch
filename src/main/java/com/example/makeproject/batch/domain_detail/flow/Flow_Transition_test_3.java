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
// * @info : Flow Job의 Transition API 들을 활용하는 연습 테스트 클래스
// * @name : Flow_Transition_test_3
// * @date : 2023/01/19 6:11 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - Flow_Transition_3.java 파일의 추가적인 연습 클래스임.
// *
// * - 해당 클래스의 흐름
// * 1. step1() -> (FAILED) -> step2() -> (FAILED) -> stop()
// * 2. step1() -> (!FAILED) -> step3() -> step4() -> end()
// * 3. step1() -> (FAILED) -> step2() -> (!FAILED) -> step5() -> end()
// ************/
//
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class Flow_Transition_test_3 {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("flow_transition_test")
//                .start(step1())
//                    .on("FAILED")
//                    .to(step2())
//                    .on("FAILED")
//                    .stop()
//                .from(step1())
//                    .on("*")
//                    .to(step3())
//                    .next(step4())
//                .from(step2())
//                    .on("*")
//                    .to(step5())
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
//                    // Exit code
////                    contribution.setExitStatus(new ExitStatus("FAILED"));
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
//                    // Exit code
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
//
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
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step5() {
//        return stepBuilderFactory.get("step5")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step5 has executed");
//
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//}
