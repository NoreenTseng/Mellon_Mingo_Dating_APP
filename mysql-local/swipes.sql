DROP TABLE IF EXISTS `leftswipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;

CREATE TABLE `leftswipes` (
                              `swipe_id` int(11) NOT NULL AUTO_INCREMENT,
                              `swiper_id` int(11) NOT NULL,
                              `swiped_id` int(11) NOT NULL,
                              `swiped_at` datetime DEFAULT NULL,
                              PRIMARY KEY (`swipe_id`),
                              FOREIGN KEY (`swiper_id`) REFERENCES `profiles` (`id`),
                              FOREIGN KEY (`swiped_id`) REFERENCES `profiles` (`id`),
                              UNIQUE KEY `unique_swipe` (`swiper_id`, `swiped_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `rightswipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;

CREATE TABLE `rightswipes` (
                              `swipe_id` int(11) NOT NULL AUTO_INCREMENT,
                              `swiper_id` int(11) NOT NULL,
                              `swiped_id` int(11) NOT NULL,
                              `swiped_at` datetime DEFAULT NULL,
                              PRIMARY KEY (`swipe_id`),
                              FOREIGN KEY (`swiper_id`) REFERENCES `profiles` (`id`),
                              FOREIGN KEY (`swiped_id`) REFERENCES `profiles` (`id`),
                              UNIQUE KEY `unique_swipe` (`swiper_id`, `swiped_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;