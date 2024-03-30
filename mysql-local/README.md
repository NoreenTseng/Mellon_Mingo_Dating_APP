# How to?

## Prerequisites

Install MySQL Workbench on your local machine

## Steps

```bash
docker-compose up -d # create a database `local-db`, root password `root_password`, on `localhost:3306`

# Open your workbench, connect to the database

# Import `231001.sql` to `local-db` through workbench
```

## Cheatsheet

```bash
# stop the db

docker-compose down

# removed all db data

rm -rf ./local-db-mysql-data
```
