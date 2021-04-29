-- Mon Apr  5 18:40 2021
-- Model: New Model    Version: 1.0


SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';



-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Trabajadores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`trabajadores` ;

CREATE TABLE IF NOT EXISTS `mydb`.`trabajadores` (
	`idTrabajador` INT UNIQUE NOT NULL AUTO_INCREMENT,
    `correoElectronico` VARCHAR(45) NOT NULL,
    `contraseña` VARCHAR(45) NOT NULL,
    `rol` INT NOT NULL, -- 0 para admin general, 1 para administrador de una filial
    `idFilialTrabajador` INT,
    PRIMARY KEY (`idTrabajador`),
    CONSTRAINT `idFilialTrabajador`
		FOREIGN KEY (`idFilialTrabajador`)
		REFERENCES `mydb`.`filial` (`idFilial`)
		ON DELETE CASCADE
		ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`usuario` ;

CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
	`idUsuario` INT UNIQUE NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(45) NOT NULL,
	`apellidos` VARCHAR(45) NOT NULL,
	`correoElectronico` VARCHAR(45) UNIQUE NOT NULL,
	`dni` VARCHAR(9) UNIQUE NOT NULL,
	`fechaCaducidad` VARCHAR(45) NOT NULL,
	`direccion` VARCHAR(45) NOT NULL,
	`ciudad` VARCHAR(45) NOT NULL,
	`pais` VARCHAR(45) NOT NULL,
	`codigoPostal` INT NOT NULL,
	`fotoAnversoDNI` VARBINARY(8000) NOT NULL,
	`fotoReversoDNI` VARBINARY(8000) NOT NULL,
	`fechaCarne` DATE NOT NULL,
	PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Coche`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`coche` ;

CREATE TABLE IF NOT EXISTS `mydb`.`coche` (
	`idCoche` INT UNIQUE NOT NULL AUTO_INCREMENT,
	`modelo` VARCHAR(45) NOT NULL,
	`matricula` VARCHAR(7) UNIQUE NOT NULL,
	`marca` VARCHAR(45) NOT NULL,
	`numeroPuertas` INT NOT NULL,
	`capacidadMaletero` INT NOT NULL,
	`cambioMarchas` VARCHAR(45) NOT NULL,
	`plazas` INT NOT NULL,
	`aireCondicionado` BOOLEAN NOT NULL,
	`gama` VARCHAR(45) NOT NULL,
	`precioPorDia` DOUBLE NOT NULL,
    `enMantenimiento` BOOLEAN,
    `idFilialCoche` INT NOT NULL,
    `idModelo` INT NOT NULL,
	PRIMARY KEY (`idCoche`),
    CONSTRAINT `idFilialCoche`
		FOREIGN KEY (`idFilialCoche`)
		REFERENCES `mydb`.`filial` (`idFilial`)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT `idModelo`
		FOREIGN KEY (`idModelo`)
		REFERENCES `mydb`.`modelo` (`idModelo`)
        ON DELETE CASCADE
        ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Modelo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`modelo` ;

CREATE TABLE IF NOT EXISTS `mydb`.`modelo` (
	`idModelo` INT UNIQUE NOT NULL AUTO_INCREMENT,
    `modelo` VARCHAR(45) NOT NULL)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`filial`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`filial`;

CREATE TABLE IF NOT EXISTS `mydb`.`filial` (
	`idFilial` INT UNIQUE NOT NULL AUTO_INCREMENT,
    `comunidad` VARCHAR(45) NOT NULL,
    `ciudad` VARCHAR(45) NOT NULL,
    `calle` VARCHAR(45) NOT NULL,
    `numero` INT,
    PRIMARY KEY (`idFilial`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Disponibilidad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`disponibilidad`; 

CREATE TABLE IF NOT EXISTS `mydb`.`disponibilidad` (
	`idCocheDisponibilidad` INT NOT NULL,
    `disponibilidad` DATE NOT NULL,
    PRIMARY KEY (`idCocheDisponibilidad`),
    CONSTRAINT `idCocheDisponibilidad`
		FOREIGN KEY (`idCocheDisponibilidad`)
		REFERENCES `mydb`.`coche` (`idCoche`)
		ON DELETE CASCADE
		ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Oferta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`oferta` ;

CREATE TABLE IF NOT EXISTS `mydb`.`oferta` (
	`idOferta` INT UNIQUE NOT NULL AUTO_INCREMENT,
	`politicaCombustible` VARCHAR(45) NOT NULL,
	`politicaCancelacion` INT NOT NULL,
    `suplemento` DOUBLE NOT NULL,
	`kilometraje` INT NOT NULL,
	PRIMARY KEY (`idOferta`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`RelacionOfertaCoche`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`relacionOfertaCoche` ;

CREATE TABLE IF NOT EXISTS `mydb`.`relacionOfertaCoche` (
    `idCocheRelacion` INT NOT NULL,
    `idOfertaRelacion` INT NOT NULL,
    PRIMARY KEY (`idCocheRelacion`, `idOfertaRelacion`),
    CONSTRAINT `idCocheRelacion`
		FOREIGN KEY (`idCocheRelacion`)
		REFERENCES `mydb`.`coche` (`idCoche`)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT `idOfertaRelacion`
		FOREIGN KEY (`idOfertaRelacion`)
        REFERENCES `mybd`.`oferta` (`idOferta`)
        ON DELETE CASCADE
        ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pago`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`pago` ;

CREATE TABLE IF NOT EXISTS `mydb`.`pago` (
	`idPago` INT UNIQUE NOT NULL AUTO_INCREMENT,
	`Titular` VARCHAR(45) NOT NULL,
	`numTarjeta` VARCHAR(16) NOT NULL,
	`idUsuarioPago` INT UNIQUE NOT NULL,
	PRIMARY KEY (`idPago`),
	CONSTRAINT `idUsuarioPago`
		FOREIGN KEY (`idUsuarioPago`)
		REFERENCES `mydb`.`usuario` (`idUsuario`)
		ON DELETE CASCADE
		ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Reservas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`reserva` ;

CREATE TABLE IF NOT EXISTS `mydb`.`reserva` (
	`idReserva` INT UNIQUE NOT NULL AUTO_INCREMENT,
    `fechaReserva` DATE NOT NULL,
    `fechaRecogidaVEH` DATE NOT NULL,
    `fechaEntregaVEH` DATE NOT NULL,
    `ciudadRecogidaVEH` VARCHAR(45) NOT NULL,
	`ciudadEntregaVEH` VARCHAR(45) NOT NULL,
	`precioFinal` FLOAT NOT NULL,
	`idOfertaReserva` INT UNIQUE NOT NULL,
	`idPagoReserva` INT UNIQUE NOT NULL,
	PRIMARY KEY (`idReserva`),
	CONSTRAINT `idPagoReserva`
		FOREIGN KEY (`idPagoReserva`)
		REFERENCES `mydb`.`pago` (`idPago`)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT `idOfertaReserva`
		FOREIGN KEY (`idOfertaReserva`)
		REFERENCES `mydb`.`oferta` (`idOferta`)
		ON DELETE CASCADE
		ON UPDATE CASCADE)
ENGINE =  InnoDB;


-- -----------------------------------------------------
-- Inserts table `mydb`.`Trabajadores`
-- -----------------------------------------------------
INSERT INTO trabajadores (`idTrabajador`,`correoElectronico`,`contraseña`,`rol`) VALUES (1, '1', '1', 0);



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;