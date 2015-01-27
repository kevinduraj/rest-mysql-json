-- Create Geometry Test Table --
USE test;

DROP TABLE IF EXISTS geom;

CREATE TABLE geom (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  name varchar(32),
  geopoint GEOMETRY NOT NULL,
  PRIMARY KEY (npid),
  SPATIAL INDEX(geopoint)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Insert Few Records --
INSERT INTO test.geom VALUES (NULL, 'Santa Monica',     GeomFromText('POINT(34.0219 -118.4814)'));
INSERT INTO test.geom VALUES (NULL, 'Los Angeles',      GeomFromText('POINT(34.0500 -118.2500)'));
INSERT INTO test.geom VALUES (NULL, 'Westwood',         GeomFromText('POINT(34.0561 -118.4297)'));
INSERT INTO test.geom VALUES (NULL, 'Agoura Hills',     GeomFromText('POINT(34.1533 -118.7617)'));
INSERT INTO test.geom VALUES (NULL, 'El Segundo',       GeomFromText('POINT(33.9214 -118.4061)'));
INSERT INTO test.geom VALUES (NULL, 'Hollywood',        GeomFromText('POINT(34.1000 -118.3333)'));
INSERT INTO test.geom VALUES (NULL, 'Dallas',           GeomFromText('POINT(32.7758 -96.7967)'));
INSERT INTO test.geom VALUES (NULL, 'New York City',    GeomFromText('POINT(40.7127 -74.0059)'));
INSERT INTO test.geom VALUES (NULL, 'Chicago',          GeomFromText('POINT(41.8369 -87.6847)'));
INSERT INTO test.geom VALUES (NULL, 'Seattle',          GeomFromText('POINT(47.6097 -122.3331)'));
INSERT INTO test.geom VALUES (NULL, 'Las Vegas',        GeomFromText('POINT(36.1215 -115.1739)'));
INSERT INTO test.geom VALUES (NULL, 'Miami',            GeomFromText('POINT(25.7877 -80.2241)'));
INSERT INTO test.geom VALUES (NULL, 'Torrance',         GeomFromText('POINT(33.8347 -118.3414)'));
INSERT INTO test.geom VALUES (NULL, 'Pasadena',         GeomFromText('POINT(34.1561 -118.1319)'));
INSERT INTO test.geom VALUES (NULL, 'Simi Valley',      GeomFromText('POINT(34.2711 -118.7394)'));
INSERT INTO test.geom VALUES (NULL, 'San Francisco',    GeomFromText('POINT(37.7833 -122.4167)'));
INSERT INTO test.geom VALUES (NULL, 'Portland',         GeomFromText('POINT(45.5200 -122.6819)'));
INSERT INTO test.geom VALUES (NULL, 'Denver',           GeomFromText('POINT(39.7618 -104.8811)'));
INSERT INTO test.geom VALUES (NULL, 'Dallas',           GeomFromText('POINT(32.7758 -96.7967)'));

-- Compute Distance from Santa Monica
SET @geo = GeomFromText('POINT(34.0219 -118.4814)');

SELECT npid
     , provider_full_name
     , AsText(geopoint)
     , AsText(GeomFromWKB(geopoint))
     , ST_Distance(@geo, geopoint)
     , (ST_Distance(@geo, geopoint) * 54.7192) AS Miles
FROM geom
-- WHERE ST_Distance(@geo, geopoint) < 0.01
WHERE (ST_Distance(@geo, geopoint) * 54.7192) < 15
