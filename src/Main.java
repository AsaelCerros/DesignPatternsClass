import DBHandler.MariaDB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        MariaDB db = MariaDB.connect("jdbc:mysql://localhost:3306", "dba", "dbaPass99", "PatternsDB");

        boolean company1 = db.createCompany("AMAZON");
        boolean company2 = db.createCompany("Microsoft");
        if (company1) {
            System.out.print("Company added\n");
        } else {
            System.out.print("Company creation failed\n");
        }
        ResultSet resultEmp = db.getTableData("employees");
        try {
            while (resultEmp.next()) {
                System.out.printf("%s\t|%s\t|\n", resultEmp.getString("emp_id"), resultEmp.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet resultComp = db.getTableData("company");
        try {
            while (resultComp.next()) {
                System.out.printf("%s\t|%s\t|\n", resultComp.getString("company_id"), resultComp.getString("company_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean deleteCompany = db.deleteCompany("Microsoft");
        boolean deleteCompany2 = db.deleteCompany("AMAZON");
        if (deleteCompany) {
            System.out.print("Company deleted\n");
        } else {
            System.out.print("Company deletion failed\n");
        }

    }
}
