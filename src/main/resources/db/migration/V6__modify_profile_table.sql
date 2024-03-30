-- add profile_image_url
ALTER TABLE `profiles`
ADD COLUMN `profile_image_url` VARCHAR(45) NULL DEFAULT NULL AFTER `gender`;