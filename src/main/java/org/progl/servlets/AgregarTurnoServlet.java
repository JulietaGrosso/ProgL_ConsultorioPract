package org.progl.servlets;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.progl.Exceptions.TurnosExceptions;
import org.progl.daos.ConsultorioImpl;
import org.progl.daos.PacienteImpl;
import org.progl.daos.TurnoImpl;
import org.progl.entities.Consultorio;
import org.progl.entities.Paciente;
import org.progl.entities.Turno;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/agregar")
public class AgregarTurnoServlet extends HttpServlet {


public void doget(HttpServletRequest req, HttpServletResponse res) throws ServerException, IOException {
  
  req.setAttribute("mensaje", "Turno agregado exitosamente");
  
  RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
  
}
   
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServerException, IOException {

      DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
      Date dia = null;
      try {
        dia = (Date) format.parse(req.getParameter("dia"));
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
        Time hora = Time.valueOf(req.getParameter("hora"));
        int pacienteId = Integer.parseInt(req.getParameter("paciente"));
        int consultorioId = Integer.parseInt(req.getParameter("consultorio"));

        PacienteImpl pacienteimpl = new PacienteImpl();
        ConsultorioImpl consultorioImpl = new ConsultorioImpl();
        Paciente paciente = pacienteimpl.getById(pacienteId);
        Consultorio consultorio = consultorioImpl.getById(consultorioId); 

         try {
      
        if (paciente == null) {
            throw new TurnosExceptions(TurnosExceptions.ERROR_CONSULTORIO_INEXISTENTE);
        }
        if (consultorio == null) {
            throw new TurnosExceptions(TurnosExceptions.ERROR_CONSULTORIO_INEXISTENTE);
        }

        Turno turno = new Turno(0, dia, hora, paciente, consultorio); 
       
        TurnoImpl turnoImpl = new TurnoImpl();

        if (turnoImpl.existsById(turno)) {
            throw new TurnosExceptions(TurnosExceptions.ERROR_TURNO_DUPLICADO);
        }

        turnoImpl.insert(turno);
        res.sendRedirect("index.jsp");

    } catch (TurnosExceptions e) {
        // Mostrar mensaje de error en una página
        req.setAttribute("error", e.getMessage());
        req.getRequestDispatcher("error.jsp").forward(req, res);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
        
  
    
    
  
 








}
