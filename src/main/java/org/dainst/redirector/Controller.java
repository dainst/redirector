package org.dainst.redirector;

import java.util.Map;

import static spark.Spark.get;


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

        get( "/item/*/:id_in_category", (req,res) -> {
            String category = req.splat()[0];

            String eid = dao.getEntityID(category,req.params(":id_in_category"));
            if (eid!=null)
                res.redirect(targetUrl+"entity/"+eid, 301);
            return "ok";
        });

        get( "/books/:book_alias", (req,res) -> {
            String eid = dao.getEntityID(req.params(":book_alias"));
            if (eid!=null)
                res.redirect(targetUrl+"entity/"+eid, 301);
            return "ok";
        });
    }
}
