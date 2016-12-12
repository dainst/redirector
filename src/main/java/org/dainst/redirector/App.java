package org.dainst.redirector;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Daniel de Oliveira
 */
public class App {

    final static Logger logger = Logger.getLogger(App.class);
    private static Controller controller = null;

    private static Map<String,String> m = new HashMap<String,String>();

    public static void main(String [] args) throws FileNotFoundException {



        FileReader in = new FileReader("config/mappings.csv");
        BufferedReader br = new BufferedReader(in);

        String l;
        try {
            while ((l = br.readLine()) != null) {

                m.put(l.split(",")[0],l.split(",")[1]);
//                System.out.println(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Properties props = properties("config/config.properties");

        controller = new Controller(props.getProperty("targetUrl"),m);
    }

    private static Properties properties(String path) {
        Properties props= PropertiesLoader.loadConfiguration(path);
        if (props==null) {
            logger.error("Could not load properties from file: config.properties.");
            System.exit(1);
        }
        return props;
    }
}
