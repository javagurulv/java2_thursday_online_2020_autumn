SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

use adventure_db;

create table customers (
	id bigint unsigned auto_increment,
    name varchar(50) not null,
    email varchar(30) not null unique,
    phone varchar(12) not null unique,
    password varchar(20) not null,
    primary key (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1001;

insert into customers (name, email, phone, password) values ('Wilbur Soot', 'will_weel@yandex.com', '+37000700700', '12345@Asdfg');
insert into customers (name, email, phone, password) values ('Tommy Innit', 'tomcat@yahoo.com', '+37137137111', '12345@Asdfg');
insert into customers (name, email, phone, password) values ('Tubbo Bee', 'ilikethebee@hotmail.com', '+37176395027', '12345@Asdfg');
insert into customers (name, email, phone, password) values ('Its Fundy', 'furry@inbox.lt', '+37140982648', '12345@Asdfg');
insert into customers (name, email, phone, password) values ('Niki Nihachu', 'apch_huu@gmail.com', '+37122288800', '12345@Asdfg');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;