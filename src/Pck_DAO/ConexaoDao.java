package Pck_DAO;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConexaoDao {
    public Connection obj_connection = null;
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/db_pedido";
    private final String LOGIN = "root";
    private final String SENHA = "admim";

    public Connection getConnection() {
        try{
            Class.forName(DRIVER);
            obj_connection = DriverManager.getConnection(URL, LOGIN, SENHA);
            System.out.println("Conectado com sucesso!");
            return obj_connection;
        } catch (ClassNotFoundException erro) {
            System.out.println("DRIVER NAO ENCONTRADO" + erro.toString());
            return obj_connection;
        } catch (SQLException erro) {
            System.out.println("Erro ao conectar com o banco" + erro.toString());
            return obj_connection;
        }
    }
}