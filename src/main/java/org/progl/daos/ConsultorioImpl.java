package org.progl.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.progl.entities.Consultorio;
import org.progl.interfaces.AdminConexiones;
import org.progl.interfaces.Dao;

public class ConsultorioImpl implements Dao<Consultorio, Integer>, AdminConexiones{

  private Connection conn = null;
  
   private static final String SQL_INSERT=
      "INSERT INTO consultorio (nro_consultorio, medico) " +
      "VALUES            (      ?,      ?)";

    private static  final String  SQL_GETALL= "SELECT * FROM consultorio ORDER BY nro_consultorio" ;
   
    private static final String SQL_DELETE = "DELETE * FROM consultorio WHERE nro_consultorio = ?";

    private static  final String  SQL_UPDATE= "UPDATE consultorio SET " +
        "medico = ? "+
      "  WHERE nro_consultorio = ? " ;

    @Override
    public List<Consultorio> getAll() {
       conn = AdminConexiones.obtenerConexion();

       String sql = "SELECT * FROM consultorio ORDER BY nro_consultorio";

       PreparedStatement pst = null;
       ResultSet rs = null;

        List<Consultorio> listaConsultorios = new java.util.ArrayList<>();

        try {
        // paso 3 crear instruccion
        pst = conn.prepareStatement(SQL_GETALL);
        // paso 4 ejecutar consulta y guarda el resultado en resultset
        rs = pst.executeQuery();

        // paso 5 recorrer el resultset y guardar en una lista

          while (rs.next()){
            Consultorio consultorio = new Consultorio();
           
            consultorio.setNroConsultorio(rs.getInt("nro_consultorio"));
            consultorio.setMedico(rs.getString("medico"));
            listaConsultorios.add(consultorio);
          }
        

      // paso 6 cerrar el resultset y statement
          rs.close();
          pst.close();
          conn.close();
           
        } catch (SQLException e) {
          System.out.println("Error al crear el statement");
          throw new RuntimeException(e);
          }
        

          return listaConsultorios; 

    }

    @Override
    public void insert(Consultorio consultorio) throws SQLException {
      try(Connection conn = AdminConexiones.obtenerConexion(); 
      PreparedStatement pst = conn.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)){
        
        if(existsById(consultorio.getNroConsultorio())){
          System.out.println("El Consultorio ya existe");
          return;
        }

        pst.setInt(1, consultorio.getNroConsultorio());
        pst.setString(2, consultorio.getMedico());

        int resultado = pst.executeUpdate();

        if(resultado == 1){
          System.out.println("Consultorio agregado correctamente");
        }

        try (ResultSet rs = pst.getGeneratedKeys()){
          if(rs.next()){
            consultorio.setNroConsultorio(rs.getInt(1));
            System.out.println("ID asignado" + consultorio.getNroConsultorio());
          }
        } catch (SQLException e) {
          System.out.println("Error al insertar consultorio" + e.getMessage());
        }
      }
    }

    @Override
    public void delete(Integer nroConsultorio) {
       Connection conn = AdminConexiones.obtenerConexion();

        try{
          PreparedStatement pst = conn.prepareStatement(SQL_DELETE);
          pst.setInt(1, nroConsultorio);
          int resultado = pst.executeUpdate();
          if(resultado == 1){
              System.out.println("Consultorio eliminado correctamente");
          } else {
            System.out.println("No se pudo eliminar el consultorio");
          }
         
          pst.close();
          conn.close();
        } catch(SQLException e){
          System.out.println("No se pudo eliminar el consultorio. Error " + e.getMessage());
        }
        
    }


    @Override
    public void update(Consultorio objeto) {
      Connection conn = AdminConexiones.obtenerConexion();
     Consultorio consultorio = objeto;

      if(this.existsById(consultorio.getNroConsultorio())){

        conn = AdminConexiones.obtenerConexion();
        PreparedStatement pst = null;

         try{
            pst = conn.prepareStatement(SQL_UPDATE);
            pst.setString(1, consultorio.getMedico());
            pst.setInt(2, consultorio.getNroConsultorio());
          

            int resultado = pst.executeUpdate();

            if(resultado == 1){
              System.out.println("Los datos del consultorio se actualizaron correctamente");
            }else{
              System.out.println("No se pudo actualizar los datos del consultorio");
            }

            pst.close(); 
            conn.close();


          }catch (SQLException e){
              System.out.println("Error al crear el Statement");
          }

      }
    }



    
    @Override
    public Consultorio getById(Integer id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public boolean existsById(Integer id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }










}
