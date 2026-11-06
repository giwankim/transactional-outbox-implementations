-- Backing table for Spring Integration's JdbcChannelMessageStore (transactional outbox).
-- Copied from spring-integration-jdbc 7.1.2:
-- https://github.com/spring-projects/spring-integration/blob/v7.1.2/spring-integration-jdbc/src/main/resources/org/springframework/integration/jdbc/schema-mysql.sql
CREATE TABLE INT_CHANNEL_MESSAGE
(
    MESSAGE_ID       CHAR(36)     NOT NULL,
    GROUP_KEY        CHAR(36)     NOT NULL,
    CREATED_DATE     BIGINT       NOT NULL,
    MESSAGE_PRIORITY BIGINT,
    MESSAGE_SEQUENCE BIGINT       NOT NULL AUTO_INCREMENT UNIQUE,
    MESSAGE_CONTENT  BLOB,
    REGION           VARCHAR(100) NOT NULL,
    CONSTRAINT INT_CHANNEL_MESSAGE_PK PRIMARY KEY (REGION, GROUP_KEY, CREATED_DATE, MESSAGE_SEQUENCE)
) ENGINE = InnoDB;

CREATE INDEX INT_CHANNEL_MSG_DELETE_IDX ON INT_CHANNEL_MESSAGE (REGION, GROUP_KEY, MESSAGE_ID);
