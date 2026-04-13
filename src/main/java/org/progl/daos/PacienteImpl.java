package org.progl.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.progl.entities.Paciente;
import org.progl.interfaces.AdminConexiones;
import org.progl.interfaces.Dao;

import com.mysql.cj.xdevapi.Statement;

public class PacienteImpl implements Dao<Paciente, Integer>, AdminConexiones {

  private Connection conn = null;


    private static final String SQL_INSERT=
      "INSERT INTO paciente (nro_paciente, nombre, telefono) " +
      "VALUES            (      ?,      ?,    ?)";

    private static  final String  SQL_GETALL= "SELECT * FROM paciente ORDER BY nro_paciente" ;
    private static final String   SQL_GETBYID = "SELECT * FROM paciente WHERE nro_paciente = ?";
    
    private static final String SQL_DELETE = "DELETE * FROM paciente WHERE nro_paciente = ?";
    private static  final String  SQL_UPDATE= "UPDATE paciente SET " +
        "nombre = ?, telefono = ? " +
      "  WHERE nro_paciente = ? " ;
   

    
    @Override
    public List<Paciente> getAll() {
      
      conn = AdminConexiones.obtenerConexion();

      String sql = "SELECT * FROM paciente ORDER BY nro_paciente";

       PreparedStatement pst = null;
       ResultSet rs = null;

        List<Paciente> listaPacientes = new java.util.ArrayList<>();

        try {
        // paso 3 crear instruccion
        pst = conn.prepareStatement(SQL_GETALL);
        // paso 4 ejecutar consulta y guarda el resultado en resultset
        rs = pst.executeQuery();

        // paso 5 recorrer el resultset y guardar en una lista

          while (rs.next()){
            Paciente paciente = new Paciente();
           
            paciente.setNroPaciente(rs.getInt("nro_paciente"));
            paciente.setNombre(rs.getString("nombre"));
            paciente.setTelefono(rs.getInt("telefono"));
            listaPacientes.add(paciente);
          }
        

      // paso 6 cerrar el resultset y statement
          rs.close();
          pst.close();
          conn.close();
           
        } catch (SQLException e) {
          System.out.println("Error al crear el statement");
          throw new RuntimeException(e);
          }
        

          return listaPacientes;  

        }


    @Override
    public void insert(Paciente paciente) throws SQLException{
      try(Connection conn = AdminConexiones.obtenerConexion(); //se puede crear la exception: try catch con recursos
      PreparedStatement pst = conn.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)){
        
        if(existsById(paciente.getNroPaciente())){
          System.out.println("El Paciente ya existe");
          return;
        }

        pst.setString(1, paciente.getNombre());
        pst.setInt(2, paciente.getTelefono());
        
        int resultado = pst.executeUpdate();

        if(resultado == 1){
          System.out.println("Paciente agregado correctamente");
        }

        try (ResultSet rs = pst.getGeneratedKeys()){
          if(rs.next()){
            paciente.setNroPaciente(rs.getInt(1));
            System.out.println("ID asignado" + paciente.getNroPaciente());
          }
        } catch (SQLException e) {
          System.out.println("Error al insertar paciente" + e.getMessage());
        }
      }
    }


    @Override
    public Paciente getById(Integer nroPaciente){
        conn = AdminConexiones.obtenerConexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
     
        Paciente paciente = null;

        try {
          pst = conn.prepareStatement(SQL_GETBYID);
          pst.setInt(1, nroPaciente);
          rs = pst.executeQuery();
          if(rs.next()){
            paciente = new Paciente();
            paciente.setNroPaciente(rs.getInt("nro_paciente"));
            paciente.setNombre(rs.getString("nombre"));
            paciente.setTelefono(rs.getInt("telefono"));
          
          }

          rs.close();
          pst.close();
          conn.close();
        } catch(SQLException e){
          throw new RuntimeException(e);
        }
        return paciente;

    }


    @Override
    public boolean existsById(Integer nroPaciente){
      conn = AdminConexiones.obtenerConexion();
      PreparedStatement pst = null;
      ResultSet rs = null;
      boolean existe = false;

      try{
          pst = conn.prepareStatement(SQL_GETBYID);
          pst.setInt(1, nroPaciente);
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
    
/* 
    @Override
    public void delete(Integer nroPaciente){
        Connection conn = AdminConexiones.obtenerConexion();

        try{
          PreparedStatement pst = conn.prepareStatement(SQL_DELETE);
          pst.setInt(1, nroPaciente);
          int resultado = pst.executeUpdate();
          if(resultado == 1){
              System.out.println("Paciente eliminado correctamente");
          } else {
            System.out.println("No se pudo eliminar el paciente");
          }
         
          pst.close();
          conn.close();
        } catch(SQLException e){
          System.out.println("No se pudo eliminar el paciente. Error " + e.getMessage());
        }
        
    }*/


    @Override
    public void update(Paciente objeto){

      Connection conn = AdminConexiones.obtenerConexion();
      Paciente paciente = objeto;

      if(this.existsById(paciente.getNroPaciente())){

        conn = AdminConexiones.obtenerConexion();
        PreparedStatement pst = null;

         try{
            pst = conn.prepareStatement(SQL_UPDATE);
            pst.setString(1, paciente.getNombre());
            pst.setInt(2, paciente.getTelefono());
          

            int resultado = pst.executeUpdate();

            if(resultado == 1){
              System.out.println("Los datos del paciente se actualizaron correctamente");
            }else{
              System.out.println("No se pudo actualizar los datos del paciente");
            }

            pst.close(); 
            conn.close();


          }catch (SQLException e){
              System.out.println("Error al crear el Statement");
          }

      }

    }


    @Override
    public void deleteByFecha(Date dia) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'deleteByFecha'");
    }











}
