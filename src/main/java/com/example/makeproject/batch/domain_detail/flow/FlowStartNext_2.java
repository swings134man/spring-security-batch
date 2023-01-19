//package com.example.makeproject.batch.domain_detail.flow;
//
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//
///************
// * @info : Flow Job 구성과 start, next() 메서드 사용 법.
// * @name : FlowStartNext_2
// * @date : 2023/01/19 12:14 AM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - 조건을 주지 않고 Flow 별로 실행 시키는 간단한 예제임.
// *
// * - Step을 Flow별로 분리, 구성하는 장점은 있지만 , Step중 하나라도 실패할 경우 전체 Job이 실패하는 규칙은 동일(SimpleJob)
// * ---> 이유는 : on 메서드가 존재하지 않아서 조건 별로 전환 실행 하는것이 아니기 때문.
// *
// * - 예제 순서 : JBF 실행(Flow){FlowJobBuilder} -> Flow A 실행 (step1 -> step2) -> StepA 실행 -> Flow B 실행(step3 -> step4) -> StepB 실행
// * - 해당 코드 순서는 다름
// *
// * -> 최종 1~6 순서
// ************/
//
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class FlowStartNext_2 {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("flow_last_job")
//                .start(flowA())
//                .next(step3())
//                .next(flowB())
//                .next(step6())
//                .end()
//                .build();
//    }
//
//    // ------------------------------ Flow ------------------------------
//    // Simple Flow 객체 생성(Flow 객체의 구현체는)
//    @Bean
//    public Flow flowA() {
//        FlowBuilder<Flow> flowFlowBuilder = new FlowBuilder<>("flowA");
//        flowFlowBuilder.
//                start(step1())
//                .next(step2())
//                .end();
//        return flowFlowBuilder.build();
//    }
//
//    @Bean
//    public Flow flowB() {
//        FlowBuilder<Flow> flowFlowBuilder = new FlowBuilder<>("flowB");
//        flowFlowBuilder
//                .start(step4())
//                .next(step5())
//                .end();
//        return flowFlowBuilder.build();
//    }
//    // ------------------------------ step ------------------------------
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
//                    // 4까지 Excute, 4 = FAILED, JOB = FAILED
//                    // throw new RuntimeException("step4 has Exception");
//                    // Flow 잡일떄 on("FAILED").to(step2()).end() ---> 이런식으로 ExitStatus 를 활용 할 수 있다.
////                    contribution.setExitStatus(ExitStatus.FAILED); // 배치 스텝 Execution에서 ExitCode를 FAILED 로 설정함. 하지만 STAUTS 자체는 성공이다.
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
//    @Bean
//    public Step step6() {
//        return stepBuilderFactory.get("step6")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step6 has executed");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//}
