package estore.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "estore")
//value = "classpath:application.properties" - означает, что приложение будет искать файл application.properties
//в своём classpath. В структуре проекта директория src/main/resources входит в classpath приложения при
// старте, поэтому файл application.properties будет прочитан приложением в момент запуска.
@PropertySource(value = "classpath:application.properties")
public class ProductConfiguration {

    @Value("${database.url}")
    private String jdbcUrl;

    @Value("${database.driver.class}")
    private String driverClass;

    @Value("${database.user.name}")
    private String userName;

    @Value("${database.user.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(jdbcUrl);
        basicDataSource.setDriverClassName(driverClass);
        basicDataSource.setUsername(userName);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}

//Для того, чтобы получить доступ к прочитанным Spring свойствам конфирации нужно воспользоваться аннотацией @Value
//      @Value("${search.ordering.enabled}")
//      private boolean orderingEnabled;

//В любом классе, помеченном аннотацией @Component или @Configuration, вы можете объявить свойство на уровне класса
// и установить аннотацию @Value на нём. Как параметр при объявлении аннотации указывается название свойства из
// файла конфигурации application.properties. Spring прочитает и присвоит в указанное свойство значение взятое из
// конфигурационного файла в момент старта приложения.