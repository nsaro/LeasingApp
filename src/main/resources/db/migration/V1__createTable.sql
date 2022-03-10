CREATE TABLE `customers`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `birth_date` datetime     DEFAULT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name`  varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `vehicles`
(
    `id`                    bigint NOT NULL AUTO_INCREMENT,
    `brand`                 varchar(255) DEFAULT NULL,
    `identification_number` varchar(255) DEFAULT NULL,
    `model`                 varchar(255) DEFAULT NULL,
    `price`                 double       DEFAULT NULL,
    `year`                  int          DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `leasing_contracts`
(
    `id`              bigint NOT NULL AUTO_INCREMENT,
    `contract_number` bigint DEFAULT NULL,
    `monthly_rate`    double NOT NULL,
    `customer_id`     bigint DEFAULT NULL,
    `vehicle_id`      bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY               `FKktxx1ivm1hjnrpo7wnlj6l7a3` (`customer_id`),
    KEY               `FKm7acrdbw4pneb4v1vqrxt6sta` (`vehicle_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
