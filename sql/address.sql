/* 
ALTER TABLE address MODIFY COLUMN addressId INT auto_increment;
ALTER table address AUTO_INCREMENT = 100;

INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("1 Any Street", "Apt 101", 100, "98109", "111-1111", "2018-06-04", "Jamie", "2018-06-4", "Jens");
INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("10 My Street", "", 100, "98111", "222-2222", "2018-06-04", "Jamie", "2018-06-4", "Jens");
INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("2 That Street", "Apt 204", 100, "98112", "333-3333", "2018-06-04", "Jamie", "2018-06-4", "Jens");
INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("3 Someother Street", "", 100, "98113", "444-444", "2018-06-04", "Jamie", "2018-06-4", "Jens");
INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("4 Thatother Street", "Suite 505", 100, "98114", "555-5555", "2018-06-04", "Jamie", "2018-06-4", "Jens");
INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("5 Your Street", "", 100, "98115", "666-6666", "2018-06-04", "Jamie", "2018-06-4", "Jens");
INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("6 His Street", "", 100, "98116", "777-7777", "2018-06-04", "Jamie", "2018-06-4", "Jens");
INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("7 Her Street", "", 100, "98117", "888-8888", "2018-06-04", "Jamie", "2018-06-4", "Jens");
INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("8  Who Street", "", 100, "98118", "999-9999", "2018-06-04", "Jamie", "2018-06-4", "Jens");
INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("9 What Street", "", 100, "98119", "101-1010", "2018-06-04", "Jamie", "2018-06-4", "Jens");
INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("10 When Street", "", 100, "98120", "111-1111", "2018-06-04", "Jamie", "2018-06-4", "Jens");
INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("11 Why Street", "", 100, "98121", "121-1212", "2018-06-04", "Jamie", "2018-06-4", "Jens");
INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("12 Where Street", "", 100, "98122", "131-1313", "2018-06-04", "Jamie", "2018-06-4", "Jens");
INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("13 No Street", "", 100, "98123", "141-1414", "2018-06-04", "Jamie", "2018-06-4", "Jens");
INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("14 Their Street", "Apt C", 100, "98124", "151-1515", "2018-06-04", "Jamie", "2018-06-4", "Jens");
INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("15 No Street", "", 100, "98125", "161-1616", "2018-06-04", "Jamie", "2018-06-4", "Jens");
*/

SELECT * FROM country
WHERE (country = "US");


