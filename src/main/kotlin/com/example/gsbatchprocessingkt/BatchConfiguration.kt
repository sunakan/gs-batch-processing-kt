package com.example.gsbatchprocessingkt

//import com.example.gsbatchprocessingkt.component.JobCompletionNotificationListener
//import org.springframework.batch.core.Job
//import org.springframework.batch.core.Step
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
//import org.springframework.batch.core.launch.support.RunIdIncrementer
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//
//@Configuration
//@EnableBatchProcessing
//class BatchConfiguration {
//    @Bean
//    fun job1(jobBuilderFactory: JobBuilderFactory, listener: JobCompletionNotificationListener, step1: Step) : Job {
//        return jobBuilderFactory.get("job1")
//                .incrementer(RunIdIncrementer())
//                .listener(listener)
//                .flow(step1)
//                .end()
//                .build()
//    }
//
//    @Bean
//    fun step1(stepBuilderFactory: JobBuilderFactory) : Step {
//        return stepBuilderFactory.get("step1")
//                .<Person, Person> chunk(10)
//    }
//}
//
