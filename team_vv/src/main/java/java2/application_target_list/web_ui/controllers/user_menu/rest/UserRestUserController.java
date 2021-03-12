package java2.application_target_list.web_ui.controllers.user_menu.rest;

import java2.application_target_list.core.requests.user.*;
import java2.application_target_list.core.responses.user.*;
import java2.application_target_list.core.services.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_menu/user")
public class UserRestUserController {

    @Autowired
    private GetUserService getUserService;
    @Autowired
    private SearchUserByFirstNameService searchUserByFirstNameService;
    @Autowired
    private SearchUserByLastNameService searchUserByLastNameService;

    @GetMapping(path = "{id}", produces = "application/json")
    public GetUserResponse getUser(@PathVariable Long id) {
        GetUserRequest getUserRequest = new GetUserRequest(id);
        return getUserService.execute(getUserRequest);
    }

    @PostMapping(path = "/search_user_by_first_name",
            consumes = "application/json",
            produces = "application/json")
    public SearchUserByFirstNameResponse searchUserByFirstName(@RequestBody SearchUsersByFirstNameRequest searchUsersByFirstNameRequest){
        return searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
    }

    @PostMapping(path = "/search_user_by_last_name",
            consumes = "application/json",
            produces = "application/json")
    public SearchUserByLastNameResponse searchUserByLastName(@RequestBody SearchUsersByLastNameRequest searchUsersByLastNameRequest){
        return searchUserByLastNameService.execute(searchUsersByLastNameRequest);
    }
}
