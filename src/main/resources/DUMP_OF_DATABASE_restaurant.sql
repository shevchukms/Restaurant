-- --------------------------------------------------------
-- Сервер:                       localhost
-- Версія сервера:               5.7.17-log - MySQL Community Server (GPL)
-- ОС сервера:                   Win64
-- HeidiSQL Версія:              9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for restaurant
CREATE DATABASE IF NOT EXISTS `restaurant` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `restaurant`;

-- Dumping structure for таблиця restaurant.clients
CREATE TABLE IF NOT EXISTS `clients` (
  `client_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_name` varchar(20) DEFAULT NULL,
  `client_number_of_table` int(11) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table restaurant.clients: ~1 rows (приблизно)
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` (`client_id`, `client_name`, `client_number_of_table`) VALUES
	(1, 'Dmytro', 1);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;

-- Dumping structure for таблиця restaurant.dishes
CREATE TABLE IF NOT EXISTS `dishes` (
  `dish_id` int(11) NOT NULL AUTO_INCREMENT,
  `dish_name` varchar(300) DEFAULT NULL,
  `dish_amount` double DEFAULT NULL,
  `dish_price` double DEFAULT NULL,
  PRIMARY KEY (`dish_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table restaurant.dishes: ~0 rows (приблизно)
/*!40000 ALTER TABLE `dishes` DISABLE KEYS */;
INSERT INTO `dishes` (`dish_id`, `dish_name`, `dish_amount`, `dish_price`) VALUES
	(1, 'Cezar', 250, 50),
	(2, 'tea', 250, 55),
	(3, 'Coffe', 250, 55),
	(4, 'tiramisu', 120, 55),
	(5, 'pannacota', 100, 55),
	(6, 'pizza prosciutto cotto', 350, 95),
	(7, 'pizza fungi', 300, 85);
/*!40000 ALTER TABLE `dishes` ENABLE KEYS */;

-- Dumping structure for таблиця restaurant.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) DEFAULT NULL,
  `dish_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK_client` (`client_id`),
  KEY `FK_dish` (`dish_id`),
  CONSTRAINT `FK_client` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`),
  CONSTRAINT `FK_dish` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`dish_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table restaurant.orders: ~0 rows (приблизно)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
