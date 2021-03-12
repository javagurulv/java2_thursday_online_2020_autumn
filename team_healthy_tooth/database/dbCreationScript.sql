SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `DentalClinicDB` DEFAULT CHARACTER SET utf8MB4;
USE `DentalClinicDB` ;

CREATE TABLE IF NOT EXISTS `roles`(
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `comment` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `users`(
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `surname` VARCHAR(30) NOT NULL,
  `login` VARCHAR(30) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  `role_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`role_id`) REFERENCES `roles`(`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `personalData`(
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `surname` VARCHAR(30) NOT NULL,
  `phone` VARCHAR(30) NOT NULL,
  `personalCode` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `doctor`(
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `surname` VARCHAR(30) NOT NULL,
  `phone` VARCHAR(30) NOT NULL,
  `isEmployed` boolean NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `doctorsWorkGraphic`(
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `doctor_id` BIGINT NOT NULL,
  `monday_start` VARCHAR(30),
  `monday_end` VARCHAR(30),
  `tuesday_start` VARCHAR(30),
  `tuesday_end` VARCHAR(30),
  `wednesday_start` VARCHAR(30),
  `wednesday_end` VARCHAR(30),
  `thursday_start` VARCHAR(30),
  `thursday_end` VARCHAR(30),
  `friday_start` VARCHAR(30),
  `friday_end` VARCHAR(30),
  `saturday_start` VARCHAR(30),
  `saturday_end` VARCHAR(30),
  `sunday_start` VARCHAR(30),
  `sunday_end` VARCHAR(30),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`doctor_id`) REFERENCES `doctor`(`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `manipulation`(
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `manipulation_type` VARCHAR(30) NOT NULL,
  `price` int NOT NULL,
  `isActive` boolean NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `jowl`(
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `patient_id` BIGINT NOT NULL,
  `d18` int NOT NULL,
  `d17` int NOT NULL,
  `d16` int NOT NULL,
  `d15` int NOT NULL,
  `d14` int NOT NULL,
  `d13` int NOT NULL,
  `d12` int NOT NULL,
  `d11` int NOT NULL,
  `d21` int NOT NULL,
  `d22` int NOT NULL,
  `d23` int NOT NULL,
  `d24` int NOT NULL,
  `d25` int NOT NULL,
  `d26` int NOT NULL,
  `d27` int NOT NULL,
  `d28` int NOT NULL,
  `d55` int NOT NULL,
  `d54` int NOT NULL,
  `d53` int NOT NULL,
  `d52` int NOT NULL,
  `d51` int NOT NULL,
  `d61` int NOT NULL,
  `d62` int NOT NULL,
  `d63` int NOT NULL,
  `d64` int NOT NULL,
  `d65` int NOT NULL,
  `d48` int NOT NULL,
  `d47` int NOT NULL,
  `d46` int NOT NULL,
  `d45` int NOT NULL,
  `d44` int NOT NULL,
  `d43` int NOT NULL,
  `d42` int NOT NULL,
  `d41` int NOT NULL,
  `d31` int NOT NULL,
  `d32` int NOT NULL,
  `d33` int NOT NULL,
  `d34` int NOT NULL,
  `d35` int NOT NULL,
  `d36` int NOT NULL,
  `d37` int NOT NULL,
  `d38` int NOT NULL,
  `d85` int NOT NULL,
  `d84` int NOT NULL,
  `d83` int NOT NULL,
  `d82` int NOT NULL,
  `d81` int NOT NULL,
  `d71` int NOT NULL,
  `d72` int NOT NULL,
  `d73` int NOT NULL,
  `d74` int NOT NULL,
  `d75` int NOT NULL,
  `isMost` boolean NOT null,
  `m01s` bigint,
  `m01e` bigint,
  `m02s` bigint,
  `m02e` bigint,
  `m03s` bigint,
  `m03e` bigint,
  `m04s` bigint,
  `m04e` bigint,
  `m05s` bigint,
  `m05e` bigint,
  `m06s` bigint,
  `m06e` bigint,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`patient_id`) REFERENCES `personalData`(`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;


CREATE TABLE IF NOT EXISTS `visit`(
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `patient_id` BIGINT NOT NULL,
  `doctor_id` BIGINT NOT NULL,
  `manipulation_id` BIGINT NOT NULL,
  `dateAndTime` DATETIME NOT NULL,
  `sum` int NOT NULL,
  `tooth_number` INT NOT NULL,
  `tooth_status`  int NOT NULL,
  `Remarks` VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (`patient_id`) REFERENCES `personalData`(`id`),
  FOREIGN KEY (`doctor_id`) REFERENCES `doctor`(`id`),
  FOREIGN KEY (`manipulation_id`) REFERENCES `manipulation`(`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `plannedVisit`(
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `patient_id` BIGINT NOT NULL,
  `doctor_id` BIGINT NOT NULL,
  `dateAndTime` DATETIME NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (`patient_id`) REFERENCES `personalData`(`id`),
  FOREIGN KEY (`doctor_id`) REFERENCES `doctor`(`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;