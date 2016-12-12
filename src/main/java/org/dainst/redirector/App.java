package org.dainst.redirector;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Daniel de Oliveira
 */
public class App {

    final static Logger logger = Logger.getLogger(App.class);
    private static Controller controller = null;

    public static void main(String [] args) throws FileNotFoundException {

        FileReader in = new FileReader("config/mappings.csv");
        BufferedReader br = new BufferedReader(in);

        String l;
        try {
            while ((l = br.readLine()) != null) {
                System.out.println(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        controller = new Controller();
    }
}
