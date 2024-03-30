-- Add 'drinks', 'drugs', 'education', and 'height' columns to the 'profiles' table
ALTER TABLE `profiles`
ADD COLUMN `drinks` VARCHAR(45) NULL,
ADD COLUMN `drugs` VARCHAR(45) NULL,
ADD COLUMN `education` VARCHAR(45) NULL,
ADD COLUMN `height` INTEGER NULL;
