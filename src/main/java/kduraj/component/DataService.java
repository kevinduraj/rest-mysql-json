package kduraj.component;

import kduraj.mysql.BaseMySQL;
import org.springframework.stereotype.Component;

import kduraj.mysql.ResultObject;

import java.util.List;

@Component
public class DataService implements IDataService {

    /*--------------------------------------------------------------------------------------------
     http://localhost:8080/rest/search/point?id=15
     */
    @Override
    public ResultObject getSearchDetail(Integer id) {

        BaseMySQL mysql = new BaseMySQL();
        List list  = mysql.search();
        ResultObject resultObject = (ResultObject) list.get(1);

        return resultObject;
    }

    /*--------------------------------------------------------------------------------------------
     http://localhost:8080/search/list?id=15
     */
    @Override
    public List<ResultObject> getSearchList(Integer id) {

        BaseMySQL mysql = new BaseMySQL();
        List<ResultObject> list  = mysql.search();

        return list;
    }

    @Override
    public List<ResultObject> getSearchRadius(String lat, String lon, Integer radius) {

        BaseMySQL mysql = new BaseMySQL();
        // List<ResultObject> list  = mysql.search();
        List<ResultObject> list  = mysql.search_radius(lat,lon,radius);

        return list;
    }

    @Override
    public ResultObject getPersonInfo(Integer id) {

        ResultObject resultObject = new ResultObject();
        resultObject.setNpid(id);
        resultObject.setProvider_full_name("Santa Monica, CA");
        resultObject.setDistance(2_000);

        //BaseMySQL mysql = new BaseMySQL();
        //List list  = mysql.search();
        //ResultObject resultObject = new ResultObject();
        //ResultObject ro = (ResultObject) list.get(0);

        return resultObject;
    }
}
