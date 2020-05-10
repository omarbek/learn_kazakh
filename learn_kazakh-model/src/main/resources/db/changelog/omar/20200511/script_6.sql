insert into tasks (id, common, icon_path, name, parent_id, role_id)
values (15, 0, 'find_word.png', 'Найди слово', 3, 2);
update tasks
set navigate_path='Найди слово'
where id = 3;