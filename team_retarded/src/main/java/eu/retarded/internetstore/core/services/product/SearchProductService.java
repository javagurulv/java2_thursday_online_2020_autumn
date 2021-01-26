package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.Ordering;
import eu.retarded.internetstore.core.requests.product.Paging;
import eu.retarded.internetstore.core.requests.product.SearchProductRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.product.SearchProductResponse;
import eu.retarded.internetstore.core.services.validators.product.SearchProductValidator;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchProductService {

    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private SearchProductValidator validator;

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    public SearchProductResponse execute(SearchProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchProductResponse(errors, null);
        }

        List<Product> products = productDatabase.filter(product ->
                (product.getName().toLowerCase().contains(request.getName().toLowerCase())
                        && !request.getName().isBlank()) ||
                        (product.getDescription().toLowerCase().contains(request.getDescription().toLowerCase())
                                && !request.getDescription().isBlank()));
        products = order(products, request.getOrdering());
        products = paging(products, request.getPaging());

        return new SearchProductResponse(null, products);
    }

    private List<Product> order(List<Product> products, Ordering ordering) {
        if (orderingEnabled && ordering != null) {
            if (ordering.getOrderBy() != null) {
                Comparator<Product> comparator = ordering.getOrderBy().equals("name")
                        ? Comparator.comparing(Product::getName)
                        : Comparator.comparing(Product::getDescription);
                if (ordering.getOrderDirection().equals("DESCENDING")) {
                    comparator = comparator.reversed();
                }
                return products.stream().sorted(comparator).collect(Collectors.toList());
            }
        }
        return products;

    }

    private List<Product> paging(List<Product> products, Paging paging) {
        if (pagingEnabled && paging != null) {
            if (paging.getPageNumber() != null && paging.getPageSize() != null) {
                int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
                return products.stream()
                        .skip(skip)
                        .limit(paging.getPageSize())
                        .collect(Collectors.toList());
            }
        }
        return products;
    }
}
