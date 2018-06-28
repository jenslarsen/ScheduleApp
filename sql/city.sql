/*

ALTER TABLE city MODIFY COLUMN cityId INT auto_increment;
ALTER table city AUTO_INCREMENT = 100;

INSERT INTO city (city, countryId, createDate, createdBy, lastUpdate, lastUpdateby )
VALUES ("Seattle", 100, "2018-06-04", "Jens", "2018-06-04", "Jens");

INSERT INTO city (city, countryId, createDate, createdBy, lastUpdate, lastUpdateby )
VALUES ("Vancouver", 101, "2018-06-04", "Jens", "2018-06-04", "Jens");

INSERT INTO city (city, countryId, createDate, createdBy, lastUpdate, lastUpdateby )
VALUES ("Paris", 102, "2018-06-04", "Jens", "2018-06-04", "Jens");
*/
select * from city;