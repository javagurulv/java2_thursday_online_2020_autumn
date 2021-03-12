package java2.application_target_list.web_ui.controllers.admin_menu.rest;

import java2.application_target_list.core.requests.user.*;
import java2.application_target_list.core.responses.user.*;
import java2.application_target_list.core.services.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin_menu/user")
public class UserRestController {

    @Autowired
    private GetUserService getUserService;
    @Autowired
    private AddUserService addUserService;
    @Autowired
    private DeleteUserService deleteUserService;
    @Autowired
    private UpdateUserService updateUserService;
    @Autowired
    private SearchUserByFirstNameService searchUserByFirstNameService;
    @Autowired
    private SearchUserByLastNameService searchUserByLastNameService;

    @GetMapping(path = "{id}", produces = "application/json")
    public GetUserResponse getUser(@PathVariable Long id) {
        GetUserRequest getUserRequest = new GetUserRequest(id);
        return getUserService.execute(getUserRequest);
    }

    @PostMapping(path = "/add_user",
             consumes = "application/json",
             produces = "application/json")
    public AddUserResponse addUser(@RequestBody AddUserRequest addUserRequest){
        return addUserService.execute(addUserRequest);
    }

    @PutMapping(path = "/{userIdToChange}", consumes = "application/json", produces = "application/json")
    public UpdateUserResponse updateUser(@RequestBody UpdateUserRequest updateUserRequest){
        return updateUserService.execute(updateUserRequest);
    }

    @DeleteMapping(path = "/{userIdToDelete}", produces = "application/json")
    public DeleteUserResponse deleteUser(@PathVariable Long userIdToDelete) {
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(userIdToDelete);
        return deleteUserService.execute(deleteUserRequest);
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
