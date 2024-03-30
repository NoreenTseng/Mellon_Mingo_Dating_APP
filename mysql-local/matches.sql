DROP TABLE IF EXISTS `matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;

CREATE TABLE `matches` (
                           `match_id` int(11) NOT NULL AUTO_INCREMENT,
                           `user_id_1` int(11) NOT NULL,
                           `user_id_2` int(11) NOT NULL,
                           `matched_at` datetime DEFAULT NULL,
                           PRIMARY KEY (`match_id`),
                           FOREIGN KEY (`user_id_1`) REFERENCES `profiles` (`id`),
                           FOREIGN KEY (`user_id_2`) REFERENCES `profiles` (`id`),
                           UNIQUE KEY `unique_match` (`user_id_1`, `user_id_2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
