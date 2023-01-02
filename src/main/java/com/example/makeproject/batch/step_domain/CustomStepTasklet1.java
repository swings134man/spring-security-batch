//package com.example.makeproject.batch.step_domain;
//
//
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//
///************
// * @info : StepConfiguration 의 Tasklet Custom CLASS
// * @name : CustomStepTasklet
// * @date : 2023/01/02 5:12 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * Bean 객체를 사용해서 무언가 하려하면 @Component 주입
// ************/
//public class CustomStepTasklet1 implements Tasklet {
//    @Override
//    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//
//        System.out.println("step3 was executed By Custom");
//
//        return RepeatStatus.FINISHED;
//    }
//}
