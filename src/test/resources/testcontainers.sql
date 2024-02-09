-- MySQL Workbench Forward Engineering
DROP DATABASE IF EXISTS `test`;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `test` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `test` ;

CREATE TABLE IF NOT EXISTS `test`.`person` (
                                                 `dtype` VARCHAR(31) NOT NULL,
                                                 `id` BIGINT NOT NULL,
                                                 `address` VARCHAR(255) NULL DEFAULT NULL,
                                                 `city` VARCHAR(255) NULL DEFAULT NULL,
                                                 `last_name` VARCHAR(255) NULL DEFAULT NULL,
                                                 `name` VARCHAR(255) NULL DEFAULT NULL,
                                                 `email` VARCHAR(255) NULL DEFAULT NULL,
                                                 `password` VARCHAR(255) NULL DEFAULT NULL,
                                                 `phone_number` VARCHAR(255) NULL DEFAULT NULL,
                                                 PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `test`.`account` (
                                                  `number` BIGINT NOT NULL,
                                                  `balance` DECIMAL(38,2) NULL DEFAULT NULL,
                                                  `type` ENUM('SAVINGS', 'CURRENT', 'FIXED_DEPOSIT', 'RECURRING_DEPOSIT', 'UNKNOWN') NULL DEFAULT NULL,
                                                  `owner_id` BIGINT NULL DEFAULT NULL,
                                                  PRIMARY KEY (`number`),
                                                  INDEX `FK616isdyvoleb7sljrd32uhhw4` (`owner_id` ASC) VISIBLE,
                                                  CONSTRAINT `FK616isdyvoleb7sljrd32uhhw4`
                                                      FOREIGN KEY (`owner_id`)
                                                          REFERENCES `test`.`person` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `test`.`pocket` (
                                                 `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                 `balance` DECIMAL(38,2) NULL DEFAULT NULL,
                                                 `name` VARCHAR(255) NULL DEFAULT NULL,
                                                 `account_number` BIGINT NULL DEFAULT NULL,
                                                 PRIMARY KEY (`id`),
                                                 INDEX `FK15fjan5e5wdcoao3yjl25gd12` (`account_number` ASC) VISIBLE,
                                                 CONSTRAINT `FK15fjan5e5wdcoao3yjl25gd12`
                                                     FOREIGN KEY (`account_number`)
                                                         REFERENCES `test`.`account` (`number`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;