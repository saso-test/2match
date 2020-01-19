package test2;

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
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Configuration
@EnableBatchProcessing
//@EnableAsync
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    private static final Log logger = LogFactory.getLog(BatchConfiguration.class);

    @Bean
    public FlatFileItemReader<Match> reader() {
        return new FlatFileItemReaderBuilder<Match>()
                .name("matchItemReader")
                .linesToSkip(1)
                .resource(new ClassPathResource("fo_random.txt"))
                .delimited()
                .names(new String[]{"matchId", "marketId", "outcomeId", "specifiers", "dateInsert"})
                .lineMapper(lineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Match>() {{
                    setTargetType(Match.class);
                }})
                .build();
    }

    @Bean
    public LineMapper<Match> lineMapper() {
        final DefaultLineMapper<Match> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter("|");
        lineTokenizer.setStrict(false);
        lineTokenizer.setQuoteCharacter('\'');
        lineTokenizer.setNames(new String[] {"matchId","marketId", "outcomeId", "specifiers"});
        final MatchFieldSetMapper fieldSetMapper = new MatchFieldSetMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }

    @Bean
    public MatchProcessor processor() {
        return new MatchProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer(final DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO match (MATCH_ID, MARKET_ID, OUTCOME_ID, SPECIFIERS, DATE_INSERT) VALUES (:matchId, :marketId, :outcomeId, :specifiers, :dateInsert)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importMatchJob(NotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importMatchJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Match> writer) {
        return stepBuilderFactory.get("step1")
                .<Match, Match> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
}
