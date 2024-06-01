package ac.id.unindra.hikal_spk.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private Connection databaseConnect;

    public Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            System.out.println("Gagal koneksi" + ex);
        }
        String url = "jdbc:sqlite:app/src/main/resources/db/spk.db";
        try {
            databaseConnect = DriverManager.getConnection(url, "", "");
        } catch (Exception ex) {
            System.out.println("Gagal koneksi Database" + ex);
        }
        return databaseConnect;
    }

}
