package internet_store.user_interface.administrator_menu.create_product_menu.add_product_menu;


import internet_store.core.domain.Product;

public class AddProduct {
    private final Product product = new Product();

    public Product addProduct() {

        AddProductTitle addProductTitle = new AddProductTitle();
        AddProductDescription addProductDescription = new AddProductDescription();
        AddProductQuantity addProductQuantity = new AddProductQuantity();
        AddProductPrice addProductPrice = new AddProductPrice();

        addProductTitle.showMenuProductTitle();
        product.setTitle(addProductTitle.getUserProductTitleInput());

        addProductDescription.showMenuDescription();
        product.setDescription(addProductDescription.getUserProductDescriptionInput());

        addProductQuantity.showMenuProductQuantity();
        product.setQuantity((long) addProductQuantity.getUserProductQuantityInput());

        addProductPrice.showMenuProductPrice();
        product.setPrice(addProductPrice.getUserProductPriceInput());

        return product;
    }
}
