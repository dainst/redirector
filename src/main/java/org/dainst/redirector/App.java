package org.dainst.redirector;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Daniel de Oliveira
 */
public class App {

    private static String PROPS_PATH="config/config.properties";
    private static String REDIRECTS_PATH="config/redirects.csv";

    public static void main(String [] args) throws FileNotFoundException {

        Map<String,String> m = readRedirects(REDIRECTS_PATH);
        if (m==null) System.exit(1);

        String targetUrl;
        if ((targetUrl=properties(PROPS_PATH)
                .getProperty("targetUrl"))==null) {

            System.out.println("targetUrl not defined in config.properties.");
            System.exit(1);
        } else {
            new Controller(targetUrl,m);
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
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the props or exits with 1.
     * @param path
     * @return
     */
    private static Properties properties(String path) {
        Properties props= loadConfiguration(path);
        if (props==null) {
            System.out.println("Could not load properties from file: config.properties.");
            System.exit(1);
        }
        return props;
    }

    /**
     * @param propertiesFileRelativePath
     * @return null if an error occured
     */
    private static Properties loadConfiguration(String propertiesFileRelativePath) {
        Properties props = new Properties();
        try (
                FileInputStream is =new FileInputStream(new File(propertiesFileRelativePath)))
        {
            props.load(is);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return props;
    }
}
