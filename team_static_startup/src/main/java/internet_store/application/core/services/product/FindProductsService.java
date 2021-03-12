package internet_store.application.core.services.product;

import internet_store.application.core.database.jpa.JpaProductRepository;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.product.FindProductsRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.product.FindProductsResponse;
import internet_store.application.core.services.product.validators.FindProductsRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindProductsService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired private JpaProductRepository productRepository;
    @Autowired private FindProductsRequestValidator validator;

    public FindProductsResponse execute(FindProductsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindProductsResponse(null, errors);
        }

        List<Product> products = search(request);

        if (orderingEnabled && request.getOrdering() != null) {
            products = new OrderingProductsService().order(products, request.getOrdering());
        }
        if (pagingEnabled && request.getPaging() != null) {
            products = new PagingProductsService().page(products, request.getPaging());
        }
        return new FindProductsResponse(products, null);
    }

    private List<Product> search(FindProductsRequest request) {
        List<Product> products = null;
        if (request.isNameProvided() && !request.isDescriptionProvided()) {
            products = productRepository.findProductByProductName(request.getName());
        }
        if (!request.isNameProvided() && request.isDescriptionProvided()) {
            products = productRepository.findProductByProductDescription(request.getDescription());
        }
        if (request.isNameProvided() && request.isDescriptionProvided()) {
            products = productRepository.findProductByProductNameAndProductDescription(request.getName(), request.getDescription());
        }
        return products;
    }

}
