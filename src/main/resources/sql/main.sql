-- Warehouse table
CREATE TABLE Warehouse (
                           warehouse_id INT PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(100) NOT NULL,
                           location VARCHAR(255),
                           capacity INT
);

-- Product table
CREATE TABLE Product (
                         product_id INT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(100) NOT NULL,
                         description TEXT,
                         category VARCHAR(100),
                         supplier_id INT,
                         FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id)
);

-- Inventory table
CREATE TABLE Inventory (
                           inventory_id INT PRIMARY KEY AUTO_INCREMENT,
                           warehouse_id INT,
                           product_id INT,
                           quantity INT NOT NULL,
                           location VARCHAR(100),
                           FOREIGN KEY (warehouse_id) REFERENCES Warehouse(warehouse_id),
                           FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

-- Order table
CREATE TABLE `Order` (
                         order_id INT PRIMARY KEY AUTO_INCREMENT,
                         customer_id INT,
                         order_date DATE,
                         status VARCHAR(50),
                         FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

-- OrderItem table
CREATE TABLE OrderItem (
                           order_item_id INT PRIMARY KEY AUTO_INCREMENT,
                           order_id INT,
                           product_id INT,
                           quantity INT NOT NULL,
                           FOREIGN KEY (order_id) REFERENCES `Order`(order_id),
                           FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

-- Supplier table
CREATE TABLE Supplier (
                          supplier_id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100) NOT NULL,
                          contact_info VARCHAR(255)
);

-- Customer table
CREATE TABLE Customer (
                          customer_id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100) NOT NULL,
                          contact_info VARCHAR(255)
);

-- User table
CREATE TABLE User (
                      user_id INT PRIMARY KEY AUTO_INCREMENT,
                      username VARCHAR(50) UNIQUE NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      role_id INT,
                      FOREIGN KEY (role_id) REFERENCES Role(role_id)
);

-- Role table
CREATE TABLE Role (
                      role_id INT PRIMARY KEY AUTO_INCREMENT,
                      role_name VARCHAR(50) NOT NULL
);


select  * from GoDaung.product inner join  GoDaung.inventory on GoDaung.inventory.inventory_id = product.inventory_id;