package internet_store.web_ui.controller.client;

import internet_store.core.domain.Client;
import internet_store.core.request.client.AddClientRequest;
import internet_store.core.request.client.SearchClientRequest;
import internet_store.core.response.client.SearchClientResponse;
import internet_store.core.service.client.AddClientService;
import internet_store.core.service.client.SearchClientService;
import internet_store.core.persistence.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UpdateClientController {
    @Autowired
    private SearchClientService service;
    @Autowired
    private AddClientService addClientService;
    @Autowired
    private ClientRepository clientRepository;
    private Client updatedClient;

    @GetMapping(value = "/service/update_client")
    public String updateProduct(ModelMap modelMap) {
        modelMap.addAttribute("error", "");
        modelMap.addAttribute("disabled", "true");
        modelMap.addAttribute("client", new Client());

        return "client/update_client";
    }

    @PostMapping(value = "/search_update_client")
    public String clientForUpdate(@RequestParam(value = "search") String searchClient,
                                  @RequestParam(value = "clientName") String searchBy, ModelMap modelMap) {

        SearchClientRequest request = new SearchClientRequest(searchClient, searchBy);
        SearchClientResponse response = service.execute(request);

        List<Client> clientsResultList = response.getClient();

        if (clientsResultList.size() > 1) {
            modelMap.addAttribute("error", "Search criterion "
                    + searchClient + " have more one result. Please change search criterion and try again.");
            return "client/update_client";
        }

        if (clientsResultList.size() == 1) {
            updatedClient = response.getClient().get(0);
            modelMap.addAttribute("error", "");
            modelMap.addAttribute("disabled", "false");
            modelMap.addAttribute("client", updatedClient);
        } else {
            modelMap.addAttribute("error", "Search command error : Product not find in database");
            modelMap.addAttribute("disabled", "true");
            modelMap.addAttribute("client", new Client());

        }
        return "client/update_client";
    }

    @PostMapping(value = "/update_client")
    public String clientUpdate(@ModelAttribute(value = "client") Client client, ModelMap modelMap) {
        client.setId(updatedClient.getId());
        addClientService.execute(new AddClientRequest(client));

        modelMap.addAttribute("error", "Product successful updated to database");
        modelMap.addAttribute("disabled", "true");
        modelMap.addAttribute("client", new Client());
        return "client/update_client";
    }

    @PostMapping(value = "/update_client_back")
    public String backUpdateClientPressed() {
        return "service/service";
    }
}