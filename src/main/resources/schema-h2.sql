-- The init h2 scripts for gossip system

-- Table Article
CREATE TABLE IF NOT EXISTS `article` (
  `id`         INT                  NOT NULL AUTO_INCREMENT,
  `name`       VARCHAR(128)         NOT NULL,
  `unique_key` VARCHAR(1024) UNIQUE NOT NULL,
  `url`        VARCHAR(512)         NOT NULL,
  `deleted`    BIGINT               NOT NULL DEFAULT 0
  COMMENT '0 is normal status, the record would be deleted if this column is bigger than zero',

  PRIMARY KEY (`id`)
);

-- Table Author
CREATE TABLE IF NOT EXISTS `author` (
  `id`          INT                 NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(128)        NOT NULL,
  `email`       VARCHAR(128) UNIQUE NOT NULL,
  `homepage`    VARCHAR(256)        NOT NULL DEFAULT '',
  `author_type` INT                 NOT NULL DEFAULT 0,
  `create_time` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted`     BIGINT              NOT NULL DEFAULT 0,

  PRIMARY KEY (`id`)
);

-- Table Comment
-- TODO transform it to a jump table
CREATE TABLE IF NOT EXISTS `comment` (
  `id`               INT          NOT NULL AUTO_INCREMENT,
  `author_id`        INT          NOT NULL,
  `article_id`       INT          NOT NULL,
  `message`          TEXT         NOT NULL,
  `parent_id`        INT COMMENT 'the comment parent id',
  `status`           SMALLINT     NOT NULL DEFAULT 0,
  `create_time`      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted`          BIGINT       NOT NULL DEFAULT 0,

  PRIMARY KEY (`id`),
  FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
);
