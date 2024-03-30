-- Rename messages.'sent_at' column to 'timestamp'
-- Rename messages.'received_id' column to 'receiver_id'

ALTER TABLE `messages`
DROP FOREIGN KEY `message_receiver_id`;
ALTER TABLE `messages`
DROP COLUMN `sent_at`,
ADD COLUMN `content_type` VARCHAR(45) NULL DEFAULT NULL AFTER `content`,
ADD COLUMN `timestamp` DATETIME NULL DEFAULT NULL AFTER `content_type`,
CHANGE COLUMN `id` `id` INT NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `received_id` `receiver_id` INT NULL DEFAULT NULL ;
ALTER TABLE `messages`
ADD CONSTRAINT `message_receiver_id`
  FOREIGN KEY (`receiver_id`)
  REFERENCES `users` (`id`);
