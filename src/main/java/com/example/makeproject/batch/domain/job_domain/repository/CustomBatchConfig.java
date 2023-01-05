//package com.example.makeproject.batch.domain.job_domain.repository;
//
//
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
//import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
//import org.springframework.boot.autoconfigure.batch.BatchProperties;
//import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
///************
// * @info : batch Custom 설정 클래스
// * @name : CustomBatchConfig
// * @date : 2023/01/06 3:00 AM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - 원래라면 Batch가 초기화 될때 BasicBatchConfigure에서 createJobRepository(기본값)을 하지만 아래의 설정을 하면 커스텀한 설정대로 생성하게 된다.
// *
// * - JobRepository bean이 초기화 될떄 설정 하게 되는데 그떄 커스텀하는 방법.
// * - 여기서는 BasicBatchConfigurer 상속해서 사용함.
// *   -> 설정클래스이면서 다른 여러가지 메서드가 존재함.
// *
// ************/
//@Configuration
//public class CustomBatchConfig extends BasicBatchConfigurer {
//
//    private final DataSource dataSource;
//
//
//    protected CustomBatchConfig(BatchProperties properties, DataSource dataSource, TransactionManagerCustomizers transactionManagerCustomizers) {
//        super(properties, dataSource, transactionManagerCustomizers);
//        this.dataSource = dataSource;
//    }
//
//    // create job repository
//    @Override
//    protected JobRepository createJobRepository() throws Exception {
//
//        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//        factory.setDataSource(dataSource);
//        factory.setTransactionManager(getTransactionManager());
//        factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
//        factory.setTablePrefix("SYSTEM_");
//
//        return factory.getObject();
//    }
//}
