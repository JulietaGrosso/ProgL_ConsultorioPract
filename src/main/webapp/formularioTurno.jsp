<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="headerForm.jsp" %>

<div class="container">
    <form action="AgregarTurnoServlet" method="post">
      <label>Fecha:</label>
      <input type="date" required>

      <label>Hora:</label>
      <input type="time" required>

      <label>Nombre del paciente:</label>
      <input type="text" required>

      <label>Nombre del doctor:</label>
      <input type="text" required>

      <input type="submit" value="Guardar turno">
    </form>

    <!-- Botón para volver -->
   <button type="button" onclick="window.location.href='index.jsp'" class="btn-volver">Volver</button>


  </div>
  
  </script>
</body>
</html>