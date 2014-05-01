SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `administracion` ;
CREATE SCHEMA IF NOT EXISTS `administracion` DEFAULT CHARACTER SET utf8 ;
USE `administracion` ;

-- -----------------------------------------------------
-- Table `administracion`.`cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administracion`.`cuenta` (
  `idcuenta` INT(11) NOT NULL,
  `descripcion` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`idcuenta`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `administracion`.`empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administracion`.`empresa` (
  `idEmpresa` INT(11) NOT NULL AUTO_INCREMENT,
  `nombreEmpresa` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idEmpresa`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `administracion`.`cuenta_empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administracion`.`cuenta_empresa` (
  `idEmpresaC` INT(11) NOT NULL,
  `idCuentaC` INT(11) NOT NULL,
  `idSubCuenta` INT(11) NULL DEFAULT NULL,
  `descripcionSub` VARCHAR(60) NULL DEFAULT NULL,
  `saldo` DOUBLE NULL DEFAULT NULL,
  INDEX `idEmpresaC_idx` (`idEmpresaC` ASC),
  INDEX `idCuentaC_idx` (`idCuentaC` ASC),
  CONSTRAINT `idEmpresaC`
    FOREIGN KEY (`idEmpresaC`)
    REFERENCES `administracion`.`empresa` (`idEmpresa`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `idCuentaC`
    FOREIGN KEY (`idCuentaC`)
    REFERENCES `administracion`.`cuenta` (`idcuenta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `administracion`.`poliza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administracion`.`poliza` (
  `idEmpresaP` INT(11) NOT NULL,
  `idCuentaP` INT(11) NOT NULL,
  `idSubCuentaP` INT(11) NULL DEFAULT NULL,
  `NoPoliza` SMALLINT(6) NOT NULL,
  `fecha` DATE NOT NULL,
  `Cargos` INT(11) NULL DEFAULT NULL,
  `Abonos` INT(11) NULL DEFAULT NULL,
  INDEX `idEmpresaP_idx` (`idEmpresaP` ASC),
  INDEX `idCuentaP_idx` (`idCuentaP` ASC),
  CONSTRAINT `idCuentaP`
    FOREIGN KEY (`idCuentaP`)
    REFERENCES `administracion`.`cuenta` (`idcuenta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `idEmpresaP`
    FOREIGN KEY (`idEmpresaP`)
    REFERENCES `administracion`.`empresa` (`idEmpresa`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `administracion`.`tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administracion`.`tipo` (
  `idtipo` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idtipo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `administracion`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administracion`.`usuario` (
  `Usuario` VARCHAR(15) NOT NULL,
  `Contrasena` VARCHAR(16) NOT NULL,
  `NomPila` VARCHAR(30) NOT NULL,
  `ApPaterno` VARCHAR(20) NOT NULL,
  `ApMaterno` VARCHAR(20) NOT NULL,
  `idTipo` INT(11) NOT NULL,
  `idEmp` INT(11) NOT NULL,
  PRIMARY KEY (`Usuario`),
  INDEX `idEmp_idx` (`idEmp` ASC),
  INDEX `idTipo_idx` (`idTipo` ASC),
  CONSTRAINT `idEmp`
    FOREIGN KEY (`idEmp`)
    REFERENCES `administracion`.`empresa` (`idEmpresa`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `idTipo`
    FOREIGN KEY (`idTipo`)
    REFERENCES `administracion`.`tipo` (`idtipo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `administracion` ;

-- -----------------------------------------------------
-- Placeholder table for view `administracion`.`cuentas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `administracion`.`cuentas` (`idEmpresaC` INT, `idCuentaC` INT, `descripcion` INT, `idSubCuenta` INT, `descripcionSub` INT, `saldo` INT);

-- -----------------------------------------------------
-- View `administracion`.`cuentas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `administracion`.`cuentas`;
USE `administracion`;
CREATE  OR REPLACE VIEW `cuentas` AS 
SELECT idEmpresaC, idCuentaC, cuenta.descripcion, idSubCuenta, descripcionSub, saldo 
FROM administracion.cuenta, administracion.cuenta_empresa
WHERE cuenta.idcuenta = idCuentaC;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `administracion`.`cuenta`
-- -----------------------------------------------------
START TRANSACTION;
USE `administracion`;
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1101, 'Caja');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1102, 'Bancos');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1103, 'Inversiones Temporales');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1104, 'Clientes');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1105, 'Documentos por cobrar');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1106, 'Deudores diversos');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1107, 'Almacén');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1108, 'IVA acreditable');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1109, 'IVA por acreditar');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1110, 'Rentas pagadas por anticipado');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1111, 'Primas de seguro');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1112, 'Intereses pagados por anticipado');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1113, 'Papelería y útiles');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1114, 'Propaganda y publicidad');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1201, 'Terrenos');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1202, 'Edificio');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1203, 'Mobiliario y equipo de oficina');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1204, 'Equipo de reparto');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1205, 'Equipo de Cómputo');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1301, 'Crédito mercantil');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1302, 'Patentes');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1303, 'Marcas');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1401, 'Depósitos en garantía');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1402, 'Gastos de organización');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (1403, 'Gastos de instalación');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (2101, 'Proveedores');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (2102, 'Acreedores diversos');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (2103, 'Documentos por pagar');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (2104, 'IVA trasladado');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (2105, 'IVA por trasladar');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (2106, 'Impuestos por pagar');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (2107, 'Intereses cobrados por anticipado');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (2108, 'Rentas cobradas por anticipado');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (2109, 'PTU por pagar');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (2201, 'Docuemtnos por pagar a largo plazo');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (2202, 'Acreedores hipotecarios');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (2203, 'Crédito refaccionario');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (2204, 'Intereses cobrados por anticipado');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (3101, 'Capital Social');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (3102, 'Aportaciones para futuros aumentos de capital');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (3103, 'Donaciones');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (3104, 'Prima en venta de acciones');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (3201, 'Utilidades de ejercicios anteriores');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (3202, 'Pérdidas de ejercicios anteriores');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (3203, 'Reserva legal');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (3204, 'Utilidad del ejercicio');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (3205, 'Pérdida del ejercicio');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (4101, 'Depreciación acumulada de edificio');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (4102, 'Depreciación acumulada de mobiliario y equipo');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (4103, 'Depreciación acumulada de equipo de transporte');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (4104, 'Depreciación acumulada de equipo de cómputo');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (5100, 'Costo de ventas');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (5101, 'Gastos de venta');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (5102, 'Gastos de Administración');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (5103, 'Gastos por intereses');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (5104, 'Otros gastos');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (6100, 'Ventas');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (6102, 'Otros ingresos');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (6103, 'Otros gastos');
INSERT INTO `administracion`.`cuenta` (`idcuenta`, `descripcion`) VALUES (6104, 'Intereses cobrados');

COMMIT;


-- -----------------------------------------------------
-- Data for table `administracion`.`empresa`
-- -----------------------------------------------------
START TRANSACTION;
USE `administracion`;
INSERT INTO `administracion`.`empresa` (`idEmpresa`, `nombreEmpresa`) VALUES (1, 'Jolmes y Asociados S.A. de C.V.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `administracion`.`cuenta_empresa`
-- -----------------------------------------------------
START TRANSACTION;
USE `administracion`;
INSERT INTO `administracion`.`cuenta_empresa` (`idEmpresaC`, `idCuentaC`, `idSubCuenta`, `descripcionSub`, `saldo`) VALUES (1, 1101, NULL, NULL, 1000010.5);
INSERT INTO `administracion`.`cuenta_empresa` (`idEmpresaC`, `idCuentaC`, `idSubCuenta`, `descripcionSub`, `saldo`) VALUES (1, 1102, NULL, NULL, 5000000);
INSERT INTO `administracion`.`cuenta_empresa` (`idEmpresaC`, `idCuentaC`, `idSubCuenta`, `descripcionSub`, `saldo`) VALUES (1, 1102, 01, 'Bancomer', 1000000);
INSERT INTO `administracion`.`cuenta_empresa` (`idEmpresaC`, `idCuentaC`, `idSubCuenta`, `descripcionSub`, `saldo`) VALUES (1, 1102, 02, 'Banamex', 1500000);
INSERT INTO `administracion`.`cuenta_empresa` (`idEmpresaC`, `idCuentaC`, `idSubCuenta`, `descripcionSub`, `saldo`) VALUES (1, 1102, 03, 'HSBC', 1500000);
INSERT INTO `administracion`.`cuenta_empresa` (`idEmpresaC`, `idCuentaC`, `idSubCuenta`, `descripcionSub`, `saldo`) VALUES (1, 1102, 04, 'Banorte', 1000000);

COMMIT;


-- -----------------------------------------------------
-- Data for table `administracion`.`tipo`
-- -----------------------------------------------------
START TRANSACTION;
USE `administracion`;
INSERT INTO `administracion`.`tipo` (`idtipo`, `descripcion`) VALUES (1, 'Administrador');
INSERT INTO `administracion`.`tipo` (`idtipo`, `descripcion`) VALUES (2, 'Contador');

COMMIT;


-- -----------------------------------------------------
-- Data for table `administracion`.`usuario`
-- -----------------------------------------------------
START TRANSACTION;
USE `administracion`;
INSERT INTO `administracion`.`usuario` (`Usuario`, `Contrasena`, `NomPila`, `ApPaterno`, `ApMaterno`, `idTipo`, `idEmp`) VALUES ('jolmes', '123456', 'Jorge', 'Macías', 'Escalona', 1, 1);
INSERT INTO `administracion`.`usuario` (`Usuario`, `Contrasena`, `NomPila`, `ApPaterno`, `ApMaterno`, `idTipo`, `idEmp`) VALUES ('ana_puffy', '123456', 'Ana', 'Casas', 'García', 1, 1);
INSERT INTO `administracion`.`usuario` (`Usuario`, `Contrasena`, `NomPila`, `ApPaterno`, `ApMaterno`, `idTipo`, `idEmp`) VALUES ('monse', '123456', 'Monse', 'Chimal', 'Ramírez', 2, 1);
INSERT INTO `administracion`.`usuario` (`Usuario`, `Contrasena`, `NomPila`, `ApPaterno`, `ApMaterno`, `idTipo`, `idEmp`) VALUES ('manuel', '123456', 'Manuel', 'Hernández', 'León', 2, 1);

COMMIT;

