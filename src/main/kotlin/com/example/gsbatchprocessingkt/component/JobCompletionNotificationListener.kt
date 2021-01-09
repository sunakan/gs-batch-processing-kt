package com.example.gsbatchprocessingkt.component

import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.listener.JobExecutionListenerSupport
import org.springframework.stereotype.Component

@Component
class JobCompletionNotificationListener : JobExecutionListenerSupport() {
    override fun afterJob(jobExecution: JobExecution) {
        println("------------アフタージョブ")
        println(jobExecution)
        println("------------アフタージョブ")
    }
}
