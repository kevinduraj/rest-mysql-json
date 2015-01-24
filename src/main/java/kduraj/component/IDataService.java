package kduraj.component;

import kduraj.mysql.ResultObject;

import java.util.List;

public interface IDataService {

    public ResultObject getSearchDetail(Integer id);
    public List<ResultObject> getSearchList(Integer id);
    public List<ResultObject> getSearchRadius(String lat, String lon, Integer radius);
    public ResultObject getPersonInfo(Integer id);
}
