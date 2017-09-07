DROP DATABASE restaurant;
CREATE DATABASE restaurant; USE restaurant;
CREATE TABLE clients(
client_id INT AUTO_INCREMENT PRIMARY KEY , 
client_name VARCHARACTER(20),
client_number_of_table INT 
);
CREATE TABLE dishes(
dish_id INT AUTO_INCREMENT PRIMARY KEY , 
dish_name VARCHARACTER(300),
dish_amount DOUBLE,
dish_price DOUBLE
);
CREATE TABLE orders(
order_id INT AUTO_INCREMENT PRIMARY KEY , 
client_id INT,
dish_id INT,
CONSTRAINT FK_client FOREIGN KEY (client_id) REFERENCES clients (client_id),
CONSTRAINT FK_dish FOREIGN KEY (dish_id) REFERENCES dishes (dish_id)
);
INSERT INTO clients (client_name,client_number_of_table) VALUES ('Dmytro', 1);

INSERT INTO dishes (dish_name,dish_amount,dish_price) VALUES ('Cezar', 250 , 50 );
INSERT INTO dishes (dish_name,dish_amount,dish_price) VALUES ('tea', 250 , 55 );
INSERT INTO dishes (dish_name,dish_amount,dish_price) VALUES ('Coffe', 250 , 55 );
INSERT INTO dishes (dish_name,dish_amount,dish_price) VALUES ('tiramisu', 120 , 55 );
INSERT INTO dishes (dish_name,dish_amount,dish_price) VALUES ('pannacota', 100 , 55 );
INSERT INTO dishes (dish_name,dish_amount,dish_price) VALUES ('pizza prosciutto cotto', 350 , 95 );
INSERT INTO dishes (dish_name,dish_amount,dish_price) VALUES ('pizza fungi', 300 , 85 );
 


