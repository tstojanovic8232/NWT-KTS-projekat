INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika)
VALUES (nextval('hibernate_sequence'), 't.stojanovic8232@gmail.com', '123', TRUE, 'admin');
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja, podaci_placanja)
VALUES (nextval('hibernate_sequence'), 'teateodora2000@gmail.com', '111', TRUE, 'klijent', 'Novi Sad', '063/7077915','Teodora','Stojanovic',1,'teateodora2000@gmail.com');
INSERT INTO korisnik (id, email, lozinka, aktivan, tip_korisnika, grad, broj_tel, ime, prezime, nacin_placanja, podaci_placanja, status)
VALUES (nextval('hibernate_sequence'), 'tea@gmail.com', '222', TRUE, 'vozac', 'Novi Sad', '063/7077915','Tea','S',2,'teateodora000@gmail.com', FALSE);
