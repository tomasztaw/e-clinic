CREATE TABLE working_hours (
  working_hours_id SERIAL PRIMARY KEY,
  doctor_id     INT REFERENCES doctor (doctor_id) NOT NULL,
  day_of_week   INT NOT NULL,
  start_time    TIME NOT NULL,
  end_time      TIME NOT NULL
);
