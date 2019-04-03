USE mysql;

CREATE DATABASE PolybaseTest;

CREATE USER 'polybase'@'localhost' IDENTIFIED BY 'Vus9ebra';
GRANT SELECT,INSERT,UPDATE,EXECUTE,DELETE ON Cerberus.* TO 'polybase'@'localhost';

USE PoybaseTes;
