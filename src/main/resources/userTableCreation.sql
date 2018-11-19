drop table if exists users;

create table users (
  id int primary key DEFAULT nextval('user_id_seq'),
  username varchar(60) not null,
  password varchar(60) not null,
  enabled  int not null default 1
);

drop table if exists authorities;
create table authorities (
  id int not null primary key DEFAULT nextval('authorities_id_seq'),
  user_id  int not null,
  authority varchar(60) not null,
  constraint user_id foreign key (user_id) references users (id)
);

create unique index auth_unique_index
  on authorities (auth_user, authority);

CREATE SEQUENCE user_id_seq;
CREATE SEQUENCE authorities_id_seq;