//package com.example.makeproject.batch.domain_detail.flow;
//
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
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
// * @info : Flow Job 기본 개념 클래스
// * @name : FlowJobConfig_basic
// * @date : 2023/01/18 11:40 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - step1의 성공/ 실패 여부에 따라 3 , 2 로 이동
// * - step1 이 성공해도 , step3 가 COMPLETED 되었기 때문에 Job Execution 또한 COMPLETED
// * - > 하지만 step1은 ABANDONED 상태가 됨.
// *
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class FlowJobConfig_basic {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    // step1 성공 -> step3
//    // 실패 -> step2
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("flow_job")
//                .start(step1())
//                .on("COMPLETED")
//                .to(step3())
//                .from(step1())
//                .on("FAILED")
//                .to(step2())
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet((contribution, chunkContext) -> {
////                    System.out.println("step1 has executed");
//                    throw new RuntimeException("step1 Failed");
////                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step2 has executed");
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
//
//
//}
