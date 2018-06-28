/*
ALTER TABLE country MODIFY COLUMN countryId INT auto_increment;
ALTER table country AUTO_INCREMENT = 100;

INSERT INTO country (country, createDate, createdBy, lastUpdate, lastUpdateBy) 
VALUES ("US", "2018-06-04", "Jens", "2018-06-04", "Jens");

INSERT INTO country (country, createDate, createdBy, lastUpdate, lastUpdateBy) 
VALUES ("CA", "2018-06-04", "Jens", "2018-06-04", "Jens");

INSERT INTO country (country, createDate, createdBy, lastUpdate, lastUpdateBy) 
VALUES ("FR", "2018-06-04", "Jens", "2018-06-04", "Jens");

*/

select * from country