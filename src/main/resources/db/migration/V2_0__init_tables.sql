INSERT INTO doctors (name, surname, title, email, phone)
VALUES ('Alojzy', 'Kowalski', 'Laryngolog', 'alojzkowalski@eclinic.pl', '+48 120 121 122'),
('Anna', 'Nowak', 'Kardiolog', 'annanowak@eclinic.pl', '+48 120 130 140'),
('Kornel', 'Makuszyński', 'Lekarz rodzinny', 'kornel@eclinic.pl', '+48 120 130 142'),
('Jadwiga', 'Kuszyńska', 'Pediatra', 'jkuszynska@eclinic.pl', '+48 120 130 148'),
('Wacław', 'Piątkowski', 'Gastrolog', 'wacek@eclinic.pl', '+48 120 130 150'),
('Aleksander', 'Newski', 'Gastrolog', 'oleknew@eclinic.pl', '+48 120 130 152'),
('Urszula', 'Nowakowska', 'Lekarz rodzinny', 'ulala@eclinic.pl', '+48 120 130 158');

INSERT INTO patients (name, surname, pesel, email, phone)
VALUES ('Agata', 'Andrzejewska', '70011012345', 'aa@gmail.com', '+48 220 221 222'),
('Wojciech', 'Suchodolski', '72072514725', 'suchy@gmail.com', '+48 258 369 147'),
('Stefan', 'Zajavka', '72072514725', 'zajavka@zajavka.com', '+48 548 664 441'),
('Agnieszka', 'Spring', '8506171834', 'aga@zajavka.com', '+48 548 115 312'),
('Tomasz', 'Hibernate', '8506171837', 'hibertomasz@zajavka.com', '+48 548 656 565'),
('Bogumił', 'Kononowicz', '59031118222', 'bogutek@gmail.com', '+48 258 369 999');

INSERT INTO visits (doctor_id, patient_id, start_time, end_time, note, status)
VALUES (1, 1, '2023-06-01 08:30:00', '2023-06-01 08:40:00', 'Pacjent bardzo chory', 'odbyta'),
(2, 2, '2023-06-01 08:30:00', '2023-06-01 08:40:00', 'Chore zatoki, zwolnienie L4', 'odbyta'),
(3, 3, '2023-06-01 10:00:00', '2023-06-01 10:10:00', 'Przedawkowanie opiatów', 'odbyta'),
(4, 4, '2023-06-01 12:30:00', '2023-06-01 12:40:00', 'Pacjent symuluje chorobę', 'odbyta'),
(5, 5, '2023-06-02 08:30:00', '2023-06-02 08:40:00', 'Chore nerki, zwolnienie L4', 'odbyta'),
(6, 6, '2023-06-02 09:30:00', '2023-06-02 09:40:00', 'Pacjent bardzo chory, skierowanie do szpitala', 'odbyta'),
(7, 5, '2023-06-05 08:30:00', '2023-06-05 08:40:00', 'Stany lękowe, wymaga leczenia farmakologicznego', 'odbyta'),
(1, 2, '2023-06-05 11:30:00', '2023-06-05 11:40:00', 'Pacjent zdrowy, będzie żył', 'odbyta'),
(2, 4, '2023-06-05 13:00:00', '2023-06-05 13:10:00', 'Chora wątroba, zwolnienie L4', 'odbyta'),
(4, 5, '2023-06-06 12:30:00', '2023-06-06 12:40:00', 'Grzybica stóp', 'odbyta'),
(2, 5, '2023-06-08 08:00:00', '2023-06-08 08:10:00', 'Zapalenie wyrostka', 'odbyta');


