package internet_store.configuration;

import internet_store.integration.telegram.tasks.tasks.TaskFindProduct;
import internet_store.integration.telegram.tasks.tasks.TaskGetOrderInfoByOrderNumber;
import internet_store.integration.telegram.tasks.tasks.TaskGetPdfOrder;
import internet_store.integration.telegram.tasks.tasks.TasksControl;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(value = "internet_store.core.persistence")
@EntityScan(basePackages = "internet_store.core.domain")
@ComponentScan(basePackages = "internet_store")
@EnableTransactionManagement
@PropertySource({"classpath:store.properties"})
public class StoreConfiguration {
    @Autowired
    @Lazy
    private TaskGetPdfOrder taskGetPdfOrder;
    @Autowired
    @Lazy
    private TaskGetOrderInfoByOrderNumber taskGetOrderInfoByOrderNumber;
    @Autowired
    @Lazy
    private TaskFindProduct taskFindProduct;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/store?createDatabaseIfNotExist=true");
        dataSource.setUsername("user");
        dataSource.setPassword("user");
        return dataSource;
    }

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:/db/changelog/changelog-master.xml");
        liquibase.setShouldRun(true);
        liquibase.setDataSource(dataSource);
        return liquibase;
    }

    @Bean
    public TasksControl tasksControl() {
        TasksControl tasksControl = new TasksControl();

        tasksControl.setTask(taskGetOrderInfoByOrderNumber);
        tasksControl.setTask(taskGetPdfOrder);
        tasksControl.setTask(taskFindProduct);
        return tasksControl;
    }
}