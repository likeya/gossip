-- The initialize data for test purpose

-- Do some clean
DELETE FROM `comment`;
DELETE FROM `article`;
DELETE FROM `author`;

-- Article

INSERT INTO `article` (`id`, `name`, `unique_key`, `url`, `deleted`)
VALUES (888888, 'Test Article 1', '12345678', 'http://www.baidu.com', 0);

INSERT INTO `article` (`id`, `name`, `unique_key`, `url`, `deleted`)
VALUES (999999, 'Test Article 2', '12345679', 'http://www.example.com', 0);

INSERT INTO `article` (`id`, `name`, `unique_key`, `url`, `deleted`)
VALUES (777777, 'Test Article 3', 'a-unique-key', 'http://www.example.com/ssssss', 0);

