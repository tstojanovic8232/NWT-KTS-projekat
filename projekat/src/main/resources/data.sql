-- korisnici - admini
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime)
VALUES (nextval('korisnik_seq'), 't.stojanovic8232@gmail.com', '183492761', TRUE, 'admin', 'Novi Sad', '063/7077915',
        'Teodora',
        'Stojanovic');
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime)
VALUES (nextval('korisnik_seq'), 'igorpavlov106@gmail.com', '123', TRUE, 'admin', 'Novi Sad', '065/1234567', 'Igor',
        'Pavlov');

-- korisnici - klijenti
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja,u_voznji,blokiran)
VALUES (nextval('korisnik_seq'), 'teateodora2000@gmail.com', '111', TRUE, 'klijent', 'Novi Sad', '063/7077915',
        'Tea', 'Stojanovic', ., 'teateodora2000@gmail.com',FALSE,FALSE);
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja,u_voznji,blokiran)
VALUES (nextval('korisnik_seq'), 'pera@gmail.com', '222', TRUE, 'klijent', 'Novi Sad', '063/1234567',
        'Pera', 'Peric', 2, 'pera@gmail.com',FALSE,FALSE);
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja,u_voznji,blokiran)
VALUES (nextval('korisnik_seq'), 'mika@gmail.com', '333', TRUE, 'klijent', 'Novi Sad', '063/7654321',
        'Mika', 'Mikic', 2, 'mika@gmail.com',FALSE,FALSE);
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja,u_voznji,blokiran)
VALUES (nextval('korisnik_seq'), 'rada@gmail.com', '444', TRUE, 'klijent', 'Novi Sad', '063/9876543',
        'Rada', 'Radic', 1, 'rada@gmail.com',FALSE,FALSE);
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja,u_voznji,blokiran)
VALUES (nextval('korisnik_seq'), 'igor@gmail.com', '555', TRUE, 'klijent', 'Novi Sad', '062/1112223',
        'Igor', 'P', 1, 'igor@gmail.com',FALSE,FALSE);
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja,u_voznji,blokiran)
VALUES (nextval('korisnik_seq'), 'aldonahzero123@gmail.com', '555', TRUE, 'klijent', 'Novi Sad', '062/1112223',
        'Igor', 'P', 1, 'aldonahzero123@gmail.com',FALSE,FALSE);

-- korisnici - vozaci
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja, status, trenutna_lokacija, u_voznji,blokiran)
VALUES (nextval('korisnik_seq'), 'perica@gmail.com', '000', TRUE, 'vozac', 'Novi Sad', '064/1834927', 'Perica', 'Peric',
        2,
        'perica@gmail.com', TRUE, '45.253893, 19.847793', FALSE,FALSE);
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja, status, trenutna_lokacija, u_voznji,blokiran)
VALUES (nextval('korisnik_seq'), 'nikica@gmail.com', '999', TRUE, 'vozac', 'Novi Sad', '064/1834927', 'Nikica', 'Nikic',
        1,
        'nikica@gmail.com', FALSE, '45.235225, 19.810289', FALSE,FALSE);
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja, status, trenutna_lokacija, u_voznji,blokiran)
VALUES (nextval('korisnik_seq'), 'anica@gmail.com', '000', TRUE, 'vozac', 'Novi Sad', '064/1834927', 'Anica', 'Anic', 2,
        'anica@gmail.com', TRUE, '45.240383, 19.837863', FALSE,FALSE);

-- voznje
INSERT INTO voznja (id, vozac_id, polaziste, destinacija, broj_kilometara, napomena, datum_vreme, cena, gotova, ocena)
VALUES (nextval('voznja_seq'), 9, '44.7866, 20.4489', '44.8120, 20.4619', 5.2, 'Napomena 1', '2022-01-15 08:30:00',
        1200, true, 0);
INSERT INTO voznja (id, vozac_id, polaziste, destinacija, broj_kilometara, napomena, datum_vreme, cena, gotova, ocena)
VALUES (nextval('voznja_seq'), 9, '44.8093, 20.4682', '44.8225, 20.4546', 7.8, 'Napomena 2', '2022-01-16 12:15:00',
        1800, true, 0);
INSERT INTO voznja (id, vozac_id, polaziste, destinacija, broj_kilometara, napomena, datum_vreme, cena, gotova, ocena)
VALUES (nextval('voznja_seq'), 10, '44.8144, 20.4596', '44.7742, 20.4633', 12.3, 'Napomena 3', '2022-01-18 17:45:00',
        2500, true, 0);
