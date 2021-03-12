package internet_store.core.services.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.requests.product.SearchProductByOtherRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.SearchProductByOtherResponse;
import internet_store.core.services.product.validators.SearchProductRequestValidator;

import internet_store.database.jpa.ProductRepository;
import internet_store.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class SearchProductByOtherService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private ProductRepository productDatabase;
    @Autowired
    private SearchProductRequestValidator searchProductRequestValidator;

    public SearchProductByOtherResponse execute(SearchProductByOtherRequest searchProductRequest) {
        List<CoreError> errors = searchProductRequestValidator.validate(searchProductRequest);
        if (!errors.isEmpty()) {
            return new SearchProductByOtherResponse(errors, new ArrayList<>());
        }
        return provideSearchResultAccordingToRequest(searchProductRequest);
    }

    private SearchProductByOtherResponse provideSearchResultAccordingToRequest(SearchProductByOtherRequest searchProductRequest) {

        if (isTitleAndDescriptionAndPriceNotEmpty(searchProductRequest.getTitle(), searchProductRequest.getDescription(),
                searchProductRequest.getStartPrice(), searchProductRequest.getEndPrice())) {
            return searchByTitleAndDescriptionAndPriceIsProvided(searchProductRequest);
        }
        if (isTitleAndDescriptionFilled(searchProductRequest.getTitle(),searchProductRequest.getDescription())) {
            return searchByTitleAndDescriptionIsProvided(searchProductRequest);
        }
        if(isTitleFilledAndPriceRangeNotEmpty(searchProductRequest.getTitle(),searchProductRequest.getStartPrice(),
                searchProductRequest.getEndPrice())) {
            return searchByTitleAndPriceRangeIsProvided(searchProductRequest);
        }
        if (isDescriptionFilledPriceRangeNotEmpty(searchProductRequest.getDescription(),searchProductRequest.getStartPrice(),
                searchProductRequest.getEndPrice())) {
            return searchByDescriptionAndPriceRangeIsProvided(searchProductRequest);
        }
        if (isTitleFilled(searchProductRequest.getTitle())) {
            return searchByTitleIsProvided(searchProductRequest);
        }
        if (isDescriptionFilled(searchProductRequest.getDescription())) {
            return searchByDescriptionIsProvided(searchProductRequest);
        }
        if (isPriceRangeFilled(searchProductRequest.getStartPrice(),searchProductRequest.getEndPrice())) {
            return searchByPriceRangeIsProvided(searchProductRequest);
        }
        if (!isTitleAndDescriptionAndPriceNotEmpty(searchProductRequest.getTitle(), searchProductRequest.getDescription(),
                searchProductRequest.getStartPrice(), searchProductRequest.getEndPrice())) {
            return searchByTitleAndDescriptionIsProvided(searchProductRequest);
        }
        if (!isTitleAndDescriptionFilled(searchProductRequest.getTitle(),searchProductRequest.getDescription())) {
            return searchByTitleAndPriceRangeIsProvided(searchProductRequest);
        }
        if(!isTitleFilledAndPriceRangeNotEmpty(searchProductRequest.getTitle(),searchProductRequest.getStartPrice(),
                searchProductRequest.getEndPrice())) {
            return searchByDescriptionAndPriceRangeIsProvided(searchProductRequest);
        }
        if (!isDescriptionFilledPriceRangeNotEmpty(searchProductRequest.getDescription(),searchProductRequest.getStartPrice(),
                searchProductRequest.getEndPrice())) {
            return searchByTitleIsProvided(searchProductRequest);
        }
        if (!isTitleFilled(searchProductRequest.getTitle())) {
            return searchByDescriptionIsProvided(searchProductRequest);
        }
        if (!isDescriptionFilled(searchProductRequest.getDescription())) {
            return searchByPriceRangeIsProvided(searchProductRequest);
        }
        if (!isPriceRangeFilled(searchProductRequest.getStartPrice(),searchProductRequest.getEndPrice())) {
            return searchByPriceRangeIsProvided(searchProductRequest);
        }
        return searchByTitleAndDescriptionAndPriceIsProvided(searchProductRequest);
    }

    private boolean isTitleAndDescriptionAndPriceNotEmpty(String title, String description,
                                                          Integer startPrice, Integer endPrice){
        return title != null && !title.isEmpty() && description != null && !description.isEmpty() &&
                startPrice != 0 && endPrice != 0;
    }

    private SearchProductByOtherResponse searchByTitleAndDescriptionAndPriceIsProvided(SearchProductByOtherRequest searchProductRequest){
        List <CoreError>errors = new ArrayList<>();
        List <Product> products = productDatabase.searchAllByTitleAndDescriptionAndPriceRange(searchProductRequest.getTitle(), searchProductRequest.getDescription(),
                                                                                              searchProductRequest.getStartPrice(),searchProductRequest.getEndPrice());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain product with title: " +
                    searchProductRequest.getTitle() + ", description: " + searchProductRequest.getDescription() +
                    ", price range: from " + searchProductRequest.getStartPrice() + " till " + searchProductRequest.getEndPrice()));
            return new SearchProductByOtherResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductByOtherResponse(products);
    }

    private boolean isTitleFilled(String title){
        return title != null && !title.isEmpty();
    }

    private boolean isDescriptionFilled(String description) {
        return description != null && !description.isEmpty();
    }

    private boolean isPriceRangeFilled(Integer startPrice, Integer endPrice) {
        return startPrice != 0 && endPrice != 0;
    }

    private boolean isTitleAndDescriptionFilled(String title, String description){
        return title != null && !title.isEmpty() && description != null && !description.isEmpty();
    }

    private boolean isTitleFilledAndPriceRangeNotEmpty(String title, Integer startPrice, Integer endPrice) {
        return title != null && !title.isEmpty() && startPrice != 0 && endPrice != 0;
    }

    private boolean isDescriptionFilledPriceRangeNotEmpty(String description, Integer startPrice, Integer endPrice) {
        return description != null && !description.isEmpty() && startPrice != 0 && endPrice != 0;
    }

    private SearchProductByOtherResponse searchByTitleIsProvided(SearchProductByOtherRequest searchProductRequest){
        List <CoreError>errors = new ArrayList<>();
        List<Product> products = productDatabase.searchAllByTitle(searchProductRequest.getTitle());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products with title: " +
                    searchProductRequest.getTitle()));
            return new SearchProductByOtherResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductByOtherResponse(products);
    }

    private SearchProductByOtherResponse searchByDescriptionIsProvided(SearchProductByOtherRequest searchProductRequest){
        List <CoreError>errors = new ArrayList<>();
        List<Product>products = productDatabase.searchAllByDescription(searchProductRequest.getDescription());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products with description: " +
                    searchProductRequest.getDescription()));
            return new SearchProductByOtherResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductByOtherResponse(products);
    }

    private SearchProductByOtherResponse searchByPriceRangeIsProvided(SearchProductByOtherRequest searchProductRequest) {
        List <CoreError>errors = new ArrayList<>();
        List<Product> products = productDatabase.searchAllByPriceRange(searchProductRequest.getStartPrice(),
                searchProductRequest.getEndPrice());
        if (products.isEmpty()) {
            errors.add (new CoreError("database","Database doesn't contain products with price" +
                                            " range starting from: " + searchProductRequest.getStartPrice() +
                                            " end ending with " + searchProductRequest.getEndPrice()));
            return new SearchProductByOtherResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductByOtherResponse(products);
    }

    private SearchProductByOtherResponse searchByTitleAndDescriptionIsProvided(SearchProductByOtherRequest searchProductRequest){
        List <CoreError>errors = new ArrayList<>();
        List<Product> products = productDatabase.searchAllByTitleAndDescription(searchProductRequest.getTitle(),
                searchProductRequest.getDescription());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products with title: " +
                    searchProductRequest.getTitle() +  ", description: " + searchProductRequest.getDescription()));
            return new SearchProductByOtherResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductByOtherResponse(products);
    }

    private SearchProductByOtherResponse searchByTitleAndPriceRangeIsProvided(SearchProductByOtherRequest searchProductRequest){
        List <CoreError>errors = new ArrayList<>();
        List<Product> products = productDatabase.searchAllByTitleAndPriceRange(searchProductRequest.getTitle(),
                searchProductRequest.getStartPrice(),searchProductRequest.getEndPrice());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products with title: " + searchProductRequest.getTitle() +
                    ", price range: from " + searchProductRequest.getStartPrice() + " till " + searchProductRequest.getEndPrice()));
            return new SearchProductByOtherResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductByOtherResponse(products);
    }

    private SearchProductByOtherResponse searchByDescriptionAndPriceRangeIsProvided(SearchProductByOtherRequest searchProductRequest){
        List <CoreError>errors = new ArrayList<>();
        List<Product> products = productDatabase.searchAllByDescriptionAndPriceRange(searchProductRequest.getDescription(),
                searchProductRequest.getStartPrice(),searchProductRequest.getEndPrice());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products with description: " + searchProductRequest.getDescription() +
                    ", price range: from " + searchProductRequest.getStartPrice() + " till " + searchProductRequest.getEndPrice()));
            return new SearchProductByOtherResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductByOtherResponse(products);
    }

    private SearchProductByOtherResponse searchByTitleAndDescriptionAndPriceRangeIsProvided(SearchProductByOtherRequest searchProductRequest){
        List <CoreError>errors = new ArrayList<>();
        List<Product> products = productDatabase.searchAllByTitleAndDescriptionAndPriceRange(searchProductRequest.getTitle(),
                searchProductRequest.getDescription(),searchProductRequest.getStartPrice(),searchProductRequest.getEndPrice());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products with title: " + searchProductRequest.getTitle() +
                    ", description" + searchProductRequest.getDescription() + ", price range: from " + searchProductRequest.getStartPrice() + " till "
                    + searchProductRequest.getEndPrice()));
            return new SearchProductByOtherResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductByOtherResponse(products);
    }

    private List<Product> order(List<Product> products, Ordering ordering) {
        if (ordering.filledBoth()){
            if (ordering.getOrderBy().equals("name")){
                return sortByTitle(products, ordering);
            }else if(ordering.getOrderBy().equals("description")){
                return sortByDescription(products, ordering);
            }else{
                return sortByPrice(products,ordering);
            }
        }else{
            return products;
        }
    }

    private List<Product> sortByTitle(List<Product> products, Ordering ordering){
        if (ordering.getOrderDirection().equals("ASC")){
            return products.stream()
                    .sorted(Comparator.comparing(product -> product.getTitle()))
                    .collect(Collectors.toList());
        }else{
            return products.stream()
                    .sorted((o1,o2) -> o2.getTitle().compareTo(o1.getTitle()))
                    .collect(Collectors.toList());
        }
    }

    private List<Product> sortByPrice(List<Product> products, Ordering ordering){
        if (ordering.getOrderDirection().equals("ASC")){
            return products.stream()
                    .sorted(Comparator.comparingInt(Product::getPrice))
                    .collect(Collectors.toList());
        }else{
            return products.stream()
                    .sorted((o1,o2) -> o2.getPrice() - o1.getPrice())
                    .collect(Collectors.toList());
        }
    }

    private List<Product> sortByDescription(List<Product> products, Ordering ordering){
        if (ordering.getOrderDirection().equals("ASC")){
            return products.stream()
                    .sorted(Comparator.comparing(product -> product.getDescription()))
                    .collect(Collectors.toList());
        }else{
            return products.stream()
                    .sorted((o1,o2) -> o2.getDescription().compareTo(o1.getDescription()))
                    .collect(Collectors.toList());
        }
    }

    private List<Product> paging(List<Product> products, Paging paging) {
        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return products.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return products;
        }
    }
}
