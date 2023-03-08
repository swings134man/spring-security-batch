//package com.example.makeproject.batch.practice.scope_test;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.JobScope;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///************
// * @info : Test Batch - Scope
// * @name : ScopeJobConfig
// * @date : 2023/03/02 6:51 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description : Triger = batchController - batchtest_scope
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class ScopeJobConfig {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job scopeJob() {
//        return jobBuilderFactory.get("test_scope_batch")
//                .start(tStep1(null))
//                .next(tStep2())
//                .listener(new CustomScopeJobListener())
//                .build();
//    }
//
//
//    /**
//     * Step1() is use customTasklet,
//     * And use Custom JobListner - jobEC(String)
//     * @param startKey(JobParam)
//     * @return
//     */
//    @Bean
//    @JobScope
//    public Step tStep1(@Value("#{jobParameters['startKey']}") String startKey) {
//        log.info(">>>>>>>>>(param) step1-jobparameters={}", startKey );
//
//        return stepBuilderFactory.get("tStep1")
//                .tasklet(taskletF1(null))
//                .build();
//    }
//
//    @Bean
//    public Step tStep2() {
//        return stepBuilderFactory.get("tStep2")
//                .tasklet(taskletF2(null))
//                .listener(new CustomScopeStepListener())
//                .build();
//    }
//
//
//    @Bean
//    @StepScope
//    public Tasklet taskletF1(@Value("#{jobExecutionContext['jobEC']}") String jobEC) {
//        log.info(">>>>>>>>>(param) step1-JobExecutionConxtext={}", jobEC);
//
//        return (contribution, chunkContext) -> {
//            log.info(">>>>>>>>> tStep1 has Executed");
//            return RepeatStatus.FINISHED;
//        };
//    }
//
//
//    /**
//     * For tStep2() Tasklet
//     * @param stepEC
//     * @return
//     */
//    @Bean
//    @StepScope
//    public Tasklet taskletF2(@Value("#{stepExecutionContext['stepEC']}") String stepEC) {
//        log.info(">>>>>>>>>(param) step2-stepExecutionConxtext={}", stepEC );
//
//        return (contribution, chunkContext) -> {
//            log.info(">>>>>>>>> tStep2 has Executed");
//            return RepeatStatus.FINISHED;
//        };
//    }
//
//
//}
