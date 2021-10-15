CREATE TABLE bookTBL(
    isbn NUMBER(5) primary key,
    title varchar2(50) not null,
    author varchar2(250) not null,
    publisher varchar(50) not null,
    price number(6) not null,
    "desc" varchar2(200),
    publish_date varchar2(10)
);