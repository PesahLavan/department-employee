-- create database "test"
CREATE DATABASE test
  WITH
OWNER = postgres
  ENCODING = 'UTF8'
  LC_COLLATE = 'English_United States.1252'
  LC_CTYPE = 'English_United States.1252'
TABLESPACE = pg_default
CONNECTION LIMIT = -1;

-- import citext
CREATE EXTENSION citext;

-- create type email
CREATE DOMAIN email AS citext
  CHECK ( value ~ '^[a-zA-Z0-9.!#$%&''*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$' );

-- creat table department

CREATE TABLE departments
(
  uid serial primary key,
  department citext not null unique default 'Unemployed'
);

-- create default department
INSERT INTO departments  DEFAULT VALUES ;


-- create table "employee"
create table employee (
  uid serial primary key,
  name varchar(64),
  department_id int not null DEFAULT 0 references departments(uid) ON DELETE SET DEFAULT,
  number integer,
  email email not null unique,
  birthday date
    CHECK (lower(email) = email)
);