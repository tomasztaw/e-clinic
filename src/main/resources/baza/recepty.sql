CREATE TABLE prescriptions (
  prescription_id   SERIAL PRIMARY KEY,
  doctor_id         INT REFERENCES doctors (patient_id) NOT NULL,
  patient_id        INT REFERENCES patients (doctor_id) NOT NULL,
--  prescription_date DATE NOT NULL, !!!wykreślone
  medication_name   VARCHAR(128) NOT NULL,
  dosage            VARCHAR(64),
  instructions      TEXT,
  created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
