package org.progl.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface Dao <O,K> {
// O objeto K key clave primaria

  public List<O> getAll();

  public void insert(O objeto) throws SQLException;
  //public void update(O objeto);
  //public void delete(K id);
  public void getById(K id);
  public boolean  existsById(K id);
  
  
  

}
