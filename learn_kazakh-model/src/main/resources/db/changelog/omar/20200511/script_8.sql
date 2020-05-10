alter table alphabet_media
  add (name varchar(255) not null );
update alphabet_media set name='Аққу' where id=1;
update alphabet_media set name='Алма' where id=2;
update alphabet_media set name='Арыстан' where id=3;
update alphabet_media set name='Әтеш' where id=4;
update alphabet_media set name='Әке' where id=5;
update alphabet_media set name='Әлем' where id=6;