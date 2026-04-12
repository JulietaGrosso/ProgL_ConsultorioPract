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

import org.progl.daos.TurnoImpl;
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
      int paciente = Integer.parseInt(req.getParameter("paciente"));
      int consultorio = Integer.parseInt(req.getParameter("consultorio"));

      Turno turno = new Turno(0, dia, hora, paciente, consultorio);
      TurnoImpl turnoImpl = new TurnoImpl();
      try {
        turnoImpl.insert(turno);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      res.sendRedirect("index.jsp");
 
   }
  
  
 








}
