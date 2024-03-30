-- add match_preference

ALTER TABLE `profiles`
    ADD COLUMN `match_preference` VARCHAR(45) NOT NULL DEFAULT 'distance';

ALTER TABLE `matches`
    CHANGE `user_id_1` `sender_id` INT(11) NOT NULL,
    CHANGE `user_id_2` `receiver_id` INT(11) NOT NULL;
