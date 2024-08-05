package br.com.fiap.gestaoprodutos.config;

import br.com.fiap.gestaoprodutos.entities.Produto;
import br.com.fiap.gestaoprodutos.reporitories.ProdutoRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    @Autowired
    ProdutoRepository produtoRepository;

    @Bean
    public Job processarProduto(JobRepository jobRepository, Step step) {
        return new JobBuilder("importProdutos", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository,
                     PlatformTransactionManager platformTransactionManager,
                     ItemReader<Produto> itemReader,
                     ItemProcessor<Produto, Produto> itemProcessor,
                     ItemWriter<Produto> itemWriter) {
        return new StepBuilder("step", jobRepository)
                .<Produto, Produto>chunk(20, platformTransactionManager)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public ItemReader<Produto> itemReader() {
        BeanWrapperFieldSetMapper<Produto> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Produto.class);

        return new FlatFileItemReaderBuilder<Produto>()
                .name("itemReaderProduto")
                .resource(new ClassPathResource("produtos.csv"))
                .delimited()
                .names("id", "nome", "descricao", "quantidade_estoque", "preco")
                .fieldSetMapper(beanWrapperFieldSetMapper)
                .build();
    }

    @Bean
    public ItemWriter<Produto> itemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Produto>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .dataSource(dataSource)
                .sql("INSERT INTO tb_produto(id,nome,descricao,quantidade_estoque,preco) " +
                        "VALUES(:id,:nome,:descricao,:quantidadeEstoque,:preco)")
                .build();
    }

    @Bean
    public ItemProcessor<Produto, Produto> itemProcessor() {
        return p -> p;
    }

}
