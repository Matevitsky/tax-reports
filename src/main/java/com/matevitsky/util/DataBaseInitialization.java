package com.matevitsky.util;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.db.DbInitScriptRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;


/**
 * Run main for creating all necessary tables in the database
 */
public final class DataBaseInitialization {
    private DataBaseInitialization() {
    }

    public static final void main(String[] args) throws IOException, SQLException {
        File initialFile = new File("../Time-tracking/src/main/resources/initDB.sql");
        InputStream targetStream = new FileInputStream(initialFile);
        DbInitScriptRunner.executeScript(ConnectorDB.getConnection(), targetStream);
    }
}

