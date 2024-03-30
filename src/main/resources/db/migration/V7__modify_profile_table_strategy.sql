-- Modify profile strategyType column with default value 'DISTANCE'
ALTER TABLE `profiles`
ADD COLUMN `strategyType` VARCHAR(45) NULL DEFAULT 'DISTANCE' AFTER `hobbies`;  -- Adjust the 'AFTER' clause as needed
