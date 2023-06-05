CREATE TABLE visits (
  visit_id   SERIAL PRIMARY KEY,
  doctor_id  INT REFERENCES doctor (id) NOT NULL,
  patient_id INT REFERENCES patient (id) NOT NULL,
  start_time TIMESTAMP NOT NULL,
  end_time   TIMESTAMP NOT NULL,
  note       TEXT      NOT NULL,
  status     VARCHAR(64) NOT NULL
);
