//package com.example.makeproject.controller;
//
//import com.example.makeproject.batch.practice.scope_test.ScopeJobRunner;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///************
// * @info : Batch Test Triger Class
// * @name : BatchController
// * @date : 2023/03/02 6:48 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description : 해당 URL 로 요청을 보냈을때 Target batch job 실행.
// ************/
//@RestController
//@RequiredArgsConstructor
//public class BatchController {
//
//    //Batch
//    private final ScopeJobRunner jobRunner;
//
//    @RequestMapping("/scope/test")
//    public String batchtest_scope() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//
//        jobRunner.runjob();
//
//
//        return "Run Batch";
//    }// batchtest_scope1
//}
