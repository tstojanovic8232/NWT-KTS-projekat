-- korisnici - admini
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime)
VALUES (1, 't.stojanovic8232@gmail.com', '183492761', TRUE, 'admin', 'Novi Sad', '063/7077915',
        'Teodora', 'Stojanovic');
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime)
VALUES (2, 'igorpavlov106@gmail.com', '123', TRUE, 'admin', 'Novi Sad', '065/1234567', 'Igor',
        'Pavlov');

-- korisnici - klijenti
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja, u_voznji, blokiran)
VALUES (3, 'teateodora2000@gmail.com', '111', TRUE, 'klijent', 'Novi Sad', '063/7077915',
        'Tea', 'Stojanovic', 1, 'teateodora2000@gmail.com', FALSE, FALSE);
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja, u_voznji, blokiran)
VALUES (4, 'pera@gmail.com', '222', TRUE, 'klijent', 'Novi Sad', '063/1234567',
        'Pera', 'Peric', 2, 'pera@gmail.com', FALSE, FALSE);

-- korisnici - vozaci
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja, status, trenutna_lokacija, u_voznji, blokiran)
VALUES (5, 'perica@gmail.com', '000', TRUE, 'vozac', 'Novi Sad', '064/1834927', 'Perica', 'Peric',
        2, 'perica@gmail.com', TRUE, '45.253893, 19.847793', FALSE, FALSE);
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja, status, trenutna_lokacija, u_voznji, blokiran)
VALUES (6, 'nikica@gmail.com', '999', TRUE, 'vozac', 'Novi Sad', '064/1834927', 'Nikica', 'Nikic',
        1, 'nikica@gmail.com', FALSE, '45.235225, 19.810289', FALSE, FALSE);

-- voznje
INSERT INTO voznja (id, vozac_id, polaziste, destinacija, broj_kilometara, napomena, datum_vreme, cena, gotova, ocena)
VALUES (1, 5, '44.7866, 20.4489', '44.8120, 20.4619', 5.2, 'Napomena 1', '2022-01-15 08:30:00',
        1200, true, 0);
INSERT INTO voznja (id, vozac_id, polaziste, destinacija, broj_kilometara, napomena, datum_vreme, cena, gotova, ocena)
VALUES (2, 5, '44.8093, 20.4682', '44.8225, 20.4546', 7.8, 'Napomena 2', '2022-01-16 12:15:00',
        1800, true, 0);

-- klijenti za voznju
INSERT INTO voznje_klijenti(voznja_id, klijent_id)
VALUES (1, 3);
INSERT INTO voznje_klijenti(voznja_id, klijent_id)
VALUES (2, 4);

-- vozila
INSERT INTO vozilo (id, registracija, cena)
VALUES (1, 'ABC123', 100.00);
INSERT INTO vozilo (id, registracija, cena)
VALUES (2, 'DEF456', 200.00);

UPDATE korisnik
SET vozilo_id = 1
WHERE id = 5;
UPDATE korisnik
SET vozilo_id = 2
WHERE id = 6;
