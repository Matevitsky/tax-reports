package com.matevitsky.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * This class  for running Data base initialisation script
 */
public final class DbInitScriptRunner {

    public final static void executeScript(Connection conn, InputStream in)
            throws SQLException {
        Scanner s = new Scanner(in);
        s.useDelimiter("/\\*[\\s\\S]*?\\*/|--[^\\r\\n]*|;");

        try (Statement st = conn.createStatement()) {

            while (s.hasNext()) {
                String line = s.next().trim();

                if (!line.isEmpty()) {
                    st.execute(line);
                }
            }
        }
    }
}
