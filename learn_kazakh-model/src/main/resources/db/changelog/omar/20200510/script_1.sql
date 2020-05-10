create table roles
(
  id   int(11) AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) not null
);

create table users
(
  id       int(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  username varchar(255) not null,
  password varchar(255) not null,
  role_id  int(11)      not null
);
ALTER TABLE users
  ADD CONSTRAINT fk_users_roles
    FOREIGN KEY (role_id) REFERENCES roles (id);

create table tasks
(
  id            int(11) AUTO_INCREMENT PRIMARY KEY,
  common        tinyint(1)   not null default 0,
  icon_path     varchar(255),
  name          varchar(255) not null,
  navigate_path varchar(255),
  parent_id     int(11),
  role_id       int(11)
);
ALTER TABLE tasks
  ADD CONSTRAINT fk_tasks_tasks
    FOREIGN KEY (parent_id) REFERENCES tasks (id);
ALTER TABLE tasks
  ADD CONSTRAINT fk_tasks_roles
    FOREIGN KEY (role_id) REFERENCES roles (id);

create table alphabet
(
  id     int(11) AUTO_INCREMENT PRIMARY KEY,
  letter varchar(255) not null
);

create table alphabet_media
(
  id           int(11) AUTO_INCREMENT PRIMARY KEY,
  audio_source varchar(255) not null,
  image_source varchar(255) not null,
  alphabet_id  int(11)      not null
);
ALTER TABLE alphabet_media
  ADD CONSTRAINT fk_alphabet_media_alphabet
    FOREIGN KEY (alphabet_id) REFERENCES alphabet (id);