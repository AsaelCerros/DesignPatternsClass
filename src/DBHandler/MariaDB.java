package DBHandler;
import java.sql.*;

public class MariaDB {
    private static MariaDB db;
    private String url;
    private String username;
    private String password;
    private String database;
    private Connection connection;

    public MariaDB(String url, String username, String password, String database) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            this.connection = DriverManager.getConnection(String.format(url+"/%s", database), username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static MariaDB connect(String url, String username, String password, String database) {
        if (db == null) {
            db = new MariaDB(url, username, password, database);
        }
        return db;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public ResultSet getTableData(String table) {
        ResultSet result = null;
        try {
            Statement s = this.connection.createStatement();
            result = s.executeQuery(String.format("SELECT * FROM %s", table));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean createCompany(String company_name) {
        try {
            Statement s = this.connection.createStatement();
            s.executeQuery(String.format("INSERT INTO company (company_name) VALUES ('%s')", company_name));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteCompany(String company_name) {
        try {
            Statement s = this.connection.createStatement();
            s.executeQuery(String.format("DELETE FROM company WHERE company_name = '%s'", company_name));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
