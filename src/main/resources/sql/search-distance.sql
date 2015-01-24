SELECT npid
     , provider_full_name
     , AsText(geopoint) insurance_plan
     , ST_Distance(geopoint, POINT(?, ?)) * 54.7192 AS distance
FROM test.geom
WHERE ST_Distance(geopoint, POINT(?, ?)) * 54.7192 <= ?
ORDER BY distance;
