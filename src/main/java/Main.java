import com.matevitsky.db.ConnectorDB;
import com.matevitsky.db.DbInitScriptRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        File initialFile = new File("../taxreports/src/main/resources/initDB.sql");
        InputStream targetStream = new FileInputStream(initialFile);
        DbInitScriptRunner.executeScript(ConnectorDB.getConnection(), targetStream);
    }
}
