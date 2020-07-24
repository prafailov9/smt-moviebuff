insert into public.roles values(1,'057d0e98-0f0f-406e-9451-c5fd38577920', 'USER');
insert into public.roles values(2,'057d0e98-0f0f-406e-9451-c5fd38577920', 'DBA');
insert into public.roles values(3, '057d0e98-0f0f-406e-9451-c5fd38577920','ADMIN');

insert into public.movies values(1, '057d0e98-0f0f-406e-9451-c5fd38577920', 'The Shawshank Redemption', '2:23:00', 'Cool film', 1.2, 'Drama', '1994-10-14');
insert into public.movies values(2, '057d0e98-0f0f-406e-9451-c5fd38577920', 'The Godfather', '2:56:00', 'Very Cool film', 9.2, 'Crime', '1972-03-24');
insert into public.movies values(3, '057d0e98-0f0f-406e-9451-c5fd38577920', 'The Godfather: Part II', '3:23:00', 'Very Very Cool film', 9.2, 'Crime', '1974-12-20');


-- theater addresses
insert into public.theater_address values(1, '057d0e98-0f0f-406e-9451-c5fd38577920', '', '', '', 1, '', '');
insert into public.theater_address values(2, '057d0e98-0f0f-406e-9451-c5fd38577920', '', '', '', 2, '', '');
insert into public.theater_address values(3, '057d0e98-0f0f-406e-9451-c5fd38577920', '', '', '', 3, '', '');

-- theaters
insert into public.theaters values(1, '057d0e98-0f0f-406e-9451-c5fd38577920', 'hall-name-0', 1);
insert into public.theaters values(2, '057d0e98-0f0f-406e-9451-c5fd38577920', 'hall-name-1', 2);
insert into public.theaters values(3, '057d0e98-0f0f-406e-9451-c5fd38577920', 'hall-name-2', 3);


insert into public.screenings values(1, '057d0e98-0f0f-406e-9451-c5fd38577920', '2020-01-22 12:12:12', '2020-01-22 14:35:12', 1, 1);
insert into public.screenings values(2, '057d0e98-0f0f-406e-9451-c5fd38577920', '2020-01-22 14:35:13', '2020-01-22 17:31:12', 2, 1);
insert into public.screenings values(3, '057d0e98-0f0f-406e-9451-c5fd38577920', '2020-01-22 17:31:13', '2020-01-22 21:21:12', 3, 1);



insert into public.seats values(1, '057d0e98-0f0f-406e-9451-c5fd38577920', 1, 1);
insert into public.seats values(2, '057d0e98-0f0f-406e-9451-c5fd38577920', 2, 1);
insert into public.seats values(3, '057d0e98-0f0f-406e-9451-c5fd38577920', 3, 1);
insert into public.seats values(4, '057d0e98-0f0f-406e-9451-c5fd38577920', 4, 1);
insert into public.seats values(5, '057d0e98-0f0f-406e-9451-c5fd38577920', 5, 1);
insert into public.seats values(6, '057d0e98-0f0f-406e-9451-c5fd38577920', 6, 2);
insert into public.seats values(7, '057d0e98-0f0f-406e-9451-c5fd38577920', 7, 2);
insert into public.seats values(8, '057d0e98-0f0f-406e-9451-c5fd38577920', 8, 2);
insert into public.seats values(9, '057d0e98-0f0f-406e-9451-c5fd38577920', 9, 2);
insert into public.seats values(10, '057d0e98-0f0f-406e-9451-c5fd38577920', 10, 2);
insert into public.seats  values(11, '057d0e98-0f0f-406e-9451-c5fd38577920', 11, 3);
insert into public.seats  values(12, '057d0e98-0f0f-406e-9451-c5fd38577920', 12, 3);
insert into public.seats  values(13, '057d0e98-0f0f-406e-9451-c5fd38577920', 13, 3);
insert into public.seats  values(14, '057d0e98-0f0f-406e-9451-c5fd38577920',14, 3);
insert into public.seats  values(15, '057d0e98-0f0f-406e-9451-c5fd38577920',15, 3);