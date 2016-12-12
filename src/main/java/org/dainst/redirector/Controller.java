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
            Connection conn,
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

        get( "/item/", (req,res) -> {
            query(conn);
            return "ok";
        });
    }


    private static void query(Connection conn) {
        System.out.println("do the query");

        String query = "SELECT * from verwaltung_benutzer limit 50";

        Statement st = null;
        try {
            st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                String s = rs.getString("username");
                System.out.println(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
