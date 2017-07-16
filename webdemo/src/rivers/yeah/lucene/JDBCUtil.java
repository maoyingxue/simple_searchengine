package rivers.yeah.lucene;
import java.sql.Connection;      
import java.sql.DriverManager;       
import java.sql.SQLException;    
/**     
* JdbcUtil.java   
* @version 1.0   
* @createTime JDBC获取Connection工具类   
*/    
public class JDBCUtil {     
    private static Connection conn = null;     
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=library;IntegratedSecurity=True";     
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";     
    private static final String USER_NAME = "sa";     
    private static final String PASSWORD = "";  
      
    public static Connection getConnection() {     
        try {     
            Class.forName(JDBC_DRIVER);     
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);     
        } catch (ClassNotFoundException e) {     
            e.printStackTrace();     
        } catch (SQLException e) {     
            e.printStackTrace();     
        }     
        return conn;     
    }  
}    