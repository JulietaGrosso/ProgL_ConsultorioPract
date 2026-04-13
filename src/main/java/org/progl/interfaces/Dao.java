package org.progl.interfaces;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface Dao <O,K> {
// O objeto K key clave primaria

  public List<O> getAll();
  public void insert(O objeto) throws SQLException;
   void deleteByFecha(Date k)  throws SQLException;
  public O getById(K id);
  public boolean  existsById(K id);
   public void update(O objeto);
  
  

}
