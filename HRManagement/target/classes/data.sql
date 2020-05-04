insert into FUNCTIE(ID, NUME) values ('105', 'Director general');
insert into FUNCTIE(ID, NUME) values ('106', 'Recruiter');
insert into FUNCTIE(ID, NUME) values ('107', 'Business manager');
insert into FUNCTIE(ID, NUME) values ('108', 'Project manager');
insert into FUNCTIE(ID, NUME) values ('109', 'Dezvoltator');

insert into ANGAJAT(ID, COD, NUME, PRENUME, TELEFON, EMAIL, FUNCTIE_ID) values ('1', 'DST', 'Stefan', 'Dumitru', '0735532645', 'stefan.dumitru@yahoo.com', '106');
insert into ANGAJAT(ID, COD, NUME, PRENUME, TELEFON, EMAIL, FUNCTIE_ID) values ('2', 'PMI', 'Mihai', 'Popescu', '0733552465', 'mihai.popescu@gmail.com', '108');

insert into COMPETENTA(ID, NUME, DESCRIERE) values ('1', 'Java SE', 'Java SE');
insert into COMPETENTA(ID, NUME, DESCRIERE) values ('2', '.NET', '.NET');

insert into CANDIDAT(ID, NUME, PRENUME, TELEFON, EMAIL, RECRUITER_ID) values ('1', 'Ioana', 'Popa', '0726316473', 'popa.ioana@yahoo.com', '1');
insert into CANDIDAT(ID, NUME, PRENUME, TELEFON, EMAIL, RECRUITER_ID) values ('2', 'Cristian', 'Popescu', '', 'popco@yahoo.com', '1');
insert into CANDIDAT(ID, NUME, PRENUME, TELEFON, EMAIL, RECRUITER_ID) values ('3', 'Mihai', 'Stroe', '', 'mihaistroe@yahoo.com', '1');
insert into CANDIDAT(ID, NUME, PRENUME, TELEFON, EMAIL, RECRUITER_ID) values ('4', 'Gigel', 'Constantin', '0726544733', 'consta@yahoo.com', '1');


insert into CANDIDAT_COMPETENTA(CANDIDAT_ID, COMPETENTA_ID) values ('1', '2');

insert into EVALUARE(ID, DESCRIERE, CANDIDAT_ID, RESPONSABIL_ID) values ('1', 'Cunostinte de middle level', '1', '2');

insert into ROLE(id, name) values ('1', 'ROLE_ADMIN');
insert into ROLE(id, name) values ('2', 'ROLE_CANDIDAT');
insert into ROLE(id, name) values ('3', 'ROLE_HR');

insert into UTILIZATOR(id, username, parola, angajat_id) values ('999', 'admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', '1');
insert into UTILIZATOR(id, username, parola, angajat_id) values ('1000', 'candidat', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', '1');

insert into UTILIZATORI_ROLES(utilizator_id, role_id) values ('999', '1');
insert into UTILIZATORI_ROLES(utilizator_id, role_id) values ('999', '2');
insert into UTILIZATORI_ROLES(utilizator_id, role_id) values ('999', '3');
insert into UTILIZATORI_ROLES(utilizator_id, role_id) values ('1000', '2');