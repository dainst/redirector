package org.dainst.redirector;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Daniel de Oliveira
 */
class ConnProvider {

    private static String JDBC_DRIVER="com.mysql.jdbc.Driver";

    private Connection conn=null;

    ConnProvider(String dbJdbcUrl, String username, String password) throws Exception {
        try
        {
            Class.forName(JDBC_DRIVER).newInstance();
            conn = DriverManager.getConnection(dbJdbcUrl,
                    username, password);
        }
        catch (Exception e) {
            destroy();
            throw e;
        }
    }

    Connection conn() {
        return conn;
    }

    void destroy() throws Exception {
        if (conn!=null) conn.close();
    }
}
