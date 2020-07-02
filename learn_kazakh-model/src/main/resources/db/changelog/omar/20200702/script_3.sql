create table tests
(
  id   int(11) AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) not null
);

create table test_results
(
  user_id int(11) UNSIGNED not null,
  test_id int(11)          not null,
  score   int(11)          not null,
  UNIQUE KEY test_of_user (user_id, test_id)
);
ALTER TABLE test_results
  ADD CONSTRAINT fk_test_results_tests
    FOREIGN KEY (test_id) REFERENCES tests (id);
ALTER TABLE test_results
  ADD CONSTRAINT fk_test_results_users
    FOREIGN KEY (user_id) REFERENCES users (id);