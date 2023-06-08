INSERT INTO doctors (doctor_id, name, surname, title, email, phone)
VALUES (1, 'Alojzy', 'Kowalski', 'Laryngolog', 'alojzkowalski@eclinic.pl', '+48 120 121 122'),
(2, 'Anna', 'Nowak', 'Kardiolog', 'annanowak@eclinic.pl', '+48 120 130 140'),
(3, 'Kornel', 'Makuszyński', 'Lekarz rodzinny', 'kornel@eclinic.pl', '+48 120 130 142'),
(4, 'Jadwiga', 'Kuszyńska', 'Pediatra', 'jkuszynska@eclinic.pl', '+48 120 130 148'),
(5, 'Wacław', 'Piątkowski', 'Gastrolog', 'wacek@eclinic.pl', '+48 120 130 150'),
(6, 'Aleksander', 'Newski', 'Gastrolog', 'oleknew@eclinic.pl', '+48 120 130 152'),
(7, 'Urszula', 'Nowakowska', 'Lekarz rodzinny', 'ulala@eclinic.pl', '+48 120 130 158');

INSERT INTO patients (patient_id, name, surname, pesel, email, phone)
VALUES (1, 'Agata', 'Andrzejewska', '70011012345', 'aa@gmail.com', '+48 220 221 222'),
(2, 'Wojciech', 'Suchodolski', '72072514725', 'suchy@gmail.com', '+48 258 369 147'),
(3, 'Stefan', 'Zajavka', '72072514725', 'zajavka@zajavka.com', '+48 548 664 441'),
(4, 'Agnieszka', 'Spring', '8506171834', 'aga@zajavka.com', '+48 548 115 312'),
(5, 'Tomasz', 'Hibernate', '8506171837', 'hibertomasz@zajavka.com', '+48 548 656 565'),
(6, 'Bogumił', 'Kononowicz', '59031118222', 'bogutek@gmail.com', '+48 258 369 999');

INSERT INTO visits (visit_id, doctor_id, patient_id, start_time, end_time, note, status)
VALUES (1, 1, 1, '2023-06-01 08:30:00', '2023-06-01 08:45:00', 'Pacjent bardzo chory', 'odbyta'),
(2, 2, 2, '2023-06-01 08:35:00', '2023-06-01 08:50:00', 'Chore zatoki, zwolnienie L4', 'odbyta'),
(3, 3, 3, '2023-06-01 10:00:00', '2023-06-01 10:15:00', 'Przedawkowanie opiatów', 'odbyta'),
(4, 4, 4, '2023-06-01 12:30:00', '2023-06-01 12:50:00', 'Pacjent symuluje chorobę', 'odbyta'),
(5, 5, 5, '2023-06-02 08:30:00', '2023-06-02 08:40:00', 'Chore nerki, zwolnienie L4', 'odbyta'),
(6, 6, 6, '2023-06-02 09:30:00', '2023-06-01 09:45:00', 'Pacjent bardzo chory, skierowanie do szpitala', 'odbyta'),
(7, 7, 5, '2023-06-05 08:30:00', '2023-06-05 08:50:00', 'Stany lękowe, wymaga leczenia farmakologicznego', 'odbyta'),
(8, 1, 2, '2023-06-05 11:30:00', '2023-06-05 11:50:00', 'Pacjent zdrowy, będzie żył', 'odbyta'),
(9, 2, 4, '2023-06-05 13:00:00', '2023-06-05 13:20:00', 'Chora wątroba, zwolnienie L4', 'odbyta'),
(10, 4, 5, '2023-06-06 12:30:00', '2023-06-06 12:45:00', 'Grzybica stóp', 'odbyta');

INSERT INTO opinions (opinion_id, doctor_id, patient_id, comment, created_at)
VALUES (1, 1, 1, 'Bardzo dobry lekarz, polecam', '2023-06-01 12:45:00'),
(2, 1, 2, 'Wszystko super, polecam', '2023-06-01 13:45:00'),
(3, 6, 6, 'Słabo, lekarz chyba pijany', '2023-06-02 08:45:00'),
(4, 3, 5, 'Bardzo dobry lekarz, elegancko', '2023-06-02 12:45:00'),
(5, 1, 5, 'Chłop wystawia lewe L4', '2023-06-05 12:45:00'),
(6, 2, 1, 'Wszystko OK', '2023-06-05 13:45:00'),
(7, 4, 3, 'Olewatorskie podejście', '2023-06-05 12:45:00');

