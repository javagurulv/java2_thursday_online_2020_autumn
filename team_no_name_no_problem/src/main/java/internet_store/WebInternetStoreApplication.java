package internet_store;

import internet_store.config.SpringCoreConfiguration;
import internet_store.console_ui.ProgramMenu.ProgramMenu;
import internet_store.web_ui.config.SpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class WebInternetStoreApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SpringWebConfiguration.class);
        /* ProgramMenu programMenu = context.getBean(ProgramMenu.class);

        System.out.println("\nWelcome to the No Name No Problem Internet Store  ¯\\_(ツ)_/¯");

        while (true) {

            programMenu.printMenu();

            int userSelectedMenuNumber = programMenu.inputValidInteger("Please enter menu number");

            programMenu.executeUIAction(userSelectedMenuNumber);
        }

         */

    }

  /*  private static ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
    }

   */

}

