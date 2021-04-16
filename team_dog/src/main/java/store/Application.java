package store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}



//todo IMPLEMENT multi threaded ( multiply users usage)
//todo IMPLEMENT multi language support
//todo IMPLEMENT images to items
//todo make items & itemtypes pages url variable introduced e.g. __${#httpServletRequest.requestURI}__}
//todo add null checks for adding new items, itemtypes (html required="required" field check implementation)
//todo removing whole itemtype with items included ?
//todo populate PDF
//todo IMPLEMENT email sending service
//todo IMPLEMENT Search function on top of shop page
//todo IMPLEMENT paginations for items
//todo make PDF with jersey's Response and redirect to home/basket/ack that pdf is created, basket is empty
//todo thymeleaf adds ? in each url end
//todo make DELETE REST method for delete actions, PUT for editing
