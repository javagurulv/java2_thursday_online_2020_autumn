package internet_store.core.services.product;
/*
import internet_store.core.domain.Product;
import internet_store.core.requests.product.DeleteProductByOtherRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;
import internet_store.core.response.product.DeleteByOtherResponse;
import internet_store.core.services.product.validators.DeleteByOtherRequestValidator;
import internet_store.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteByOtherService {

    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private DeleteByOtherRequestValidator deleteByOtherRequestValidator;

    public CoreResponse execute(DeleteProductByOtherRequest deleteProductByOtherRequest) {
        List<CoreError> errors = deleteByOtherRequestValidator.validate(deleteProductByOtherRequest);
        if (!errors.isEmpty()) {
            return new DeleteByOtherResponse(errors, new ArrayList<>());
        }
        return provideDeleteResultAccordingToRequest(deleteProductByOtherRequest);
    }

    private DeleteByOtherResponse provideDeleteResultAccordingToRequest(DeleteProductByOtherRequest
                                                                               deleteProductByOtherRequest) {

        if (isTitleAndDescriptionAndPriceNotEmptyForDelete(deleteProductByOtherRequest.getTitle(), deleteProductByOtherRequest.getDescription(),
                deleteProductByOtherRequest.getStartPrice(), deleteProductByOtherRequest.getEndPrice())) {
            return deleteByTitleAndDescriptionAndPriceIsProvided(deleteProductByOtherRequest);
        }
        if (isTitleFilledToForDelete(deleteProductByOtherRequest.getTitle())) {
            return deleteByTitleIsProvidedForDelete(deleteProductByOtherRequest);
        }
        if (isDescriptionFilledForDelete(deleteProductByOtherRequest.getDescription())) {
            return deleteByDescriptionIsProvided(deleteProductByOtherRequest);
        }
        if (isPriceRangeFilledForDelete(deleteProductByOtherRequest.getStartPrice(),deleteProductByOtherRequest.getEndPrice())){
            return deleteByPriceRangeIsProvided(deleteProductByOtherRequest);
        }
        if (!isTitleFilledToForDelete(deleteProductByOtherRequest.getTitle())) {
            return deleteByDescriptionIsProvided(deleteProductByOtherRequest);
        }
        if (!isDescriptionFilledForDelete(deleteProductByOtherRequest.getDescription())) {
            return deleteByPriceRangeIsProvided(deleteProductByOtherRequest);
        }
        if (!isTitleFilledToForDelete(deleteProductByOtherRequest.getTitle()) && (!isDescriptionFilledForDelete(deleteProductByOtherRequest.getDescription()))) {
            return deleteByPriceRangeIsProvided(deleteProductByOtherRequest);
        }
        if (!isDescriptionFilledForDelete(deleteProductByOtherRequest.getDescription()) &&
                (!isPriceRangeFilledForDelete(deleteProductByOtherRequest.getStartPrice(), deleteProductByOtherRequest.getEndPrice()))) {
            return deleteByTitleIsProvidedForDelete(deleteProductByOtherRequest);
        }
        if (!isTitleFilledToForDelete(deleteProductByOtherRequest.getDescription()) &&
                (!isPriceRangeFilledForDelete(deleteProductByOtherRequest.getStartPrice(), deleteProductByOtherRequest.getEndPrice()))) {
            return deleteByDescriptionIsProvided(deleteProductByOtherRequest);
        }
        return deleteByTitleAndDescriptionAndPriceIsProvided(deleteProductByOtherRequest);
    }

    private boolean isTitleAndDescriptionAndPriceNotEmptyForDelete(String title, String description,
                                                                   Integer startPrice, Integer endPrice){
        return title != null && !title.isEmpty() && description != null && !description.isEmpty() &&
                startPrice != null && endPrice != null;
    }

    private DeleteByOtherResponse deleteByTitleAndDescriptionAndPriceIsProvided(DeleteProductByOtherRequest deleteProductByOtherRequest){
        List <CoreError>errors = new ArrayList<>();
        List <Product> products = productDatabase.findAllByTitleAndDescription(deleteProductByOtherRequest.getTitle(), deleteProductByOtherRequest.getDescription());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain product with title: " +
                    deleteProductByOtherRequest.getTitle() + ", description: " + deleteProductByOtherRequest.getDescription() +
                    ", price range: from " + deleteProductByOtherRequest.getStartPrice() + " till " + deleteProductByOtherRequest.getEndPrice()));
            return new DeleteByOtherResponse(errors, new ArrayList<>());
        }
        return new DeleteByOtherResponse(products);
    }

    private boolean isTitleFilledToForDelete(String title){
        return title != null && !title.isEmpty();
    }

    private boolean isDescriptionFilledForDelete(String description) {
        return description != null && !description.isEmpty();
    }

    private boolean isPriceRangeFilledForDelete(Integer startPrice, Integer endPrice) {
        return startPrice != null && endPrice != null;
    }

    private DeleteByOtherResponse deleteByTitleIsProvidedForDelete(DeleteProductByOtherRequest deleteProductByOtherRequest){
        List <CoreError>errors = new ArrayList<>();
        List<Product> products = productDatabase.findAllByTitle(deleteProductByOtherRequest.getTitle());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products with title: " +
                    deleteProductByOtherRequest.getTitle()));
            return new DeleteByOtherResponse(errors, new ArrayList<>());
        }
        return new DeleteByOtherResponse(products);
    }

    private DeleteByOtherResponse deleteByDescriptionIsProvided(DeleteProductByOtherRequest deleteProductByOtherRequest){
        List <CoreError>errors = new ArrayList<>();
        productDatabase.deleteAllByDescription(deleteProductByOtherRequest.getDescription());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products with description: " +
                    searchProductRequest.getDescription()));
            return new DeleteByOtherResponse(errors, new ArrayList<>());
        }
        return new DeleteByOtherResponse(products);
    }

    private DeleteByOtherResponse deleteByPriceRangeIsProvided(DeleteProductByOtherRequest deleteProductByOtherRequest) {
        List <CoreError>errors = new ArrayList<>();
        List<Product> products = productDatabase.findAllByPriceRange(deleteProductByOtherRequest.getStartPrice(),
                deleteProductByOtherRequest.getEndPrice());
        if (products.isEmpty()) {
            errors.add (new CoreError("database","Database doesn't contain products with price" +
                    " range starting from: " + deleteProductByOtherRequest.getStartPrice() +
                    " end ending with " + deleteProductByOtherRequest.getEndPrice()));
            return new DeleteByOtherResponse(errors, new ArrayList<>());
        }
        return new DeleteByOtherResponse(products);
    }
}

 */