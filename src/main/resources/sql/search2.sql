SELECT npid
     , provider_full_name
     , AsText(geopoint) insurance_plan
     , (ST_Distance(GeomFromText('POINT('+?+' '+?+')'), geopoint) * 54.7192) distance
FROM test.geom
WHERE (ST_Distance(GeomFromText('POINT('+?+' '+?+')'), geopoint) * 54.7192) < ? ;
