import com.matevitsky.util.MD5Util;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        System.out.println(MD5Util.encryptPassword("admin"));

    }
}
