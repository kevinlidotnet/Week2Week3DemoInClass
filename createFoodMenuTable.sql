CREATE TABLE `foodmenu` (
  `idfoodmenu` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `spicylevel` int DEFAULT '0',
  `calorie` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`idfoodmenu`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
