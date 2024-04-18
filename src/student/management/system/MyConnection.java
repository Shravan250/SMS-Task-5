
package student.management.system;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class MyConnection {
    
    private static String username = "root";
    private static String password = "Shravan@252005";
    
    private static String dataConn = "jdbc:mysql://localhost:3306/student_management";
    private static Connection con = null;
    
    public static Connection getConnection(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dataConn , username , password);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return con;
    }
    
    
}
