CREATE TABLE reviews (
  review_id INTEGER Primary Key,
  game_name VARCHAR(100) NOT NULL,
  console VARCHAR(10) NOT NULL,
  year_released VARCHAR(4) NOT NULL,
  reviewer VARCHAR(100) NOT NULL,
  review_score INTEGER NOT NULL
);

insert into reviews values(1, 'Ocarina of Time', 'N64', 1998, 'Justin Wheeler', 5);
insert into reviews values(2, 'Smash Brothers Melee', 'Gamecube', '2001', 'Justin Wheeler', 5);
insert into reviews values(3, 'Crusader Kings 2', 'PC', '2012', 'Justin Wheeler', 4);
insert into reviews values(4, 'NBA 2K17', 'PS4', '2016', 'Breanna Yuu', 3);
insert into reviews values(5, 'Chrono Trigger', 'SNES', '1995', 'Eric Chaplin', 4);
insert into reviews values(6, 'Total War: Shogun 2', 'PC', '2011', 'Justin Wheeler', 5);
insert into reviews values(7, 'Hearthstone', 'PC', '2014', 'Justin Wheeler', 3);
insert into reviews values(8, 'Onimusha', 'PS2', '2001', 'James Coleman', 3);
insert into reviews values(9, 'Pokemon Stadium', 'N64', '1998', 'Justin Wheeler', 3);
insert into reviews values(10, 'Call of Duty: Modern Warfare 3', 'XBOX360', '2011', 'Justin Wheeler', 2);
insert into reviews values(11, 'Hotel Mario', 'CD-i', '1994', 'Justin Wheeler', 1);
insert into reviews values(12, 'Street Fighter V', 'PS4', '2016', 'James Coleman', 2);
insert into reviews values(13, 'Tekken Tag Tournament 2', 'PS3', '2012', 'James Coleman', 3);
insert into reviews values(14, 'Bubsy 3D', 'PS1', '1996', 'Breanna Yuu', 2);
insert into reviews values(15, 'Shaq Fu', 'SNES', '1994', 'Yami Yugi', 1);
insert into reviews values(16, 'Mortal Kombat 3', 'SNES', '1995', 'Yami Yugi', 4);
