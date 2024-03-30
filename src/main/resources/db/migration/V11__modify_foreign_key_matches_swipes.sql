-- Altering foreign keys for the leftswipes table
ALTER TABLE `leftswipes` DROP FOREIGN KEY `leftswipes_ibfk_1`;
ALTER TABLE `leftswipes` DROP FOREIGN KEY `leftswipes_ibfk_2`;

ALTER TABLE `leftswipes` ADD CONSTRAINT `fk_leftswipes_swiper_id` FOREIGN KEY (`swiper_id`) REFERENCES `profiles` (`user_id`);
ALTER TABLE `leftswipes` ADD CONSTRAINT `fk_leftswipes_swiped_id` FOREIGN KEY (`swiped_id`) REFERENCES `profiles` (`user_id`);

ALTER TABLE `rightswipes` DROP FOREIGN KEY `rightswipes_ibfk_1`;
ALTER TABLE `rightswipes` DROP FOREIGN KEY `rightswipes_ibfk_2`;

ALTER TABLE `rightswipes` ADD CONSTRAINT `fk_rightswipes_swiper_id` FOREIGN KEY (`swiper_id`) REFERENCES `profiles` (`user_id`);
ALTER TABLE `rightswipes` ADD CONSTRAINT `fk_rightswipes_swiped_id` FOREIGN KEY (`swiped_id`) REFERENCES `profiles` (`user_id`);

ALTER TABLE `matches` DROP FOREIGN KEY `matches_ibfk_1`;
ALTER TABLE `matches` DROP FOREIGN KEY `matches_ibfk_2`;

ALTER TABLE `matches` ADD CONSTRAINT `fk_matches_user_id_1` FOREIGN KEY (`sender_id`) REFERENCES `profiles` (`user_id`);
ALTER TABLE `matches` ADD CONSTRAINT `fk_matches_user_id_2` FOREIGN KEY (`receiver_id`) REFERENCES `profiles` (`user_id`);

ALTER TABLE `profiles` ADD UNIQUE (user_id);
