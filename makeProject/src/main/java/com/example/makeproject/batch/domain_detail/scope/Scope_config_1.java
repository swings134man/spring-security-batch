//package com.example.makeproject.batch.domain_detail.scope;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.JobScope;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///************
// * @info : Batch - Scope 예제 conifg class
// * @name : Scope_config_1
// * @date : 2023/03/02 2:23 AM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @param : message="spring run settings(batch paramter)"
// * @Description :
// *
// * - Scope 란 : Spring Container에서 관리되는 Bean의 범위
// *
// * - JobScope, StepScope는 사용시점에 Bean이 생성된다.
// * - 프록시 모드로 생성됨 -> 어플리케이션 실행 시점.
// *      -> 실행 시점에 빈을 호출한다.
// *
// * - 즉 실행시점(동적)인 @Value를 사용하려면 @JobScope, @StepScope를 필수적으로 선언해야 한다. (선언해야지만 표현식에 해당하는 값을 참조할 수 있음.)
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class Scope_config_1 {
//
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("job_scope_config")
//                .start(step1(null))
//                .next(step2())
//                .listener(new JobListenerScope())
//                .build();
//    }
//
//
//    /**
//     * This is step1() for Job(jobname=job_scope_config), jobParameters=message(String),
//     * 만약 @JobScope를 선언하지 않으면 jobParamter를 찾을수 없다는 error 발생.
//     * @param message(String): before start(profile settings)
//     * @return
//     */
//    @Bean
//    @JobScope
//    public Step step1(@Value("#{jobParameters['message']}") String message) {
//
//        System.out.println(">>> step1 message= " + message); // parameter
//
//        return stepBuilderFactory.get("step1")
//                .tasklet(tasklet1(null))
//                .build();
//    }
//
//    /**
//     * this is step2()
//     * And add StepListener.
//     * @return
//     */
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet(tasklet2(null))
//                .listener(new StepListenerScope())
//                .build();
//    }
//
//    /**
//     * This is Tasklet For step1(), JobListener에 의해 Parameter 받는다.(name{String})
//     *
//     * @param JobExectionContext: name(String)
//     * @return
//     */
//    @Bean
//    @StepScope
//    public Tasklet tasklet1(@Value("#{jobExecutionContext['name']}") String name) {
//        System.out.println(">>> tasklet1 name = "  + name);
//        return (contribution, chunkContext) -> {
//            System.out.println("Tasklet1 has executed By (Step1)");
//            return RepeatStatus.FINISHED;
//        };
//    }// tasklet1
//
//    /**
//     * This is Taskelt For step2(), StepListener에 의해 paramter를 받는다.(name2{String})
//     * 만약 @StepScope를 선언하지 않는다면, step Executions 을 찾을 수 없다는 에러 발생. (@Value를 사용하기 때문임.)
//     * @param stepExecutionContext: name2(String)
//     * @return
//     */
//    @Bean
//    @StepScope
//    public Tasklet tasklet2(@Value("#{stepExecutionContext['name2']}") String name2) {
//        System.out.println(">>> tasklet2 name2 = " + name2);
//        return (contribution, chunkContext) -> {
//            System.out.println("Tasklet1 has executed By (Step2)");
//            return RepeatStatus.FINISHED;
//        };
//    }// tasklet2
//
//
//}
