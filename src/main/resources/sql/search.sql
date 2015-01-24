-- Create Geometry Test Table --
USE test;

-- Compute Distance from Santa Monica
SET @geo = GeomFromText('POINT(34.0219 -118.4814)');

SELECT npid
     , (ST_Distance(@geo, geopoint) * 54.7192) AS Miles
     , ST_DISTANCE(geopoint, POINT(34.0219, -118.4814)) * 54.7192 AS dist
FROM geom
