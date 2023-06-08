CREATE TABLE godziny_przyjec (
    id SERIAL PRIMARY KEY,
    lekarz_id INT,
    dzien DATE,
    godzina_rozpoczecia TIME,
    godzina_zakonczenia TIME,
    zajete BOOLEAN DEFAULT false
);

CREATE TABLE reservations (
    id             SERIAL  PRIMARY KEY,
    doctor_id      INT     REFERENCES doctors (doctor_id) NOT NULL,
    day            DATE    NOT NULL,
    start_time_wh  TIME    NOT NULL,
    end_time_wh    TIME    NOT NULL,
    occupied       BOOLEAN DEFAULT false
);

--zapytanie wyszukujące pierwszy możliwy termin termin
SELECT *
FROM godziny_przyjec
WHERE lekarz_id = {ID_LEKARZA}
  AND dzien >= CURRENT_DATE
  AND godzina_zakonczenia - godzina_rozpoczecia >= {CZAS_WIZYTY}
  AND zajete = false
ORDER BY dzien, godzina_rozpoczecia
LIMIT 1;


CREATE TABLE grafik_lekarzy (
    id SERIAL PRIMARY KEY,
    lekarz_id INT,
    dzien_tygodnia INT,
    godzina_rozpoczecia TIME,
    godzina_zakonczenia TIME
);

CREATE TABLE doctors_schedule (
    id            SERIAL PRIMARY KEY,
    doctor_id     INT    REFERENCES doctors (doctor_id) NOT NULL,
    day_of_week   INT    NOT NULL,
    start_time_ds TIME   NOT NULL,
    end_time_ds   TIME   NOT NULL
);


--wyszukiwanie uwzględniające dzień dzień tygodnia
SELECT *
FROM godziny_przyjec
WHERE lekarz_id = {ID_LEKARZA}
  AND dzien >= CURRENT_DATE
  AND godzina_zakonczenia - godzina_rozpoczecia >= {CZAS_WIZYTY}
  AND zajete = false
  AND EXTRACT(ISODOW FROM dzien) = {NUMER_DNIA_TYGODNIA}
  AND EXISTS (
    SELECT 1
    FROM grafik_lekarzy
    WHERE lekarz_id = {ID_LEKARZA}
      AND dzien_tygodnia = {DZIEN_TYGODNIA}
      AND godzina_rozpoczecia <= godzina_rozpoczecia
      AND godzina_zakonczenia >= godzina_zakonczenia
  )
ORDER BY dzien, godzina_rozpoczecia;


