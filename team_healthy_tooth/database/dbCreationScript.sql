SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `DentalClinicDB` DEFAULT CHARACTER SET utf8MB4;
USE `DentalClinicDB` ;

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
  `d18` VARCHAR(20) NOT NULL,
  `d17` VARCHAR(20) NOT NULL,
  `d16` VARCHAR(20) NOT NULL,
  `d15` VARCHAR(20) NOT NULL,
  `d14` VARCHAR(20) NOT NULL,
  `d13` VARCHAR(20) NOT NULL,
  `d12` VARCHAR(20) NOT NULL,
  `d11` VARCHAR(20) NOT NULL,
  `d21` VARCHAR(20) NOT NULL,
  `d22` VARCHAR(20) NOT NULL,
  `d23` VARCHAR(20) NOT NULL,
  `d24` VARCHAR(20) NOT NULL,
  `d25` VARCHAR(20) NOT NULL,
  `d26` VARCHAR(20) NOT NULL,
  `d27` VARCHAR(20) NOT NULL,
  `d28` VARCHAR(20) NOT NULL,
  `d55` VARCHAR(20) NOT NULL,
  `d54` VARCHAR(20) NOT NULL,
  `d53` VARCHAR(20) NOT NULL,
  `d52` VARCHAR(20) NOT NULL,
  `d51` VARCHAR(20) NOT NULL,
  `d61` VARCHAR(20) NOT NULL,
  `d62` VARCHAR(20) NOT NULL,
  `d63` VARCHAR(20) NOT NULL,
  `d64` VARCHAR(20) NOT NULL,
  `d65` VARCHAR(20) NOT NULL,
  `d48` VARCHAR(20) NOT NULL,
  `d47` VARCHAR(20) NOT NULL,
  `d46` VARCHAR(20) NOT NULL,
  `d45` VARCHAR(20) NOT NULL,
  `d44` VARCHAR(20) NOT NULL,
  `d43` VARCHAR(20) NOT NULL,
  `d42` VARCHAR(20) NOT NULL,
  `d41` VARCHAR(20) NOT NULL,
  `d31` VARCHAR(20) NOT NULL,
  `d32` VARCHAR(20) NOT NULL,
  `d33` VARCHAR(20) NOT NULL,
  `d34` VARCHAR(20) NOT NULL,
  `d35` VARCHAR(20) NOT NULL,
  `d36` VARCHAR(20) NOT NULL,
  `d37` VARCHAR(20) NOT NULL,
  `d38` VARCHAR(20) NOT NULL,
  `d85` VARCHAR(20) NOT NULL,
  `d84` VARCHAR(20) NOT NULL,
  `d83` VARCHAR(20) NOT NULL,
  `d82` VARCHAR(20) NOT NULL,
  `d81` VARCHAR(20) NOT NULL,
  `d71` VARCHAR(20) NOT NULL,
  `d72` VARCHAR(20) NOT NULL,
  `d73` VARCHAR(20) NOT NULL,
  `d74` VARCHAR(20) NOT NULL,
  `d75` VARCHAR(20) NOT NULL,
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
  `tooth_status`  VARCHAR(30) NOT NULL,
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