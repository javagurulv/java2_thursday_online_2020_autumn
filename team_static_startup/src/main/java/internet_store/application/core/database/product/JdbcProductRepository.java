package internet_store.application.core.database.product;

import internet_store.application.core.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Component
//@Profile("mysql")
public class JdbcProductRepository implements ProductRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long add(Product product) {
        String query = "INSERT INTO products (name, description, price) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getPrice());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public boolean deleteByProductId(Long productId) {
        String query = "DELETE FROM products WHERE id = ?";
        return jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, productId);
            return preparedStatement;
        }) == 1;
    }

    @Override
    public boolean delete(Product product) {
        String query = "DELETE FROM products WHERE name=? AND description=?";
        int affectedRows = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            return preparedStatement;
        });
        return affectedRows > 0;
    }

    @Override
    public boolean deleteByProductName(String product) {
        String query = "DELETE FROM products WHERE name=?";
        int affectedRows = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product);
            return preparedStatement;
        });
        return affectedRows > 0;
    }

    @Override
    public List<Product> findByProductName(String productName) {
        String query = "SELECT * FROM products WHERE name LIKE ?";
        return jdbcTemplate.query(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productName);
            return preparedStatement;
        }, new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public List<Product> findByProductDescription(String productDescription) {
        String query = "SELECT * FROM products WHERE description LIKE ?";
        return jdbcTemplate.query(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productDescription);
            return preparedStatement;
        }, new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public List<Product> findByNameAndDescription(String name, String description) {
        String query = "SELECT * FROM products WHERE name LIKE ? AND description LIKE ?";
        return jdbcTemplate.query(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            return preparedStatement;
        }, new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public List<Product> getProductList() {
        String query = "SELECT * FROM products";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            String query = "SELECT * FROM products WHERE id = ?";
            Product foundProduct = jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(Product.class));
            return Optional.ofNullable(foundProduct);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean changeProductName(Long id, String newName) {
        String query = "UPDATE products SET name = ? WHERE id = ?";
        int affectedRows = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newName);
            preparedStatement.setLong(2, id);
            return preparedStatement;
        });
        return affectedRows > 0;
    }

}
