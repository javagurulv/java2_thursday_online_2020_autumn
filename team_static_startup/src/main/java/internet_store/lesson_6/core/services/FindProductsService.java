package internet_store.lesson_6.core.services;

import internet_store.lesson_6.core.database.Database;
import internet_store.lesson_6.core.domain.Product;
import internet_store.lesson_6.core.requests.FindProductsRequest;
import internet_store.lesson_6.core.responses.CoreError;
import internet_store.lesson_6.core.responses.FindProductsResponse;
import internet_store.lesson_6.core.services.validators.FindProductsRequestValidator;
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

    @Autowired private Database database;
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
            products = database.findByProductName(request.getName());
        }
        if (!request.isNameProvided() && request.isDescriptionProvided()) {
            products = database.findByProductDescription(request.getDescription());
        }
        if (request.isNameProvided() && request.isDescriptionProvided()) {
            products = database.findByNameAndDescription(request.getName(), request.getDescription());
        }
        return products;
    }

}
