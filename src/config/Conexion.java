package config;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {

    String bd = "caducidades";
    String url = "jdbc:mysql://localhost:33065/";
    String user = "root";
    String password = "";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection con;

    public Connection getConnection() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + bd, user, password);
             System.out.println("Conexión segura a la base de datos " + bd);
        } catch (Exception e) {
    
             System.out.println("ERROR ");
            System.out.println(e);
       
        }

        return con;
    }

    public static void main(String[] args) //throws SQLException//
    {
        Conexion con = new Conexion();
        con.getConnection();
    }

}
