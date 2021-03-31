package store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}



//todo make multi threaded ( multiply users usage)
//todo make multi language support
//todo add images to items
//todo make items & itemtypes pages url variable introduced e.g. __${#httpServletRequest.requestURI}__}
//todo add null checks for adding new items, itemtypes
//todo removing whole itemtype with items included ?
//todo populate PDF
//todo introduce email sending service
//todo make Search function on top of shop page
//todo make paginations for items
//todo make PDF with jersey's Response and redirect to home/basket/ack that pdf is created, basket is empty
// TODO thymeleaf adds ? in each url end
// todo make DELETE REST method for delete actions, PUT for editing
