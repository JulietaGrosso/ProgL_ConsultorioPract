package org.progl.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import org.progl.daos.TurnoImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cancelar")
public class CancelarTurnoServlet extends AgregarTurnoServlet {

  @Override
  public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException{

    String fechaStr = req.getParameter("dia");
    if (fechaStr == null || fechaStr.isEmpty()) {
        res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Debe indicar una fecha");
        return;
    }

    java.sql.Date fecha = java.sql.Date.valueOf(fechaStr);

    TurnoImpl turnoImpl = new TurnoImpl();
    try {
        turnoImpl.deleteByFecha(fecha); // nuevo método en tu DAO
        res.setStatus(HttpServletResponse.SC_OK);
    } catch (SQLException e) {
        e.printStackTrace();
        res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar turnos");
    }
    
  }


}
