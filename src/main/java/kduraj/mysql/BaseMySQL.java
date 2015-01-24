package kduraj.mysql;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.InputStream;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseMySQL {

    private String host;
    private String port;
    private String database;
    private String user;
    private String password;
    private String templateSQL;


    private static final String DB_PROPERTIES_FILE = "db.properties";
    private static final String SELECT_QUERY_FILE_PROPERTY = "db.query.select.file";

    public BaseMySQL() {

        Properties props;
        try {
            InputStream fileIn = getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE);
            props = new Properties();
            props.load(fileIn);
        } catch (Exception ex) {
            throw new RuntimeException("Unable to load file " + DB_PROPERTIES_FILE, ex);
        }

        host = props.getProperty("db.hostname");
        port = props.getProperty("db.port");
        database = props.getProperty("db.database");
        user = props.getProperty("db.username");
        password = props.getProperty("db.password");
        templateSQL = props.getProperty(SELECT_QUERY_FILE_PROPERTY);
    }


    /**
     * Search Geo Point Locations
     */
    public List search() {
    
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String dbURL = "jdbc:mysql://" + host + ":" + port + "/" + database;

        List array = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, user, password); 

            String SQL =
                    " SELECT fid                                                                                                                                                                           \n" +
                            "  , city\n" +
                            "  , AsText(geopoint) point\n" +
                            "  , (ST_Distance(GeomFromText('POINT(34.0219 -118.4814)'), geopoint) * 54.7192) miles \n" +
                            " FROM test.geom\n" +
                            " WHERE (ST_Distance(GeomFromText('POINT(34.0219 -118.4814)'), geopoint) * 54.7192) < 15";

            pst = conn.prepareStatement(SQL);
            rs = pst.executeQuery();


            while (rs.next()) {

                ResultObject resultObject = new ResultObject();
                resultObject.setNpid(rs.getInt(1));
                resultObject.setProvider_full_name(rs.getString(2));
                resultObject.setInsurance_plan(rs.getString(3));
                resultObject.setDistance(rs.getDouble(4));

                array.add(resultObject);

            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(BaseMySQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(BaseMySQL.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return array;
    }

    public List search_radius(String lat, String lon, Integer radius) {
        
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

        List array = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password); 
            String SQL = FileUtils.readFileToString(locateClassPathResource(templateSQL)); 

            pst = conn.prepareStatement(SQL);
            pst.setString(1, lat);
            pst.setString(2, lon);
            pst.setString(3, lat);
            pst.setString(4, lon);
            pst.setInt(5, radius);
            rs  = pst.executeQuery();

            while (rs.next()) {
                ResultObject resultObject = new ResultObject();
                resultObject.setNpid(rs.getInt(1));
                resultObject.setProvider_full_name(rs.getString(2));
                resultObject.setInsurance_plan(rs.getString(3));
                resultObject.setDistance(rs.getDouble(4));
                array.add(resultObject);
            }

        } catch (SQLException | java.io.IOException ex) {
            Logger lgr = Logger.getLogger(BaseMySQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(BaseMySQL.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return array;
    }

    private File locateClassPathResource(String resourcePath) {
        if (StringUtils.isEmpty(resourcePath)) {
            throw new RuntimeException("No resource path specified");
        }   

        URL resource = getClass().getClassLoader().getResource(resourcePath);
        if (resource == null) {
            throw new RuntimeException("Unable to locate " + resourcePath);
        }   

        File file;
        try {
            file = new File(resource.toURI());
        } catch (Exception ex) {
            throw new RuntimeException("Unable to create a file from resource " + resource.getPath());
        }   

        return file;
    }    

}
