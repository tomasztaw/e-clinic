CREATE TABLE doctors
(
    doctor_id SERIAL          PRIMARY KEY,
    name      VARCHAR(20)     NOT NULL,
    surname   VARCHAR(20)     NOT NULL,
    title     VARCHAR(20)     NOT NULL,
    phone     VARCHAR(32)     NOT NULL,
    email     VARCHAR(32)     NOT NULL
);

CREATE TABLE patients
(
    patient_id SERIAL          PRIMARY KEY,
    name       VARCHAR(20)     NOT NULL,
    surname    VARCHAR(20)     NOT NULL,
    pesel      VARCHAR(20)     NOT NULL,
    phone      VARCHAR(32)     NOT NULL,
    email      VARCHAR(32)     NOT NULL
);

CREATE TABLE visits (
  visit_id   SERIAL PRIMARY KEY,
  doctor_id  INT REFERENCES doctors (doctor_id) NOT NULL,
  patient_id INT REFERENCES patients (patient_id) NOT NULL,
  start_time TIMESTAMP NOT NULL,
  end_time   TIMESTAMP NOT NULL,
  note       TEXT      NOT NULL,
  status     VARCHAR(64) NOT NULL
);

CREATE TABLE opinions (
    opinion_id SERIAL PRIMARY KEY,
    doctor_id  INT REFERENCES doctors (doctor_id)   NOT NULL,
    patient_id INT REFERENCES patients (patient_id) NOT NULL,
    comment    TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE prescriptions (
  prescription_id   SERIAL PRIMARY KEY,
  doctor_id         INT REFERENCES doctors (doctor_id) NOT NULL,
  patient_id        INT REFERENCES patients (patient_id) NOT NULL,
  medication_name   VARCHAR(128) NOT NULL,
  dosage            VARCHAR(64),
  instructions      TEXT,
  created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

