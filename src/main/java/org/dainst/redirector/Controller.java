package org.dainst.redirector;

import spark.Response;

import java.util.Map;
import java.util.Properties;

import static spark.Spark.get;


/**
 * @author Daniel de Oliveira
 */
class Controller {

    Controller(
            DAO dao,
            String targetUrl,
            Properties redirectMappings,
            String contactInfo
            ) {

        get( "/drupal/", (req,res) -> {
            if (!req.queryParams("q").contains("node")) return with404(res,contactInfo);
            String nodeNumber = req.queryParams("q").split("node\\/")[1];
            if (redirectMappings.get(nodeNumber) == null) return with404(res,contactInfo);
            String redirectUrlPartial = (String) redirectMappings.get(nodeNumber);
            if (redirectUrlPartial == null) return with404(res,contactInfo);
            res.redirect(targetUrl+redirectUrlPartial, 301);
            return "ok";
        });

        get( "/drupal/node/:node_id", (req,res) -> {
            String redirectUrlPartial = (String) redirectMappings.get(req.params(":node_id"));
            if (redirectUrlPartial == null) return with404(res,contactInfo);
            res.redirect(targetUrl+redirectMappings.get(req.params(":node_id")), 301);
            return "ok";
        });

        get( "/item/*/:id_in_category", (req,res) -> {
            String category = req.splat()[0];
            String eid = dao.getEntityID(category,req.params(":id_in_category"));
            if (eid == null) return with404(res,contactInfo);
            res.redirect(targetUrl+"entity/"+eid, 301);
            return "ok";
        });

        get( "/books/:book_alias", (req,res) -> {
            String eid = dao.getEntityID(req.params(":book_alias"));
            if (eid == null) return with404(res,contactInfo);
            res.redirect(targetUrl+"entity/"+eid, 301);
            return "ok";
        });
    }

    private String with404(Response res, String contactInfo) {
        res.status(404);
        return ("<h1>The requested URL was not found on this server. "+
                "(Die angeforderte URL wurde auf diesem Server nicht gefunden.)</h1>"+
                "<p>Please contact {C} if the URL is a permalink. " +
                "(Bitte kontaktieren Sie {C}, wenn es sich bei der URL um einen Permalink handelt.)</p>")
                        .replace("{C}",contactInfo);
    }
}
