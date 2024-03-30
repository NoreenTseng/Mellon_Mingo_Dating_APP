DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `name` varchar(45) DEFAULT NULL,
                         `password` varchar(45) DEFAULT NULL,
                         `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `id_UNIQUE` (`id`)
);
INSERT INTO `users` (name, password) VALUES ('Noreen', 'password');

DROP TABLE IF EXISTS `profiles`;

CREATE TABLE `profiles` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `user_id` int(11) DEFAULT NULL,
                            `nickname` varchar(45) DEFAULT NULL,
                            `age` int(11) DEFAULT NULL,
                            `gender` varchar(45) DEFAULT NULL,
                            `image_urls` TEXT,
                            `bio` varchar(255) DEFAULT NULL,
                            `last_latitude` double DEFAULT NULL,
                            `last_longitude` double DEFAULT NULL,
                            `looking_for` varchar(45) DEFAULT NULL,
                            `mbti` varchar(45) DEFAULT NULL,
                            `hobbies` TEXT,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `id_UNIQUE` (`id`),
                            KEY `user_id_index` (`user_id`),
                            CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

DROP TABLE IF EXISTS `matches`;

CREATE TABLE `matches` (
                           `id` int(11) NOT NULL,
                           `user_id_1` int(11) DEFAULT NULL,
                           `user_id_2` int(11) DEFAULT NULL,
                           `matched_at` datetime DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `id_UNIQUE` (`id`),
                           KEY `user_id_1_index` (`user_id_1`),
                           KEY `user_id_2_index` (`user_id_2`),
                           CONSTRAINT `user_id_1_index` FOREIGN KEY (`user_id_1`) REFERENCES `users` (`id`),
                           CONSTRAINT `user_id_2_index` FOREIGN KEY (`user_id_2`) REFERENCES `users` (`id`)
);

DROP TABLE IF EXISTS `messages`;

CREATE TABLE `messages` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `sender_id` int(11) DEFAULT NULL,
                            `received_id` int(11) DEFAULT NULL,
                            `content` varchar(45) DEFAULT NULL,
                            `sent_at` datetime DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `id_UNIQUE` (`id`),
                            KEY `sender_index` (`sender_id`),
                            KEY `received_index` (`received_id`),
                            CONSTRAINT `message_receiver_id` FOREIGN KEY (`received_id`) REFERENCES `users` (`id`),
                            CONSTRAINT `message_sender_id` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`)
);



DROP TABLE IF EXISTS `leftswipes`;

CREATE TABLE `leftswipes` (
                              `swipe_id` int(11) NOT NULL AUTO_INCREMENT,
                              `swiper_id` int(11) NOT NULL,
                              `swiped_id` int(11) NOT NULL,
                              `swiped_at` datetime DEFAULT NULL,
                              PRIMARY KEY (`swipe_id`),
                              FOREIGN KEY (`swiper_id`) REFERENCES `profiles` (`id`),
                              FOREIGN KEY (`swiped_id`) REFERENCES `profiles` (`id`),
                              UNIQUE KEY `unique_swipe` (`swiper_id`, `swiped_id`)
);

DROP TABLE IF EXISTS `rightswipes`;

CREATE TABLE `rightswipes` (
                               `swipe_id` int(11) NOT NULL AUTO_INCREMENT,
                               `swiper_id` int(11) NOT NULL,
                               `swiped_id` int(11) NOT NULL,
                               `swiped_at` datetime DEFAULT NULL,
                               PRIMARY KEY (`swipe_id`),
                               FOREIGN KEY (`swiper_id`) REFERENCES `profiles` (`id`),
                               FOREIGN KEY (`swiped_id`) REFERENCES `profiles` (`id`),
                               UNIQUE KEY `unique_swipe` (`swiper_id`, `swiped_id`)
);

DROP TABLE IF EXISTS `matches`;

CREATE TABLE `matches` (
                           `match_id` int(11) NOT NULL AUTO_INCREMENT,
                           `user_id_1` int(11) NOT NULL,
                           `user_id_2` int(11) NOT NULL,
                           `matched_at` datetime DEFAULT NULL,
                           PRIMARY KEY (`match_id`),
                           FOREIGN KEY (`user_id_1`) REFERENCES `profiles` (`id`),
                           FOREIGN KEY (`user_id_2`) REFERENCES `profiles` (`id`),
                           UNIQUE KEY `unique_match` (`user_id_1`, `user_id_2`)
);