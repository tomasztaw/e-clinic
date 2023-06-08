CREATE TABLE opinions (
  opinion_id SERIAL PRIMARY KEY,
  doctor_id     INT REFERENCES doctor (doctor_id) NOT NULL,
  patient_name  VARCHAR(32) NOT NULL,
  rating        INT NOT NULL,
  comment       TEXT NOT NULL,
  created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE opinions (
    opinion_id SERIAL PRIMARY KEY,
    doctor_id  INT REFERENCES doctors (doctor_id)   NOT NULL,
    patient_id INT REFERENCES patients (patient_id) NOT NULL,
    comment    TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
