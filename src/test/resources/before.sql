insert into order(id, orderPrice, orderStatus, orderDate, fulfillmentStatus) values ('1', 1000, 'PENDING_PAYMENT', DATE '2018-04-01', 'PENDING');
insert into order(id, orderPrice, orderStatus, orderDate, fulfillmentStatus) values ('2', 2000, 'PAID', DATE '2018-04-02', 'PENDING');
insert into order(id, orderPrice, orderStatus, orderDate, fulfillmentStatus) values ('3', 3000, 'PENDING_FULFILLMENT', DATE '2018-04-03', 'PENDING');
insert into order(id, orderPrice, orderStatus, orderDate, fulfillmentStatus) values ('4', 4000, 'PARTIALLY_FULFILLED', DATE '2018-04-03', 'PARTIAL');
insert into order(id, orderPrice, orderStatus, orderDate, fulfillmentStatus) values ('5', 5000, 'FULFILLED', DATE '2018-04-03', 'DONE');

insert into item(id, productid, quantity, perunitcost, fulfillmentstatus, order_id) values ('1','1', 100, 10, 'PENDING', '1');
insert into item(id, productid, quantity, perunitcost, fulfillmentstatus, order_id) values ('2','2', 50, 4, 'PENDING', '2');
insert into item(id, productid, quantity, perunitcost, fulfillmentstatus, order_id) values ('3','3', 150, 12, 'PENDING', '2');
insert into item(id, productid, quantity, perunitcost, fulfillmentstatus, order_id) values ('4','1', 300, 10, 'PENDING', '3');
insert into item(id, productid, quantity, perunitcost, fulfillmentstatus, order_id) values ('5','3', 333, 10, 'DONE', '4');
insert into item(id, productid, quantity, perunitcost, fulfillmentstatus, order_id) values ('6','1', 67, 10, 'PENDING', '4');
insert into item(id, productid, quantity, perunitcost, fulfillmentstatus, order_id) values ('7','3', 500, 10, 'DONE', '5');

insert into fulfillment(id, status, order_id) values ('1', 'PENDING', '1');
insert into fulfillment(id, status, order_id) values ('2', 'PENDING', '2');
insert into fulfillment(id, status, order_id) values ('3', 'PENDING', '2');
insert into fulfillment(id, status, order_id) values ('4', 'PENDING', '3');
insert into fulfillment(id, status, order_id) values ('5', 'DONE', '4');
insert into fulfillment(id, status, order_id) values ('6', 'PENDING', '4');
insert into fulfillment(id, status, order_id) values ('7', 'DONE', '5');