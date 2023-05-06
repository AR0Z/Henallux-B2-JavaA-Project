/* create a database "atrouver" if doesn't exist */
CREATE DATABASE IF NOT EXISTS atrouver;

USE atrouver;

CREATE TABLE IF NOT EXISTS `Country` (
    id      INT NOT NULL AUTO_INCREMENT,
    label   VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(label)
);

CREATE TABLE IF NOT EXISTS `Locality` (
    id          INT NOT NULL AUTO_INCREMENT,
    label       VARCHAR(50) NOT NULL,
    zip_code    VARCHAR(10) NOT NULL,
    country_id  INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(label, zip_code)
);

CREATE TABLE IF NOT EXISTS `Customer`(
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

CREATE TABLE IF NOT EXISTS `Order`(
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

CREATE TABLE IF NOT EXISTS `Line`( 
    id              INT NOT NULL AUTO_INCREMENT,
    quantity        INT NOT NULL,
    unitary_price   INT NOT NULL,
    order_id        INT NOT NULL,
    product_id      INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `Product`( 
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

CREATE TABLE IF NOT EXISTS `Category`( 
    id          INT NOT NULL AUTO_INCREMENT,
    label       VARCHAR(50) NOT NULL,
    information VARCHAR(500),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `Supplier`( 
    id                  INT NOT NULL AUTO_INCREMENT,
    label                VARCHAR(50) NOT NULL,
    phone               VARCHAR(20) NOT NULL,
    email               VARCHAR(200) NOT NULL,
    street_and_number   VARCHAR(200),
    locality_id         INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `Supply`( 
    id          INT NOT NULL AUTO_INCREMENT,
    cost        INT NOT NULL,
    product_id  INT NOT NULL,
    supplier_id INT NOT NULL,
    PRIMARY KEY (id)
);

/* FOREIGN KEY */

ALTER TABLE `Locality` ADD FOREIGN KEY (country_id) REFERENCES `Country` (id);
ALTER TABLE `Customer` ADD FOREIGN KEY (locality_id) REFERENCES `Locality` (id);
ALTER TABLE `Order` ADD FOREIGN KEY (customer_id) REFERENCES `Customer` (id);
ALTER TABLE `Line` ADD FOREIGN KEY (order_id) REFERENCES `Order` (id);
ALTER TABLE `Line` ADD FOREIGN KEY (product_id) REFERENCES `Product` (id);
ALTER TABLE `Product` ADD FOREIGN KEY (category_id) REFERENCES `Category` (id);
ALTER TABLE `Supplier` ADD FOREIGN KEY (locality_id) REFERENCES `Locality` (id);
ALTER TABLE `Supply` ADD FOREIGN KEY (product_id) REFERENCES `Product` (id);
ALTER TABLE `Supply` ADD FOREIGN KEY (supplier_id) REFERENCES `Supplier` (id);

/* CONSTRAINTS */

ALTER TABLE `Customer` ADD CONSTRAINT `Customer_type` CHECK (type_customer IN (0, 1));
ALTER TABLE `Order` ADD CONSTRAINT `Order_type` CHECK (type_order IN (0, 1));
ALTER TABLE `Product` ADD CONSTRAINT `Product_price` CHECK (price > 0);
ALTER TABLE `Product` ADD CONSTRAINT `Product_cost` CHECK (cost > 0);
ALTER TABLE `Product` ADD CONSTRAINT `Product_size` CHECK (size > 0);
ALTER TABLE `Product` ADD CONSTRAINT `Product_stock` CHECK (stock > 0);
ALTER TABLE `Supply` ADD CONSTRAINT `Supply_cost` CHECK (cost > 0);


/* INSERTS */

 -- Pays :
INSERT INTO Country (label) VALUES ('Belgique'), ('France'), ('Pays-Bas'), ('Allemagne'), ('Luxembourg'), ('Suisse'), ('Royaume-Uni'), ('Irlande'), ('Espagne'), ('Portugal');

-- Localités :

    -- Belgique :
    INSERT INTO Locality (label, zip_code, country_id) VALUES ('Bruxelles', '1000', 1), ('Liège', '4000', 1), ('Namur', '5000', 1), ('Anvers', '2000', 1), ('Gand', '9000', 1);

    -- France :
    INSERT INTO Locality (label, zip_code, country_id) VALUES ('Paris', '75000', 2), ('Lyon', '69000', 2), ('Marseille', '13000', 2), ('Lille', '59000', 2), ('Bordeaux', '33000', 2);

    -- Allemagne :
    INSERT INTO Locality (label, zip_code, country_id) VALUES ('Berlin', '10178', 4), ('Munich', '80331', 4), ('Francfort', '60306', 4), ('Hambourg', '20099', 4), ('Cologne', '50667', 4);

    -- Luxembourg :
    INSERT INTO Locality (label, zip_code, country_id) VALUES ('Luxembourg', '1009', 5), ('Esch-sur-Alzette', '4002', 5), ('Dudelange', '3450', 5), ('Pétange', '4730', 5), ('Ettelbruck', '9055', 5);

-- Customers :

INSERT INTO Customer (first_name, last_name, email, street_and_number, locality_id, type_customer) VALUES ('Marie', 'Martin', 'marie.martin@gmail.com', 'Rue du Midi 10', 1, 0);
INSERT INTO Customer (first_name, last_name, email, phone, street_and_number, locality_id, billing_address, type_customer) VALUES ('Pierre', 'Durant', 'pîerre.durant@gmail.com', '0478/12.34.56', 'Rue du Midi 16', 1, 'Rue du Midi 16', 1);

-- Orders :

INSERT INTO `Order` (order_date, is_paid, customer_id, reduction, type_order, date_of_payment, shipping_adress) VALUES ('2020-01-01', 1, 1, 0, 0, '2020-01-01', 'Rue du Midi 10');
INSERT INTO `Order` (order_date, is_paid, customer_id, reduction, type_order, date_of_payment, shipping_adress) VALUES ('2020-01-01', 1, 2, 0, 0, '2020-01-01', 'Rue du Midi 16');

-- Lines :


-- Products :

INSERT INTO Product (label, color, price, cost, size, stock, addition_date, is_shippable, category_id, information, image_link) VALUES ('Chaise', 'Blanc', 50, 20, 50, 10, '2020-01-01', 1, 1, 'Chaise en bois', 'https://www.ikea.com/be/fr/images/products/ingolf-chaise-blanc__0717143_PE731522_S5.JPG?f=s');
INSERT INTO Product (label, color, price, cost, size, stock, addition_date, is_shippable, category_id, information, image_link) VALUES ('Table', 'Blanc', 100, 50, 100, 10, '2020-01-01', 1, 1, 'Table en bois', 'https://www.ikea.com/be/fr/images/products/ingatorp-table-extensible-blanc__0717144_PE731523_S5.JPG?f=s');
INSERT INTO Product (label, color, price, cost, size, stock, addition_date, is_shippable, category_id, information, image_link) VALUES ('Armoire', 'Blanc', 200, 100, 200, 10, '2020-01-01', 1, 1, 'Armoire en bois', 'https://www.ikea.com/be/fr/images/products/brimnes-armoire-avec-3-portes-blanc__0717145_PE731524_S5.JPG?f=s');
INSERT INTO Product (label, color, price, cost, size, stock, addition_date, is_shippable, category_id, information, image_link) VALUES ('Canapé', 'Blanc', 300, 150, 300, 10, '2020-01-01', 1, 1, 'Canapé en bois', 'https://www.ikea.com/be/fr/images/products/ektorp-canape-3-places-nordvalla-blanc__0717146_PE731525_S5.JPG?f=s');
INSERT INTO Product (label, color, price, cost, size, stock, addition_date, is_shippable, category_id, information, image_link) VALUES ('Bureau', 'Blanc', 400, 200, 400, 10, '2020-01-01', 1, 1, 'Bureau en bois', 'https://www.ikea.com/be/fr/images/products/malm-bureau-blanc__0717147_PE731526_S5.JPG?f=s');

-- Categories :

INSERT INTO Category (label) VALUES ('Meubles'), ('Décoration'), ('Luminaires'), ('Tapis'), ('Rangements'), ('Cuisine'), ('Salle de bain'), ('Chambre'), ('Jardin'), ('Bureau');

-- Suppliers :

INSERT INTO Supplier (label, phone, email, street_and_number, locality_id) VALUES ('IKEA', '02/719.19.22', 'ikea.supply@ikea.com', 'Rue du centre 5', 2);
INSERT INTO Supplier (label, phone, email, street_and_number, locality_id) VALUES ('Leen Bakker', '02/719.19.22', 'leenbekker.supply@gmail.com', 'Rue du centre 2', 4);

