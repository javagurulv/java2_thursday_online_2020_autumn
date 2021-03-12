package adventure_time.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

 @Configuration
 @ComponentScan(basePackages = "adventure_time")
 @PropertySource(value = "classpath:application.properties")
 @EnableTransactionManagement //  включает управление транзакциями

public class AdminConfiguration {

     // JDBC-configuration

     @Value("${jdbc.url}")  // jdbc.url=jdbc:mysql://localhost:3306/adventure_db
     private String jdbcUrl;
     @Value("${driverClass}")    // driverClass=com.mysql.cj.jdbc.Driver
     private String driverClass;
     @Value("${database.user.name}") // database.user.name=root
     private String userName;
     @Value("${database.user.password}") // database.user.password= 
     private String password;

     @Bean
     public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
     }

     @Bean
     public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


    // ORM-configuration

     @Bean
     public Properties hibernateProperties(
             @Value("${hibernate.show_sql}") Boolean showSql,
             @Value("${hibernate.hbm2ddl.auto}") String hbm2ddl,
             @Value("${hibernate.dialect}") String dialect) {
         Properties properties = new Properties();
         properties.put("hibernate.show_sql", showSql);
         properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
         properties.put("hibernate.dialect", dialect);
         return properties;
     }

     @Bean
     public SessionFactory sessionFactory(DataSource dataSource,
                                          @Value("${hibernate.packagesToScan}") String packagesToScan,
                                          Properties hibernateProperties
     ) throws IOException {
         LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
         sessionFactoryBean.setPackagesToScan(packagesToScan);
         sessionFactoryBean.setDataSource(dataSource);
         sessionFactoryBean.setHibernateProperties(hibernateProperties);
         sessionFactoryBean.afterPropertiesSet();
         return sessionFactoryBean.getObject();
     }

     @Bean
     public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
         return new HibernateTransactionManager(sessionFactory);
     }


}

/*
     @Bean - Spring аннотация позволяет включить созданную методом dataSource() инстанцию объекта DataSource в Spring IoC-контейнер.
     Это означает, что все Spring компоненты в контейнере могут ссылаться на инстанцию DataSource в качестве зависимости.

     Метод DataSource dataSource() является типичным примером реализации дизайн паттерна Factory Method.

     JdbcTemplate является центральным классом в базовом пакете JDBC. Выполняет запросы или обновления SQL, инициируя итерацию по ResultSets и
     перехватывая исключения JDBC и переводя их в общую, более информативную иерархию исключений, определенную в пакете org.springframework.dao.

    @Configuration используется для указания класса конфигурации.

    @ComponentScan указывает на главный пакет аппликации, в котором при старте приложения будет происходить поиск классов,
    проаннотированных аннотацией @Component. Все найденные классы с аннотацией @Component будут включены в контекст приложения,
    и для них будут настроены зависимости.

    Spring Framework поддерживает несколько способов хранения конфигурации:
        1. properties файлы
        2. xml файлы
        3. конфигурация в Java коде
        Здесь, в классе AdminConfiguration, реализовано хранение конфигурации в коде.

    value = "classpath:application.properties" - означает, что приложение будет искать файл application.properties в своём classpath.
    В структуре проекта директория src/main/resources входит в classpath приложения при старте, поэтому файл application.properties будет
    прочитан приложением в момент запуска.

    TransactionManager нужен для управления транзакциями запросов к базе данных в Spring приложении.

 */