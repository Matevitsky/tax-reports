import com.matevitsky.entity.Client;
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

        Client client = Client.newBuilder()
                .withFirstName("Sergey")
                .withLastName("test")
                .withId(2)
                .withPassword("test")
                .withCompanyName("test")
                .withEmail("test@test.test")
                .build();
        clientService.update(client);

        //TODO: create Header for client page
        //TODO: удалить все логгер дебаг
    }
}
