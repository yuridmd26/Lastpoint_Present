-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema presentdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema presentdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `presentdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `presentdb` ;

-- -----------------------------------------------------
-- Table `presentdb`.`TB_DISCIPLINE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `presentdb`.`TB_DISCIPLINE` (
  `DIS_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `DIS_CODE` VARCHAR(50) NOT NULL,
  `DIS_NAME` VARCHAR(100) NOT NULL,
  `DIS_DESCRIPTION` VARCHAR(255) NULL,
  PRIMARY KEY (`DIS_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `presentdb`.`TB_GROUP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `presentdb`.`TB_GROUP` (
  `GRO_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `GRO_CODE` VARCHAR(50) NOT NULL,
  `GRO_NAME` VARCHAR(100) NOT NULL,
  `GRO_DESCRIPTION` VARCHAR(255) NULL,
  PRIMARY KEY (`GRO_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `presentdb`.`TB_USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `presentdb`.`TB_USER` (
  `USE_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `USE_CODE` VARCHAR(50) NOT NULL,
  `USE_NAME` VARCHAR(100) NOT NULL,
  `USE_LOGIN` VARCHAR(100) NOT NULL,
  `USE_PASSWORD` VARCHAR(100) NOT NULL,
  `USE_TYPE` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`USE_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `presentdb`.`TB_EVENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `presentdb`.`TB_EVENT` (
  `EVE_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `EVE_CODE` VARCHAR(50) NOT NULL,
  `EVE_NAME` VARCHAR(100) NOT NULL,
  `EVE_DESCRIPTION` VARCHAR(255) NULL,
  `EVE_STARTDATETIME` DATETIME NOT NULL,
  `EVE_ENDDATETIME` DATETIME NOT NULL,
  `EVE_USEID` BIGINT NOT NULL,
  PRIMARY KEY (`EVE_ID`),
  INDEX `IND_EVE_USEID` (`EVE_USEID` ASC) VISIBLE,
  CONSTRAINT `FK_EVE_USEID`
    FOREIGN KEY (`EVE_USEID`)
    REFERENCES `presentdb`.`TB_USER` (`USE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `presentdb`.`TB_CLASS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `presentdb`.`TB_CLASS` (
  `CLA_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `CLA_GROID` BIGINT NOT NULL,
  `CLA_DISID` BIGINT NOT NULL,
  `CLA_EVEID` BIGINT NOT NULL,
  PRIMARY KEY (`CLA_ID`),
  INDEX `IND_CLA_GROID` (`CLA_GROID` ASC) VISIBLE,
  INDEX `IND_CLA_DISID` (`CLA_DISID` ASC) VISIBLE,
  INDEX `IND_CLA_EVEID` (`CLA_EVEID` ASC) VISIBLE,
  CONSTRAINT `FK_CLA_DISID`
    FOREIGN KEY (`CLA_DISID`)
    REFERENCES `presentdb`.`TB_DISCIPLINE` (`DIS_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CLA_GROID`
    FOREIGN KEY (`CLA_GROID`)
    REFERENCES `presentdb`.`TB_GROUP` (`GRO_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CLA_EVEID`
    FOREIGN KEY (`CLA_EVEID`)
    REFERENCES `presentdb`.`TB_EVENT` (`EVE_ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `presentdb`.`TB_COURSE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `presentdb`.`TB_COURSE` (
  `COU_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `COU_CODE` VARCHAR(50) NOT NULL,
  `COU_NAME` VARCHAR(100) NOT NULL,
  `COU_DESCRIPTION` VARCHAR(255) NULL,
  PRIMARY KEY (`COU_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `presentdb`.`TB_CLASS_COURSE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `presentdb`.`TB_CLASS_COURSE` (
  `CCO_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `CCO_COUID` BIGINT NOT NULL,
  `CCO_CLAID` BIGINT NOT NULL,
  PRIMARY KEY (`CCO_ID`),
  INDEX `IND_CCO_COUID` (`CCO_COUID` ASC) VISIBLE,
  INDEX `IND_CCO_CLAID` (`CCO_CLAID` ASC) VISIBLE,
  CONSTRAINT `FK_CCO_COUID`
    FOREIGN KEY (`CCO_COUID`)
    REFERENCES `presentdb`.`TB_COURSE` (`COU_ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCO_CLAID`
    FOREIGN KEY (`CCO_CLAID`)
    REFERENCES `presentdb`.`TB_CLASS` (`CLA_ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `presentdb`.`TB_ATTENDANCE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `presentdb`.`TB_ATTENDANCE` (
  `ATT_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `ATT_USEID` BIGINT NOT NULL,
  `ATT_EVEID` BIGINT NOT NULL,
  PRIMARY KEY (`ATT_ID`),
  INDEX `IND_ATT_USEID` (`ATT_USEID` ASC) VISIBLE,
  INDEX `IND_ATT_EVEID` (`ATT_EVEID` ASC) VISIBLE,
  CONSTRAINT `FK_ATT_USEID`
    FOREIGN KEY (`ATT_USEID`)
    REFERENCES `presentdb`.`TB_USER` (`USE_ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ATT_EVEID`
    FOREIGN KEY (`ATT_EVEID`)
    REFERENCES `presentdb`.`TB_EVENT` (`EVE_ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `presentdb`.`TB_USER_EVENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `presentdb`.`TB_USER_EVENT` (
  `UEV_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `UEV_USEID` BIGINT NOT NULL,
  `UEV_EVEID` BIGINT NOT NULL,
  PRIMARY KEY (`UEV_ID`),
  INDEX `IND_UEV_USEID` (`UEV_USEID` ASC) VISIBLE,
  INDEX `IND_UEV_EVEID` (`UEV_EVEID` ASC) INVISIBLE,
  CONSTRAINT `FK_UEV_USEID`
    FOREIGN KEY (`UEV_USEID`)
    REFERENCES `presentdb`.`TB_USER` (`USE_ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_UEV_EVEID`
    FOREIGN KEY (`UEV_EVEID`)
    REFERENCES `presentdb`.`TB_EVENT` (`EVE_ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
