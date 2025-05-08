START TRANSACTION;

INSERT INTO customers (id, created, email, modified, name) VALUES
(1, CURRENT_TIMESTAMP, 'email@email.com', CURRENT_TIMESTAMP, 'John Doe'),
(2, CURRENT_TIMESTAMP, 'email@email.com', CURRENT_TIMESTAMP, 'Jane Doe'),
(3, CURRENT_TIMESTAMP, 'email@email.com', CURRENT_TIMESTAMP, 'Jim Doe');

INSERT INTO products (id, created, default_price, modified, name, deleted) VALUES
(1, CURRENT_TIMESTAMP, 10.00, CURRENT_TIMESTAMP, 'Product 1', false),
(2, CURRENT_TIMESTAMP, 20.00, CURRENT_TIMESTAMP, 'Product 2', false),
(3, CURRENT_TIMESTAMP, 30.00, CURRENT_TIMESTAMP, 'Product 3', false);

INSERT INTO orders (id, created, modified, order_date, quantity, status, total, customer_id) VALUES
(1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_DATE, 2, 'CREATED', 20.00, 1),
(2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_DATE, 1, 'CREATED', 10.00, 2),
(3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_DATE, 3, 'CREATED', 90.00, 3);

INSERT INTO order_item (id, created, modified, order_id, product_id, quantity, price) VALUES
(1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, 2, 15.0),
(2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 2, 1, 5.0),
(3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 3, 3, 5.0);

COMMIT;