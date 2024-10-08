CREATE TABLE `covidcase` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NULL,
  `status` VARCHAR(45) NULL,
  `age_group` VARCHAR(45) NULL,
  `gender` VARCHAR(45) NULL,
  `count` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
