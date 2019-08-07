package com.matevitsky.db;


import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Connection pool realisation
 */
public final class ConnectorDB {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("database");
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_DRIVER = "db.driver";
    private static final String DB_POOLSIZE = "db.poolsize";
    private static final BasicDataSource DS = new BasicDataSource();
    private static final Logger LOGGER = Logger.getLogger(ConnectorDB.class);

    /**
     * Get connection from the pool
     *
     * @return Connection
     * @throws SQLException if something went wrong
     */
    public static Connection getConnection() throws SQLException {

        String url = RESOURCE_BUNDLE.getString(DB_URL);
        String user = RESOURCE_BUNDLE.getString(DB_USER);
        String password = RESOURCE_BUNDLE.getString(DB_PASSWORD);
        String driver = RESOURCE_BUNDLE.getString(DB_DRIVER);
        int poolSize = Integer.parseInt(RESOURCE_BUNDLE.getString(DB_POOLSIZE));
        DS.setDriverClassName(driver);
        DS.setUrl(url);
        DS.setUsername(user);
        DS.setPassword(password);
        DS.setMinIdle(5);
        DS.setMaxIdle(10);
        DS.setMaxOpenPreparedStatements(poolSize);

        return DS.getConnection();
    }
}