1.для каждого катера вывести даты выхода в море с указанием улова
   SELECT b.name AS boat_name, ft.departure_date, c.catch_count
   FROM boat b
   JOIN fishing_trip ft ON b.id = ft.boat_id
   JOIN catch c ON ft.id = c.fishing_trip_id;
2. SELECT b.name AS boat_name, COUNT(ft.id) AS trip_count
   FROM boat b
   JOIN fishing_trip ft ON b.id = ft.boat_id
   GROUP BY b.name;
3. SELECT b.name AS boat_name
   FROM boat b
   JOIN fishing_trip ft ON b.id = ft.boat_id
   JOIN catch c ON ft.id = c.fishing_trip_id
   WHERE c.catch_count > :threshold;
4.SELECT fs.name AS fish_species, ft.departure_date, ft.return_date, c.catch_count
  FROM fish_species fs
  JOIN catch c ON fs.id = c.fish_species_id
  JOIN fishing_trip ft ON c.fishing_trip_id = ft.id;