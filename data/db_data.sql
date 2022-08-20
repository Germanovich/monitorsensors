INSERT INTO users (login, password, role)
VALUES ('user', '$2a$10$eBZkl6ELqe.N/lvCq6xvnOIWtwmC5hq2YnwwR.vSyPXNFhTil2S1W', 'Viewer'),
       ('admin', '$2a$10$Z/R6KRpNjag.cJzkXepAhOxYZP/sB98e4PBEblYvxc8i.nEOP1YOW', 'Administrator');

INSERT INTO units (name)
VALUES ('bar'),
       ('voltage'),
       ('°С'),
       ('%');

INSERT INTO types (name)
VALUES ('Pressure'),
       ('Voltage'),
       ('Temperature'),
       ('Humidity');

INSERT INTO ranges ("from", "to")
VALUES (10, 15),
       (1, 6),
       (1, 2),
       (22, 45);

INSERT INTO sensors ("name", "model", "range_id", "type_id", "unit_id")
VALUES ('Barometer', 'ca-23', 1, 2, 3),
       ('Golometer', 'go-21', 3, 3, 1);
       
       INSERT INTO sensors ("name", "model", "range_id", "type_id", "unit_id", "location")
VALUES ('Metrometer', 'ar:002', 2, 1, 2, 'bedroom');
       
       INSERT INTO sensors ("name", "model", "range_id", "type_id", "unit_id", "location", "description")
VALUES ('Barometer', 'ca-200', 4, 3, 4, 'kitchen', 'desc_23');