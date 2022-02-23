ALTER TABLE projects ADD COLUMN date_creation DATE;

UPDATE projects SET date_creation = '2018.01.13' WHERE id = 22;
UPDATE projects SET date_creation = '2017.03.15' WHERE id = 23;
UPDATE projects SET date_creation = '2015.05.21' WHERE id = 24;