package estore.core.service;

import estore.core.requests.Ordering;
import estore.core.requests.Paging;
import estore.core.validation.CoreError;
import estore.core.validation.SearchProductByNameValidator;
import estore.database.ProductDB;
import estore.dependency_injection.DIComponent;
import estore.dependency_injection.DIDependency;
import estore.domain.Product;
import estore.core.requests.SearchProductByNameRequest;
import estore.core.responses.SearchProductByNameResponse;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@DIComponent
public class SearchProductByNameService {

    @DIDependency
    private ProductDB productDB;
    @DIDependency
    private SearchProductByNameValidator validator;

//    public SearchProductByNameService(ProductDB productDB, SearchProductByNameValidator validator) {
//        this.productDB = productDB;
//        this.validator = validator;
//    }

    public SearchProductByNameResponse execute(SearchProductByNameRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new SearchProductByNameResponse(errors);
        }

        List<Product> foundProducts = productDB.searchProductByName(request.getProductName());
        foundProducts = order(foundProducts, request.getOrdering());
        foundProducts = paging(foundProducts, request.getPaging());
        return new SearchProductByNameResponse(foundProducts, foundProducts.size());
    }

    private List<Product> order(List<Product> products, Ordering ordering) {
        if (ordering != null) {
            Comparator<Product> comparator = ordering.getOrderBy().toLowerCase().equals("name")
                    ? Comparator.comparing(Product::getName)
                    : Comparator.comparing(Product::getPrice);
            if (ordering.getOrderDirection().toLowerCase().equals("descending") ||
                    ordering.getOrderDirection().toLowerCase().equals("desc")) {
                comparator = comparator.reversed();
            }
            return products.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return products;
        }
    }

    private List<Product> paging(List<Product> products, Paging paging) {
        if (paging != null) {
            int pageSize = Integer.valueOf(paging.getPageSize());
            int skip = (Integer.valueOf(paging.getPageNumber()) - 1) * pageSize;
            return products.stream()
                    .skip(skip)
                    .limit(pageSize)
                    .collect(Collectors.toList());
        } else {
            return products;
        }
    }
}
