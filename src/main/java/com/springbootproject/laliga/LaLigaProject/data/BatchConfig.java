package com.springbootproject.laliga.LaLigaProject.data;

import com.springbootproject.laliga.LaLigaProject.model.Match;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.io.File;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    private static  final String[] FIELDS_NAMES=new String[]{
            "season","date","homeTeam","awayTeam","FTHG","FTAG","FTR", "HTHG", "HTAG","HTR"};

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<MatchInputData> reader() {
       String url="laliga-matches.csv";
        return new FlatFileItemReaderBuilder<MatchInputData>()
                .name("matchItemReader")
                .resource(new ClassPathResource(url))
                .delimited()
                .names(FIELDS_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInputData>() {{
                    setTargetType(MatchInputData.class);
                }})
                .build();
    }

    @Bean
    public MatchDataProcessor processor() {
        return new MatchDataProcessor();
    }


    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())

                .sql("INSERT INTO match(season,date,home_team,away_team,FTHG,FTAG,FTR)" +
                        "  VALUES (:season, :date, :homeTeam, :awayTeam, :FTHG, :FTAG, :FTR)")
                .dataSource(dataSource)
                .build();
    }



    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Match> writer) {
        return stepBuilderFactory.get("step1")
                .<MatchInputData, Match> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}
