package internet_store.application.web_ui.controllers.rest;

import internet_store.application.core.requests.customer.*;
import internet_store.application.core.responses.customer.*;
import internet_store.application.core.services.customer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    @Autowired private GetCustomerService getCustomerService;
    @Autowired private AddCustomerService addCustomerService;
    @Autowired private UpdateCustomerService updateCustomerService;
    @Autowired private DeleteByCustomerIdService deleteByCustomerIdService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetCustomerResponse getCustomer(@PathVariable Long id) {
        GetCustomerRequest request = new GetCustomerRequest(id);
        return getCustomerService.execute(request);
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddCustomerResponse addCustomer(@RequestBody AddCustomerRequest request) {
        return addCustomerService.execute(request);
    }

    @PutMapping(path = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public UpdateCustomerResponse updateCustomer(@RequestBody UpdateCustomerRequest request) {
        return updateCustomerService.execute(request);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public DeleteByCustomerIdResponse deleteBook(@PathVariable Long id) {
        DeleteByCustomerIdRequest request = new DeleteByCustomerIdRequest(id);
        return deleteByCustomerIdService.execute(request);
    }

}
/*
@RestController - данная аннотация указывает на то, что данный класс
                  будет являться REST контроллером.

@RequestMapping("/book") - данная аннотация указывает на относительный
                           адрес ресурса запросы на который будет
                           обрабатывать данный класс.

@GetMapping - указывает на то, что HTTP GET запросы приходящие
        на относительный url /book/{id} будут обрабатываться методом getBook().
        В url вместо {id} может быть указано любое целое число.

@PathVariable Long id - указывает на то, что значение {id}
        из адресной строки должно быть превращено в Long и передано
        в метод getBook() в качестве параметра.

        @PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
Указывает на то, что метод addBook() будет обрабатывать HTTP POST
запросы на url /book/.

consumes = "application/json" - указывает на то, что в теле HTTP POST
запроса должен быть JSON документ.

Пример JSON документа для запроса: {"title": "AAA", "author": "WWW"}.

produces = "application/json" - указывает на то, что в теле HTTP
ответа будет JSON документ.

Пример JSON документа для ответа:
{
    "errors": null,
    "newBook": {
        "id": 1231,
        "title": "AAA",
        "author": "WWW"
    }
}

@PutMapping - указывает на то, что HTTP PUT запросы приходящие
на относительный url /book/{id} будут обрабатываться методом updateBook()

@DeleteMapping - указывает на то, что HTTP DELETE запросы приходящие
на относительный url /book/{id} будут обрабатываться методом deleteBook()
*/
