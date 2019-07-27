import com.matevitsky.service.ClientServiceImpl;
import com.matevitsky.service.interfaces.ClientService;
import com.matevitsky.util.MD5Util;

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


       /* Client client = Client.newClientBuilder()
                .withFirstName("test1")
                .withLastName("test1")
                .withPassword("test1")
                .withEmail("test@test.test")
                .withCompanyName("Sony")

                .withInspectorId(1)
                .build();
        ClientServiceImpl clientService1 = new ClientServiceImpl();
        boolean b = clientService1.create(client);*/

        System.out.println(MD5Util.encryptPassword("test"));


        //TODO: create Header for client page

        //TODO: удалить все логгер дебаг

        //TODO: inspector logout
        //TODO: убрать button  колонку из таблиц
    }
}
