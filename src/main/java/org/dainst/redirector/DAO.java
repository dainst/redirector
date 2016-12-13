package org.dainst.redirector;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * @author Daniel de Oliveira
 */
class DAO {

    private Connection conn;

    DAO(ConnProvider connProvider) {
        this.conn = connProvider.conn();
    }

    /**
     * @return {@code null} if not successful
     */
    String getEntityID(String tableName,String foreignKey) {

        return execQuery("SELECT ArachneEntityID FROM arachneentityidentification "+
                "WHERE ForeignKey='"+foreignKey+"'"+
                "AND TableName='"+tableName+"'");
    }

    /**
     * @return {@code null} if not successful
     */
    String getEntityID(String bookAlias) {

        return execQuery("SELECT ArachneEntityID FROM arachneentityidentification "+
                "WHERE ForeignKey=(SELECT PS_BuchID FROM buch WHERE alias='"+bookAlias+"')"+
                "AND TableName='buch'");
    }

    private String execQuery(String query) {
        try (ResultSet rs = conn.createStatement().executeQuery(query)) {
            if (rs.next())
                return rs.getString("ArachneEntityID");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
