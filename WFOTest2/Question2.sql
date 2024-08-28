-- Drop the database if it already exists
IF EXISTS (SELECT name FROM sys.databases WHERE name = N'WFOTest2')
DROP DATABASE WFOTest2;
GO

-- Create the new database
CREATE DATABASE WFOTest2;
GO

-- Use the newly created database
USE WFOTest2;
GO

-- Create User table
CREATE TABLE [User] (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL
);

-- Create Order table
CREATE TABLE [Order] (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    datetime DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES [User](id)
);

-- Create OrderDetail table
CREATE TABLE OrderDetail (
    id INT IDENTITY(1,1) PRIMARY KEY,
    order_id INT NOT NULL,
    item_name VARCHAR(200) NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES [Order](id)
);

-- Insert Users
INSERT INTO [User] (name, age) VALUES 
('John Doe', 30),
('Jane Smith', 25),
('Alice Johnson', 35),
('Bob Williams', 28),
('Charlie Brown', 40);

-- Insert Orders
INSERT INTO [Order] (user_id, datetime) VALUES 
(1, '2024-01-15 10:00:00'),
(1, '2024-02-20 14:30:00'),
(2, '2024-02-10 09:15:00'),
(2, '2024-03-05 16:45:00'),
(3, '2024-01-25 11:30:00'),
(3, '2024-02-28 13:00:00'),
(3, '2024-03-10 15:45:00'),
(4, '2024-02-05 08:30:00'),
(4, '2024-03-15 12:15:00'),
(5, '2024-01-30 10:45:00'),
(5, '2024-03-20 14:00:00');

-- Insert Order Details
INSERT INTO OrderDetail (order_id, item_name, total) VALUES 
(1, 'Laptop', 999.99),
(1, 'Mouse', 29.99),
(2, 'Keyboard', 89.99),
(2, 'Monitor', 299.99),
(2, 'Headphones', 79.99),
(3, 'Smartphone', 599.99),
(4, 'Tablet', 399.99),
(4, 'Case', 39.99),
(5, 'Printer', 199.99),
(6, 'Ink Cartridge', 49.99),
(6, 'Paper', 19.99),
(7, 'External HDD', 129.99),
(8, 'Gaming Mouse', 69.99),
(8, 'Mousepad', 19.99),
(9, 'Webcam', 89.99),
(10, 'Office Chair', 249.99),
(11, 'Desk Lamp', 39.99),
(11, 'USB Hub', 29.99);
