package internet_store.application.core.services.product;

import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.product.Paging;

import java.util.List;
import java.util.stream.Collectors;

public class PagingProductsService {

    public List<Product> page (List<Product> products, Paging paging) {
        if (paging != null && bothPageSizeAndNumberAreProvided(paging)){
            int numberOfProductsToSkip = paging.getPageSize() * (paging.getPageNumber() - 1);
            return products.stream()
                    .skip(numberOfProductsToSkip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return products;
        }
    }

    private boolean bothPageSizeAndNumberAreProvided (Paging paging) {
        return ((paging.getPageSize() != null) && (paging.getPageNumber() != null));
    }

}
