package org.dainst.redirector;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * TODO put duplicate code to library
 * @author Daniel de Oliveira
 */
public class PropertiesLoader {

    final static Logger logger = Logger.getLogger(PropertiesLoader.class);

    public static Properties loadConfiguration(String propertiesFilePath) {
        Properties props = new Properties();
        try (
                FileInputStream is =new FileInputStream(new File(propertiesFilePath)))
        {
            props.load(is);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }
        return props;
    }
}