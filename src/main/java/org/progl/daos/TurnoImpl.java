package org.progl.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;

import org.eclipse.tags.shaded.org.apache.xalan.xsltc.compiler.sym;
import org.progl.entities.Consultorio;
import org.progl.entities.Paciente;
import org.progl.entities.Turno;
import org.progl.interfaces.AdminConexiones;
import org.progl.interfaces.Dao;

public class TurnoImpl implements Dao<Turno,Integer>, AdminConexiones{

  private Connection conn = null;

   private static final String SQL_INSERT=
      "INSERT INTO turno (id, dia, hora, nro_paciente, nro_consultorio) " +
      "VALUES            (      ?,      ?,    ?,    ?,    ?)";


     private static  final String  SQL_GETALL= "SELECT * FROM turno ORDER BY id" ;
     private static final String SQL_GETBYID = "SELECT * FROM turno WHERE id = ?";
     private static final String SQL_DELETE = "DELETE * FROM turno WHERE id = ?";
     
    private static  final String  SQL_UPDATE= "UPDATE turno SET " +
        "dia = ?, hora = ?, nro_paciente = ?, nro_consultorio = ? " +
      "  WHERE id = ? " ;

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

        // paso 5 recorrer el resultset y guardar 

          while (rs.next()){
            Turno turno = new Turno();
           
            turno.setDia(rs.getDate("dia"));
            turno.setHora(rs.getTime("hora"));
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


    @Override
    public void insert(Turno turno) throws SQLException{
      try(Connection conn = AdminConexiones.obtenerConexion(); //se puede crear la exception: try catch con recursos
      PreparedStatement pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)){
        
        if(existsById(turno.getId())){
          System.out.println("El Turno ya existe");
          return;
        }

        pst.setString(1, turno.getDia().toString());
        pst.setString(2,turno.getHora().toString());
        
        
        int resultado = pst.executeUpdate();

        if(resultado == 1){
          System.out.println("Turno agregado correctamente");
        }

        try (ResultSet rs = pst.getGeneratedKeys()){
          if(rs.next()){
            turno.setId(rs.getInt(1));
            System.out.println("ID asignado" + turno.getId());
          }
        } catch (SQLException e) {
          System.out.println("Error al insertar turno" + e.getMessage());
        }
      }
    }


    @Override
    public Turno getById(Integer id){
        conn = AdminConexiones.obtenerConexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean existe = false;
        Turno turno = null;

        try {
          pst = conn.prepareStatement(SQL_GETBYID);
          pst.setInt(1, id);
          rs = pst.executeQuery();
          if(rs.next()){
            turno = new Turno();
            turno.setId(rs.getInt("id"));
            turno.setDia(rs.getDate("dia"));
            turno.setHora(rs.getTime("hora"));
            turno.setPaciente(rs.getInt("id_paciente"));
            turno.setConsultorio(rs.getInt("id_consultorio"));
          }

          rs.close();
          pst.close();
          conn.close();
        } catch(SQLException e){
          throw new RuntimeException(e);
        }
        return turno;

    }


    @Override
    public boolean existsById(Integer id){
      conn = AdminConexiones.obtenerConexion();
      PreparedStatement pst = null;
      ResultSet rs = null;
      boolean existe = false;

      try{
          pst = conn.prepareStatement(SQL_GETBYID);
          pst.setInt(1, id);
          rs = pst.executeQuery();

          if(rs.next()){
            existe = true;
          }
          rs.close();
          pst.close();
          conn.close();
      }catch(SQLException e){
        throw new RuntimeException(e);
      }

      return existe;
    }
    

    @Override
    public void delete(Integer id){
        Connection conn = AdminConexiones.obtenerConexion();

        try{
          PreparedStatement pst = conn.prepareStatement(SQL_DELETE);
          pst.setInt(1, id);
          int resultado = pst.executeUpdate();
          if(resultado == 1){
              System.out.println("Turno eliminado correctamente");
          } else {
            System.out.println("No se pudo eliminar el turno");
          }
          pst.close();
          conn.close();
        } catch(SQLException e){
          System.out.println("No se pudo eliminar el turno. Error " + e.getMessage());
        }
        
    }


    @Override
    public void update(Turno objeto){
      Connection conn = AdminConexiones.obtenerConexion();

      Turno turno = objeto;

      if(this.existsById(turno.getId())){
        conn = AdminConexiones.obtenerConexion();
        PreparedStatement pst = null;

         try{
        pst = conn.prepareStatement(SQL_UPDATE);
        pst.setDate(1, (Date) turno.getDia());
        pst.setTime(2, turno.getHora());
        
        int resultado = pst.executeUpdate();

        if(resultado == 1){
          System.out.println("El turno se actualizó correctamente");
        }else{
          System.out.println("No se pudo actualizar el turno");
        }

        pst.close(); 
        conn.close();


      }catch (SQLException e){
          System.out.println("Error al crear el Statement");
      }

      }

    }


  
}