INSERT INTO opinions (doctor_id, patient_id, visit_id, comment, created_at)
VALUES (1, 1, 1,'Bardzo dobry lekarz, polecam', '2023-06-01 12:45:00'),
(1, 2, 8, 'Wszystko super, polecam', '2023-06-05 13:45:00'),
(6, 6, 6, 'Słabo, lekarz chyba pijany', '2023-06-02 16:45:00'),
(5, 5, 5, 'Bardzo dobry lekarz, elegancko', '2023-06-02 12:45:00'),
(7, 5, 7, 'Chłop wystawia lewe L4', '2023-06-05 12:45:00'),
(2, 4, 9, 'Wszystko OK', '2023-06-05 13:45:00'),
(4, 5, 10, 'Olewatorskie podejście', '2023-06-06 18:45:00');

INSERT INTO prescriptions (doctor_id, patient_id, medication_name, dosage, instructions, created_at)
VALUES (1, 1, 'medicKA', 'dawka 3 x 3', 'rano i wieczorem', '2023-06-01 08:40:00'),
(2, 2, 'medicKA2W', 'dawka 2 x 5', 'rano i wieczorem', '2023-06-01 08:40:00'),
(3, 3, 'medicAAA', 'dawka 3 x 1', 'rano i wieczorem', '2023-06-01 10:10:00'),
(4, 4, 'medicKAPP', 'dawka 3 x 2', 'wieczorem', '2023-06-01 12:40:00'),
(5, 5, 'medicKAMM', 'dawka 2 x 2', 'rano', '2023-06-02 08:40:00'),
(6, 6, 'medicKAD', 'dawka 1 x 2', 'rano i wieczorem', '2023-06-01 09:40:00'),
(7, 5, 'medicKA55', 'dawka 3 x 4', 'przed posiłkiem', '2023-06-05 08:40:00'),
(1, 2, 'medicKA77', 'dawka 3 x 2', 'rano i po obiedzie', '2023-06-05 11:40:00'),
(2, 4, 'medicKWE', 'dawka 4 x 5', 'rano i wieczorem', '2023-06-05 13:10:00'),
(4, 5, 'medicOOP', 'dawka 3 x 1', 'rano, popołudniu i wieczorem', '2023-06-06 12:40:00'),
(2, 5, 'Maść na bul dupy', 'dawka 3 x 1', 'przed snem', '2023-06-08 08:10:00');

INSERT INTO doctors_schedule (doctor_id, day_of_week, start_time_ds, end_time_ds)
VALUES (1, 1, '08:00', '14:00'),
(1, 2, '09:00', '12:00'),
(1, 3, '08:00', '14:00'),
(1, 4, '09:00', '12:00'),
(1, 5, '08:00', '14:00');

INSERT INTO doctors_schedule (doctor_id, day_of_week, start_time_ds, end_time_ds)
VALUES (2, 1, '08:00', '14:00'),
(2, 2, '08:00', '14:00'),
(2, 3, '08:00', '14:00'),
(2, 4, '08:00', '14:00'),
(2, 5, '08:00', '14:00');

INSERT INTO doctors_schedule (doctor_id, day_of_week, start_time_ds, end_time_ds)
VALUES (3, 1, '08:00', '14:00'),
(3, 2, '08:00', '14:00'),
(3, 3, '08:00', '14:00'),
(3, 4, '08:00', '14:00'),
(3, 5, '08:00', '14:00');

INSERT INTO doctors_schedule (doctor_id, day_of_week, start_time_ds, end_time_ds)
VALUES (4, 1, '08:00', '14:00'),
(4, 3, '08:00', '14:00'),
(4, 5, '08:00', '14:00');

INSERT INTO doctors_schedule (doctor_id, day_of_week, start_time_ds, end_time_ds)
VALUES (5, 2, '12:00', '17:00'),
(5, 4, '12:00', '17:00');

INSERT INTO doctors_schedule (doctor_id, day_of_week, start_time_ds, end_time_ds)
VALUES (6, 4, '09:00', '15:00'),
(6, 5, '08:00', '15:00');

INSERT INTO doctors_schedule (doctor_id, day_of_week, start_time_ds, end_time_ds)
VALUES (7, 1, '10:00', '17:00'),
(7, 3, '10:00', '17:00'),
(7, 5, '10:00', '17:00');
