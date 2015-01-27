-- Create Geometry Test Table --
USE test;

DROP TABLE IF EXISTS geom2;

CREATE TABLE geom2 (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  name varchar(32),
  latitude DOUBLE,
  longitude DOUBLE,
  geopoint GEOMETRY NOT NULL,
  PRIMARY KEY (npid),
  SPATIAL INDEX(geopoint)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Insert Few Records --
INSERT INTO test.geom2 VALUES (NULL, 'Santa Monica',     34.0219, -118.4814, POINT(34.0219, -118.4814));
INSERT INTO test.geom2 VALUES (NULL, 'Los Angeles',      34.0500, -118.2500, POINT(34.0500, -118.2500));
INSERT INTO test.geom2 VALUES (NULL, 'Westwood',         34.0561, -118.4297, POINT(34.0561, -118.4297));
INSERT INTO test.geom2 VALUES (NULL, 'Agoura Hills',     34.1533, -118.7617, POINT(34.1533, -118.7617));
INSERT INTO test.geom2 VALUES (NULL, 'El Segundo',       33.9214, -118.4061, POINT(33.9214, -118.4061));
INSERT INTO test.geom2 VALUES (NULL, 'Hollywood',        34.1000, -118.3333, POINT(34.1000, -118.3333));
INSERT INTO test.geom2 VALUES (NULL, 'Dallas',           32.7758, -96.7967,  POINT(32.7758, -96.7967));
INSERT INTO test.geom2 VALUES (NULL, 'New York City',    40.7127, -74.0059,  POINT(40.7127, -74.0059));
INSERT INTO test.geom2 VALUES (NULL, 'Chicago',          41.8369, -87.6847,  POINT(41.8369, -87.6847));
INSERT INTO test.geom2 VALUES (NULL, 'Seattle',          47.6097, -122.3331, POINT(47.6097, -122.3331));
INSERT INTO test.geom2 VALUES (NULL, 'Las Vegas',        36.1215, -115.1739, POINT(36.1215, -115.1739));
INSERT INTO test.geom2 VALUES (NULL, 'Miami',            25.7877, -80.2241,  POINT(25.7877, -80.2241));
INSERT INTO test.geom2 VALUES (NULL, 'Torrance',         33.8347, -118.3414, POINT(33.8347, -118.3414));
INSERT INTO test.geom2 VALUES (NULL, 'Pasadena',         34.1561, -118.1319, POINT(34.1561, -118.1319));
INSERT INTO test.geom2 VALUES (NULL, 'Simi Valley',      34.2711, -118.7394, POINT(34.2711, -118.7394));
INSERT INTO test.geom2 VALUES (NULL, 'San Francisco',    37.7833, -122.4167, POINT(37.7833, -122.4167));
INSERT INTO test.geom2 VALUES (NULL, 'Portland',         45.5200, -122.6819, POINT(45.5200, -122.6819));
INSERT INTO test.geom2 VALUES (NULL, 'Denver',           39.7618, -104.8811, POINT(39.7618, -104.8811));
INSERT INTO test.geom2 VALUES (NULL, 'Dallas',           32.7758, -96.7967,  POINT(32.7758, -96.7967));

-- Compute Distance from Santa Monica
SET @geo = POINT(34.0219, -118.4814);

SELECT npid
     , provider_full_name
     , AsText(geopoint)
     , AsText(GeomFromWKB(geopoint))
     , ST_Distance(@geo, geopoint)
     , (ST_Distance(@geo, geopoint) * 54.7192) AS Miles
FROM geom2
-- WHERE ST_Distance(@geo, geopoint) < 0.01
WHERE (ST_Distance(@geo, geopoint) * 54.7192) < 15
