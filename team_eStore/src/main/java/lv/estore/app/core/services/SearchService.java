package lv.estore.app.core.services;

import lv.estore.app.core.errors.Errors;
import lv.estore.app.core.request.CoreRequest;
import lv.estore.app.core.request.Ordering;
import lv.estore.app.core.responses.CoreResponse;
import lv.estore.app.core.responses.SearchResponse;
import lv.estore.app.core.validators.iValidator;
import lv.estore.app.dependency_injection.DIComponent;
import lv.estore.app.domain.Product;
import lv.estore.app.repository.iDatabase;

import java.util.Comparator;
import java.util.List;

@DIComponent
public class SearchService implements iService {

    private Comparator<Product> priceComparatorAsc = Comparator.comparing(Product::getPrice);
    private Comparator<Product> priceComparatorDesc = Comparator.comparing(Product::getPrice, Comparator.reverseOrder());
    private Comparator<Product> nameComparatorAsc = Comparator.comparing(Product::getName);
    private Comparator<Product> nameComparatorDesc = Comparator.comparing(Product::getName, Comparator.reverseOrder());

    private final iValidator validator;
    private final iDatabase database;

    public SearchService(final iDatabase database, final iValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    /**
     * Method to Order products.
     * @param request CoreRequest
     * @return response CoreResponse
     */
    @Override
    public CoreResponse execute(final CoreRequest request) {
        CoreResponse response;
        List<Product> products;
        Errors errors = validator.validate(request);
        if (errors.getAllErrors().isEmpty()) {
            products = database.searchByName(request.getName());
            orderByNameOrPrice(products, request.getOrdering());
            response = new SearchResponse(products);
        } else {
            response = new SearchResponse(errors);
        }
        return response;
    }

    /**
     * Method to order products by 'Name' and 'Price'.
     * @param products before sorting.
     * @param ordering Ordering.
     * @return List<Product> sorted.
     */
    private void orderByNameOrPrice(final List<Product> products, final Ordering ordering) {
        if ("name".equalsIgnoreCase(ordering.getOrderBy())) {
            if ("asc".equalsIgnoreCase(ordering.getOrderDirection())) {
                products.sort(nameComparatorAsc);
            } else if ("desc".equalsIgnoreCase(ordering.getOrderDirection())) {
                products.sort(nameComparatorDesc);
            }
        }
        if ("price".equalsIgnoreCase(ordering.getOrderBy())) {
            if ("asc".equalsIgnoreCase(ordering.getOrderDirection())) {
                products.sort(priceComparatorAsc);
            } else if ("desc".equalsIgnoreCase(ordering.getOrderDirection())) {
                products.sort(priceComparatorDesc);
            }
        }
    }
}
