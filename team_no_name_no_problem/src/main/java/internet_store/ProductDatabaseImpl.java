package internet_store;

import internet_store.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDatabaseImpl implements ProductDatabase{

    private Long id= 1L;
    private List<Product> productList = new ArrayList<>();

    List <Product> getProductList(){
        return productList;
    }

    @Override
    public boolean save(Product product) {
        if (productList.contains(product)){
            return false;
        }else{
            product.setId(id);
            productList.add(product);
            id++;
        }
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        for (int i = 0; i< productList.size(); i++){
            if (productList.get(i).getId() == id){
                productList.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean printProducts() {
        if (productList.size() > 0) {
            System.out.println("Products in database: ");
            productList.forEach(System.out::println);
            return true;
        }else{
            System.out.println("Database is empty");
            return false;
        }
    }

    @Override
    public boolean changeTitle(long id, String newTitle) {
        for (int i = 0; i < productList.size(); i++){
            if (productList.get(i).getId() == id) {
                productList.get(i).setTitle(newTitle);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean changeDescription(long id, String newDescription) {
        for (int i = 0; i < productList.size(); i++){
            if (id == productList.get(i).getId()){
                productList.get(i).setDescription(newDescription);
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Product> findAnyByTitle(String title) {
        return productList.stream()
                .filter(product -> product.getTitle().toLowerCase().startsWith(title.toLowerCase()))
                .findAny();
    }

    @Override
    public List<Product> findAllByTitle(String title) {
        List <Product> listOfSpecificProducts = new ArrayList<>();
        for (Product product : productList){
            if (product.getTitle().toLowerCase().startsWith(title.toLowerCase())){
                listOfSpecificProducts.add(product);
            }
        }
        return listOfSpecificProducts;
    }

}
