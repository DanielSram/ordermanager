CREATE TABLE customer (
  id          BIGINT  PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(255) NOT NULL,
  phone       VARCHAR(50) NOT NULL
);

CREATE TABLE orders (
  id          BIGINT  PRIMARY KEY AUTO_INCREMENT,
  customer_id BIGINT,
  state       VARCHAR(50) NOT NULL,
  foreign key (customer_id) references customer(id)
);

CREATE TABLE product (
  id          BIGINT  PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(255) NOT NULL,
  price       DECIMAL NOT NULL
);

CREATE TABLE order_item (
  id          BIGINT  PRIMARY KEY AUTO_INCREMENT,
  order_id    BIGINT,
  product_id  BIGINT,
  quantity    INTEGER,
  foreign key (order_id)   references orders(id),
  foreign key (product_id) references product(id)
);
