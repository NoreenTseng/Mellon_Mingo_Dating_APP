# Mellon_Mingo_Dating_APP
#### A Team of 5 People

## How to run?

```bash
# Make sure docker is running!

# Step 1: run MySQL instance
cd ./mysql-local
docker-compose up -d

# Wait a few seconds for the mysql instance to be ready (10 seconds should be enough)

# Step 2: Run the application

# When you run the application, Flyway (the database migration tool) will run the migration scripts for you.

# Two ways of running it
# option 1: run the app in IDEA, use the Big Green build button
# option 2: command line
cd <repo-root>
mvn clean spring-boot:run

# Migration tool tutorials:
# Blog: https://medium.com/version-1/db-migrations-in-spring-boot-7d48e5e18738
# Youtube: https://www.youtube.com/watch?v=p1V5GcKUJv0
```

## I screw up the DB, help me!

```bash
# Delete the entire local MySQL instance
cd ./mysql-local

docker-compose down

rm -rf local-db-mysql-data

# Then apply the steps in `How to run?`
```
