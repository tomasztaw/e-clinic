CREATE TABLE prescriptions (
  prescription_id   SERIAL PRIMARY KEY,
  patient_id        INT REFERENCES patient (id) NOT NULL,
  doctor_id         INT REFERENCES doctor (id) NOT NULL,
  prescription_date DATE NOT NULL,
  medication_name   VARCHAR(128) NOT NULL,
  dosage            VARCHAR(64),
  instructions      TEXT,
  created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
