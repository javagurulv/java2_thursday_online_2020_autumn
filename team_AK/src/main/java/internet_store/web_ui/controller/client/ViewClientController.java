package internet_store.web_ui.controller.client;

import internet_store.core.service.client.paging.ClientPagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewClientController {

    @Autowired
    private ClientPagingService paging;

    @GetMapping(value = "/service/view_client")
    public String showClient(ModelMap modelMap) {
        paging.startPaging();
        modelMap.addAttribute("info", "");
        refreshData(modelMap);
        return "client/view_client";
    }

    @PostMapping(value = "/view_client_next")
    public String nextPageClientControl(ModelMap modelMap) {
        if (paging.isLastPage()) {
            modelMap.addAttribute("info", "View control : Last page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(true);
        }
        refreshData(modelMap);
        return "client/view_client";
    }

    @PostMapping(value = "/view_client_prev")
    public String prevPageClientControl(ModelMap modelMap) {
        if (paging.isFirstPage()) {
            modelMap.addAttribute("info", "View control : First page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(false);
        }
        refreshData(modelMap);
        return "client/view_client";
    }

    private void refreshData(ModelMap modelMap) {
        modelMap.addAttribute("clientList", paging.getListOnePage());
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
    }
}