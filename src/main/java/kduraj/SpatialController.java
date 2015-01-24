package kduraj;

import kduraj.component.IDataService;
import kduraj.mysql.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spatial")
public class SpatialController {

    @Autowired
    private IDataService dataService;

    // http://localhost:8080/rest/spatial/point?id=15
    @RequestMapping("/point")
    public ResultObject getPersonDetail(
            @RequestParam(value = "id",
                    required = false,
                    defaultValue = "0") Integer id
    ) {

        ResultObject resultObject = dataService.getSearchDetail(id);
        return resultObject;
    }

    // http://localhost:8080/rest/spatial/list?lat=34.0561&lon=-118.4297&radius=25
    @RequestMapping("/list")
    public List<ResultObject> getSearchList(
            @RequestParam(value = "lat") String lat,
            @RequestParam(value = "lon") String lon,
            @RequestParam(value = "radius") Integer radius
    ) {

        // List<ResultObject> list = dataService.getSearchList(id);
        List<ResultObject> list = dataService.getSearchRadius(lat,lon,radius);
        return list;
    }
}
