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
      <div class="turno" data-fecha="12/04/2026">
        <div>
          <div class="fecha">12/04/2026 - 10:00</div>
          <div class="detalle">Paciente: Juan Pérez | Doctor: Dra. Gómez</div>
        </div>
        <div class="acciones">
         
          <button onclick="cancelarDia('12/04/2026')">Cancelar</button>
        </div>
      </div>
     
          <div class="acciones-globales">
            <label>Eliminar turnos por fecha:</label>
            <input type="date" id="fechaEliminar">
            <button onclick="eliminarPorFecha()">Eliminar</button>
          </div>
    </div>
  </div>

 


<script>
  function eliminarPorFecha() {
    const fechaInput = document.getElementById('fechaEliminar').value;
    if (!fechaInput) return;

    // Convertir formato yyyy-mm-dd a dd/mm/yyyy
    const partes = fechaInput.split("-");
    const fechaFormateada = partes[2] + "/" + partes[1] + "/" + partes[0];

    const turnos = document.querySelectorAll(`.turno[data-fecha='${fechaFormateada}']`);
    turnos.forEach(t => t.remove());
  }
</script>
  

</body>
</html>