#!/bin/bash

# Navigate to the project root directory

# Start the MySQL database in Docker
docker-compose up -d

# Wait for a few seconds to ensure the MySQL server is fully up and running
sleep 10

# Import the SQL file into the MySQL database
# Replace '231001.sql' with your actual SQL file name
# Replace 'root_password' with your actual MySQL root password
docker exec -i $(docker-compose ps -q db) mysql -uroot -proot_password local-db < 231001.sql

docker exec -i $(docker-compose ps -q db) mysql -uroot -proot_password local-db < 2023_11_21_UserMag_V1.sql
docker exec -i $(docker-compose ps -q db) mysql -uroot -proot_password local-db < swipes.sql
docker exec -i $(docker-compose ps -q db) mysql -uroot -proot_password local-db < matches.sql
docker exec -i $(docker-compose ps -q db) mysql -uroot -proot_password local-db < 231121_story.sql


echo "Data import completed."