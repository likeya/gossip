-- The init mysql scripts for gossip system

-- Table Article
CREATE TABLE IF NOT EXISTS `article` (
  `id`         INT                  NOT NULL AUTO_INCREMENT,
  `name`       VARCHAR(128)         NOT NULL,
  `unique_key` VARCHAR(1024) UNIQUE NOT NULL,
  `url`        VARCHAR(512)         NOT NULL,
  `deleted`    BIGINT               NOT NULL DEFAULT 0
  COMMENT '0 is normal status, the record would be deleted if this column is bigger than zero',

  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPRESSED
  KEY_BLOCK_SIZE = 8;

-- Table Author
CREATE TABLE IF NOT EXISTS `author` (
  `id`          INT                 NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(128)        NOT NULL,
  `email`       VARCHAR(128) UNIQUE NOT NULL,
  `homepage`    VARCHAR(256)        NOT NULL DEFAULT '',
  `author_type` INT                 NOT NULL DEFAULT 0
  COMMENT '0 identity the guest commenter',
  `create_time` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT 'first comment time, would be the import time if create from other system',
  `deleted`     BIGINT              NOT NULL DEFAULT 0
  COMMENT '0 is normal status, the record would be deleted if this column is bigger than zero',

  PRIMARY KEY (`id`),
  KEY `author_type_idx` (`author_type`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPRESSED
  KEY_BLOCK_SIZE = 8;

-- Table Comment
-- TODO transform it to a jump table
CREATE TABLE IF NOT EXISTS `comment` (
  `id`               INT          NOT NULL   AUTO_INCREMENT,
  `author_id`        INT          NOT NULL,
  `article_id`       INT          NOT NULL,
  `message`          TEXT         NOT NULL,
  `parent_id`        INT COMMENT 'the comment parent id',
  `status`           SMALLINT     NOT NULL   DEFAULT 0
  COMMENT '0 requires the operator\'s examination',
  `create_time`      TIMESTAMP    NOT NULL   DEFAULT CURRENT_TIMESTAMP
  COMMENT 'first comment time, would be the import time if create from other system',
  `last_update_time` TIMESTAMP    NOT NULL   DEFAULT CURRENT_TIMESTAMP,
  `version`          VARCHAR(128) NOT NULL   DEFAULT ''
  COMMENT 'reserve column for optimistic lock',
  `deleted`          BIGINT       NOT NULL   DEFAULT 0
  COMMENT '0 is normal status, the record would be deleted if this column is bigger than zero',

  PRIMARY KEY (`id`),
  FOREIGN KEY `comment_author_idx` (`author_id`) REFERENCES `author` (`id`),
  FOREIGN KEY `comment_article_idx` (`article_id`) REFERENCES `article` (`id`),
  KEY `parent_comment_idx` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPRESSED
  KEY_BLOCK_SIZE = 8;
