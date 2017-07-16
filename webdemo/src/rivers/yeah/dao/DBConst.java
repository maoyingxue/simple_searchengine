package rivers.yeah.dao;

public class DBConst 
{
    // mysql的驱动类，定义为常量CLASS_NAME
    public static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static final String CONNET_URL ="jdbc:sqlserver://localhost:1433;databaseName=library;IntegratedSecurity=True"; 
    // 数据库的用户名，定义为常量USER_NAME
    public static final String USERNAME = "sa";
    // 数据库的密码，定义为常量PASSWORD
    public static final String PASSWORD = "";
}