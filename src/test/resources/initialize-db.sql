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

-- Author
INSERT INTO `author` (`id`, `name`, `email`, `homepage`, `author_type`, `create_time`, `deleted`)
VALUES (888888, 'Test Author', 'example@example.com', 'http://www.example.com', NULL, NULL, NULL);

-- Comment
INSERT INTO `comment` (`id`, `author_id`, `article_id`, `message`, `parent_id`, `status`, `create_time`, `last_update_time`, `deleted`)
VALUES (111111, 888888, 888888, 'Test message', NULL, NULL, NULL, NULL, 0);

INSERT INTO `comment` (`id`, `author_id`, `article_id`, `message`, `parent_id`, `status`, `create_time`, `last_update_time`, `deleted`)
VALUES (111112, 888888, 888888, 'Test message', 111111, NULL, NULL, NULL, 0);

INSERT INTO `comment` (`id`, `author_id`, `article_id`, `message`, `parent_id`, `status`, `create_time`, `last_update_time`, `deleted`)
VALUES (111113, 888888, 888888, 'Test message', 111112, NULL, NULL, NULL, 0);
