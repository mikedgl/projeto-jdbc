package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Db {

    private static Connection connection = null;

    //Obter conexão com o banco de dados
    public static Connection getConnection(){
        if(connection == null){
            try {
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                connection = DriverManager.getConnection(url, properties);
            } catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        return connection;
    }

    //Fechar conexão com o banco de dados
    public static void closeConnection(){
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    //Carregar propriedades de conexão do banco de dados
    private static Properties loadProperties(){
        try(FileInputStream fileInputStream = new FileInputStream("db.properties")){
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        } catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }

    //Fechar resultSet
    public static void closeResultSet(ResultSet resultSet){
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    //Fechar statement
    public static void closeStatement(Statement statement){
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
