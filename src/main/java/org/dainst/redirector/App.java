package org.dainst.redirector;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Properties;

import static spark.Spark.port;

/**
 * @author Daniel de Oliveira
 */
public class App {

    private static String PROPS_PATH="config/config.properties";
    private static String REDIRECTS_PATH="config/redirects.properties";


    private static Properties props = null;

    public static void main(String [] args) throws FileNotFoundException {

        props = loadConf(PROPS_PATH);

        ConnProvider connProvider = getConn(
                getProp("dbJdbcUrl"), getProp("username"), getProp("password"));
        prepareShutDown(connProvider);

        port(Integer.parseInt(getProp("serverPort")));
        new Controller(
                new DAO(connProvider)
                , getProp("targetUrl"),
                loadConf(REDIRECTS_PATH),
                getProp("contactInfo"));
    }

    private static void prepareShutDown(ConnProvider connProvider) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Shutting down redirector.");
                try {
                    connProvider.destroy();
                } catch (Exception e) {
                    System.err.println("Error: "+e.getMessage());
                }
            }
        });
    }

    private static ConnProvider getConn(String dbJdbcUrl,String username,String password) {
        try {
            return new ConnProvider(dbJdbcUrl,username,password);
        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
            System.exit(1);
            return null; // dead code
        }
    }

    private static String getProp(String propertyName)  {

        String s = (String) props.get(propertyName);
        if (s==null) {
            System.err.println(propertyName+" not defined in "+PROPS_PATH+".");
            System.exit(1);
        }
        return s;
    }

    private static Properties loadConf(String path) {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(path));
            return props;
        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
            System.exit(1);
            return null; // dead code
        }
    }
}
