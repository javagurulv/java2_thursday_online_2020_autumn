package java2.application_target_list.web_ui.controllers.user_menu.rest;

import java2.application_target_list.core.requests.target.*;
import java2.application_target_list.core.responses.target.*;
import java2.application_target_list.core.services.target.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_menu/target")
public class TargetRestUserController {

    @Autowired
    private GetTargetService getTargetService;
    @Autowired
    private AddTargetService addTargetService;
    @Autowired
    private SearchTargetByNameService searchTargetByNameService;
    @Autowired
    private SearchTargetByDescriptionService searchTargetByDescriptionService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetTargetResponse getTarget(@PathVariable Long id) {
        GetTargetRequest getTargetRequest = new GetTargetRequest(id);
        return getTargetService.execute(getTargetRequest);
    }

    @PostMapping(path = "/add_target",
            consumes = "application/json",
            produces = "application/json")
    public AddTargetResponse addTarget(@RequestBody AddTargetRequest addTargetRequest) {
        return addTargetService.execute(addTargetRequest);
    }


    @PostMapping(path = "/search_target_by_name",
            consumes = "application/json",
            produces = "application/json")
    public SearchTargetByNameResponse searchTargetByName(@RequestBody SearchTargetByNameRequest searchTargetByNameRequest) {
        return searchTargetByNameService.execute(searchTargetByNameRequest);
    }

    @PostMapping(path = "/search_target_by_description",
            consumes = "application/json",
            produces = "application/json")
    public SearchTargetByDescriptionResponse searchTargetByDescription(@RequestBody SearchTargetByDescriptionRequest searchTargetByDescriptionRequest) {
        return searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);
    }
}
