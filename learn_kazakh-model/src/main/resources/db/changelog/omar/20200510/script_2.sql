INSERT INTO roles (id, name)
VALUES (1, 'Администратор');
INSERT INTO roles (id, name)
VALUES (2, 'Пользователь (ребенок)');
INSERT INTO roles (id, name)
VALUES (3, 'Пользователь (взрослый)');

INSERT INTO tasks (id, common, icon_path, name, navigate_path, parent_id, role_id)
VALUES (1, 1, null, 'Домой', 'Главная', null, null);
INSERT INTO tasks (id, common, icon_path, name, navigate_path, parent_id, role_id)
VALUES (2, 0, null, 'Учиться', 'Алфавит', null, 2);
INSERT INTO tasks (id, common, icon_path, name, navigate_path, parent_id, role_id)
VALUES (4, 1, null, 'В бой!', null, null, null);
INSERT INTO tasks (id, common, icon_path, name, navigate_path, parent_id, role_id)
VALUES (5, 1, null, 'Клуб', null, null, null);
INSERT INTO tasks (id, common, icon_path, name, navigate_path, parent_id, role_id)
VALUES (6, 1, null, 'Контакты', null, null, null);
INSERT INTO tasks (id, common, icon_path, name, navigate_path, parent_id, role_id)
VALUES (7, 1, null, 'О нас', null, null, null);
INSERT INTO tasks (id, common, icon_path, name, navigate_path, parent_id, role_id)
VALUES (8, 1, null, 'Замечания', null, null, null);
INSERT INTO tasks (id, common, icon_path, name, navigate_path, parent_id, role_id)
VALUES (9, 1, null, 'Отзывы', null, null, null);
INSERT INTO tasks (id, common, icon_path, name, navigate_path, parent_id, role_id)
VALUES (10, 1, 'menu/main.png', 'Главная', null, 1, null);
INSERT INTO tasks (id, common, icon_path, name, navigate_path, parent_id, role_id)
VALUES (11, 1, 'menu/books.png', 'Книги', null, 1, null);
INSERT INTO tasks (id, common, icon_path, name, navigate_path, parent_id, role_id)
VALUES (12, 1, 'menu/music.png', 'Музыка', null, 1, null);
INSERT INTO tasks (id, common, icon_path, name, navigate_path, parent_id, role_id)
VALUES (13, 0, 'menu/alphabet.png', 'Алфавит', null, 2, 2);

INSERT INTO alphabet (id, letter)
VALUES (1, 'Аа');
INSERT INTO alphabet (id, letter)
VALUES (2, 'Әә');

INSERT INTO alphabet_media (id, audio_source, image_source, alphabet_id)
VALUES (1, 'akku.m4a', 'akku.jpg', 1);
INSERT INTO alphabet_media (id, audio_source, image_source, alphabet_id)
VALUES (2, 'alma.m4a', 'alma.jpg', 1);
INSERT INTO alphabet_media (id, audio_source, image_source, alphabet_id)
VALUES (3, 'arystan.m4a', 'arystan.png', 1);
INSERT INTO alphabet_media (id, audio_source, image_source, alphabet_id)
VALUES (4, 'atesh.m4a', 'atesh.jpg', 2);