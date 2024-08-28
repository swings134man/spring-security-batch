//package com.example.makeproject.batch.domain_detail.step.exit_status;
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
// * @info : Exit Status - Custom
// * @name : CustomExitStatus
// * @date : 2023/01/26 11:22 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - Exit status 를 사용자 정의(Custom) 하기.
// * - 사용자 정의 Exit Status 에 따라 Flow 지정하기.
// *
// * - Listner에서 Custom ExitStatus 를 정의해줄 수 있다.
// *
// * *** 흐름 : step1() FAILED -> step2() -> step2의 ExitStatus 가 PASS 라면 -> batch stop
// * -> 정상 작동시 : JobExecution=STOPPED(성공했다면 stop() 메서드가 존재하기 떄문.), stpe2() -> StepExecution.ExitCode=PASS, stepExecution.status=COMPLETED(스텝은 완료되었기 때문.)
// *
// * -----
// * - to, on에서 정의한 상태값 EX(PASS)가 값이 맞지않는다면 실패한것으로 간주하여 -> JobExecution = FAILED 가 되어버림.
// *   ---> from() 정의하여 해결 할 수 있으나, 해당 클래스의 공부 취지에 맞지않음(여기선 Custom status code를 체크해야 하기 때문.)
// *
// * - AbstractStep(step 최상위) 내부에 finally 안에 ExitStatus 를 재정의(afterStep()내부에서)
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class CustomExitStatus {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("cus_status")
//                .start(step1())
//                    .on("FAILED")
//                    .to(step2())
//                    .on("PASS")
//                    .stop()
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step1 has executed");
//                    // FAILED 처리를 위해 -> ExitStatus 를 FAILED 로 셋팅.
//                    contribution.getStepExecution().setExitStatus(ExitStatus.FAILED);
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
//                // step2()가 종료되고 나면 -> Listener 에서 Exit Status -> PASS 지정해줌.
//                .listener(new PassCheckingListener())
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
//}
