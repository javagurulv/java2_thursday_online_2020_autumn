SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

use adventure_db;

create table if not exists tours (
	id bigint unsigned auto_increment ,
    event bigint unsigned not null,
    tour_start date not null,
    ticket_coast decimal(8,2) not null,
    available_tickets int not null,
    completed boolean default false,
    primary key (id),
    foreign key (event) references events (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 10001;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;