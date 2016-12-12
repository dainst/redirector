package org.dainst.redirector;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Daniel de Oliveira
 */
public class App {

    private static String PROPS_PATH="config/config.properties";
    private static String REDIRECTS_PATH="config/redirects.csv";
    private static String JDBC_DRIVER="com.mysql.jdbc.Driver";

    private static Map<String,String> props = null;

    public static void main(String [] args) throws FileNotFoundException {

        props = loadConf(PROPS_PATH,"=");
        new Controller(
                new DAO(getConnection(get("dbJdbcUrl"),get("username"),get("password")))
                ,get("targetUrl"),
                loadConf(REDIRECTS_PATH,","));
    }

    private static Connection getConnection(String dbJdbcUrl,String username,String password) {
        Connection conn=null;
        try
        {
            Class.forName(JDBC_DRIVER).newInstance();
            conn = DriverManager.getConnection(dbJdbcUrl,
                    username, password);
            return conn;
//            conn.close(); // TODO implement proper tear down
        }
        catch (Exception e) {
            if (conn!=null) try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.err.println("Error: "+e.getMessage());
            System.exit(1);
            return null; // dead code
        }
    }

    private static String get(String propertyName)  {

        String s = props.get(propertyName);
        if (s==null) {
            System.err.println(propertyName+" not defined in "+PROPS_PATH+".");
            System.exit(1);
        }
        return s;
    }

    private static Map<String,String> loadConf(String path,String sep) {
        try {
            return ConfReader.read(path,sep);
        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
            System.exit(1);
            return null; // dead code
        }
    }
}
