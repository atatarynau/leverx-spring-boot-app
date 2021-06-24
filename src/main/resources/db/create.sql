CREATE TABLE IF NOT EXISTS owners
(
    owner_id  bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name varchar (20),
    last_name  varchar (20),
    passport_number varchar (20),
    is_alive   boolean NOT NULL,
    phone_number varchar (20)
);

CREATE TABLE IF NOT EXISTS pets
(
    pet_id   bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    is_alive   boolean NOT NULL,
    breed      varchar (20),
    name       varchar (20),
    age        int,
    owner_id   bigint,
    CONSTRAINT owner_id_fk FOREIGN KEY (owner_foreing_key) REFERENCES owner (owner_id)
);

CREATE TABLE IF NOT EXISTS cat
(
    cat_id bigint PRIMARY KEY,
    has_wool boolean NOT NULL,
    CONSTRAINT pet_id_fk FOREIGN KEY (cat_id) REFERENCES pet (pet_id)
);

CREATE TABLE IF NOT EXISTS dog
(
    dog_id bigint PRIMARY KEY,
    is_trained boolean NOT NULL,
    CONSTRAINT pet_id_fk FOREIGN KEY (dog_id) REFERENCES pet (pet_id)
);