DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;

CREATE TABLE IF NOT EXISTS categories
(
    id    LONG PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE,
    title VARCHAR(24)                     NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS products
(
    id          LONG PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE,
    title       VARCHAR(24)                     NOT NULL UNIQUE,
    price       DOUBLE                          NOT NULL,
    image_url    VARCHAR(264)                    NOT NULL,
    category_id LONG                            NOT NULL,
    CONSTRAINT fk_category
        FOREIGN KEY (category_id)
            REFERENCES categories (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
