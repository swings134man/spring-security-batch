//package com.example.makeproject.batch.domain_basic.job_domain.launcher;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.launch.support.SimpleJobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
///************
// * @info : Batch - Launcher For Test Controller class
// * @name : JobLauncherController
// * @date : 2023/01/06 10:29 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - 배치 job 작업을 - > 동기적, 비동기적 실행 테스트를 위한 컨트롤러 클래스
// * - Batch File -> JobLauncherConfig
// ************/
//@RestController
//public class JobLauncherController {
//
//    @Autowired
//    private Job job;
//
//    @Autowired
//    private JobLauncher jobLauncher; // Proxt 객체 생성됨.
//
//    //비동기 방식을 위한 sett
//    @Autowired
//    private BasicBatchConfigurer basicBatchConfigurer; // Proxy 객체의 실제 객체를 가지고 있음.
//
//    // 동기적 방식
//    // Client가 파라미터를 주고 -> batch에서 해당 파라미터를 저장해서 활용 함.
//    // batch 작업이 모두 끝난후 return 됨으로 -> THread.sleep(3s) 으로 인해서 3초 이후에 반환됨.
//    @PostMapping("/batch/launch/sync")
//    public String launch(@RequestBody JobMember member) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//
//        // job parameters
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("id", member.getId())
//                .addDate("date", new Date())
//                .toJobParameters();
//
//        // Job 실행.
//        jobLauncher.run(job, jobParameters);
//        return "batch completed";
//    } // sync
//
//
//    // 비동기적 방식
//    // - 동기방식 보다 3초 빠르게 설정되어있음 -> Thread.sleep -> 왜냐하면 batch 결과를 기다리는것이 아닌 JobExecution 객체를 바로 반환하기 때문.
//    @PostMapping("/batch/launch/async")
//    public String asyncLaunch(@RequestBody JobMember member) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//
//        // job parameters
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("id", member.getId())
//                .addDate("date", new Date())
//                .toJobParameters();
//
//        // 비동기 배치실행
//        // simple 잡 런처에 있는 메서드를 사용하기 위해서는 수동으로 아래와 같이 작업 할 수 있다.
//        SimpleJobLauncher simpleJobLauncher = (SimpleJobLauncher)basicBatchConfigurer.getJobLauncher();
//        simpleJobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
//
//        // Job 실행.
//        jobLauncher.run(job, jobParameters);
//
//        return "async batch completed";
//    } // sync
//
//
//}
