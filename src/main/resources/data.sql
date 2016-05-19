INSERT INTO USERS (username,password,email,name,role) VALUES('admin','$2a$10$hxFXm9rOc4HZ5pUUDU9Meuq6Hg1JsaAlqU.tqkRJuYiF5EfmLvitO','admin@pizzeria.com', 'Admin','ADMIN');
INSERT INTO USERS (username,password,email,name,role) VALUES('user1','$2a$10$uIiLSr8fJqZkThJVAoiGWu98.8k0tNNRJa2TAcoOtVsqyij94/r2i','user1@pizzeria.com', 'User 1','USER');
INSERT INTO USERS (username,password,email,name,role) VALUES('user2','$2a$10$uIiLSr8fJqZkThJVAoiGWu98.8k0tNNRJa2TAcoOtVsqyij94/r2i','user2@pizzeria.com', 'User 2','USER');

INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Small (4 slices)', 'SIZE', 6.99, 10, true);
INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Medium (6 slices)', 'SIZE', 12.99, 10, true);
INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Large (8 slices)', 'SIZE', 17.99, 10, true);
INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Extra Large (12 slices)', 'SIZE', 21.99, 10, true);

INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('No Sauce', 'SAUCE', 0.00, 10, true);
INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Original', 'SAUCE', 0.00, 10, true);
INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Chunky Tomato', 'SAUCE', 0.99, 10, true);
INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Spicy', 'SAUCE', 0.99, 10, true);

INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Extra-Thin', 'BASE', 0.00, 10, true);
INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Thin Crust', 'BASE', 0.00, 10, true);
INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Deep Dish', 'BASE', 0.00, 10, true);
INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Stuffed', 'BASE', 4.99, 10, true);

INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Bacon', 'TOPPING', 0.99, 10, true);
INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Chicken', 'TOPPING', 0.99, 10, true);
INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Italian Sausage', 'TOPPING', 0.99, 10, true);
INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Ground Beef', 'TOPPING', 0.99, 10, true);
INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Black Olives', 'TOPPING', 0.99, 10, true);
INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Green Olives', 'TOPPING', 0.99, 10, true);
INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Broccoli', 'TOPPING', 0.99, 10, true);
INSERT INTO ITEM (name,category,price,stock_qty,enabled) VALUES ('Extra Cheese', 'TOPPING', 0.99, 10, true);