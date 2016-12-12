package org.dainst.redirector;

import org.apache.log4j.Logger;

import java.util.Map;

import static spark.Spark.*;


/**
 * @author Daniel de Oliveira
 */
public class Controller {

    private final static Logger logger = Logger.getLogger(Controller.class);
    public static final String ID = ":id";

    public Controller(
            String targetUrl,
            Map<String,String> m
            ) {

        for (String key : m.keySet()) {
            get( "/"+key, (req,res) -> {
                res.redirect(targetUrl+m.get(key), 301);
                return "hallo";
            });
        }
    }
}
