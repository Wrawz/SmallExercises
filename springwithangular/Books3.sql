CREATE TABLE customer (
  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  first_name VARCHAR(75)  NOT NULL  ,
  last_name VARCHAR(300)  NULL  ,
  email VARCHAR(255)  NULL  ,
  country VARCHAR(100)  NULL  ,
  state VARCHAR(100)  NULL  ,
  zip_code VARCHAR(50)  NULL  ,
  city VARCHAR(100)  NULL  ,
  street VARCHAR(100)  NULL  ,
  username VARCHAR(30)  NULL  ,
  password_2 VARCHAR(50)  NULL    ,
PRIMARY KEY(id));



CREATE TABLE author (
  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  name VARCHAR(200)  NOT NULL  ,
  birth DATE  NULL  ,
  death DATE  NULL  ,
  country VARCHAR(150)  NULL  ,
  about TEXT  NULL    ,
PRIMARY KEY(id));



CREATE TABLE orders (
  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  customer_id INTEGER UNSIGNED  NOT NULL  ,
  order_tracking_number VARCHAR(255)  NULL  ,
  total_price DECIMAL(19,2)  NULL  ,
  total_quantity INTEGER UNSIGNED  NULL  ,
  status_2 VARCHAR(255)  NULL  ,
  date_created DATE  NULL  ,
  last_updated DATE  NULL    ,
PRIMARY KEY(id)  ,
INDEX order_2_FKIndex1(customer_id),
  FOREIGN KEY(customer_id)
    REFERENCES customer(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE book (
  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  orders_id INTEGER UNSIGNED  NOT NULL  ,
  author_id INTEGER UNSIGNED  NOT NULL  ,
  title VARCHAR(200)  NOT NULL  ,
  synopsis TEXT  NULL  ,
  genre VARCHAR(300)  NULL  ,
  original_language VARCHAR(30)  NULL  ,
  price DECIMAL(19,2)  NOT NULL  ,
  image_url TEXT  NULL  ,
  available_languages TEXT  NULL  ,
  published_year INTEGER UNSIGNED  NULL  ,
  bought BOOL  NULL    ,
PRIMARY KEY(id)  ,
INDEX book_FKIndex1(author_id)  ,
INDEX book_FKIndex2(orders_id),
  FOREIGN KEY(author_id)
    REFERENCES author(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(orders_id)
    REFERENCES orders(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);




