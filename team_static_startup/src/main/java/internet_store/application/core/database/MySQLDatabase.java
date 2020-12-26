package internet_store.application.core.database;

import internet_store.application.core.domain.Product;
import internet_store.application.core.services.DatabaseConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MySQLDatabase implements Database {

    @Autowired
    private DatabaseConnectionService connectionService;

    @Override
    public Long add(Product product) {
        Connection connection = null;
        Long id = null;
        try {
            connection = connectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO products (name, description, price) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) id = resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionService.closeConnection(connection);
        }
        return id;
    }

    @Override
    public boolean deleteByProductId(Long productId) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM products WHERE id = ?");
            preparedStatement.setLong(1, productId);
            int deletedProduct = preparedStatement.executeUpdate();
            return deletedProduct == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionService.closeConnection(connection);
        } return false;
    }

    @Override
    public boolean delete(Product product) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM products WHERE name = ? AND description = ?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            int deletedProducts = preparedStatement.executeUpdate();
            return deletedProducts > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionService.closeConnection(connection);
        } return false;
    }

    @Override
    public boolean deleteByProductName(String product) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM products WHERE name = ?");
            preparedStatement.setString(1, product);
            int deletedProduct = preparedStatement.executeUpdate();
            return deletedProduct > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionService.closeConnection(connection);
        } return false;
    }

    @Override
    public List<Product> findByProductName(String productName) {
        Connection connection = null;
        List<Product> products = new ArrayList<>();
        try {
            connection = connectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM products WHERE name = ?");
            preparedStatement.setString(1, productName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");
                Product productFound = new Product(name, description, price);
                productFound.setId(id);
                products.add(productFound);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionService.closeConnection(connection);
        } return products;
    }

    @Override
    public List<Product> findByProductDescription(String productDescription) {
        Connection connection = null;
        List<Product> products = new ArrayList<>();
        try {
            connection = connectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM products WHERE description = ?");
            preparedStatement.setString(1, productDescription);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");
                Product productFound = new Product(name, description, price);
                productFound.setId(id);
                products.add(productFound);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionService.closeConnection(connection);
        } return products;
    }

    @Override
    public List<Product> findByNameAndDescription(String name, String description) {
        Connection connection = null;
        List<Product> products = new ArrayList<>();
        try {
            connection = connectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM products WHERE name = ? AND description = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long foundId = resultSet.getLong("id");
                String foundName = resultSet.getString("name");
                String foundDescription = resultSet.getString("description");
                BigDecimal foundPrice = resultSet.getBigDecimal("price");
                Product productFound = new Product(foundName, foundDescription, foundPrice);
                productFound.setId(foundId);
                products.add(productFound);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionService.closeConnection(connection);
        } return products;
    }

    @Override
    public List<Product> getProductList() {
        Connection connection = null;
        List<Product> products = new ArrayList<>();
        try {
            connection = connectionService.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                Long foundId = resultSet.getLong("id");
                String foundName = resultSet.getString("name");
                String foundDescription = resultSet.getString("description");
                BigDecimal foundPrice = resultSet.getBigDecimal("price");
                Product productFound = new Product(foundName, foundDescription, foundPrice);
                productFound.setId(foundId);
                products.add(productFound);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionService.closeConnection(connection);
        } return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM products WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long foundId = resultSet.getLong("id");
                String foundName = resultSet.getString("name");
                String foundDescription = resultSet.getString("description");
                BigDecimal foundPrice = resultSet.getBigDecimal("price");
                Product productFound = new Product(foundName, foundDescription, foundPrice);
                productFound.setId(foundId);
                return Optional.of(productFound);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionService.closeConnection(connection);
        } return Optional.empty();
    }

    @Override
    public boolean changeProductName(Long id, String newName) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE products SET name = ? WHERE id = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setLong(2, id);
            int updatedProduct = preparedStatement.executeUpdate();
            return updatedProduct > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionService.closeConnection(connection);
        } return false;
    }
}
