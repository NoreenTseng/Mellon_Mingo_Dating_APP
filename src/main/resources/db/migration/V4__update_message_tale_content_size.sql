-- Update content size in messages table
ALTER TABLE `messages`
CHANGE COLUMN `content` `content` VARCHAR(255) NULL DEFAULT NULL ;