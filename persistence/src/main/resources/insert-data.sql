insert into smt.roles values(1, '057d0e98-0f0f-406e-9451-c5fd38577920', 'ADMIN');

insert into smt.users values(1, '057d0e98-0f0f-406e-9451-c5fd38577921', 'admin', 'admin', '', 1);

insert into smt.movies values(1, '057d0e98-0f0f-406e-9451-c5fd38577922', 'The Shawshank Redemption', '2:23:00', 'Cool film', 9.3, 'Drama', '1994-10-14');
insert into smt.movies values(2, '057d0e98-0f0f-406e-9451-c5fd38577923', 'The Godfather', '2:56:00', 'Very Cool film', 9.2, 'Crime', '1972-03-24');
insert into smt.movies values(3, '057d0e98-0f0f-406e-9451-c5fd38577924', 'The Godfather: Part II', '3:23:00', 'Very Very Cool film', 9.2, 'Crime', '1974-12-20');

-- theater addresses

insert into smt.theater_address values(1,'ext-id-1', '', '', '', 1, '');
insert into smt.theater_address values(2,'ext-id-2', '', '', '', 1, '');
insert into smt.theater_address values(3,'ext-id-3', '', '', '', 1, '');

-- theaters
insert into smt.theaters values(1,'ext-id-1', 'hall-name-0', 1);
insert into smt.theaters values(2,'ext-id-2', 'hall-name-1', 2);
insert into smt.theaters values(3,'ext-id-3', 'hall-name-2', 3);

-- screenings insert without subselects
--insert into smt.screenings values(1, '2020-01-22 12:12:12', '2020-01-22 14:35:12', (select id from  smt.movies where id=1), (select id from smt.theaters where id=1));
--insert into smt.screenings values(2, '2020-01-22 14:35:13', '2020-01-22 17:31:12', (select id from  smt.movies where id=2), (select id from smt.theaters where id=1));
--insert into smt.screenings values(3, '2020-01-22 17:31:13', '2020-01-22 21:21:12', (select id from  smt.movies where id=3), (select id from smt.theaters where id=1));
insert into smt.screenings values(1,'', '2020-01-22 12:12:12', '2020-01-22 14:35:12', 1, 1);
insert into smt.screenings values(2,'', '2020-01-22 14:35:13', '2020-01-22 17:31:12', 2, 1);
insert into smt.screenings values(3,'', '2020-01-22 17:31:13', '2020-01-22 21:21:12', 3, 1);

insert into smt.seats values(1,'', 1, (select id from smt.theaters where id = 1));
insert into smt.seats values(2,'', 2, (select id from smt.theaters where id = 1));
insert into smt.seats values(3,'', 3, (select id from smt.theaters where id = 1));
insert into smt.seats values(4,'', 4, (select id from smt.theaters where id = 1));
insert into smt.seats values(5,'', 5, (select id from smt.theaters where id = 1));
insert into smt.seats values(6,'', 6, (select id from smt.theaters where id = 2));
insert into smt.seats values(7,'', 7, (select id from smt.theaters where id = 2));
insert into smt.seats values(8,'', 8, (select id from smt.theaters where id = 2));
insert into smt.seats values(9,'', 9, (select id from smt.theaters where id = 2));
insert into smt.seats values(10,'', 10, (select id from smt.theaters where id = 2));
insert into smt.seats  values(11,'', 11, (select id from smt.theaters where id = 3));
insert into smt.seats  values(12,'', 12, (select id from smt.theaters where id = 3));
insert into smt.seats  values(13,'', 13, (select id from smt.theaters where id = 3));
insert into smt.seats  values(14,'', 14, (select id from smt.theaters where id = 3));
insert into smt.seats  values(15,'', 15, (select id from smt.theaters where id = 3));