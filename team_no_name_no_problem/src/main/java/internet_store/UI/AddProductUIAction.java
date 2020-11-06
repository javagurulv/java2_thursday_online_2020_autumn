package internet_store.UI;

import internet_store.domain.Product;
import internet_store.services.AddProductService;

class AddProductUIAction implements UIAction {

    private AddProductService addProductService;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public AddProductUIAction(AddProductService addProductService) {
        this.addProductService = addProductService;
    }

    public void execute(){
        String title = inputCheckUtility.inputValidString("Please enter product's title: ");

        String description = inputCheckUtility.inputValidString("Please enter product's description");

        int price = inputCheckUtility.inputValidInteger("Please enter product's price");

        Product newProduct = new Product(title, description, price);
        addProductService.addProduct(newProduct);
    }

}

