create database codecoolshop;

create table if not exists supplier
(
  name        varchar not null,
  description varchar,
  id          serial  not null
    constraint supplier_id_pk
    primary key
);

create unique index if not exists supplier_id_uindex
  on supplier (id);

create table if not exists category
(
  id          serial  not null
    constraint category_pkey
    primary key,
  name        varchar not null,
  description varchar
);

create table if not exists product
(
  id               serial           not null
    constraint product_pkey
    primary key,
  name             varchar          not null,
  description      varchar,
  default_price    double precision not null,
  default_currency varchar          not null,
  supplier_id      integer          not null
    constraint product_supplier_id_fk
    references supplier,
  category_id      integer          not null
    constraint product_category_id_fk
    references category
);

create unique index if not exists product_id_uindex
  on product (id);

create unique index if not exists category_id_uindex
  on category (id);

create table if not exists credit_card
(
  id              serial  not null
    constraint credit_card_pkey
    primary key,
  card_number     varchar not null,
  card_holder     varchar not null,
  expiration_date varchar not null,
  cvv             integer not null
);

create table if not exists "user"
(
  id             serial  not null
    constraint user_pkey
    primary key,
  username       varchar not null,
  password       varchar not null,
  email_adress   varchar not null,
  phone_number   varchar,
  credit_card_id integer not null
    constraint user_credit_card_id_fk
    references credit_card
);

create table if not exists "order"
(
  id      serial  not null
    constraint order_pkey
    primary key,
  date    date    not null,
  user_id integer not null
    constraint order_user_id_fk
    references "user"
);

create unique index if not exists order_id_uindex
  on "order" (id);

create unique index if not exists user_id_uindex
  on "user" (id);

create unique index if not exists credit_card_id_uindex
  on credit_card (id);

create unique index if not exists credit_card_card_number_uindex
  on credit_card (card_number);

create table if not exists cart
(
  id          serial  not null
    constraint cart_pkey
    primary key,
  total_price double precision,
  user_id     integer not null
    constraint cart_user_id_fk
    references "user"
);

create unique index if not exists cart_id_uindex
  on cart (id);

create table if not exists cart_product
(
  cart_id    integer not null
    constraint cart_product_cart_id_fk
    references cart,
  product_id integer not null
    constraint cart_product_product_id_fk
    references product
);

create table if not exists product_order
(
  order_id   integer not null
    constraint product_order_order_id_fk
    references "order",
  product_id integer not null
    constraint product_order_product_id_fk
    references product
);

create table if not exists shipping_adress
(
  id      serial  not null
    constraint shipping_adress_pkey
    primary key,
  country varchar not null,
  city    varchar not null,
  zip     integer not null,
  adress  varchar not null
);

create unique index if not exists shipping_adress_id_uindex
  on shipping_adress (id);

create table if not exists billing_adress
(
  id      serial  not null
    constraint billing_adress_pkey
    primary key,
  country varchar not null,
  city    varchar not null,
  zip     integer not null,
  adress  varchar not null
);

create unique index if not exists billing_adress_id_uindex
  on billing_adress (id);

create table if not exists shipping_user
(
  shipping_id integer not null
    constraint shipping_user_shipping_adress_id_fk
    references shipping_adress,
  user_id     integer not null
    constraint shipping_user_user_id_fk
    references "user"
);

create table if not exists billing_user
(
  billing_id integer not null
    constraint billing_user_billing_adress_id_fk
    references billing_adress,
  user_id    integer not null
    constraint billing_user_user_id_fk
    references "user"
);