INSERT INTO voznja (id, vozac_id, polaziste, destinacija, broj_kilometara, napomena, datum_vreme, cena, gotova, ocena)
VALUES (nextval('voznja_seq'), 11, '44.7875, 20.4715', '44.8085, 20.4115', 9.7, 'Napomena 4', '2022-01-19 10:00:00',
        2000, true, 0);
INSERT INTO voznja (id, vozac_id, polaziste, destinacija, broj_kilometara, napomena, datum_vreme, cena, gotova, ocena)
VALUES (nextval('voznja_seq'), 10, '44.8151, 20.4347', '44.7948, 20.4552', 6.5, 'Napomena 5', '2022-01-20 15:30:00',
        1500, true, 0);
INSERT INTO voznja (id, vozac_id, polaziste, destinacija, broj_kilometara, napomena, datum_vreme, cena, gotova, ocena)
VALUES (nextval('voznja_seq'), 10, '44.7944, 20.4599', '44.8210, 20.4434', 8.9, 'Napomena 6', '2022-01-22 09:45:00',
        1900, true, 0);
INSERT INTO voznja (id, vozac_id, polaziste, destinacija, broj_kilometara, napomena, datum_vreme, cena, gotova, ocena)
VALUES (nextval('voznja_seq'), 9, '44.8242, 20.4412', '44.7967, 20.4573', 7.1, 'Napomena 7', '2022-01-23 13:20:00',
        1700, true, 0);
INSERT INTO voznja (id, vozac_id, broj_kilometara, napomena, datum_vreme, cena, gotova, ocena, polaziste, destinacija)
VALUES (nextval('voznja_seq'), 11, 20.5, 'Nema napomene', '2023-03-05 12:00:00', 1500.00, false, 0, '44.7866, 20.4489',
        '44.8158, 20.4569');
INSERT INTO voznja (id, vozac_id, broj_kilometara, napomena, datum_vreme, cena, gotova, ocena, polaziste, destinacija)
VALUES (nextval('voznja_seq'), 9, 10.0, 'Putovanje sa kućnim ljubimcem', '2023-03-15 08:30:00', 800.00, false, 0,
        '45.2671, 19.8335', '45.2488, 19.7930');
INSERT INTO voznja (id, vozac_id, broj_kilometara, napomena, datum_vreme, cena, gotova, ocena, polaziste, destinacija)
VALUES (nextval('voznja_seq'), 11, 30.2, 'Dostava hrane', '2023-03-20 18:45:00', 2000.00, false, 0, '44.7623, 19.6927',
        '44.7844, 19.6959');

-- klijenti za voznju
INSERT INTO voznje_klijenti(voznja_id, klijent_id)
VALUES (1, 3);
INSERT INTO voznje_klijenti(voznja_id, klijent_id)
VALUES (2, 4);
INSERT INTO voznje_klijenti(voznja_id, klijent_id)
VALUES (3, 8);
INSERT INTO voznje_klijenti(voznja_id, klijent_id)
VALUES (4, 5);
INSERT INTO voznje_klijenti(voznja_id, klijent_id)
VALUES (5, 4);
INSERT INTO voznje_klijenti(voznja_id, klijent_id)
VALUES (6, 6);
INSERT INTO voznje_klijenti(voznja_id, klijent_id)
VALUES (7, 7);
INSERT INTO voznje_klijenti(voznja_id, klijent_id)
VALUES (8, 7);
INSERT INTO voznje_klijenti(voznja_id, klijent_id)
VALUES (9, 5);
INSERT INTO voznje_klijenti(voznja_id, klijent_id)
VALUES (10, 6);

-- vozila
INSERT INTO vozilo (id, registracija, cena)
VALUES (nextval('vozilo_seq'), 'ABC123', 100.00);
INSERT INTO vozilo (id, registracija, cena)
VALUES (nextval('vozilo_seq'), 'DEF456', 200.00);
INSERT INTO vozilo (id, registracija, cena)
VALUES (nextval('vozilo_seq'), 'GHI789', 300.00);

UPDATE korisnik
SET vozilo_id = 1
WHERE id = 9;
UPDATE korisnik
SET vozilo_id = 2
WHERE id = 10;
UPDATE korisnik
SET vozilo_id = 3
WHERE id = 11;