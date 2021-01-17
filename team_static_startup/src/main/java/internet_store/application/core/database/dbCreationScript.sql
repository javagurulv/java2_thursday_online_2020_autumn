SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `taskbase` DEFAULT CHARACTER SET utf8mb3;
USE `taskbase` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

CREATE TABLE IF NOT EXISTS `products` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `productname` VARCHAR(200) NOT NULL,
  `price` BIGINT NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

ALTER TABLE `products`
  ADD `productdescription` VARCHAR(1000);


CREATE TABLE IF NOT EXISTS `customers` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `phone` BIGINT NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;


CREATE TABLE IF NOT EXISTS `shopping_cart` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `customer_id` BIGINT NOT NULL,
  `product_id` BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

CREATE TABLE IF NOT EXISTS `orders` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `shopping_cart_id` BIGINT NOT NULL,
  `product_id` BIGINT NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at`  TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (`shopping_cart_id`) REFERENCES `shopping_cart` (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

INSERT INTO products (productname, price) VALUES ('iPhone 12', 899);
INSERT INTO products (id, productname, price) VALUES (1004, 'iPhone 10', 599);
INSERT INTO products (id, productname, price, description) VALUES (1004, 'iPhone 5', 199, 'Retro for stylish looks');

SELECT * FROM products;

SELECT id, productname, price FROM products
WHERE id > 1003;

SELECT productname, price FROM products
WHERE productname LIKE 'IPHON%';

SELECT id, productname, price FROM products
WHERE productname LIKE 'IPHON%' OR price <= 1000;

SELECT id, productname, price FROM products
WHERE productname LIKE 'IPHON%' OR price <= 500
ORDER BY price ASC
LIMIT 5;

