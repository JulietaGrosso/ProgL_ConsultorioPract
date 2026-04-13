<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>


<div class="container">
    <button onclick="document.getElementById('listaTurnos').style.display='block'">
      Ver lista de turnos
    </button>
    <a href="formulario.html">
  <button type="button">Agregar turno</button>
</a>


    <!-- Lista de turnos -->
    <div id="listaTurnos" class="turnos" style="display:none;">
      
      <c:forEach var="turno" items="${listaTurnos}"
      <div class="turno">
          <div>
            <div class="dia">${turno.dia}</div>
            <div class="detalle">Hora: ${turno.hora}</div>
            <div class="detalle">Paciente: ${turno.paciente}</div>
            <div class="detalle">Medico: ${turno.consultorio}</div>

          </div>
            
          <div class="acciones-globales">
            <label>Eliminar turnos por fecha:</label>
              <input type="date" id="fechaEliminar" required>
                 <button type="button" onclick="eliminarPorFecha()">Eliminar</button>
          </div>
    </div>
        
    </div>
  </div>

 


  <script>
        async function eliminarPorFecha() {
            const fecha = document.getElementById("fechaEliminar").value;
            if (!fecha) return;

            const response = await fetch("TurnoServlet?dia=" + fecha, {
                method: "DELETE"
            });

            if (response.ok) {
                alert("Turnos eliminados correctamente");
                window.location.href = "index.jsp"; // redirige al index
            } else {
                alert("Error al eliminar los turnos");
            }
        }
  </script>
  

</body>
</html>