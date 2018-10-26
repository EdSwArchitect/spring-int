CREATE TABLE `dblearning`.`brooklyn_bridge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start_date` DATETIME NOT NULL,
  `location` VARCHAR(64) NOT NULL,
  `pedestrians` INT NOT NULL,
  `towards_m` INT NOT NULL,
  `towards_b` INT NOT NULL,
  `weather` VARCHAR(64) NOT NULL,
  `temperature` INT NOT NULL,
  `precipitation` DOUBLE NOT NULL,
  `lat` DOUBLE NOT NULL,
  `lon` DOUBLE NOT NULL,
  `events` VARCHAR(64) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
