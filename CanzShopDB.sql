CREATE TABLE Staff (
ID serial PRIMARY KEY,
Last_Name text NOT NULL,
Name text NOT NULL,
Middle_Name text NULL,
Gender text NOT NULL,
Email text NOT NULL UNIQUE,
Password text NOT NULL
);

CREATE TABLE Concurrency (
ID serial PRIMARY KEY,
Staff_ID integer REFERENCES Staff (ID)
);

CREATE TABLE Supplier (
ID serial PRIMARY KEY,
Name text NOT NULL UNIQUE
);

CREATE TABLE Event (
ID serial PRIMARY KEY,
Name text NOT NULL UNIQUE
);

CREATE TABLE Storage (
ID serial PRIMARY KEY,
Address text NOT NULL UNIQUE,
Capacity integer NOT NULL CONSTRAINT Positive_Capacity CHECK (Capacity >= 0)
);

CREATE TABLE Product (
ID serial PRIMARY KEY,
Number text NOT NULL UNIQUE,
Name text NOT NULL,
Price numeric NOT NULL CONSTRAINT Positive_Price CHECK (Price::numeric >= 0.00),
Amount integer NOT NULL CONSTRAINT Positive_Amount CHECK (Amount >= 0),
Supplier_ID integer REFERENCES Supplier (ID),
Storage_ID integer REFERENCES Storage (ID),
Event_ID integer REFERENCES Event (ID)
);

CREATE TABLE Category (
ID serial PRIMARY KEY,
Name text NOT NULL UNIQUE
);

CREATE TABLE Compliance (
ID serial PRIMARY KEY,
Product_ID integer REFERENCES Product (ID),
Category_ID integer REFERENCES Category (ID)
);

CREATE TABLE Buyer (
ID serial PRIMARY KEY,
Phone varchar(11) NULL,
Email text NOT NULL UNIQUE,
Password text NOT NULL
);

CREATE TABLE Discount_Card (
ID serial PRIMARY KEY REFERENCES Buyer (ID),
Number integer NOT NULL UNIQUE,
Discount integer NOT NULL 
CONSTRAINT Positive_Discount CHECK (Discount >= 0), 
CONSTRAINT Logical_Discount CHECK (Discount <= 100)
);

CREATE TABLE Cart (
ID serial PRIMARY KEY,
Status boolean NULL default(false),
Amount int NOT NULL,
Buyer_ID integer REFERENCES Buyer (ID),
Compliance_ID integer REFERENCES Compliance (ID)
);

CREATE TABLE Orderr (
ID serial PRIMARY KEY REFERENCES Cart (ID),
Number integer NOT NULL,
Date date NULL default(now()),
Payment boolean NULL default(false),
Concurrency_ID integer REFERENCES Concurrency (ID),
Status_ID integer REFERENCES Status (ID) default(1),
Delivery_ID integer REFERENCES Delivery_Point (ID)
);

CREATE TABLE Userr (
ID serial PRIMARY KEY,
Active boolean NOT NULL,
Password text NOT NULL,
Username text NOT NULL
);

CREATE TABLE User_Role (
User_ID serial PRIMARY KEY REFERENCES Userr (ID),
Roles varchar(255) NOT NULL
);
END;
