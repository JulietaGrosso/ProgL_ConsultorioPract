package org.progl.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.progl.entities.Consultorio;
import org.progl.entities.Paciente;
import org.progl.entities.Turno;
import org.progl.interfaces.AdminConexiones;
import org.progl.interfaces.Dao;

public class TurnoImpl implements Dao<Turno,Integer>, AdminConexiones{

  private Connection conn = null;

   private static final String SQL_INSERT=
      "INSERT INTO turno (id, dia, hora, nro_paciente, nro_consultorio) " +
      "VALUES            (      ?,      ?,    ?,    ?,    ?);";


     private static  final String  SQL_GETALL= "SELECT * FROM turno ORDER BY id" ;


    @Override
    public List<Turno> getAll() {
      
      conn = AdminConexiones.obtenerConexion();

      String sql = "SELECT * FROM turno ORDER BY id";

       PreparedStatement pst = null;
       ResultSet rs = null;

        List<Turno> listaTurnos = new java.util.ArrayList<>();

        try {
        // paso 3 crear instruccion
        pst = conn.prepareStatement(SQL_GETALL);
        // paso 4 ejecutar consulta y guarda el resultado en resultset
        rs = pst.executeQuery();

        // paso 5 recorrer el resultset y guardar las imágenes en una lista

          while (rs.next()){
            Turno turno = new Turno();
           
            turno.setDia(rs.getDate("dia"));
            turno.setHora(rs.getTime("hora").toLocalTime());
            turno.setPaciente(rs.getInt("nro_paciente"));
            turno.setConsultorio(rs.getInt("nro_consultorio"));
            listaTurnos.add(turno);
          }
        

      // paso 6 cerrar el resultset y statement
          rs.close();
          pst.close();
          conn.close();
          
      } catch (SQLException e) {
          System.out.println("Error al crear el statement");
          throw new RuntimeException(e);
          }

      return listaTurnos;  

    }



  
}
