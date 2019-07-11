import com.matevitsky.service.ClientService;
import com.matevitsky.service.ClientServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        File initialFile = new File("../taxreports/src/main/resources/initDB.sql");
        InputStream targetStream = new FileInputStream(initialFile);
        //  DbInitScriptRunner.executeScript(ConnectorDB.getConnection(), targetStream);

        ClientService clientService = new ClientServiceImpl();

        clientService.deleteById(1);

        //TODO: create Header for client page
        //TODO: удалить все логгер дебаг
    }
}
