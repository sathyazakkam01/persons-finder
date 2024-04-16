INSERT INTO person (name) VALUES ('Person 1');
INSERT INTO person (name) VALUES ('Person 2');
INSERT INTO person (name) VALUES ('Person 3');
INSERT INTO person (name) VALUES ('Person 4');
INSERT INTO person (name) VALUES ('Person 5');
INSERT INTO person (name) VALUES ('Person 6');
INSERT INTO person (name) VALUES ('Person 7');
INSERT INTO person (name) VALUES ('Person 8');
INSERT INTO person (name) VALUES ('Person 9');
INSERT INTO person (name) VALUES ('Person 10');
INSERT INTO person (name) VALUES ('Person 11');
INSERT INTO person (name) VALUES ('Person 12');
INSERT INTO person (name) VALUES ('Person 13');
INSERT INTO person (name) VALUES ('Person 14');
INSERT INTO person (name) VALUES ('Person 15');
INSERT INTO person (name) VALUES ('Person 16');
INSERT INTO person (name) VALUES ('Person 17');
INSERT INTO person (name) VALUES ('Person 18');
INSERT INTO person (name) VALUES ('Person 19');
INSERT INTO person (name) VALUES ('Person 20');
INSERT INTO person (name) VALUES ('Person 21');
INSERT INTO person (name) VALUES ('Person 22');
INSERT INTO person (name) VALUES ('Person 23');
INSERT INTO person (name) VALUES ('Person 24');
INSERT INTO person (name) VALUES ('Person 25');
INSERT INTO person (name) VALUES ('Person 26');
INSERT INTO person (name) VALUES ('Person 27');
INSERT INTO person (name) VALUES ('Person 28');
INSERT INTO person (name) VALUES ('Person 29');
INSERT INTO person (name) VALUES ('Person 30');
INSERT INTO person (name) VALUES ('Person 31');
INSERT INTO person (name) VALUES ('Person 32');
INSERT INTO person (name) VALUES ('Person 33');
INSERT INTO person (name) VALUES ('Person 34');
INSERT INTO person (name) VALUES ('Person 35');
INSERT INTO person (name) VALUES ('Person 36');
INSERT INTO person (name) VALUES ('Person 37');
INSERT INTO person (name) VALUES ('Person 38');
INSERT INTO person (name) VALUES ('Person 39');
INSERT INTO person (name) VALUES ('Person 40');
INSERT INTO person (name) VALUES ('Person 41');
INSERT INTO person (name) VALUES ('Person 42');
INSERT INTO person (name) VALUES ('Person 43');
INSERT INTO person (name) VALUES ('Person 44');
INSERT INTO person (name) VALUES ('Person 45');
INSERT INTO person (name) VALUES ('Person 46');
INSERT INTO person (name) VALUES ('Person 47');
INSERT INTO person (name) VALUES ('Person 48');
INSERT INTO person (name) VALUES ('Person 49');
INSERT INTO person (name) VALUES ('Person 50');



INSERT INTO location (reference_id, latitude, longitude)
SELECT id,
    40.7128 + ((RANDOM() * 2 - 1) * 0.09),  -- 0.09 is roughly the latitude degree shift for 10 km at this latitude
    -74.0060 + ((RANDOM() * 2 - 1) * 0.12)  -- 0.12 is roughly the longitude degree shift for 10 km at this latitude
FROM (
    SELECT id FROM person
    ORDER BY RANDOM()
    LIMIT 50
) AS subquery
WHERE NOT EXISTS (
    SELECT 1 FROM location WHERE location.reference_id = subquery.id
);