package dental_clinic;

import dental_clinic.console_ui.ProgramMenu.ProgramMenu;
import web_ui.config.SpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class WebDentalClinicApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringWebConfiguration.class);

        ProgramMenu programMenu = context.getBean(ProgramMenu.class);
        while (true) {
            programMenu.print();
            int menuNumber = programMenu.inputValidInteger();
            programMenu.executeMenuUIAction(menuNumber);
        }
    }
}

/*@SpringBootApplication - данная аннотация необходима для
автоконфигурации библиотек Spring входящих в Spring Boot.

ConfigurableApplicationContext context = SpringApplication.run(SpringWebConfiguration.class);

Так можно запустить Spring Ioc контекст используя
библиотеку Spring Boot.*/