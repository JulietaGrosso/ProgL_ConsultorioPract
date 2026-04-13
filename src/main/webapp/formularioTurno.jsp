<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="headerForm.jsp" %>

<div class="container">
    <form>
      <label>Fecha:</label>
      <input type="date" required>

      <label>Hora:</label>
      <input type="time" required>

      <label>Nombre del paciente:</label>
      <input type="text" placeholder="Ej: Juan Pérez" required>

      <label>Nombre del doctor:</label>
      <input type="text" placeholder="Ej: Dra. Gómez" required>

      <input type="submit" value="Guardar turno">
    </form>

    <!-- Botón para volver -->
    <a href="index.html">
      <button>Volver</button>
    </a>
  </div>
  
  </script>
</body>
</html>