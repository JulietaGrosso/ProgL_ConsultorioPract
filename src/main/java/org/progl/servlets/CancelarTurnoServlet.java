package org.progl.servlets;

import java.io.IOException;
import java.sql.Date;

import org.progl.daos.TurnoImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cancelar")
public class CancelarTurnoServlet extends AgregarTurnoServlet {

  @Override
  public void doDelete(HttpServletRequest req, HttpServletResponse res){

    String id = req.getParameter("id");
    Date dia = Date.valueOf(req.getParameter("dia"));


    TurnoImpl turnoImpl = new TurnoImpl();
    turnoImpl.delete(Integer.parseInt(id));

    res.setStatus(200);
    
  }


}
