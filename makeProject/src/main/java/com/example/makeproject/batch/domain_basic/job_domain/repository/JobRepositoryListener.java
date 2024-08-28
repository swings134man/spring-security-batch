//package com.example.makeproject.batch.domain.job_domain.repository;
//
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///************
// * @info : Job Repository Listener
// * @name : JobRepositoryListner
// * @date : 2023/01/06 2:30 AM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - Job 실행 전후로 이벤트를 받을 수 있는 JobExecution Listener
// * - 정보를 가져오는 로직이 들어있음.
// *
// * - 2번쨰 실행 떄 파라미터 값 바꿔야함.
// *
// ************/
//@Component
//public class JobRepositoryListener implements JobExecutionListener {
//
//    @Autowired
//    private JobRepository jobRepository;
//
//    @Override
//    public void beforeJob(JobExecution jobExecution) {
//
//    }
//
//    @Override
//    public void afterJob(JobExecution jobExecution) {
//        //잡이 수행이 된 이후에 실행되는 Method
//        String jobName = jobExecution.getJobInstance().getJobName(); // Job name
//
//        // job Parameter 생성.
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("requestDate", "20230106").toJobParameters();
//
//        // 마지막 실행된 잡 info
//        JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, jobParameters);//Parameter : DB 에 이미 저장되어있는 값이어야 한다.
//        if(lastJobExecution != null) {
//            // job Execution 내부의 step Execution 가져오기
//            for (StepExecution execution : lastJobExecution.getStepExecutions()) {
//                BatchStatus status = execution.getStatus();// 상태
//                ExitStatus exitStatus = execution.getExitStatus(); // 종료 상태
//                String stepName = execution.getStepName(); //step name
//
//                System.out.println("status : " + status);
//                System.out.println("Exit status : " + exitStatus);
//                System.out.println("step name : " + stepName);
//            }
//        }
//
//    } // after Job
//}
