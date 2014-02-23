/*
SQLyog Community v11.31 (64 bit)
MySQL - 5.1.48-community 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `fabricacion` (
	`id` bigint (20),
	`numero_serie` varchar (300),
	`codigo` bigint (10),
	`modelo` varchar (300),
	`fecha_fabricacion` timestamp ,
	`proveedor` varchar (300)
); 
