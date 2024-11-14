CREATE DATABASE IF NOT EXISTS credentialmgmt_db;

CREATE USER IF NOT EXISTS 'testUser'@'localhost';
GRANT ALL PRIVILEGES ON credentialmgmt_db.* TO 'testUser'@'localhost';
SET PASSWORD FOR 'testUser'@'localhost' = 'j8$9!eS!8zL3#uS';
FLUSH PRIVILEGES;
commit;
