package internet_store.web_ui.controllers.rest;

import internet_store.core.requests.product.*;
import internet_store.core.response.product.*;
import internet_store.core.services.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")

public class ProductRestController {

    @Autowired
    private SearchProductByIdService searchProductByIdService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public SearchProductByIdResponse searchProductById(@PathVariable Long id) {
        SearchProductByIdRequest request = new SearchProductByIdRequest(id);
        return searchProductByIdService.execute(request);
    }

    @Autowired
    private SearchProductByOtherService searchProductByOtherService;

    @PostMapping(path = "/search", produces = "application/json")
    public SearchProductByOtherResponse searchProductByOther(@RequestBody SearchProductByOtherRequest request) {
        return searchProductByOtherService.execute(request);
    }

    @Autowired
    private AddProductService addProductService;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddProductResponse addProduct(@RequestBody AddProductRequest request) {
        return addProductService.execute(request);
    }

    @Autowired
    private ChangeProductService changeProductService;

    @PutMapping(path = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public ChangeProductResponse changeProduct(@RequestBody ChangeProductRequest request) {
        return changeProductService.execute(request);
    }

    @Autowired
    private DeleteProductByIdService deleteProductByIdService;
    @DeleteMapping(path = "/{id}", produces = "application/json")
    public DeleteProductByIdResponse deleteProductById(@PathVariable Long id) {
        DeleteProductByIdRequest request = new DeleteProductByIdRequest(id);
        return deleteProductByIdService.execute(request);
    }

    @Autowired
    private DeleteProductByOtherService deleteProductByOtherService;
    @DeleteMapping(path = "/delete", produces = "application/json")
    public DeleteProductByOtherResponse deleteProductByOther(@RequestBody DeleteProductByOtherRequest request) {
        return deleteProductByOtherService.execute(request);
    }

}


