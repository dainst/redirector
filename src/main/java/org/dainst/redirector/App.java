package org.dainst.redirector;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Daniel de Oliveira
 */
public class App {

    private static String PROPS_PATH="config/config.properties";
    private static String REDIRECTS_PATH="config/redirects.csv";
    private static String JDBC_DRIVER="com.mysql.jdbc.Driver";

    private static Properties props = null;

    public static void main(String [] args) throws FileNotFoundException {

        loadConfiguration(PROPS_PATH);
        Map<String,String> redirects = readRedirects(REDIRECTS_PATH);

        new Controller(
                new DAO(getConnection(get("dbJdbcUrl"),get("username"),get("password")))
                ,get("targetUrl"),
                redirects);
    }



    private static Connection getConnection(String dbJdbcUrl,String username,String password) {
        try
        {
            Class.forName(JDBC_DRIVER).newInstance();
            Connection conn = DriverManager.getConnection(dbJdbcUrl,
                    username, password);
            return conn;
//            conn.close(); // TODO implement proper tear down
        }
        catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
            System.exit(1);
            return null; // dead code
        }
    }




    private static Map<String,String> readRedirects(String redirectsPath) {

        Map<String,String> m = new HashMap<>();
        FileReader in;
        try {

            in = new FileReader(REDIRECTS_PATH);
            BufferedReader br = new BufferedReader(in);

            String l;
            while ((l = br.readLine()) != null) {
                m.put(l.split(",")[0],l.split(",")[1]);
            }
            in.close();
            return m;
        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
            System.exit(1);
            return null; // dead code
        }
    }


    private static String get(String propertyName)  {

        String s = props.getProperty(propertyName);
        if (s==null) {
            System.err.println(propertyName+" not defined in "+PROPS_PATH+".");
            System.exit(1);
        }
        return s;
    }


    private static void loadConfiguration(String propertiesFileRelativePath) {
        props = new Properties();
        try (
                FileInputStream is =new FileInputStream(new File(propertiesFileRelativePath)))
        {
            props.load(is);
        } catch (IOException e) {
            System.err.println("Could not load properties from file: "+propertiesFileRelativePath);
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
