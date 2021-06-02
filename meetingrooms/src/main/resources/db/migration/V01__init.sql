

CREATE TABLE IF NOT EXISTS `meetingrooms` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb3_hungarian_ci NOT NULL,
  `width` int(10) unsigned NOT NULL,
  `length` int(10) unsigned NOT NULL,
  `area` int(10) unsigned GENERATED ALWAYS AS (`width` * `length`) VIRTUAL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_hungarian_ci;

INSERT INTO `meetingrooms` (`name`, `width`, `length`) VALUES
	('Valéria', 5, 7),
	('Benjámin', 9, 9),
	('Ábrahám', 7, 11),
	('Ménrót', 12, 14),
	('Ráhel', 3, 4);
