package org.dainst.redirector;
import java.util.Map;
import static spark.Spark.*;


/**
 * @author Daniel de Oliveira
 */
class Controller {

    Controller(
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
