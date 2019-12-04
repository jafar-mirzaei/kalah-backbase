# Backbase Kalah assignment



### Create Database (H2)

create database kalah_db; -- Creates the new database
create user 'kalah_user'@'%' identified by 'kalah_password'; -- Creates the user
grant all on kalah_db.* to 'kalah_user'@'%'; -- Gives all privileges to the new user on the newly created database