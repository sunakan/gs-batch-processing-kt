package com.example.gsbatchprocessingkt

import com.example.gsbatchprocessingkt.component.JobCompletionNotificationListener
import com.example.gsbatchprocessingkt.entity.Person
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.mapping.FieldSetMapper
import org.springframework.batch.item.file.transform.DefaultFieldSetFactory
import org.springframework.batch.item.file.transform.FieldSet
import org.springframework.batch.item.file.transform.LineTokenizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import javax.sql.DataSource

@Configuration
@EnableBatchProcessing
class BatchConfiguration {
    @Bean
    fun job1(jobBuilderFactory: JobBuilderFactory, listener: JobCompletionNotificationListener, job1step1: Step): Job {
        println("--------------------------------------------------------------------------------job1 begin")
        println(jobBuilderFactory)
        println("---")
        println(listener)
        println("---")
        println(job1step1)
        println("--------------------------------------------------------------------------------job1 end")
        return jobBuilderFactory["job1"]
            .incrementer(RunIdIncrementer())
            .listener(listener)
            .flow(job1step1)
            .end()
            .build()
    }

    @Bean
    fun job1step1(stepBuilderFactory: StepBuilderFactory, writer: JdbcBatchItemWriter<Person>): Step {
        println("--------------------------------------------------------------------------------job1step1 begin")
        println(stepBuilderFactory)
        println("---")
        println(writer)
        println("--------------------------------------------------------------------------------job1step1 end")
        return stepBuilderFactory["job1step1"]
            .chunk<Person, Person>(10)
            .reader(reader())
            .processor(PersonItemProcessor())
            .writer(writer)
            .build()
    }

    @Bean
    fun reader(): FlatFileItemReader<Person> {
        println("--------------------------------------------------------------------------------FlatFileItemReader<Person>を新規作成, called reader()")
        return FlatFileItemReaderBuilder<Person>()
            .name("personItemReader")
            .lineTokenizer(CsvTokenizer())
            .fieldSetMapper(PersonFieldSetMapper())
            .resource(ClassPathResource("sample-data.csv"))
            .build()
    }

    @Bean
    fun writer(dataSource: DataSource?): JdbcBatchItemWriter<Person> {
        println("--------------------------------------------------------------------------------JdbcBatchItemWriter<Person>を新規作成, called writer()")
        return JdbcBatchItemWriterBuilder<Person>()
            .itemSqlParameterSourceProvider(BeanPropertyItemSqlParameterSourceProvider())
            .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
            .dataSource(dataSource!!)
            .build()
    }

    class CsvTokenizer() : LineTokenizer {
        override fun tokenize(csvLine: String?): FieldSet {
            println("--------------------------------------------------------------------------------tokenize begin")
            println(csvLine)
            println("--------------------------------------------------------------------------------tokenize end")
            if (csvLine == null) throw Exception()
            val fields = csvLine.split(",").toTypedArray()
            val names = arrayOf("firstName", "lastName")
            return DefaultFieldSetFactory().create(fields, names)
        }
    }

    class PersonFieldSetMapper : FieldSetMapper<Person> {
        override fun mapFieldSet(fs: FieldSet): Person {
            println("--------------------------------------------------------------------------------mapFieldSet begin")
            println(fs)
            println("--------------------------------------------------------------------------------mapFieldSet end")
            return Person(
                firstName = fs.readString("firstName"),
                lastName = fs.readString("lastName")
            )
        }
    }
}
