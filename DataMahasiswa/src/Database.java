import java.sql.*;

public class Database {
    private Connection connection;
    private Statement statement;

    public Database(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_mahasiswa?&useSSL=false&allowPublicKeyRetrieval=true","root","");
            statement = connection.createStatement();
        }catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public ResultSet selectcekQuery(String sql){
        try {
            statement.executeQuery(sql);
            return statement.getResultSet();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public int insertUpdateDelateQuery(String sql){
        try {
            return statement.executeUpdate(sql);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public Statement getStatement(){
        return statement;
    }

}
