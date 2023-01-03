INSERT INTO admin (id, email, lozinka, ime, prezime, grad, broj_tel, aktivan, u_voznji, blokiran)
VALUES (nextval('hibernate_sequence'), 't.stojanovic8232@gmail.com', '123', 'Teodora', 'Stojanovic', 'Novi Sad',
        '065/1234567', TRUE, FALSE, FALSE);
