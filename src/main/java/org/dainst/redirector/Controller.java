package org.dainst.redirector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import static spark.Spark.*;


/**
 * @author Daniel de Oliveira
 */
class Controller {

    Controller(
            DAO dao,
            String targetUrl,
            Map<String,String> m
            ) {

        get( "/drupal/", (req,res) -> {
            if (req.queryParams("q").contains("node")) {
                String nodeNumber = req.queryParams("q").split("node\\/")[1];
                if (m.get(nodeNumber) == null) return null;
                res.redirect(targetUrl+m.get(nodeNumber), 301);
            }
            return "ok";
        });

        get( "/item/*/:id", (req,res) -> {
            String eid = dao.getEntityID(req.splat()[0],req.params(":id"));
            if (eid!=null)
                res.redirect(targetUrl+"entity/"+eid, 301);
            return "ok";
        });
    }
}
