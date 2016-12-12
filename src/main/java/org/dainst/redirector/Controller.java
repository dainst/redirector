package org.dainst.redirector;

import org.apache.log4j.Logger;

import static spark.Spark.*;


/**
 * @author Daniel de Oliveira
 */
public class Controller {

    private final static Logger logger = Logger.getLogger(Controller.class);
    public static final String ID = ":id";

    public Controller(
            ) {
        get( "/", (req,res) -> {
            res.redirect("http://arachne.dainst.org/project/grako", 301);
            return "hallo";
        });

    }
}
