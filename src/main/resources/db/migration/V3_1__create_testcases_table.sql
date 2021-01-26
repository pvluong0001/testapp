-- testcases table --
CREATE TABLE `testcases`
(
    `id`          bigint   NOT NULL AUTO_INCREMENT,
    `name`        varchar(255),
    `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `created_at`  datetime NOT NULL,
    `updated_at`  datetime                                                  DEFAULT NULL,
    `deleted_at`  datetime                                                  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE utf8mb4_general_ci;

-- scenario_testcase table --
CREATE TABLE `scenario_testcase`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `testcase_id` bigint       DEFAULT NULL,
    `scenario_id` bigint       DEFAULT NULL,
    `order`       smallint     DEFAULT 0,
    `name`        varchar(255) DEFAULT NULL,
    `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    PRIMARY KEY (`id`),
    KEY `fk_testcase_id_st` (`testcase_id`),
    KEY `fk_scenario_id_st` (`scenario_id`),
    CONSTRAINT `fk_testcase_id_st` FOREIGN KEY (`testcase_id`) REFERENCES `testcases` (`id`),
    CONSTRAINT `fk_scenario_id_st` FOREIGN KEY (`scenario_id`) REFERENCES `scenarios` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE utf8mb4_general_ci;