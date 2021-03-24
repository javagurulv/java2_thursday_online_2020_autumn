package internet_store.application;

import internet_store.application.web_ui.config.SpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebConfiguration.class, args);
    }

}
