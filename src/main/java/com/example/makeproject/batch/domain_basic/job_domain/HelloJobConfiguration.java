//package com.example.makeproject.batch.first;
//
//import lombok.RequiredArgsConstructor;
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
// * @info : Batch first Test file
// * @name : HelloJobConfiguration
// * @date : 2022/12/27 8:47 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * 실행 순서
// * job 구동 -> job 포함하고 있는 step 실행 -> step 내부의 tasklet 실행.
// *
// ************/
//
///* Job 1개 정의. */
//@Configuration
//@RequiredArgsConstructor
//public class HelloJobConfiguration {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
////    @Bean
////    public Job helloJob(){
////        return jobBuilderFactory.get("helloJob")  // Job 생성
////                .start(helloStep()) // Step 객체를 실행. Step이 필수로 존재해야함.
////                .next(helloStep2()) // Step1이 종료후 step2 진행.
////                .build();
////    }
//
//
//    /*Step 구현체를 만들어줌.*/
//    // Step이 실행될때 tasklet 을 호출. -> tasklet 내부의 비지니스 로직 수행.
//    @Bean
//    public Step helloStep() {
//        return stepBuilderFactory.get("helloStep")
//                .tasklet((contribution, chunkContext) -> { // -> Tasklet : Step 안에서 단일 태스크로 수행되는 로직.
//                    System.out.println("============================");
//                    System.out.println("Hello Spring batch~~~1");
//                    System.out.println("============================");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    /* 2번째 실행할 Step */
//    // 기본적으로 tasklet을 무한 반복 시킴. -> RepeatStatus(상태) 에서 반복할건지, 종료할건지 설정. -> null, RepeatStatus.FINISHED; 종료
//    @Bean
//    public Step helloStep2() {
//        return stepBuilderFactory.get("helloStep2")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//
//                        // 비지니스 로직
//                        System.out.println("============================");
//                        System.out.println("Heloo Spring Batch 2!!!! was excuted");
//                        System.out.println("============================");
//
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }
//
//}// class
