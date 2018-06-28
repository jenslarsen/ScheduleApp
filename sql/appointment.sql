/* 

delete from appointment;
ALTER TABLE appointment MODIFY COLUMN appointmentId INT auto_increment;
ALTER table appointment AUTO_INCREMENT = 100;


INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES (108, 'Business meeting', 'Important Stuff', 'Main conference room', 'Jens', 'http://www.google.com', '2018-06-27 18:30:00', '2018-06-27 19:00:00', 
'2018-06-27 18:26:30', 'Jens', '2018-06-27 18:26:30', 'Jens' );


INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES (106, 'Computer meeting', 'Keyboards Stuff', 'Little conference room', 'Jens', 'http://www.google.com', '2018-06-27 18:30:00', '2018-06-27 19:00:00', '2018-06-27 18:26:30', 'Jens', '2018-06-27 18:26:30', 'Jens' );

INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES (109, 'Party meeting', 'Not So Important Stuff', 'Big conference room', 'Jamie', 'http://www.microsoft.com', '2018-06-28 18:30:00', '2018-06-28 19:00:00', '2018-06-27 18:26:30', 'Jens', '2018-06-27 18:26:30', 'Jens' );

INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES (111, 'Construction meeting', 'Building Stuff', 'Side conference room', 'Jens', 'http://www.sony.com', '2018-06-28 18:30:00', '2018-06-28 19:00:00', '2018-06-27 18:26:30', 'Jens', '2018-06-27 18:26:30', 'Jens' );

INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES (106, 'Sales meeting', 'Customer Stuff', 'Left conference room', 'Jamie', 'http://www.oracle.com', '2018-06-27 18:30:00', '2018-06-27 19:00:00', '2018-06-27 18:26:30', 'Jens', '2018-06-27 18:26:30', 'Jens' );

INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES (105, 'IT meeting', 'Computer Stuff', 'Right conference room', 'Jens', 'http://www.dell.com', '2018-06-28 18:30:00', '2018-06-28 19:00:00', '2018-06-27 18:26:30', 'Jens', '2018-06-27 18:26:30', 'Jens' );

INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES (103, 'BBQ meeting', 'Chicken Stuff', 'Top conference room', 'Jamie', 'http://www.kc.com', '2018-06-27 18:30:00', '2018-06-27 19:00:00', '2018-06-27 18:26:30', 'Jens', '2018-06-27 18:26:30', 'Jens' );

INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES (112, 'HR meeting', 'Violation Stuff', 'Bottom conference room', 'Jens', 'http://www.indeed.com', '2018-06-28 18:30:00', '2018-06-28 19:00:00', '2018-06-27 18:26:30', 'Jens', '2018-06-27 18:26:30', 'Jens' );

INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES (100, 'Conference meeting', 'Phone Stuff', '1st floor conference room', 'Jamie', 'http://www.avaya.com', '2018-06-27 18:30:00', '2018-06-27 19:00:00', '2018-06-27 18:26:30', 'Jens', '2018-06-27 18:26:30', 'Jens' );

INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES (101, 'Development meeting', 'Money Stuff', '2nd floor conference room', 'Jens', 'http://www.cash.com', '2018-06-28 18:30:00', '2018-06-28 19:00:00', '2018-06-27 18:26:30', 'Jens', '2018-06-27 18:26:30', 'Jens' );

INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES (102, 'Lunch meeting', 'Food Stuff', '3rd floor conference room', 'Jamie', 'http://www.homegrown.com', '2018-06-27 18:30:00', '2018-06-27 19:00:00', '2018-06-27 18:26:30', 'Jens', '2018-06-27 18:26:30', 'Jens' );

INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES (103, 'Translation meeting', 'French Stuff', 'Starbux', 'Jens', 'http://www.french.com', '2018-06-28 18:30:00', '2018-06-28 19:00:00', '2018-06-27 18:26:30', 'Jens', '2018-06-27 18:26:30', 'Jens' );

INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES (104, 'Photography meeting', 'Camera Stuff', 'Top Pot', 'Jamie', 'http://www.canon.com', '2018-06-27 18:30:00', '2018-06-27 19:00:00', '2018-06-27 18:26:30', 'Jens', '2018-06-27 18:26:30', 'Jens' );

INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES (109, 'Marketing meeting', 'Poster Stuff', 'Library conference room', 'Jens', 'http://www.spl.com', '2018-06-28 18:30:00', '2018-06-28 19:00:00', '2018-06-27 18:26:30', 'Jens', '2018-06-27 18:26:30', 'Jens' );

INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES (113, 'TV meeting', 'Antenna Stuff', 'Small conference room', 'Jamie', 'http://www.lg.com', '2018-06-27 18:30:00', '2018-06-27 19:00:00', '2018-06-27 18:26:30', 'Jens', '2018-06-27 18:26:30', 'Jens' );

INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)
VALUES (114, 'Reading meeting', 'Book Stuff', 'Main conference room', 'Jens', 'http://www.tor.com', '2018-06-28 18:30:00', '2018-06-28 19:00:00', '2018-06-27 18:26:30', 'Jens', '2018-06-27 18:26:30', 'Jens' );

*/

select * from appointment