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
    `correoElectronico` VARCHAR(45) UNIQUE NOT NULL,
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
    `contrasena` VARCHAR(45) NOT NULL,
	`correoElectronico` VARCHAR(45) UNIQUE NOT NULL,
	`dni` VARCHAR(9) UNIQUE NOT NULL,
	`direccion` VARCHAR(45) NOT NULL,
	`ciudad` VARCHAR(45) NOT NULL,
	`codigoPostal` INT NOT NULL,
	`fechaCarne` DATE NOT NULL,
	PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Coche`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cocheOfertado` ;

CREATE TABLE IF NOT EXISTS `mydb`.`cocheOfertado` (
	`idCocheOfertado` INT UNIQUE NOT NULL AUTO_INCREMENT,
	`matricula` VARCHAR(7) UNIQUE NOT NULL,
	`marca` VARCHAR(45) NOT NULL,
	`modelo` VARCHAR(45) NOT NULL,
	`numeroPuertas` INT NOT NULL,
	`capacidadMaletero` INT NOT NULL,
	`cambioMarchas` VARCHAR(45) NOT NULL,
	`plazas` INT NOT NULL,
	`aireAcondicionado` BOOLEAN NOT NULL,
	`precioPorDia` DOUBLE NOT NULL,
    `enMantenimiento` BOOLEAN NOT NULL,
    `politicaCombustible` VARCHAR(45) NOT NULL,
	`politicaCancelacion` INT NOT NULL,
    `kilometraje` INT NOT NULL,
    `idFilialCoche` INT NOT NULL,

	PRIMARY KEY (`idCocheOfertado`),
    CONSTRAINT `idFilialCoche`
		FOREIGN KEY (`idFilialCoche`)
		REFERENCES `mydb`.`filial` (`idFilial`)
		ON DELETE CASCADE
		ON UPDATE CASCADE)
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
	`idDisponibilidad` INT UNIQUE NOT NULL AUTO_INCREMENT,
	`idCocheDisponibilidad` INT NOT NULL,
    `fechaNoDisp` DATE NOT NULL,
	`fechaInicioDisp` DATE NOT NULL,
    PRIMARY KEY (`idDisponibilidad`),
    CONSTRAINT `idCocheDisponibilidad`
		FOREIGN KEY (`idCocheDisponibilidad`)
		REFERENCES `mydb`.`cocheOfertado` (`idCocheOfertado`)
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
	`idUsuarioPago` INT NOT NULL,
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
    `fechaRecogida` DATE NOT NULL,
    `fechaEntrega` DATE NOT NULL,
    `precioFinal` FLOAT NOT NULL,
    `idCiudadRecogida` INT NOT NULL,
	`idCiudadEntrega` INT NOT NULL,
	`idCocheReservado` INT NOT NULL,
	`idPagoReserva` INT NOT NULL,
	PRIMARY KEY (`idReserva`),
	CONSTRAINT `idPagoReserva`
		FOREIGN KEY (`idPagoReserva`)
		REFERENCES `mydb`.`pago` (`idPago`)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT `idCocheReservado`
		FOREIGN KEY (`idCocheReservado`)
		REFERENCES `mydb`.`cocheOfertado` (`idCocheOfertado`)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT `idCiudadRecogida`
		FOREIGN KEY (`idCiudadRecogida`)
        REFERENCES `mydb`.`filial` (`idFilial`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT `idCiudadEntrega`
		FOREIGN KEY (`idCiudadEntrega`)
        REFERENCES `mydb`.`filial` (`idFilial`)
        ON DELETE CASCADE
        ON UPDATE CASCADE)
ENGINE =  InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;