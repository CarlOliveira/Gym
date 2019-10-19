package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public Connection getConexao(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/gym?useTimezone=true&serverTimezone=UTC", "root", "");
        } 
        catch (Exception e){
        	System.out.println("Erro ao conectar !");
            throw new RuntimeException(e);
        }
    }

}