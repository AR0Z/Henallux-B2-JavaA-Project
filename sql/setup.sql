/* create a database "atrouver" if doesn't exist */
CREATE DATABASE IF NOT EXISTS atrouver;

USE atrouver;

CREATE TABLE IF NOT EXISTS `country` (
    id      INT NOT NULL AUTO_INCREMENT,
    label   VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(label)
);

CREATE TABLE IF NOT EXISTS `locality` (
    id          INT NOT NULL AUTO_INCREMENT,
    label       VARCHAR(50) NOT NULL,
    zip_code    VARCHAR(10) NOT NULL,
    country_id  INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(label, zip_code)
);

CREATE TABLE IF NOT EXISTS `customer`(
    id                  INT NOT NULL AUTO_INCREMENT,
    last_name           VARCHAR(50) NOT NULL,
    first_name          VARCHAR(50) NOT NULL,
    email               VARCHAR(100) NOT NULL,
    locality_id         INT NOT NULL,
    phone               VARCHAR(20),
    street_and_number   VARCHAR(200),
    type_customer       INT NOT NULL,
    has_fidelity_card   BOOLEAN,
    billing_address     VARCHAR(200),
    TVA                 VARCHAR(20),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `order`(
    id                 INT NOT NULL AUTO_INCREMENT,
    order_date         DATE NOT NULL,
    is_paid            BOOLEAN NOT NULL,
    customer_id        INT NOT NULL,
    reduction          FLOAT,
    type_order         INT NOT NULL,
    date_of_payment    DATE,
    shipping_adress    VARCHAR(200),
    invoice_details    VARCHAR(500),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `line`( 
    id              INT NOT NULL AUTO_INCREMENT,
    quantity        INT NOT NULL,
    unitary_price   INT NOT NULL,
    order_id        INT NOT NULL,
    product_id      INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `product`( 
    id              INT NOT NULL AUTO_INCREMENT,
    label            VARCHAR(50) NOT NULL,
    color           VARCHAR(20) NOT NULL,
    price           FLOAT NOT NULL,
    cost            FLOAT NOT NULL,
    size            INT NOT NULL,
    stock           INT NOT NULL,
    addition_date   DATE NOT NULL,
    is_shippable    BOOLEAN NOT NULL,
    category_id     INT NOT NULL,
    information     VARCHAR(500),
    image_link      VARCHAR(500),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `category`( 
    id          INT NOT NULL AUTO_INCREMENT,
    label       VARCHAR(50) NOT NULL,
    information VARCHAR(500),
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS `supplier`( 
    id                  INT NOT NULL AUTO_INCREMENT,
    label                VARCHAR(50) NOT NULL,
    phone               VARCHAR(20) NOT NULL,
    email               VARCHAR(200) NOT NULL,
    street_and_number   VARCHAR(200),
    locality_id         INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `supply`( 
    id          INT NOT NULL AUTO_INCREMENT,
    cost        INT NOT NULL,
    product_id  INT NOT NULL,
    supplier_id INT NOT NULL,
    PRIMARY KEY (id)
);

/* FOREIGN KEY */

ALTER TABLE `locality` ADD FOREIGN KEY (country_id) REFERENCES `country` (id);
ALTER TABLE `customer` ADD FOREIGN KEY (locality_id) REFERENCES `locality` (id);
ALTER TABLE `order` ADD FOREIGN KEY (customer_id) REFERENCES `customer` (id);
ALTER TABLE `line` ADD FOREIGN KEY (order_id) REFERENCES `order` (id);
ALTER TABLE `line` ADD FOREIGN KEY (product_id) REFERENCES `product` (id);
ALTER TABLE `product` ADD FOREIGN KEY (category_id) REFERENCES `category` (id);
ALTER TABLE `supplier` ADD FOREIGN KEY (locality_id) REFERENCES `locality` (id);
ALTER TABLE `supply` ADD FOREIGN KEY (product_id) REFERENCES `product` (id);
ALTER TABLE `supply` ADD FOREIGN KEY (supplier_id) REFERENCES `supplier` (id);

/* CONSTRAINTS */

ALTER TABLE `customer` ADD CONSTRAINT `Customer_type` CHECK (type_customer IN (0, 1));
ALTER TABLE `order` ADD CONSTRAINT `Order_type` CHECK (type_order IN (0, 1));
ALTER TABLE `product` ADD CONSTRAINT `Product_price` CHECK (price > 0);
ALTER TABLE `product` ADD CONSTRAINT `Product_cost` CHECK (cost > 0);
ALTER TABLE `product` ADD CONSTRAINT `Product_size` CHECK (size > 0);
ALTER TABLE `product` ADD CONSTRAINT `Product_stock` CHECK (stock > 0);
ALTER TABLE `supply` ADD CONSTRAINT `Supply_cost` CHECK (cost > 0);


/* INSERTS */

 -- Pays :
INSERT INTO country (label) VALUES ('Belgique'), ('France'), ('Pays-Bas'), ('Allemagne'), ('Luxembourg'), ('Suisse'), ('Royaume-Uni'), ('Irlande'), ('Espagne'), ('Portugal');

-- Localités :

    -- Belgique :
    INSERT INTO locality (label, zip_code, country_id) VALUES ('Bruxelles', '1000', 1), ('Liège', '4000', 1), ('Namur', '5000', 1), ('Anvers', '2000', 1), ('Gand', '9000', 1);

    -- France :
    INSERT INTO locality (label, zip_code, country_id) VALUES ('Paris', '75000', 2), ('Lyon', '69000', 2), ('Marseille', '13000', 2), ('Lille', '59000', 2), ('Bordeaux', '33000', 2);

    -- Allemagne :
    INSERT INTO locality (label, zip_code, country_id) VALUES ('Berlin', '10178', 4), ('Munich', '80331', 4), ('Francfort', '60306', 4), ('Hambourg', '20099', 4), ('Cologne', '50667', 4);

    -- Luxembourg :
    INSERT INTO locality (label, zip_code, country_id) VALUES ('Luxembourg', '1009', 5), ('Esch-sur-Alzette', '4002', 5), ('Dudelange', '3450', 5), ('Pétange', '4730', 5), ('Ettelbruck', '9055', 5);

-- Customers :

INSERT INTO customer (first_name, last_name, email, street_and_number, locality_id, type_customer) VALUES ('Marie', 'Martin', 'marie.martin@gmail.com', 'Rue du Midi 10', 1, 0);
INSERT INTO customer (first_name, last_name, email, phone, street_and_number, locality_id, billing_address, type_customer) VALUES ('Pierre', 'Durant', 'pîerre.durant@gmail.com', '0478/12.34.56', 'Rue du Midi 16', 1, 'Rue du Midi 16', 1);
INSERT INTO `customer` (`last_name`, `first_name`, `email`, `locality_id`, `phone`, `street_and_number`, `type_customer`, `has_fidelity_card`, `billing_address`, `TVA`)
VALUES
    ('Smith', 'John', 'john.smith@example.com', 1, '1234567890', '123 Main St', 0, 1, '456 Billing St', '123456789'),
    ('Doe', 'Jane', 'jane.doe@example.com', 2, '0987654321', '456 High St', 0, 0, '789 Billing Rd', '987654321'),
    ('Garcia', 'Maria', 'maria.garcia@example.com', 3, '5554443333', '789 Oak Ave', 1, 1, '123 Invoice St', '123987456'),
    ('Kim', 'Ji-eun', 'jekim@example.com', 4, '1231231234', '234 Pine St', 1, 0, '456 Invoice Rd', '456789123'),
    ('Brown', 'Michael', 'michael.brown@example.com', 5, '9876543210', '567 Main St', 0, 1, '789 Billing St', '987654321'),
    ('Chen', 'Wei', 'wei.chen@example.com', 6, '1112223333', '890 Elm St', 0, 0, '123 Invoice Rd', '123456789'),
    ('Singh', 'Raj', 'raj.singh@example.com', 7, '4445556666', '123 Oak St', 1, 1, '456 Billing Ave', '987654321'),
    ('Garcia', 'Juan', 'juan.garcia@example.com', 8, '7778889999', '456 Pine Ave', 0, 0, '789 Invoice Rd', '456123789'),
    ('Lee', 'Hae-won', 'haewon.lee@example.com', 9, '5556667777', '789 Maple St', 1, 1, '123 Billing Rd', '789456123'),
    ('Jones', 'Emily', 'emily.jones@example.com', 10, '2223334444', '234 Oak Ave', 0, 0, '456 Billing St', '456789123');

-- Orders :

INSERT INTO `order` (order_date, is_paid, customer_id, reduction, type_order, date_of_payment, shipping_adress) VALUES ('2020-01-01', 1, 1, 0, 0, '2020-01-01', 'Rue du Midi 10');
INSERT INTO `order` (order_date, is_paid, customer_id, reduction, type_order, date_of_payment, shipping_adress) VALUES ('2020-01-01', 1, 2, 0, 0, '2020-01-01', 'Rue du Midi 16');

-- Lines :

-- Categories :

INSERT INTO category (label) VALUES ('Meubles'), ('Décoration'), ('Luminaires'), ('Tapis'), ('Rangements'), ('Cuisine'), ('Salle de bain'), ('Chambre'), ('Jardin'), ('Bureau');

-- Products :

INSERT INTO product (label, color, price, cost, size, stock, addition_date, is_shippable, category_id, information, image_link) VALUES ('Chaise', 'Blanc', 50, 20, 50, 10, '2020-01-01', 1, 1, 'Chaise en bois', 'https://www.ikea.com/be/fr/images/products/ingolf-chaise-blanc__0717143_PE731522_S5.JPG?f=s');
INSERT INTO product (label, color, price, cost, size, stock, addition_date, is_shippable, category_id, information, image_link) VALUES ('Table', 'Blanc', 100, 50, 100, 10, '2020-01-01', 1, 1, 'Table en bois', 'https://www.ikea.com/be/fr/images/products/ingatorp-table-extensible-blanc__0717144_PE731523_S5.JPG?f=s');
INSERT INTO product (label, color, price, cost, size, stock, addition_date, is_shippable, category_id, information, image_link) VALUES ('Armoire', 'Blanc', 200, 100, 200, 10, '2020-01-01', 1, 1, 'Armoire en bois', 'https://www.ikea.com/be/fr/images/products/brimnes-armoire-avec-3-portes-blanc__0717145_PE731524_S5.JPG?f=s');
INSERT INTO product (label, color, price, cost, size, stock, addition_date, is_shippable, category_id, information, image_link) VALUES ('Canapé', 'Blanc', 300, 150, 300, 10, '2020-01-01', 1, 1, 'Canapé en bois', 'https://www.ikea.com/be/fr/images/products/ektorp-canape-3-places-nordvalla-blanc__0717146_PE731525_S5.JPG?f=s');
INSERT INTO product (label, color, price, cost, size, stock, addition_date, is_shippable, category_id, information, image_link) VALUES ('Bureau', 'Blanc', 400, 200, 400, 10, '2020-01-01', 1, 1, 'Bureau en bois', 'https://www.ikea.com/be/fr/images/products/malm-bureau-blanc__0717147_PE731526_S5.JPG?f=s');

-- Suppliers :

INSERT INTO supplier (label, phone, email, street_and_number, locality_id) VALUES ('IKEA', '02/719.19.22', 'ikea.supply@ikea.com', 'Rue du centre 5', 2);
INSERT INTO supplier (label, phone, email, street_and_number, locality_id) VALUES ('Leen Bakker', '02/719.19.22', 'leenbekker.supply@gmail.com', 'Rue du centre 2', 4);
INSERT INTO `supplier` (label, phone, email, street_and_number, locality_id)
VALUES
    ('Zenith Furnishings', '555-1234', 'zenithfurnishings@example.com', '123 Main St.', 1),
    ('Regal Interiors', '555-5678', 'regalinteriors@example.com', '456 Elm St.', 2),
    ('Elite Home Decor', '555-9012', 'elitehomedecor@example.com', '789 Oak St.', 3),
    ('Grand Designs', '555-3456', 'granddesigns@example.com', '321 Pine St.', 4),
    ('Lavish Living', '555-7890', 'lavishliving@example.com', '654 Maple St.', 5),
    ('Modern Edge Furniture', '555-2345', 'modernedgefurniture@example.com', '987 Cherry St.', 6),
    ('Chic Home Furnishings', '555-6789', 'chichomefurnishings@example.com', '246 Cedar St.', 7),
    ('Signature Style Interiors', '555-0123', 'signaturestyleinteriors@example.com', '135 Birch St.', 8),
    ('Fine Furnishings Co.', '555-4567', 'finefurnishingsco@example.com', '864 Spruce St.', 9),
    ('Luxe Living Furniture', '555-8901', 'luxelivingfurniture@example.com', '579 Sycamore St.', 10);

-- Supply :

INSERT INTO supply (cost, product_id, supplier_id) VALUES (25, 1, 1);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (30, 2, 2);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (35, 3, 3);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (40, 4, 4);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (45, 5, 5);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (50, 1, 6);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (55, 2, 7);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (60, 3, 8);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (65, 4, 9);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (70, 5, 10);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (75, 1, 11);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (80, 2, 12);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (85, 3, 1);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (90, 4, 2);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (95, 5, 3);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (100, 1, 4);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (105, 2, 5);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (110, 3, 6);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (115, 4, 7);
INSERT INTO supply (cost, product_id, supplier_id) VALUES (120, 5, 8);

-- Orders :

INSERT INTO `order` (order_date, is_paid, customer_id, reduction, type_order, date_of_payment, shipping_adress,invoice_details)
VALUES ('2020-01-01', 1, 2, 0, 1, '2020-01-01', 'Rue du centre 5', 'Facture'),
       ('2020-01-01', 1, 1, 0, 1, '2020-01-01', 'Rue du centre 5', 'Facture'),
       ('2020-01-01', 1, 3, 0, 1, '2020-01-01', 'Rue du centre 5', 'Facture'),
       ('2020-01-01', 1, 1, 0, 0, null, null, 'Facture'),
       ('2020-01-01', 1, 5, 0, 0, null, null, 'Facture'),
       ('2020-01-01', 1, 1, 0, 0, null, null, 'Facture'),
       ('2020-01-01', 1, 1, 0, 1, '2020-01-01', 'Rue du centre 5', 'Facture'),
       ('2020-01-01', 1, 1, 0, 1, '2020-01-01', 'Rue du centre 5', 'Facture');

-- Order_line :

INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (2, 50, 1, 1);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (3, 100, 1, 2);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (1, 200, 1, 3);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (2, 300, 1, 4);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (1, 400, 1, 5);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (3, 50, 2, 1);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (2, 100, 2, 2);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (1, 200, 2, 3);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (1, 300, 2, 4);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (2, 400, 2, 5);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (2, 50, 3, 1);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (3, 100, 3, 2);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (1, 200, 3, 3);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (2, 300, 3, 4);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (1, 400, 3, 5);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (3, 50, 4, 1);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (2, 100, 4, 2);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (1, 200, 4, 3);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (1, 300, 4, 4);
INSERT INTO `line` (quantity, unitary_price, order_id, product_id) VALUES (2, 400, 4, 5);
