SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

use adventure_db;

create table if not exists tickets (
    id bigint unsigned auto_increment,
    tour bigint unsigned not null,
    customer bigint unsigned references customers (id),
    sale_date date not null,
    available boolean default true,
    primary key (id),
    foreign key (tour) references tours (id),
    foreign key (customer) references customers (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 10001;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;