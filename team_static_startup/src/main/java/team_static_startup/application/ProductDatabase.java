package team_static_startup.application;

public interface ProductDatabase {

    Long save(Product product);

    boolean delete(Long productId);

    boolean delete(Product product);

    void deleteByProductName(String product);

}
