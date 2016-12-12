package org.dainst.redirector;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * @author Daniel de Oliveira
 */
public class DAO {

    private Connection conn;

    public DAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param tn
     * @param fk
     * @return {@code null} if not successful
     */
    public String getEntityID(String tn,String fk) {

        String query = "SELECT ArachneEntityID FROM arachneentityidentification "+
                "WHERE ForeignKey='"+fk+"'"+
                "AND TableName='"+tn+"'";

        try (ResultSet rs = conn.createStatement().executeQuery(query)) {
            if (rs.next())
                return rs.getString("ArachneEntityID");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