INSERT INTO prescriptions (prescription_id, doctor_id, patient_id, medication_name, dosage, instructions, created_at)
VALUES (1, 1, 1, 'dawka 3 x 5', 'medicKA', 'rano i wieczorem', '2023-06-01 08:45:00'),
(2, 2, 2, 'dawka 3 x 5', 'rano i wieczorem', 'medicKA2W', '2023-06-01 08:50:00'),
(3, 3, 3, 'dawka 3 x 5', 'rano i wieczorem', 'medicAAA', '2023-06-01 10:15:00'),
(4, 4, 4, 'dawka 3 x 5', 'rano i wieczorem', 'medicKAPP', '2023-06-01 12:50:00'),
(5, 5, 5, 'dawka 3 x 5', 'rano i wieczorem', 'medicKAMM', '2023-06-02 08:40:00'),
(6, 6, 6, 'dawka 3 x 5', 'rano i wieczorem', 'medicKAD', '2023-06-01 09:45:00'),
(7, 7, 5, 'dawka 3 x 5', 'rano i wieczorem', 'medicKA55', '2023-06-05 08:50:00'),
(8, 1, 2, 'dawka 3 x 5', 'rano i wieczorem', 'medicKA77', '2023-06-05 11:50:00'),
(9, 2, 4, 'dawka 3 x 5', 'rano i wieczorem', 'medicKWE', '2023-06-05 13:20:00'),
(10, 4, 5, 'dawka 3 x 5', 'rano i wieczorem', 'medicOOP', '2023-06-06 12:45:00');

INSERT INTO doctors_schedule (id, doctor_id, day_of_week, start_time_ds, end_time_ds)
VALUES (1, 1, 1, '08:00', '14:00'),
(2, 1, 2, '09:00', '12:00'),
(3, 1, 3, '08:00', '14:00'),
(4, 1, 4, '09:00', '12:00'),
(5, 1, 5, '08:00', '14:00');

INSERT INTO doctors_schedule (id, doctor_id, day_of_week, start_time_ds, end_time_ds)
VALUES (6, 2, 1, '08:00', '14:00'),
(7, 2, 2, '08:00', '14:00'),
(8, 2, 3, '08:00', '14:00'),
(9, 2, 4, '08:00', '14:00'),
(10, 2, 5, '08:00', '14:00');

INSERT INTO doctors_schedule (id, doctor_id, day_of_week, start_time_ds, end_time_ds)
VALUES (11, 3, 1, '08:00', '14:00'),
(12, 3, 2, '08:00', '14:00'),
(13, 3, 3, '08:00', '14:00'),
(14, 3, 4, '08:00', '14:00'),
(15, 3, 5, '08:00', '14:00');

INSERT INTO doctors_schedule (id, doctor_id, day_of_week, start_time_ds, end_time_ds)
VALUES (16, 4, 1, '08:00', '14:00'),
(17, 4, 3, '08:00', '14:00'),
(18, 4, 5, '08:00', '14:00');

INSERT INTO doctors_schedule (id, doctor_id, day_of_week, start_time_ds, end_time_ds)
VALUES (19, 5, 2, '12:00', '17:00'),
(20, 5, 4, '12:00', '17:00');

INSERT INTO doctors_schedule (id, doctor_id, day_of_week, start_time_ds, end_time_ds)
VALUES (21, 6, 4, '09:00', '15:00'),
(22, 6, 5, '08:00', '15:00');

INSERT INTO doctors_schedule (id, doctor_id, day_of_week, start_time_ds, end_time_ds)
VALUES (23, 7, 1, '10:00', '17:00'),
(24, 7, 3, '10:00', '17:00'),
(25, 7, 5, '10:00', '17:00');
