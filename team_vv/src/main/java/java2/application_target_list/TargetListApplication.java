package java2.application_target_list;

import java2.application_target_list.web_ui.config.SpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TargetListApplication {

     public static void main(String[] args) {
         SpringApplication.run(SpringWebConfiguration.class);
         System.out.println("Application is running");
     }
}
