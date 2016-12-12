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


        get( "/drupal/", (req,res) -> {

            if (req.queryParams("q").contains("node")) {
                String nodeNumber = req.queryParams("q").split("node\\/")[1];
                res.redirect(targetUrl+m.get(nodeNumber), 301);
            }

            return "ok";
        });
    }
}
