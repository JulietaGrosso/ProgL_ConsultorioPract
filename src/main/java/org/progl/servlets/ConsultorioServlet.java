package org.progl.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.progl.daos.TurnoImpl;
import org.progl.entities.Turno;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/consultorio")
public class ConsultorioServlet extends HttpServlet{

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    TurnoImpl turnoImpl = new TurnoImpl();
    List<Turno> turnos =turnoImpl.getAll();
    response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<html><body>");
    for (Turno turno : turnos) {
      
      out.println("<h1>Nombre turno: " + turno.getPaciente() + "</h1>");
    }

   
   
  
    out.println("<h1>Bienvenido al Consultorio</h1>");
    out.println("</body></html>");
  }



}
