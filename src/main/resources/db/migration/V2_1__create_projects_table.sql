-- projects table --
CREATE TABLE `projects` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_general_ci;

-- scenario table --
CREATE TABLE `scenario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_general_ci;

-- tags table --
CREATE TABLE `tags` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  constraint tag_name_unique unique (name),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_general_ci;

-- project_scenario table --
CREATE TABLE `project_scenario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `project_id` bigint DEFAULT NULL,
  `scenario_id` bigint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`id`),
  KEY `fk_project_id_ps` (`project_id`),
  KEY `fk_scenario_id_ps` (`scenario_id`),
  CONSTRAINT `fk_project_id_ps` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `fk_scenario_id_ps` FOREIGN KEY (`scenario_id`) REFERENCES `scenario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_general_ci;

-- tag_scenario table --
CREATE TABLE `tag_scenario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tag_id` bigint DEFAULT NULL,
  `scenario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tag_id_ts` (`tag_id`),
  KEY `fk_scenario_id_ts` (`scenario_id`),
  CONSTRAINT `fk_tag_id_ts` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`),
  CONSTRAINT `fk_scenario_id_ts` FOREIGN KEY (`scenario_id`) REFERENCES `scenario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_general_ci;