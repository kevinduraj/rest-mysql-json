package kduraj;

import kduraj.mysql.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kduraj.component.IDataService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RestController
@RequestMapping("/search")
public class DataController {

    @Autowired
    private IDataService dataService;

    // http://localhost:8080/rest/search/point?id=15
    @RequestMapping("/point")
    public ResultObject getPersonDetail(
            @RequestParam(value = "id",
                    required = false,
                    defaultValue = "0") Integer id
    ) {

        ResultObject resultObject = dataService.getSearchDetail(id);
        return resultObject;
    }

    // http://localhost:8080/rest/search/list?id=15
    @RequestMapping("/list")
    public List<ResultObject> getSearchList(
            @RequestParam(value = "id",
                    required = false,
                    defaultValue = "0") Integer id
    ) {

        List<ResultObject> list = dataService.getSearchList(id);
        return list;
    }
    /*--------------------------------------------------------------------------------------------*/
    @RequestMapping("/info")
    public ResultObject getPersonInfo(
            @RequestParam(value = "id",
                    required = false,
                    defaultValue = "0") Integer id
    ) {

        ResultObject resultObject = dataService.getPersonInfo(id);
        return resultObject;
    }
    /*--------------------------------------------------------------------------------------------
    http://localhost:8084/data/list/15
    */
    @RequestMapping(value = "/list/{id}"
		    , method = RequestMethod.GET
		    , headers = "Accept=application/json")
    
    public ResultObject retrieveBook(@PathVariable int id)  {
	
        ResultObject resultObject = dataService.getPersonInfo(id);
        return resultObject;
	
    }
    /*--------------------------------------------------------------------------------------------
    http://localhost:8084/data/test/kevin/15
    */
    @RequestMapping(value = "/test/{title}/{id}"
		    , method = RequestMethod.GET
		    , headers = "Accept=application/json")
    
    public ResultObject getTitle(@PathVariable String title, @PathVariable int id)  {
	
        ResultObject resultObject = dataService.getPersonInfo(id);
        return resultObject;
	
    }    
    /*--------------------------------------------------------------------------------------------*/ 
}
