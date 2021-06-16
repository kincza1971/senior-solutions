CREATE TABLE `meetings` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`organizer` VARCHAR(255) NOT NULL,
	`from_dt` TIMESTAMP NOT NULL,
	`to_dt` TIMESTAMP NOT NULL,
	`mr_id` INT UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `KF_Meeting_meetingroom` FOREIGN KEY (`mr_id`) REFERENCES `meetingrooms` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
)
COLLATE='utf8mb3_hungarian_ci'
;
