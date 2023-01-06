//package com.example.makeproject.batch.domain_detail.job;
//
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///************
// * @info : 잡 빌더 팩토리에 대한 클래스
// * @name : JobBuilderFactoryConfig_1
// * @date : 2023/01/07 2:42 AM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - step 으로만 구성되면 - Simple Job builder -> simple job 객체 생성
// * - Flow 로 구성된다면 -> Flow Job builder -> flow job 객체 생성.
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class JobBuilderFactoryConfig_1 {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    // simple Job
////    @Bean
////    public Job job() {
////        return jobBuilderFactory.get("builderFactoryJob")
////                .start(step1())// simpleJobBuilder 가 생성됨.
////                .next(step2())
////                .build(); // simpleJobBuild 하게됨. -> job = SimpleJob
////    }
//
//    // flow job
//    // job이 flow 를 시작 -> 종료 후 -> step5() 실행시키게 되는 흐름.
//    @Bean
//    public Job job2() {
//        return jobBuilderFactory.get("builderFactoryJob_flow")
//                .start(flow())
//                .next(step5())
//                .end() //flow 잡 구문을 마칠때는 End
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step1 has executed");
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
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//    // ----------------------- Flow Job --------------------------------
//    // FlowJobBuilder 를 통해서 flow 타입의 job이 생성됨.
//    // flow가 start 되면 -> JobFlowBuilder로 이동 -> 각 flow, step을 실행하게 함.
//    @Bean
//    public Flow flow() {
//        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow"); // Step 과 동일레벨, -> FlowJob내부에 Flow가 있음.
//        flowBuilder.start(step3())
//                .next(step4())
//                .end(); // flow는 end 로 끝내줘야함.
//
//        return flowBuilder.build();
//    }
//
//    @Bean
//    public Step step3() {
//        return stepBuilderFactory.get("step3")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step3 has executed");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step4() {
//        return stepBuilderFactory.get("step4")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step4 has executed");
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
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//}
