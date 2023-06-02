CREATE TABLE pet
(
    id                  SERIAL      NOT NULL,
    pet_store_pet_id    BIGINT      NOT NULL,
    name                VARCHAR(64) NOT NULL,
    category            VARCHAR(64) NOT NULL,
    employee_id         INT         NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_pet_employee
        FOREIGN KEY (employee_id)
            REFERENCES employee (employee_id)
);