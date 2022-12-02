import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private static Connection con = null;

    static {
        String url = "jdbc:mysql://localhost:3306/petcare";
        String username = "root";
        String password ="PetCare.it326";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, username, password);
            if(con != null) {
                System.out.println("Connection successful!");
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public static Connection getConnection() {
        return con;
    }
    
}
