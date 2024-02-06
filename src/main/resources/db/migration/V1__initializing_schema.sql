CREATE TABLE IF NOT EXISTS `url_model` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `short_key` VARCHAR(255) NOT NULL,
    `original_url` VARCHAR(255) NOT NULL,
    `key_generation_method` VARCHAR(255) NOT NULL
);