-- Rename users.'password' column to 'hashed_password'
ALTER TABLE `users`
    CHANGE COLUMN `password` `hashed_password` varchar(255) DEFAULT NULL;

-- Rename users.'name' column to 'username'
ALTER TABLE `users`
    CHANGE COLUMN `name` `username` varchar(255) DEFAULT NULL;