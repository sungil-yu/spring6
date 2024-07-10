create table orders (id bigint not null, no varchar(255), total number(38,2), primary key (id))
create sequence orders_SEQ start with 1 increment by 50
