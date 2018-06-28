/* 

delete from customer;
ALTER TABLE customer MODIFY COLUMN customerId INT auto_increment;
ALTER table customer AUTO_INCREMENT = 100;

INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("Al Alberts", 100, 1, "2018-06-04", "Jens", "2018-06-04", "Jens");
INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("Ben Bark", 101, 1, "2018-06-04", "Jens", "2018-06-04", "Jens");
INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("Cat Chan", 102, 1, "2018-06-04", "Jens", "2018-06-04", "Jens");
INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("Dev Dock", 103, 1, "2018-06-04", "Jens", "2018-06-04", "Jens");
INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("Ed Earp", 104, 1, "2018-06-04", "Jens", "2018-06-04", "Jens");
INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("Fae Fu", 105, 1, "2018-06-04", "Jens", "2018-06-04", "Jens");
INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("Gigi Gup", 106, 1, "2018-06-04", "Jens", "2018-06-04", "Jens");
INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("Hao Hed", 107, 1, "2018-06-04", "Jens", "2018-06-04", "Jens");
INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("Ian Ipp", 108, 1, "2018-06-04", "Jens", "2018-06-04", "Jens");
INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("Jon Jiff", 109, 1, "2018-06-04", "Jens", "2018-06-04", "Jens");
INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("Kel Kza", 110, 1, "2018-06-04", "Jens", "2018-06-04", "Jens");
INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES ("Lee Lo", 111, 1, "2018-06-04", "Jens", "2018-06-04", "Jens");


SELECT customer.customerName,customer.active,customer.customerId,
	address.address,address.addressId,address.address2,
    city.city,address.postalCode,address.phone, country.country 
FROM (((customer INNER JOIN address on customer.addressId = address.addressId) 
INNER JOIN city on address.cityId = city.cityId) 
INNER JOIN country on city.countryId = country.countryId)
WHERE customer.active = 1;

*/

select * from customer;

