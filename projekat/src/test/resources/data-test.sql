-- korisnici - admini
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime)
VALUES (nextval('korisnik_seq'), 't.stojanovic8232@gmail.com', '183492761', TRUE, 'admin', 'Novi Sad', '063/7077915',
        'Teodora', 'Stojanovic');
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime)
VALUES (nextval('korisnik_seq'), 'igorpavlov106@gmail.com', '123', TRUE, 'admin', 'Novi Sad', '065/1234567', 'Igor',
        'Pavlov');

-- korisnici - klijenti
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja, u_voznji, blokiran)
VALUES (nextval('korisnik_seq'), 'teateodora2000@gmail.com', '111', TRUE, 'klijent', 'Novi Sad', '063/7077915',
        'Tea', 'Stojanovic', 1, 'teateodora2000@gmail.com', FALSE, FALSE);
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja, u_voznji, blokiran)
VALUES (nextval('korisnik_seq'), 'pera@gmail.com', '222', TRUE, 'klijent', 'Novi Sad', '063/1234567',
        'Pera', 'Peric', 2, 'pera@gmail.com', FALSE, FALSE);

-- korisnici - vozaci
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja, status, trenutna_lokacija, u_voznji, blokiran)
VALUES (nextval('korisnik_seq'), 'perica@gmail.com', '000', TRUE, 'vozac', 'Novi Sad', '064/1834927', 'Perica', 'Peric',
        2, 'perica@gmail.com', TRUE, '45.253893, 19.847793', FALSE, FALSE);
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja,
                      podaci_placanja, status, trenutna_lokacija, u_voznji, blokiran)
VALUES (nextval('korisnik_seq'), 'nikica@gmail.com', '999', TRUE, 'vozac', 'Novi Sad', '064/1834927', 'Nikica', 'Nikic',
        1, 'nikica@gmail.com', FALSE, '45.235225, 19.810289', FALSE, FALSE);

-- vozila
INSERT INTO vozilo (id, registracija, cena)
VALUES (nextval('vozilo_seq'), 'ABC123', 100.00);
INSERT INTO vozilo (id, registracija, cena)
VALUES (nextval('vozilo_seq'), 'DEF456', 200.00);

UPDATE korisnik
SET vozilo_id = 1
WHERE id = 5;
UPDATE korisnik
SET vozilo_id = 2
WHERE id = 6;