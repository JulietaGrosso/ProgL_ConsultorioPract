package org.progl.interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface AdminConexiones {
    static Connection obtenerConexion(){
       String dbDriver= "com.mysql.cj.jdbc.Driver";
    
       String dbCadenaConexion=
        "jdbc:mysql://host.docker.internal:3306/consultorio?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
       String dbUsuario="root";
       String dbPass="root";

       Connection conn = null;

       try {
        Class.forName(dbDriver);
        conn = DriverManager.getConnection(dbCadenaConexion,dbUsuario,dbPass);

       } catch (ClassNotFoundException e) {
          System.out.println("No se encontró el driver de la base de datos");
          throw new RuntimeException(e);
       } catch(SQLException e){
        System.out.println("No se pudo conectar a la base de datos");
        throw new RuntimeException(e); 
       }
       System.out.println("Conexión exitosa a la base de datos");
       return conn;
      
    }


}
