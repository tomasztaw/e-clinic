CREATE TABLE medical_history (
  history_id SERIAL PRIMARY KEY,
  patient_id INT REFERENCES patient (id) NOT NULL,
  doctor_id  INT REFERENCES doctor (id) NOT NULL,
  visit_date DATE NOT NULL,
  notes TEXT,
  prescription_id INT REFERENCES prescriptions (prescription_id),
);
