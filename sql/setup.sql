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

INSERT INTO Customer (first_name, last_name, email, phone_number, address, zip_code, locality_id, billing_address, billing_zip_code, billing_locality_id, type) VALUES ('Jean', 'Dupont', 'jean.dupont@gmail.com', '02 123 45 67', 'Rue du Midi 10', '1000', 1, 'Rue Royale 20', '1000', 1, 0);