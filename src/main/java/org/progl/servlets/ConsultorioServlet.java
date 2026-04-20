package org.progl.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.progl.daos.TurnoImpl;
import org.progl.entities.Turno;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/consultorio")
public class ConsultorioServlet extends HttpServlet{

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    TurnoImpl turnoImpl = new TurnoImpl();
    List<Turno> turnos =turnoImpl.getAll();
    request.setAttribute("turnos", turnos);
    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
    rd.forward(request, response);

   
    
  }



}
