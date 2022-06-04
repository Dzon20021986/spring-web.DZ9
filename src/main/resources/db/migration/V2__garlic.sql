create table if not exists garlic (id bigserial primary key , title varchar(255), cost int, category varchar(255));

insert into garlic (title, cost, category)
values ( 'Яица', 50,'Пищевая категория'),
       ( 'Марковь',40,'Овощная категория'),
       ( 'Лук', 35,'Овощная категория'),
       ( 'Свекла', 45,'Овощная категория'),
       ( 'Горох', 20,'Овощная категория'),
       ( 'Капуста', 60,'Овощная категория'),
       ( 'Кабачки', 33,'Овощная категория'),
       ( 'Ананасы', 145,'Фруктовая категория'),
       ( 'Дыни', 150,'Фруктовая категория'),
       ( 'Апельсины', 120,'Фруктовая категория'),
       ( 'Яблоки', 20, 'Фруктовая категория'),
       ( 'Мандарины', 111,'Фруктовая категория'),
       ( 'Мука', 66,'Зерновая категория'),
       ( 'Кефир', 67,'Молочная категория'),
       ( 'Йогурт', 89,'Молочная категория'),
       ( 'Чеснок', 25,'Овощная категория'),
       ( 'Макароны', 55,'Зерновая категория'),
       ( 'Рис', 130,'Зерновая категория'),
       ( 'Гречка', 135,'Зерновая категория'),
       ( 'Молоко', 55,'Молочная категория');

create table users (
    id bigserial primary key,
    username varchar(36) not null,
    password varchar(80) not null,
    email varchar(50) unique,
    created_at timestamp default current_timestamp,
    update_at timestamp default current_timestamp
);

create table roles (
    id bigserial primary key,
    name varchar(50) not null,
    created_at timestamp default current_timestamp,
    update_at timestamp default current_timestamp
);

CREATE TABLE users_roles(
    user_id bigint not null  references users(id),
    role_id bigint not null  references roles(id),
    primary key (user_id, role_id)
);

insert into roles(name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('Max', '$2a$12$y6VpnODzkjKDrEfqKDNg/.EWshwCh2uPCPTltckkpDkAOcPpWEPBa',max_777@gmail.com),
       ('Oleg', '$2a$12$cYacIkLiN6tBtYBYSZyhAuQZtB2qWK87fKYUcUR6QH5D0AJiHuyke', oleg_999@mail.com);

insert into users_roles(user_id, role_id)
values (1, 1),
       (2, 2);

//create table if not exists cart(id bigserial primary key , status varchar(255), product_id bigserial, count long,  FOREIGN KEY (product_id) REFERENCES garlic (id) );